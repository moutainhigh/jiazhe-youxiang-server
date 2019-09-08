/*
 * Copyright (c) 2019 橙谊科技
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.vo.resp.boc;

import com.jiazhe.youxiang.server.vo.BaseVO;

/**
 * @author tu
 * @version 1.0
 * @description 中行储蓄卡（BOCDC）查询可用库存返回参数
 * @created 2019-09-08 11:23
 */
public class BOCDCQueryStockResp extends BaseVO {

    private String bizCode;

    private String bizDesc;

    private String giftCardNo;

    private String giftCardPwd;

    private String eviCode;

    private String cardExpDate;

    private String ebuyId;

    public String getBizCode() {
        return bizCode;
    }

    public void setBizCode(String bizCode) {
        this.bizCode = bizCode;
    }

    public String getBizDesc() {
        return bizDesc;
    }

    public void setBizDesc(String bizDesc) {
        this.bizDesc = bizDesc;
    }

    public String getGiftCardNo() {
        return giftCardNo;
    }

    public void setGiftCardNo(String giftCardNo) {
        this.giftCardNo = giftCardNo;
    }

    public String getGiftCardPwd() {
        return giftCardPwd;
    }

    public void setGiftCardPwd(String giftCardPwd) {
        this.giftCardPwd = giftCardPwd;
    }

    public String getEviCode() {
        return eviCode;
    }

    public void setEviCode(String eviCode) {
        this.eviCode = eviCode;
    }

    public String getCardExpDate() {
        return cardExpDate;
    }

    public void setCardExpDate(String cardExpDate) {
        this.cardExpDate = cardExpDate;
    }

    public String getEbuyId() {
        return ebuyId;
    }

    public void setEbuyId(String ebuyId) {
        this.ebuyId = ebuyId;
    }
}
