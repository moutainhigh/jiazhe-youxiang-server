package com.jiazhe.youxiang.server.dto.order.orderinfo;

import com.jiazhe.youxiang.server.common.enums.OrderOpreationTypeEnum;
import com.jiazhe.youxiang.server.vo.BaseObject;

import java.util.Date;

/**
 * @Description: java类作用描述
 * @Author: zwx
 * @CreateDate: 2019/11/23 12:44
 */
public class OrderTrackDTO extends BaseObject {

    private static final long serialVersionUID = 8532935448575785626L;

    private Integer id;

    private Integer orderid;

    private OrderOpreationTypeEnum opreation;

    private String username;

    private String extInfo;

    private Byte isDeleted;

    private Date addTime;

    private Date modTime;

    private String msg = "";

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public OrderOpreationTypeEnum getOpreation() {
        return opreation;
    }

    public void setOpreation(OrderOpreationTypeEnum opreation) {
        this.opreation = opreation;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getModTime() {
        return modTime;
    }

    public void setModTime(Date modTime) {
        this.modTime = modTime;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
