package com.jiazhe.youxiang.server.biz.rechargecard;

import com.jiazhe.youxiang.server.common.enums.CodeStatusEnum;
import com.jiazhe.youxiang.server.service.rechargecard.RCExchangeCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author tu
 * @description：
 * @date 2018/10/21
 */
@Service("rcExchangeCodeBiz")
public class RCExchangeCodeBiz {

    @Autowired
    private RCExchangeCodeService rcExchangeCodeService;

    public int startUsing(Integer id) {
        return rcExchangeCodeService.changeCodeStatus(id, CodeStatusEnum.START_USING.getId().byteValue());
    }

    public int stopUsing(Integer id) {
        return rcExchangeCodeService.changeCodeStatus(id, CodeStatusEnum.STOP_USING.getId().byteValue());
    }

    public int changeExpiryTime(Integer id, Date expiryTime) {
        return rcExchangeCodeService.changeExpiryTime(id,expiryTime);
    }
}
