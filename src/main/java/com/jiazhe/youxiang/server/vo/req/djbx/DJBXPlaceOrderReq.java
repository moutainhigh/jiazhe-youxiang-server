package com.jiazhe.youxiang.server.vo.req.djbx;

import com.jiazhe.youxiang.server.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author TU
 * @description
 * @date 2020-05-20.
 */
public class DJBXPlaceOrderReq extends BaseVO {

    @ApiModelProperty("代理人code")
    private String agentCode;

    @ApiModelProperty("验证码")
    private String verifiCode;

    @ApiModelProperty("客户id，必填")
    private Integer customerId;

    @ApiModelProperty("商品id，必填")
    private Integer productId;

    @ApiModelProperty("下单城市code，必填")
    private String customerCityCode;

    @ApiModelProperty("下单数量，必填")
    private Integer count;

    @ApiModelProperty("订单地址，服务类商品必填")
    private String customerAddress;

    @ApiModelProperty("订单联系电话，服务类商品必填")
    private String customerMobile;

    @ApiModelProperty("订单接收人，服务类商品必填")
    private String customerName;

    @ApiModelProperty("客户留言，服务类商品选填")
    private String customerRemark;

    @ApiModelProperty("预约时间，服务类商品必填")
    private Long serviceTime;

    @ApiModelProperty("是否支持微信支付 是：'true' 否：'false'")
    private String cashSupport;

    public String getAgentCode() {
        return agentCode;
    }

    public void setAgentCode(String agentCode) {
        this.agentCode = agentCode;
    }

    public String getVerifiCode() {
        return verifiCode;
    }

    public void setVerifiCode(String verifiCode) {
        this.verifiCode = verifiCode;
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

    public String getCashSupport() {
        return cashSupport;
    }

    public void setCashSupport(String cashSupport) {
        this.cashSupport = cashSupport;
    }

}
