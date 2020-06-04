package com.jiazhe.youxiang.server.dto.point.pointexchangecode;

import com.jiazhe.youxiang.server.dto.customer.CustomerDTO;
import com.jiazhe.youxiang.server.dto.project.ProjectDTO;
import com.jiazhe.youxiang.server.vo.BaseObject;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

/**
 * @author tu
 * @description：
 * @date 2018/12/13
 */
public class PointExchangeCodeDTO extends BaseObject {

    private static final long serialVersionUID = 4622825675083489757L;

    private Integer id;

    private Integer batchId;

    private String batchName;

    private String pointName;

    private String batchDescription;

    private Integer projectId;

    private ProjectDTO projectDTO;

    private String cityCodes;

    private String productIds;

    private String code;

    private String keyt;

    private Date pointEffectiveTime;

    private Date pointExpiryTime;

    private Integer validityPeriod;

    private Byte expiryType;

    private BigDecimal faceValue;

    private Date expiryTime;

    private Byte status;

    private Byte used;

    private String outOrderCode;

    private Integer customerId;

    private CustomerDTO customerDTO;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBatchId() {
        return batchId;
    }

    public void setBatchId(Integer batchId) {
        this.batchId = batchId;
    }

    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    public String getPointName() {
        return pointName;
    }

    public void setPointName(String pointName) {
        this.pointName = pointName;
    }

    public String getBatchDescription() {
        return batchDescription;
    }

    public void setBatchDescription(String batchDescription) {
        this.batchDescription = batchDescription;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public ProjectDTO getProjectDTO() {
        return projectDTO;
    }

    public void setProjectDTO(ProjectDTO projectDTO) {
        this.projectDTO = projectDTO;
    }

    public String getCityCodes() {
        return cityCodes;
    }

    public void setCityCodes(String cityCodes) {
        this.cityCodes = cityCodes;
    }

    public String getProductIds() {
        return productIds;
    }

    public void setProductIds(String productIds) {
        this.productIds = productIds;
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

    public Date getPointExpiryTime() {
        return pointExpiryTime;
    }

    public void setPointExpiryTime(Date pointExpiryTime) {
        this.pointExpiryTime = pointExpiryTime;
    }

    public Integer getValidityPeriod() {
        return validityPeriod;
    }

    public void setValidityPeriod(Integer validityPeriod) {
        this.validityPeriod = validityPeriod;
    }

    public Byte getExpiryType() {
        return expiryType;
    }

    public void setExpiryType(Byte expiryType) {
        this.expiryType = expiryType;
    }

    public BigDecimal getFaceValue() {
        return faceValue;
    }

    public void setFaceValue(BigDecimal faceValue) {
        this.faceValue = faceValue;
    }

    public Date getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(Date expiryTime) {
        this.expiryTime = expiryTime;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Byte getUsed() {
        return used;
    }

    public void setUsed(Byte used) {
        this.used = used;
    }

    public String getOutOrderCode() {
        return outOrderCode;
    }

    public void setOutOrderCode(String outOrderCode) {
        this.outOrderCode = outOrderCode;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public CustomerDTO getCustomerDTO() {
        return customerDTO;
    }

    public void setCustomerDTO(CustomerDTO customerDTO) {
        this.customerDTO = customerDTO;
    }

    public Date getPointEffectiveTime() {
        return pointEffectiveTime;
    }

    public void setPointEffectiveTime(Date pointEffectiveTime) {
        this.pointEffectiveTime = pointEffectiveTime;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PointExchangeCodeDTO that = (PointExchangeCodeDTO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(batchId, that.batchId) &&
                Objects.equals(batchName, that.batchName) &&
                Objects.equals(pointName, that.pointName) &&
                Objects.equals(batchDescription, that.batchDescription) &&
                Objects.equals(projectId, that.projectId) &&
                Objects.equals(projectDTO, that.projectDTO) &&
                Objects.equals(cityCodes, that.cityCodes) &&
                Objects.equals(productIds, that.productIds) &&
                Objects.equals(code, that.code) &&
                Objects.equals(keyt, that.keyt) &&
                Objects.equals(pointEffectiveTime, that.pointEffectiveTime) &&
                Objects.equals(pointExpiryTime, that.pointExpiryTime) &&
                Objects.equals(validityPeriod, that.validityPeriod) &&
                Objects.equals(expiryType, that.expiryType) &&
                Objects.equals(faceValue, that.faceValue) &&
                Objects.equals(expiryTime, that.expiryTime) &&
                Objects.equals(status, that.status) &&
                Objects.equals(used, that.used) &&
                Objects.equals(outOrderCode, that.outOrderCode) &&
                Objects.equals(customerId, that.customerId) &&
                Objects.equals(customerDTO, that.customerDTO);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, batchId, batchName, pointName, batchDescription, projectId, projectDTO, cityCodes, productIds, code, keyt, pointEffectiveTime, pointExpiryTime, validityPeriod, expiryType, faceValue, expiryTime, status, used, outOrderCode, customerId, customerDTO);
    }
}
