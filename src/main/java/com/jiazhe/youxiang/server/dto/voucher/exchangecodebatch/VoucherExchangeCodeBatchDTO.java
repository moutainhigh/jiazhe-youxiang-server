package com.jiazhe.youxiang.server.dto.voucher.exchangecodebatch;

import com.jiazhe.youxiang.server.vo.BaseObject;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author TU
 * @description
 * @date 2018/10/24.
 */
public class VoucherExchangeCodeBatchDTO extends BaseObject {

    private Integer id;

    private String name;

    private String voucherName;

    private Integer amount;

    private Integer count;

    private Date expiryTime;

    private Byte status;

    private Byte isMade;

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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
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

    public String getVoucherName() {
        return voucherName;
    }

    public void setVoucherName(String voucherName) {
        this.voucherName = voucherName;
    }

    public Byte getIsMade() {
        return isMade;
    }

    public void setIsMade(Byte isMade) {
        this.isMade = isMade;
    }
}
