/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.service.impl.product;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jiazhe.youxiang.base.util.ListUtils;
import com.jiazhe.youxiang.server.adapter.ProductAdapter;
import com.jiazhe.youxiang.server.common.enums.ProductCodeEnum;
import com.jiazhe.youxiang.server.common.exceptions.ProductException;
import com.jiazhe.youxiang.server.dao.mapper.ProductPricePOMapper;
import com.jiazhe.youxiang.server.dao.mapper.manual.product.ProductPricePOManualMapper;
import com.jiazhe.youxiang.server.domain.po.ProductPricePO;
import com.jiazhe.youxiang.server.domain.po.ProductPricePOExample;
import com.jiazhe.youxiang.server.dto.product.ProductPriceDTO;
import com.jiazhe.youxiang.server.service.SysCityService;
import com.jiazhe.youxiang.server.service.product.ProductPriceService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 在这里编写类的功能描述
 *
 * @author niexiao
 * @created 2018/10/30
 */
@Service("productPriceService")
public class ProductPriceServiceImpl implements ProductPriceService {

    @Autowired
    private ProductPricePOMapper productPricePOMapper;

    @Autowired
    private ProductPricePOManualMapper productPricePOManualMapper;

    @Autowired
    private SysCityService sysCityService;

    @Override
    public List<Integer> getProductIdsByCityCodes(List<String> cityCodes) {
        return productPricePOManualMapper.getProductIdsByCityIds(cityCodes);
    }

    @Transactional
    @Override
    public void batchAddAndUpdatePrice(Integer productId, List<String> cityCodes, BigDecimal price) {
        ProductPricePOExample productPricePOExample = new ProductPricePOExample();
        ProductPricePOExample.Criteria criteria = productPricePOExample.createCriteria();
        criteria.andProductIdEqualTo(productId);
        criteria.andCityCodeIn(cityCodes);
        List<ProductPricePO> productPricePOList = productPricePOMapper.selectByExample(productPricePOExample);

        List<String> updateCityCodes = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(productPricePOList)) {
            productPricePOList.stream().forEach(item -> {
                if (cityCodes.contains(item.getCityCode())) {
                    cityCodes.remove(item.getCityCode());
                    updateCityCodes.add(item.getCityCode());
                }
            });
        }
        if (CollectionUtils.isNotEmpty(updateCityCodes)) {
            //更新价格
            productPricePOManualMapper.batchUpdate(productId, updateCityCodes, price);
        }
        if (CollectionUtils.isNotEmpty(cityCodes)) {
            productPricePOList.clear();
            ListUtils.removeDuplicate(cityCodes);
            Map<String, String> cityMap = sysCityService.getCityMapByCodes(cityCodes);
            cityCodes.stream().forEach(item -> {
                ProductPricePO productPricePO = new ProductPricePO();
                productPricePO.setCityCode(item);
                if (Strings.isNotBlank(cityMap.get(item))) {
                    productPricePO.setCityName(cityMap.get(item));
                } else {
                    throw new ProductException(ProductCodeEnum.PRODUCT_PRICE_CITY_NOT_FOUND);
                }
                productPricePO.setProductId(productId);
                productPricePO.setPrice(price);
                productPricePOList.add(productPricePO);
            });
            if (CollectionUtils.isNotEmpty(productPricePOList)) {
                productPricePOManualMapper.batchAddPrice(productPricePOList);
            } else {
                throw new ProductException(ProductCodeEnum.PRODUCT_PRICE_BATCH_ADD_ERROR);
            }
        }
    }

    @Override
    public ProductPriceDTO getPriceById(Integer id) {
        ProductPricePO productPricePO = productPricePOMapper.selectByPrimaryKey(id);
        return ProductAdapter.productPricePO2DTO(productPricePO);
    }

    @Override
    public ProductPriceDTO getPriceByCity(Integer productId, String cityCode) {
        ProductPricePOExample productPricePOExample = new ProductPricePOExample();
        ProductPricePOExample.Criteria criteria = productPricePOExample.createCriteria();
        criteria.andProductIdEqualTo(productId);
        criteria.andCityCodeEqualTo(cityCode);
        List<ProductPricePO> productPricePOList = productPricePOMapper.selectByExample(productPricePOExample);
        if (CollectionUtils.isNotEmpty(productPricePOList)) {
            if (productPricePOList.size() == 1) {
                return ProductAdapter.productPricePO2DTO(productPricePOList.get(0));
            } else {
                throw new ProductException(ProductCodeEnum.PRODUCT_PRICE_CONFLICT_ERROR);
            }
        }
        return null;
    }

    @Override
    public List<ProductPriceDTO> getListByProductId(Integer productId) {
        ProductPricePOExample productPricePOExample = new ProductPricePOExample();
        ProductPricePOExample.Criteria criteria = productPricePOExample.createCriteria();
        criteria.andProductIdEqualTo(productId);
        List<ProductPricePO> productPricePOList = productPricePOMapper.selectByExample(productPricePOExample);
        return productPricePOList.stream().map(ProductAdapter::productPricePO2DTO).collect(Collectors.toList());
    }

    @Override
    public void batchDeletePrice(List<Integer> ids) {
        productPricePOManualMapper.batchDelete(ids);
    }

    @Override
    public Map<Integer, List<ProductPriceDTO>> getPriceMap(List<Integer> productIds, List<String> cityCodes, Integer status) {
        Map<Integer, List<ProductPriceDTO>> result = Maps.newHashMap();
        if (CollectionUtils.isNotEmpty(productIds) && CollectionUtils.isNotEmpty(cityCodes)) {
            ProductPricePOExample productPricePOExample = new ProductPricePOExample();
            ProductPricePOExample.Criteria criteria = productPricePOExample.createCriteria();
            criteria.andProductIdIn(productIds);
            criteria.andCityCodeIn(cityCodes);
            if (status != null) {
                criteria.andStatusEqualTo(status.byteValue());
            }
            List<ProductPricePO> productPricePOList = productPricePOMapper.selectByExample(productPricePOExample);

            productPricePOList.stream().forEach(item -> {
                if (!result.keySet().contains(item.getProductId())) {
                    result.put(item.getProductId(), Lists.newArrayList());
                }
                result.get(item.getProductId()).add(ProductAdapter.productPricePO2DTO(item));
            });
        }
        return result;
    }
}