package com.jiazhe.youxiang.server.vo.resp.eleproductcode;

import com.jiazhe.youxiang.server.vo.BaseVO;
import com.jiazhe.youxiang.server.vo.resp.product.ProductResp;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @author TU
 * @description
 * @date 2018/10/25.
 */
public class EleProductCodeResp extends BaseVO {

    private static final long serialVersionUID = 945447596984890166L;

    @ApiModelProperty("电子码id")
    private Integer id;

    @ApiModelProperty("对应商品id")
    private Integer productId;

    private ProductResp productResp;

    @ApiModelProperty("批次名")
    private String batchName;

    @ApiModelProperty("电子码")
    private String code;

    @ApiModelProperty("电子密钥")
    private String keyt;

    @ApiModelProperty("是否已经兑换出去，0为未兑换，1为已兑换")
    private Byte status;

    @ApiModelProperty("过期时间，指在本系统过期时间，过期后不发放给客户")
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

    public ProductResp getProductResp() {
        return productResp;
    }

    public void setProductResp(ProductResp productResp) {
        this.productResp = productResp;
    }
}
