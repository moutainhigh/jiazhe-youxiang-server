package com.jiazhe.youxiang.server.vo.req.rechargecard.rcexchangecode;

import com.jiazhe.youxiang.server.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author TU
 * @description
 * @date 2018/10/23.
 */
public class CodeChargeReq extends BaseVO {

    @ApiModelProperty("密钥")
    String keyt ;

    @ApiModelProperty("客户id")
    Integer id;

    public String getKeyt() {
        return keyt;
    }

    public void setKeyt(String keyt) {
        this.keyt = keyt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
