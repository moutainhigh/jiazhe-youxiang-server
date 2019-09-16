/*
 * Copyright (c) 2017 ue-link.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.dto.point;

import com.jiazhe.youxiang.server.vo.BaseObject;

/**
 * 在这里编写类的功能描述
 *
 * @author niexiao
 * @created 2019/1/3
 */
public class PurchaseOrderDTO extends BaseObject {

    private static final long serialVersionUID = -3902191298303756569L;
    /**
     * 商户号
     */
    private String merchantNo;
    /**
     * 终端号
     */
    private String posNo;
    /**
     * 参考号
     */
    private String referNo;
    /**
     * 卡号
     */
    private String cardNo;
    /**
     * 扣减积分数
     */
    private String bonus;
    /**
     * 日期
     */
    private String date;
    /**
     * 时间
     */
    private String time;

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }

    public String getPosNo() {
        return posNo;
    }

    public void setPosNo(String posNo) {
        this.posNo = posNo;
    }

    public String getReferNo() {
        return referNo;
    }

    public void setReferNo(String referNo) {
        this.referNo = referNo;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getBonus() {
        return bonus;
    }

    public void setBonus(String bonus) {
        this.bonus = bonus;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}