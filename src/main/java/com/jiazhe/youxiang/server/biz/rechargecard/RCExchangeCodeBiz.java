package com.jiazhe.youxiang.server.biz.rechargecard;

import com.jiazhe.youxiang.server.common.constant.CommonConstant;
import com.jiazhe.youxiang.server.common.enums.CodeStatusEnum;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecode.RCExchangeCodeDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecode.RCExchangeCodeEditDTO;
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

    public void startUsing(Integer id) {
        rcExchangeCodeService.changeCodeStatus(id, CodeStatusEnum.START_USING.getId().byteValue());
    }

    public void stopUsing(Integer id) {
        rcExchangeCodeService.changeCodeStatus(id, CodeStatusEnum.STOP_USING.getId().byteValue());
    }

    public void changeExpiryTime(Integer id, Date expiryTime) {
        rcExchangeCodeService.changeExpiryTime(id,expiryTime);
    }

    public void customerSelfCharge(Integer id, String keyt)  {
        rcExchangeCodeService.codeCharge(CommonConstant.EXCHANGETYPE_CUSTOMER_CODE_EXCHANGE,id,keyt);
    }

    public void backstageCodeCharge(Integer id, String keyt)  {
        rcExchangeCodeService.codeCharge(CommonConstant.EXCHANGETYPE_USER_CODE_EXCHANGE,id,keyt);
    }

    public List<RCExchangeCodeDTO> getList(Integer batchId, String code, String keyt, Byte status, Byte used, Paging paging) {
        return rcExchangeCodeService.getList(batchId,code,keyt,status,used,paging);
    }

    public List<RCExchangeCodeDTO> getByBatchId(Integer id) {
        return rcExchangeCodeService.getByBatchId(id);
    }

    public RCExchangeCodeDTO getById(Integer id) {
        return rcExchangeCodeService.getById(id);
    }

    public void editSave(RCExchangeCodeEditDTO dto) {
        rcExchangeCodeService.editSave(dto);
    }
}
