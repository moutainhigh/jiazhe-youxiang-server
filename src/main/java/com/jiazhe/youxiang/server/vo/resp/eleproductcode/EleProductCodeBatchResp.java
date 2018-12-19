package com.jiazhe.youxiang.server.vo.resp.eleproductcode;

import com.jiazhe.youxiang.server.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author TU
 * @description
 * @date 2018/11/27.
 */
public class EleProductCodeBatchResp extends BaseVO {

    @ApiModelProperty("批次名")
    private String batchName;

    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }
}
