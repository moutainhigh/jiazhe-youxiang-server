/*
 * Copyright (c) 2019 橙谊科技
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.vo.req.boc;

import com.jiazhe.youxiang.base.util.DateUtil;
import com.jiazhe.youxiang.base.util.boccc.BOCCCUtils;
import com.jiazhe.youxiang.server.vo.BaseVO;

import java.util.Date;

/**
 * @author tu
 * @version 1.0
 * @description 中行信用卡使用已使用更新接口参数
 * @created 2019-09-13 18:22
 */
public class BOCCCUsedReq extends BaseVO {

    BOCCCUsedReq() {
        this.wSign = "R";
        this.usedDate = BOCCCUtils.getNowTime().substring(0, 8);
        this.usedTime = BOCCCUtils.getNowTime().substring(9, 17);

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
     * 优惠券标识
     */
    private String wSign;

    /**
     * 使用日期
     */
    private String usedDate;

    /**
     * 使用时间
     */
    private String usedTime;

    public String getWaresId() {
        return waresId;
    }

    public void setWaresId(String waresId) {
        this.waresId = waresId;
    }

    public String getwEid() {
        return wEid;
    }

    public void setwEid(String wEid) {
        this.wEid = wEid;
    }

    public String getwSign() {
        return wSign;
    }

    public void setwSign(String wSign) {
        this.wSign = wSign;
    }

    public String getUsedDate() {
        return usedDate;
    }

    public void setUsedDate(String usedDate) {
        this.usedDate = usedDate;
    }

    public String getUsedTime() {
        return usedTime;
    }

    public void setUsedTime(String usedTime) {
        this.usedTime = usedTime;
    }
}
