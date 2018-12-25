package com.jiazhe.youxiang.server.vo.resp.advancepay;

import com.jiazhe.youxiang.server.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author TU
 * @description 预支记录
 * @date 2018/12/10.
 */
public class AdvancePayResp extends BaseVO {

    @ApiModelProperty("预支记录id")
    private Integer id;

    @ApiModelProperty("预支金额")
    private BigDecimal advancePay;

    @ApiModelProperty("预支时间")
    private Long advanceTime;

    @ApiModelProperty("预支备注")
    private String remark;

    @ApiModelProperty("预留字段")
    private String extInfo;

    @ApiModelProperty("是否删除")
    private Byte isDeleted;

    @ApiModelProperty("添加时间")
    private Long addTime;

    @ApiModelProperty("修改时间")
    private Long modTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getAdvancePay() {
        return advancePay;
    }

    public void setAdvancePay(BigDecimal advancePay) {
        this.advancePay = advancePay;
    }

    public Long getAdvanceTime() {
        return advanceTime;
    }

    public void setAdvanceTime(Long advanceTime) {
        this.advanceTime = advanceTime;
    }

    public String getExtInfo() {
        return extInfo;
    }

    public void setExtInfo(String extInfo) {
        this.extInfo = extInfo;
    }

    public Byte getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Long getAddTime() {
        return addTime;
    }

    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }

    public Long getModTime() {
        return modTime;
    }

    public void setModTime(Long modTime) {
        this.modTime = modTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
