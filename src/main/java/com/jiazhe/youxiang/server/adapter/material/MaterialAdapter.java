package com.jiazhe.youxiang.server.adapter.material;

import com.jiazhe.youxiang.server.dto.material.MaterialSummaryDto;
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
}
