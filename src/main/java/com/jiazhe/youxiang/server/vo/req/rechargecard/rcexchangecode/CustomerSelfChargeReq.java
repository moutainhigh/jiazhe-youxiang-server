package com.jiazhe.youxiang.server.vo.req.rechargecard.rcexchangecode;

import com.jiazhe.youxiang.server.vo.BaseVO;

/**
 * @author TU
 * @description
 * @date 2018/10/23.
 */
public class CustomerSelfChargeReq extends BaseVO {

    String keyt ;

    Integer customerId;

    public String getKeyt() {
        return keyt;
    }

    public void setKeyt(String keyt) {
        this.keyt = keyt;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
}
