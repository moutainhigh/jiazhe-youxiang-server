package com.jiazhe.youxiang.server.vo.resp.point.exchangecodebatch;

import com.jiazhe.youxiang.server.vo.BaseVO;
import com.jiazhe.youxiang.server.vo.resp.project.ProjectResp;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * @author TU
 * @description
 * @date 2018/12/13.
 */
public class PointExchangeCodeBatchResp extends BaseVO{

    private static final long serialVersionUID = -236258377524509836L;

    @ApiModelProperty("批次id")
    private Integer id;

    @ApiModelProperty("批次名称")
    private String name;

    @ApiModelProperty("兑换后充值卡名称")
    private String pointName;

    @ApiModelProperty("批次下兑换码数量")
    private Integer amount;

    @ApiModelProperty("面额")
    private BigDecimal faceValue;

    @ApiModelProperty("批次过期时间")
    private Long expiryTime;

    @ApiModelProperty("批次状态，0停用、1启用")
    private Byte status;

    @ApiModelProperty("是否是虚拟批次")
    private Byte isVirtual;

    @ApiModelProperty("批次下兑换码是否已经制作，0为未制作，1为已制作")
    private Byte isMade;

    @ApiModelProperty("项目id")
    private Integer projectId;

    private ProjectResp projectResp;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public BigDecimal getFaceValue() {
        return faceValue;
    }

    public void setFaceValue(BigDecimal faceValue) {
        this.faceValue = faceValue;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(Long expiryTime) {
        this.expiryTime = expiryTime;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Byte getIsVirtual() {
        return isVirtual;
    }

    public void setIsVirtual(Byte isVirtual) {
        this.isVirtual = isVirtual;
    }

    public Byte getIsMade() {
        return isMade;
    }

    public void setIsMade(Byte isMade) {
        this.isMade = isMade;
    }

    public String getPointName() {
        return pointName;
    }

    public void setPointName(String pointName) {
        this.pointName = pointName;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public ProjectResp getProjectResp() {
        return projectResp;
    }

    public void setProjectResp(ProjectResp projectResp) {
        this.projectResp = projectResp;
    }
}
