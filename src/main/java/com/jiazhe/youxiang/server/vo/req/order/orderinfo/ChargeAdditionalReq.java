package com.jiazhe.youxiang.server.vo.req.order.orderinfo;

import com.jiazhe.youxiang.server.vo.BaseVO;
import com.jiazhe.youxiang.server.vo.req.IdReq;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;

import java.math.BigDecimal;

/**
 * @author tu
 * @description：
 * @date 2018/10/24
 */
public class ChargeAdditionalReq extends IdReq{

    @ApiModelProperty("额外支付费用")
    private BigDecimal additionalPay ;

    public BigDecimal getAdditionalPay() {
        return additionalPay;
    }

    public void setAdditionalPay(BigDecimal additionalPay) {
        this.additionalPay = additionalPay;
    }
}
