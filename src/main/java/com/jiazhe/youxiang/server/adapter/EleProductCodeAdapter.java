package com.jiazhe.youxiang.server.adapter;

import com.jiazhe.youxiang.server.domain.po.ElectronicProductExchangeCodePO;
import com.jiazhe.youxiang.server.dto.eleproductexcode.BatchNameDTO;
import com.jiazhe.youxiang.server.dto.eleproductexcode.EleProductCodeDTO;
import com.jiazhe.youxiang.server.vo.resp.eleproductcode.BatchNameResp;
import com.jiazhe.youxiang.server.vo.resp.eleproductcode.EleProductCodeResp;

/**
 * @author TU
 * @description
 * @date 2018/10/24.
 */
public class EleProductCodeAdapter {
    public static BatchNameResp batchNameDTO2BatchNameResp(BatchNameDTO batchNameDTO) {
        if (batchNameDTO == null) {
            return null;
        }
        BatchNameResp batchNameResp = new BatchNameResp();
        batchNameResp.setName(batchNameDTO.getName());
        return batchNameResp;
    }

    public static EleProductCodeResp DTO2Resp(EleProductCodeDTO eleProductCodeDTO) {
        if (eleProductCodeDTO == null) {
            return null;
        }
        EleProductCodeResp eleProductCodeResp = new EleProductCodeResp();
        eleProductCodeResp.setId(eleProductCodeDTO.getId());
        eleProductCodeResp.setProductId(eleProductCodeDTO.getProductId());
        eleProductCodeResp.setProductResp(ProductAdapter.productDTO2VO(eleProductCodeDTO.getProductDTO()));
        eleProductCodeResp.setBatchName(eleProductCodeDTO.getBatchName());
        eleProductCodeResp.setCode(eleProductCodeDTO.getCode());
        eleProductCodeResp.setKeyt(eleProductCodeDTO.getKeyt());
        eleProductCodeResp.setStatus(eleProductCodeDTO.getStatus());
        eleProductCodeResp.setExpiryTime(eleProductCodeDTO.getExpiryTime());
        return eleProductCodeResp;
    }

    public static EleProductCodeDTO PO2DTO(ElectronicProductExchangeCodePO electronicProductExchangeCodePO) {
        if (electronicProductExchangeCodePO == null) {
            return null;
        }
        EleProductCodeDTO eleProductCodeDTO = new EleProductCodeDTO();
        eleProductCodeDTO.setId(electronicProductExchangeCodePO.getId());
        eleProductCodeDTO.setProductId(electronicProductExchangeCodePO.getProductId());
        eleProductCodeDTO.setBatchName(electronicProductExchangeCodePO.getBatchName());
        eleProductCodeDTO.setCode(electronicProductExchangeCodePO.getCode());
        eleProductCodeDTO.setKeyt(electronicProductExchangeCodePO.getKeyt());
        eleProductCodeDTO.setStatus(electronicProductExchangeCodePO.getStatus());
        eleProductCodeDTO.setExpiryTime(electronicProductExchangeCodePO.getExpiryTime());
        return eleProductCodeDTO;
    }

    public static ElectronicProductExchangeCodePO DTO2PO(EleProductCodeDTO eleProductCodeDTO) {
        if (eleProductCodeDTO == null) {
            return null;
        }
        ElectronicProductExchangeCodePO electronicProductExchangeCodePO = new ElectronicProductExchangeCodePO();
        electronicProductExchangeCodePO.setId(eleProductCodeDTO.getId());
        electronicProductExchangeCodePO.setProductId(eleProductCodeDTO.getProductId());
        electronicProductExchangeCodePO.setBatchName(eleProductCodeDTO.getBatchName());
        electronicProductExchangeCodePO.setCode(eleProductCodeDTO.getCode());
        electronicProductExchangeCodePO.setKeyt(eleProductCodeDTO.getKeyt());
        electronicProductExchangeCodePO.setStatus(eleProductCodeDTO.getStatus());
        electronicProductExchangeCodePO.setExpiryTime(eleProductCodeDTO.getExpiryTime());
        return electronicProductExchangeCodePO;
    }
}
