/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.service.impl.product;

import com.google.common.collect.Lists;
import com.jiazhe.youxiang.server.adapter.ProductAdapter;
import com.jiazhe.youxiang.server.biz.EleProductCodeBiz;
import com.jiazhe.youxiang.server.biz.ProductBiz;
import com.jiazhe.youxiang.server.common.constant.CommonConstant;
import com.jiazhe.youxiang.server.common.enums.ProductCodeEnum;
import com.jiazhe.youxiang.server.common.exceptions.ProductException;
import com.jiazhe.youxiang.server.dao.mapper.ProductPOMapper;
import com.jiazhe.youxiang.server.dao.mapper.manual.EleProductCodePOManualMapper;
import com.jiazhe.youxiang.server.dao.mapper.manual.product.ProductPOManualMapper;
import com.jiazhe.youxiang.server.domain.po.ProductCategoryPO;
import com.jiazhe.youxiang.server.domain.po.ProductPO;
import com.jiazhe.youxiang.server.domain.po.ProductPOExample;
import com.jiazhe.youxiang.server.dto.product.ProductAddDTO;
import com.jiazhe.youxiang.server.dto.product.ProductCategoryDTO;
import com.jiazhe.youxiang.server.dto.product.ProductDTO;
import com.jiazhe.youxiang.server.dto.product.ProductPriceDTO;
import com.jiazhe.youxiang.server.dto.product.ProductUpdateDTO;
import com.jiazhe.youxiang.server.service.product.ProductCategoryService;
import com.jiazhe.youxiang.server.service.product.ProductPriceService;
import com.jiazhe.youxiang.server.service.product.ProductService;
import com.jiazhe.youxiang.server.vo.Paging;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 在这里编写类的功能描述
 *
 * @author niexiao
 * @created 2018/10/30
 */
