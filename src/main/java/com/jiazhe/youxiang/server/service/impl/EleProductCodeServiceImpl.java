package com.jiazhe.youxiang.server.service.impl;

import com.jiazhe.youxiang.server.adapter.EleProductCodeAdapter;
import com.jiazhe.youxiang.server.dao.mapper.manual.EleProductCodePOManualMapper;
import com.jiazhe.youxiang.server.domain.po.ElectronicProductExchangeCodePO;
import com.jiazhe.youxiang.server.dto.eleproductexcode.EleProductCodeDTO;
import com.jiazhe.youxiang.server.service.EleProductCodeService;
import com.jiazhe.youxiang.server.service.product.ProductService;
import com.jiazhe.youxiang.server.vo.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author TU
 * @description
 * @date 2018/11/12.
 */
@Service("eleProductCodeService")
@Transactional(rollbackFor = Exception.class)
public class EleProductCodeServiceImpl implements EleProductCodeService {

    @Autowired
    private EleProductCodePOManualMapper eleProductCodePOManualMapper;
    @Autowired
    private ProductService productService;

    @Override
    public List<EleProductCodeDTO> getList(Integer productId, String batchName, Byte status, String code, String keyt, Paging paging) {
        Integer count = eleProductCodePOManualMapper.count(productId, batchName, status, code, keyt);
        List<ElectronicProductExchangeCodePO> pOList = eleProductCodePOManualMapper.query(productId, batchName, status, code, keyt, paging.getOffset(), paging.getLimit());
        paging.setTotal(count);
        List<EleProductCodeDTO> eleProductCodeDTOList = pOList.stream().map(EleProductCodeAdapter::PO2DTO).collect(Collectors.toList());
        eleProductCodeDTOList.stream().forEach(bean -> {
            bean.setProductDTO(productService.getById(bean.getProductId()));
        });
        return eleProductCodeDTOList;
    }

    @Override
    public List<EleProductCodeDTO> getAllBatch() {
        List<ElectronicProductExchangeCodePO> pOList = eleProductCodePOManualMapper.getAllBatch();
        return pOList.stream().map(EleProductCodeAdapter::PO2DTO).collect(Collectors.toList());
    }

    @Override
    public void batchInsert(List<ElectronicProductExchangeCodePO> poList) {
        eleProductCodePOManualMapper.batchInsert(poList);
    }
}
