package com.jiazhe.youxiang.base.util.boccc;

import java.util.Date;

/**
 * @author TU
 * @description  优惠券使用信息
 * @date 2019-09-12.
 */
public class BOCCCCouponUsedEntity {

    private String id;

    private String batchId;

    private String giftNo;

    private String keyt;

    private Date usedTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getGiftNo() {
        return giftNo;
    }

    public void setGiftNo(String giftNo) {
        this.giftNo = giftNo;
    }

    public String getKeyt() {
        return keyt;
    }

    public void setKeyt(String keyt) {
        this.keyt = keyt;
    }

    public Date getUsedTime() {
        return usedTime;
    }

    public void setUsedTime(Date usedTime) {
        this.usedTime = usedTime;
    }
}
