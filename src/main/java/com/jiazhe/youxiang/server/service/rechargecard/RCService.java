package com.jiazhe.youxiang.server.service.rechargecard;

import java.util.Date;

/**
 * @author TU
 * @description
 * @date 2018/10/23.
 */
public interface RCService {

    /**
     * 修改充值卡启用、停用状态
     * @param id
     * @param status
     * @return
     */
    int changeStatus(Integer id, Byte status);

    /**
     * 修改充值卡过期时间
     * @param id
     * @param expiryTime
     * @return
     */
    int changeExpiryTime(Integer id, Date expiryTime);
}
