package com.jiazhe.youxiang.server.dto.eleproductexcode;

import com.jiazhe.youxiang.server.dto.product.ProductDTO;
import com.jiazhe.youxiang.server.vo.BaseObject;

import java.util.Date;

/**
 * @author TU
 * @description
 * @date 2018/10/25.
 */
public class EleProductCodeDTO extends BaseObject {

    private Integer id;

    private Integer productId;

    private ProductDTO productDTO;

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

    public ProductDTO getProductDTO() {
        return productDTO;
    }

    public void setProductDTO(ProductDTO productDTO) {
        this.productDTO = productDTO;
    }
}
