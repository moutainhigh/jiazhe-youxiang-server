package com.jiazhe.youxiang.server.vo.req.order.orderinfo;

import com.jiazhe.youxiang.server.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author tu
 * @description：客户下单请求参数
 * @date 2018/10/24
 */
public class PlaceOrderReq extends BaseVO {

    @ApiModelProperty("下单方式，前台下单0，后台下单1")
    private Byte type;

    @ApiModelProperty("客户id")
    private Integer customerId;

    @ApiModelProperty("商品id")
    private Integer productId;

    @ApiModelProperty("下单城市code")
    private String customerCityCode;

    @ApiModelProperty("下单数量")
    private Integer count;

    @ApiModelProperty("订单地址")
    private String customerAddress;

    @ApiModelProperty("订单联系电话")
    private String customerMobile;

    @ApiModelProperty("订单接收人")
    private String customerName;

    @ApiModelProperty("客户留言")
    private String customerRemark;

    @ApiModelProperty("提供服务人员")
    private String workerName;

    @ApiModelProperty("服务人员电话")
    private String workerMobile;

    @ApiModelProperty("服务时间")
    private Date serviceTime;

    /**
     * 使用的代金券ids
     */
    @ApiModelProperty("代金券ids")
    private String voucherIds;

    /**
     * 使用的充值卡ids
     */
    @ApiModelProperty("充值卡ids")
    private String rechargeCardIds;

    /**
     * 每张充值卡使用的金额
     */
    @ApiModelProperty("充值卡金额")
    private String cardMoneys;

    @ApiModelProperty("订单成本")
    private BigDecimal totalCost;

    @ApiModelProperty("订单备注")
    private String comments;

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerMobile() {
        return customerMobile;
    }

    public void setCustomerMobile(String customerMobile) {
        this.customerMobile = customerMobile;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerRemark() {
        return customerRemark;
    }

    public void setCustomerRemark(String customerRemark) {
        this.customerRemark = customerRemark;
    }

    public String getWorkerName() {
        return workerName;
    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }

    public String getWorkerMobile() {
        return workerMobile;
    }

    public void setWorkerMobile(String workerMobile) {
        this.workerMobile = workerMobile;
    }

    public Date getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(Date serviceTime) {
        this.serviceTime = serviceTime;
    }

    public String getCustomerCityCode() {
        return customerCityCode;
    }

    public void setCustomerCityCode(String customerCityCode) {
        this.customerCityCode = customerCityCode;
    }

    public String getVoucherIds() {
        return voucherIds;
    }

    public void setVoucherIds(String voucherIds) {
        this.voucherIds = voucherIds;
    }

    public String getRechargeCardIds() {
        return rechargeCardIds;
    }

    public void setRechargeCardIds(String rechargeCardIds) {
        this.rechargeCardIds = rechargeCardIds;
    }

    public String getCardMoneys() {
        return cardMoneys;
    }

    public void setCardMoneys(String cardMoneys) {
        this.cardMoneys = cardMoneys;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
