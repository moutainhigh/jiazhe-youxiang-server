package com.jiazhe.youxiang.server.vo.resp.eleproductexcode;

import com.jiazhe.youxiang.server.vo.BaseVO;

import java.util.Date;

/**
 * @author TU
 * @description
 * @date 2018/10/25.
 */
public class EleProductExCodeResp extends BaseVO {

    private static final long serialVersionUID = 945447596984890166L;

    private Integer id;

    private Integer productId;

    private String batchName;

    private String code;

    private String keyt;

    private Byte status;

    private Date expiryTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getKeyt() {
        return keyt;
    }

    public void setKeyt(String keyt) {
        this.keyt = keyt;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(Date expiryTime) {
        this.expiryTime = expiryTime;
    }
}
