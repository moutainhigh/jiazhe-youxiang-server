package com.jiazhe.youxiang.server.vo.resp.advancepay;

import com.jiazhe.youxiang.server.vo.BaseVO;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author TU
 * @description 预支记录
 * @date 2018/12/10.
 */
public class AdvancePayResp extends BaseVO {

    private Integer id;

    private BigDecimal advancePay;

    private Long advanceTime;

    private String remark;

    private String extInfo;

    private Byte isDeleted;

    private Long addTime;

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
