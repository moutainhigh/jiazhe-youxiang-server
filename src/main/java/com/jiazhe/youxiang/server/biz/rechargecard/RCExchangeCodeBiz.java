package com.jiazhe.youxiang.server.biz.rechargecard;

import com.jiazhe.youxiang.server.common.enums.CodeStatusEnum;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecode.RCExchangeCodeDTO;
import com.jiazhe.youxiang.server.service.rechargecard.RCExchangeCodeService;
import com.jiazhe.youxiang.server.vo.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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

    public int customerSelfCharge(String mobile, String keyt) {
        return rcExchangeCodeService.codeCharge(1,mobile,keyt);
    }

    public int backstageCodeCharge(String mobile, String keyt) {
        return rcExchangeCodeService.codeCharge(0,mobile,keyt);
    }

    public List<RCExchangeCodeDTO> getList(Integer batchId, String code, String keyt, Byte status, Byte used, Paging paging) {
        return null;
    }
}
