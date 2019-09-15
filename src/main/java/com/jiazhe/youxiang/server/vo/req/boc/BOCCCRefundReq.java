package com.jiazhe.youxiang.server.vo.req.boc;

import com.jiazhe.youxiang.server.vo.BaseVO;

/**
 * @author TU
 * @description 中行信用卡（BOCCC）请求退货接口
 * @date 2019-09-06.
 */
public class BOCCCRefundReq extends BaseVO {

    public BOCCCRefundReq() {
        this.flag = "Y";
    }

    /**
     * 商品ID
     */
    private String waresId;

    /**
     * 库存编码
     */
    private String wEid;

    /**
     * 订单ID
     */
    private String orderId;

    /**
     * 优惠券码
     */
    private String wInfo;

    /**
     * 是否可以退货 N不可以 Y可以
     */
    private String flag;

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

    public String getwEid() {
        return wEid;
    }

    public void setwEid(String wEid) {
        this.wEid = wEid;
    }

    public String getFlag() {
        return flag;
    }
}
