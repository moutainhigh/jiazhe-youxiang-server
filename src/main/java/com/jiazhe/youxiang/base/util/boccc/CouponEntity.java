package com.jiazhe.youxiang.base.util.boccc;

/**
 * @author TU
 * @description   代金券实体类
 * @date 2019-09-03.
 */
public class CouponEntity {

    private String id;

    private String batchId;

    private String productId;

    private String keyt;

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

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getKeyt() {
        return keyt;
    }

    public void setKeyt(String keyt) {
        this.keyt = keyt;
    }
}
