package com.jiazhe.youxiang.server.adapter;

import com.jiazhe.youxiang.server.dto.eleproductexcode.BatchNameDTO;
import com.jiazhe.youxiang.server.dto.eleproductexcode.EleProductExCodeDTO;
import com.jiazhe.youxiang.server.vo.resp.eleproductexcode.BatchNameResp;
import com.jiazhe.youxiang.server.vo.resp.eleproductexcode.EleProductExCodeResp;

/**
 * @author TU
 * @description
 * @date 2018/10/24.
 */
public class EleProductExCodeAdapter {
    public static BatchNameResp batchNameDTO2BatchNameResp(BatchNameDTO batchNameDTO) {
        if (batchNameDTO == null) {
            return null;
        }
        BatchNameResp batchNameResp = new BatchNameResp();
        batchNameResp.setName(batchNameDTO.getName());
        return batchNameResp;
    }

    public static EleProductExCodeResp DTO2Resp(EleProductExCodeDTO eleProductExCodeDTO) {
        if (eleProductExCodeDTO == null) {
            return null;
        }
        EleProductExCodeResp eleProductExCodeResp = new EleProductExCodeResp();
        eleProductExCodeResp.setId(eleProductExCodeDTO.getId());
        eleProductExCodeResp.setProductId(eleProductExCodeDTO.getProductId());
        eleProductExCodeResp.setBatchName(eleProductExCodeDTO.getBatchName());
        eleProductExCodeResp.setCode(eleProductExCodeDTO.getCode());
        eleProductExCodeResp.setKeyt(eleProductExCodeDTO.getKeyt());
        eleProductExCodeResp.setStatus(eleProductExCodeDTO.getStatus());
        eleProductExCodeResp.setExpiryTime(eleProductExCodeDTO.getExpiryTime());
        return eleProductExCodeResp;
    }
}
