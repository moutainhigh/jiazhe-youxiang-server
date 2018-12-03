package com.jiazhe.youxiang.server.vo.req.order.orderinfo;

import com.jiazhe.youxiang.server.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author tu
 * @description：
 * @date 2018/10/24
 */
public class CustomerPlaceOrderReq  extends BaseVO {

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

    @ApiModelProperty("预约时间")
    private Long serviceTime;

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

    public String getCustomerCityCode() {
        return customerCityCode;
    }

    public void setCustomerCityCode(String customerCityCode) {
        this.customerCityCode = customerCityCode;
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

    public Long getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(Long serviceTime) {
        this.serviceTime = serviceTime;
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

}
