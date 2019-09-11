package com.jiazhe.youxiang.server.vo.req.boc;

import com.jiazhe.youxiang.server.vo.BaseVO;

/**
 * @author TU
 * @description  中行信用卡（BOCCC）请求退货接口
 * @date 2019-09-06.
 */
public class BOCCCRefundReq extends BaseVO {

    private String orderId ;

    private String waresId ;

    private String wInfo ;

    private String returnDate ;

    private String returnTime ;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getWaresId() {
        return waresId;
    }

    public void setWaresId(String waresId) {
        this.waresId = waresId;
    }

    public String getwInfo() {
        return wInfo;
    }

    public void setwInfo(String wInfo) {
        this.wInfo = wInfo;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(String returnTime) {
        this.returnTime = returnTime;
    }
}