@Service("productService")
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductPOMapper productPOMapper;

    @Autowired
    private ProductPOManualMapper productPOManualMapper;

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private ProductPriceService productPriceService;
    @Autowired
    private EleProductCodePOManualMapper eleProductCodePOManualMapper;


    @Override
    public void add(ProductAddDTO productAddDTO) {
        //验证商品分类是否存在
        ProductCategoryDTO productCategoryDTO = productCategoryService.getCategoryById(productAddDTO.getProductCategoryId());
        if (productCategoryDTO == null) {
            throw new ProductException(ProductCodeEnum.PRODUCT_CATEGORY_IS_NULL);
        }
        ProductPO productPO = ProductAdapter.productAddDTO2PO(productAddDTO);
        productPOMapper.insertSelective(productPO);
    }

    @Override
    public ProductDTO getById(Integer id) {
        ProductPO productPO = productPOMapper.selectByPrimaryKey(id);
        ProductCategoryDTO productCategory = productCategoryService.getCategoryById(productPO.getProductCategoryId());
        List<ProductPriceDTO> productPriceList = productPriceService.getAllPriceList(productPO.getId());
        ProductDTO productDTO = ProductAdapter.productPO2DTO(productPO);
        productDTO.setProductCategory(productCategory);
        productDTO.setProductPriceList(productPriceList);
        return productDTO;
    }

    @Override
    public List<ProductDTO> getList(Integer productCategoryId, String name, Integer productType, List<String> cityCodes, Integer status, Paging paging, boolean detail) {
        List<Integer> productIds = Lists.newArrayList();
        //如果有cityCodes传入则只返回在该城市有价格信息的商品
        if (CollectionUtils.isNotEmpty(cityCodes)) {
            // 获取城市列表中有价格的商品ID集合
            productIds = productPriceService.getProductIdsByCityCodes(cityCodes);
        }
        Integer count = productPOManualMapper.count(productCategoryId, name, productType, status, productIds);
        List<ProductPO> productPOList = productPOManualMapper.query(productCategoryId, name, productType, status, productIds, paging.getOffset(), paging.getLimit());
        paging.setTotal(count);
        List<ProductDTO> productDTOList = productPOList.stream().map(ProductAdapter::productPO2DTO).collect(Collectors.toList());
        //判断是否需要拼装详细信息
        if (detail) {
            ProductCategoryDTO productCategory = productCategoryService.getCategoryById(productCategoryId);
            //获取商品的价格map，不限制价格生效状态
            Map<Integer, List<ProductPriceDTO>> productPriceMap = productPriceService.getPriceMap(productIds, cityCodes, null);
            productDTOList.forEach(item -> {
                item.setProductCategory(productCategory);
                item.setProductPriceList(productPriceMap.get(item.getId()));
            });
        }
        return productDTOList;
    }

    @Override
    public List<ProductDTO> getListForCustomer(Integer productCategoryId, String name, Integer productType, String cityCode, Paging paging) {
        List<ProductDTO> result = Lists.newArrayList();
        ProductCategoryDTO productCategory = productCategoryService.getCategoryById(productCategoryId);
        //首先判断商品大类是否上架
        if (productCategory != null && productCategory.getStatus().equals(ProductBiz.CODE_PRODUCT_SELL)) {
            //先找到所有符合条件的商品ID
            List<ProductPO> allProductPOList = productPOManualMapper.query(productCategoryId, name, productType, ProductBiz.CODE_PRODUCT_SELL, null, null, null);
            List<ProductPO> canSellProductList = Lists.newArrayList();
            if (CollectionUtils.isNotEmpty(allProductPOList)) {
                allProductPOList.forEach(item -> {
                            boolean canSell = true;
                            //如果是商品类型电子卡且没有找到对应的电子卡兑换码，则需要从商品列表中删除
                            if (item.getProductType().equals(ProductBiz.CODE_TYPE_ELEPRODUCT)) {
                                //查找对应的电子卡兑换码数量,看是否有可兑换的电子卡兑换码
                                Integer eleProductCodeCode = eleProductCodePOManualMapper.count(item.getId(), null, EleProductCodeBiz.CODE_ELEPRODUCT_CODE_EXCHANGED, null, null);
                                canSell = eleProductCodeCode > 0;
                            }
                            if (canSell) {
                                canSellProductList.add(item);
                            }
                        }
                );
                //获得该城市所有的在售商品的价格列表
                List<Integer> productIds = canSellProductList.stream().map(item -> item.getId()).collect(Collectors.toList());
                Map<Integer, List<ProductPriceDTO>> productPriceMap = productPriceService.getPriceMap(productIds, Lists.newArrayList(cityCode), ProductBiz.CODE_PRODUCT_SELL);
                //total值是所有在售且该城市价格生效的商品
                paging.setTotal(productPriceMap.keySet().size());
                //再次查询商品信息列表，为了分页，后期有优化空间
                List<ProductPO> productPOList = productPOManualMapper.queryByIds(productPriceMap.keySet().stream().collect(Collectors.toList()), paging.getOffset(), paging.getLimit());
                if (MapUtils.isNotEmpty(productPriceMap)) {
                    result = productPOList.stream().map(ProductAdapter::productPO2DTO).collect(Collectors.toList());
                    result.forEach(item -> {
                        item.setProductCategory(productCategory);
                        item.setProductPriceList(productPriceMap.get(item.getId()));
                    });
                }
            }
        }
        return result;
    }

    @Override
    public void update(ProductUpdateDTO productUpdateDTO) {
        ProductPO productPO = ProductAdapter.productUpdateDTO2PO(productUpdateDTO);
        productPO.setModTime(new Date());
        productPOMapper.updateByPrimaryKeySelective(productPO);
    }

    @Override
    public void delete(Integer id) {
        ProductPO productPO = new ProductPO();
        productPO.setId(id);
        productPO.setModTime(new Date());
        productPO.setIsDeleted(CommonConstant.CODE_DELETED);
        productPOMapper.updateByPrimaryKeySelective(productPO);
    }

    @Override
    public void updateStatus(Integer id, Integer status) {
        ProductPO productPO = new ProductPO();
        productPO.setId(id);
        productPO.setModTime(new Date());
        productPO.setStatus(status.byteValue());
        productPOMapper.updateByPrimaryKeySelective(productPO);
    }

    @Override
    public List<ProductDTO> getAllList(Integer productType, Integer status) {
        List<ProductDTO> result = Lists.newArrayList();
        ProductPOExample productPOExample = new ProductPOExample();
        ProductPOExample.Criteria criteria = productPOExample.createCriteria();
        if (null != productType) {
            criteria.andProductTypeEqualTo(productType);
        }
        if (null != status) {
            criteria.andStatusEqualTo(status.byteValue());
        }
        criteria.andIsDeletedEqualTo(CommonConstant.CODE_NOT_DELETED);
        List<ProductPO> productPOList = productPOMapper.selectByExample(productPOExample);

        Map<Integer, ProductCategoryPO> categoryMap = productCategoryService.getCategoryMap();
        productPOList.forEach(item -> {
            ProductCategoryPO productCategoryPO = categoryMap.get(item.getProductCategoryId());
            //说明没有对应的大类，说明大类被删除了或者数据出错，总之没有对应大类的商品也是有问题的，不应显示出来
            if (null != productCategoryPO) {
                ProductDTO productDTO = ProductAdapter.productPO2DTO(item);
                productDTO.setProductCategory(ProductAdapter.productCategoryPO2DTO(productCategoryPO));
                result.add(productDTO);
            }

        });
        return result;
    }
}