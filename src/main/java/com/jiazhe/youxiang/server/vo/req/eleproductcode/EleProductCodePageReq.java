package com.jiazhe.youxiang.server.vo.req.eleproductcode;

import com.jiazhe.youxiang.server.vo.req.PageSizeNumReq;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author TU
 * @description
 * @date 2018/10/25.
 */
public class EleProductCodePageReq extends PageSizeNumReq {

    private static final long serialVersionUID = -1761724421448178065L;

    @ApiModelProperty("商品id")
    private Integer productId ;

    @ApiModelProperty("批次名称")
    private String batchName ;

    @ApiModelProperty("状态：0-未兑换，1-已兑换")
    private Byte status;

    @ApiModelProperty("电子码")
    private String code;

    @ApiModelProperty("电子密钥")
    private String keyt;

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

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
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
}
