package com.jiazhe.youxiang.server.vo.resp.order.orderinfo;

import com.jiazhe.youxiang.server.dto.product.ProductDTO;
import com.jiazhe.youxiang.server.vo.BaseObject;
import com.jiazhe.youxiang.server.vo.BaseVO;
import com.jiazhe.youxiang.server.vo.resp.customer.CustomerResp;
import com.jiazhe.youxiang.server.vo.resp.product.ProductResp;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author tu
 * @description：
 * @date 2018/10/24
 */
public class OrderInfoResp extends BaseVO {

    @ApiModelProperty("订单id")
    private Integer id;

    @ApiModelProperty("订单号")
    private String orderCode;

    @ApiModelProperty("客户id")
    private Integer customerId;

    @ApiModelProperty("下单城市code")
    private String customerCityCode;

    @ApiModelProperty("下单城市名")
    private String customerCityName;

    @ApiModelProperty("商品id")
    private Integer productId;

    @ApiModelProperty("下单时单价")
    private BigDecimal productPrice;

    @ApiModelProperty("下单数量")
    private Integer count;

    @ApiModelProperty("客户地址")
    private String customerAddress;

    @ApiModelProperty("收货人电话")
    private String customerMobile;

    @ApiModelProperty("收货人姓名")
    private String customerName;

    @ApiModelProperty("客户留言")
    private String customerRemark;

    @ApiModelProperty("服务人员姓名")
    private String workerName;

    @ApiModelProperty("服务人员电话")
    private String workerMobile;

    @ApiModelProperty("下单时间")
    private Long orderTime;

    @ApiModelProperty("预约时间")
    private Long serviceTime;

    @ApiModelProperty("服务时间")
    private Long realServiceTime;

    @ApiModelProperty("积分卡支付金额")
    private BigDecimal payPoint;

    @ApiModelProperty("充值卡支付金额")
    private BigDecimal payRechargeCard;

    @ApiModelProperty("代金券代替数量")
    private BigDecimal payVoucher;

    @ApiModelProperty("在线支付金额")
    private BigDecimal payCash;

    @ApiModelProperty("待支付金额")
    private BigDecimal payment;

    @ApiModelProperty("订单总金额")
    private BigDecimal totalAmount;

    @ApiModelProperty("订单成本")
    private BigDecimal cost;

    @ApiModelProperty("订单备注")
    private String comments;

    @ApiModelProperty("订单类型，0为后台下单 1为app下单")
    private Byte type;

    @ApiModelProperty("订单状态【1代付款，2待派单，3待服务，4已完成，5取消待审核，6取消审核未通过，7已取消】")
    private Byte status;

    @ApiModelProperty("审核不通过理由")
    private String auditReason;

    @ApiModelProperty("电子码信息")
    private String extInfo;

    private CustomerResp customerResp;

    private ProductResp productResp;

    private ProductResp serviceProductResp;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
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

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
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

    public Long getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Long orderTime) {
        this.orderTime = orderTime;
    }

    public Long getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(Long serviceTime) {
        this.serviceTime = serviceTime;
    }

    public Long getRealServiceTime() {
        return realServiceTime;
    }

    public void setRealServiceTime(Long realServiceTime) {
        this.realServiceTime = realServiceTime;
    }

    public BigDecimal getPayPoint() {
        return payPoint;
    }

    public void setPayPoint(BigDecimal payPoint) {
        this.payPoint = payPoint;
    }

    public BigDecimal getPayRechargeCard() {
        return payRechargeCard;
    }

    public void setPayRechargeCard(BigDecimal payRechargeCard) {
        this.payRechargeCard = payRechargeCard;
    }

    public BigDecimal getPayVoucher() {
        return payVoucher;
    }

    public void setPayVoucher(BigDecimal payVoucher) {
        this.payVoucher = payVoucher;
    }

    public BigDecimal getPayCash() {
        return payCash;
    }

    public void setPayCash(BigDecimal payCash) {
        this.payCash = payCash;
    }

    public BigDecimal getPayment() {
        return payment;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getAuditReason() {
        return auditReason;
    }

    public void setAuditReason(String auditReason) {
        this.auditReason = auditReason;
    }

    public CustomerResp getCustomerResp() {
        return customerResp;
    }

    public void setCustomerResp(CustomerResp customerResp) {
        this.customerResp = customerResp;
    }

    public ProductResp getProductResp() {
        return productResp;
    }

    public void setProductResp(ProductResp productResp) {
        this.productResp = productResp;
    }

    public ProductResp getServiceProductResp() {
        return serviceProductResp;
    }

    public void setServiceProductResp(ProductResp serviceProductResp) {
        this.serviceProductResp = serviceProductResp;
    }

    public String getCustomerCityCode() {
        return customerCityCode;
    }

    public void setCustomerCityCode(String customerCityCode) {
        this.customerCityCode = customerCityCode;
    }

    public String getCustomerCityName() {
        return customerCityName;
    }

    public void setCustomerCityName(String customerCityName) {
        this.customerCityName = customerCityName;
    }

    public String getExtInfo() {
        return extInfo;
    }

    public void setExtInfo(String extInfo) {
        this.extInfo = extInfo;
    }
}
