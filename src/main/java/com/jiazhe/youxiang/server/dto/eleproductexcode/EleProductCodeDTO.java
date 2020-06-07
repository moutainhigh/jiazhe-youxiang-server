package com.jiazhe.youxiang.server.dto.eleproductexcode;

import com.jiazhe.youxiang.server.dto.point.pointexchangecode.PointExchangeCodeDTO;
import com.jiazhe.youxiang.server.dto.product.ProductDTO;
import com.jiazhe.youxiang.server.vo.BaseObject;

import java.util.Date;
import java.util.Objects;

/**
 * @author TU
 * @description
 * @date 2018/10/25.
 */
public class EleProductCodeDTO extends BaseObject {

    private static final long serialVersionUID = 8953367776219805313L;

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

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        EleProductCodeDTO that = (EleProductCodeDTO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(batchName, that.batchName) &&
                Objects.equals(code, that.code) &&
                Objects.equals(keyt, that.keyt) &&
                Objects.equals(expiryTime, that.expiryTime) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, batchName, code, keyt, expiryTime, status);
    }
}
