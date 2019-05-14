package com.jiazhe.youxiang.server.adapter.material;

import com.jiazhe.youxiang.server.domain.po.MaterialInfoPO;
import com.jiazhe.youxiang.server.dto.material.MaterialDto;
import com.jiazhe.youxiang.server.dto.material.MaterialSummaryDto;
import com.jiazhe.youxiang.server.vo.resp.material.MaterialResp;
import com.jiazhe.youxiang.server.vo.resp.material.MaterialSummaryResp;

/**
 * @author TU
 * @description
 * @date 2019-05-13.
 */
public class MaterialAdapter {


    public static MaterialSummaryResp summaryDto2Resp(MaterialSummaryDto materialSummaryDto) {
        if (materialSummaryDto == null) {
            return null;
        }
        MaterialSummaryResp materialSummaryResp = new MaterialSummaryResp();
        materialSummaryResp.setId(materialSummaryDto.getId());
        materialSummaryResp.setPayeeName(materialSummaryDto.getPayeeName());
        materialSummaryResp.setReceiveTotal(materialSummaryDto.getReceiveTotal());
        materialSummaryResp.setProductValueTotal(materialSummaryDto.getProductValueTotal());
        materialSummaryResp.setUsedProductValueTotal(materialSummaryDto.getUsedProductValueTotal());
        return materialSummaryResp;
    }

    public static MaterialResp dto2Resp(MaterialDto materialDto) {
        if (materialDto == null) {
            return null;
        }
        MaterialResp materialResp = new MaterialResp();
        materialResp.setId(materialDto.getId());
        materialResp.setTransferTime(materialDto.getTransferTime().getTime());
        materialResp.setTransferAmount(materialDto.getTransferAmount());
        materialResp.setMaterialValue(materialDto.getMaterialValue());
        materialResp.setPayerId(materialDto.getPayerId());
        materialResp.setPayerName(materialDto.getPayerName());
        materialResp.setPayeeId(materialDto.getPayeeId());
        materialResp.setPayeeName(materialDto.getPayeeName());
        materialResp.setRemark(materialDto.getRemark());
        return materialResp;
    }

    public static MaterialDto po2Dto(MaterialInfoPO materialInfoPO) {
        if (materialInfoPO == null) {
            return null;
        }
        MaterialDto materialDto = new MaterialDto();
        materialDto.setId(materialInfoPO.getId());
        materialDto.setTransferTime(materialInfoPO.getTransferTime());
        materialDto.setTransferAmount(materialInfoPO.getTransferAmount());
        materialDto.setMaterialValue(materialInfoPO.getMaterialValue());
        materialDto.setPayerId(materialInfoPO.getPayerId());
        materialDto.setPayerName(materialInfoPO.getPayerName());
        materialDto.setPayeeId(materialInfoPO.getPayeeId());
        materialDto.setPayeeName(materialInfoPO.getPayeeName());
        materialDto.setRemark(materialInfoPO.getRemark());
        return materialDto;
    }
}
