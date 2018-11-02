package com.jiazhe.youxiang.server.biz.rechargecard;

import com.jiazhe.youxiang.server.adapter.rechargecard.RCAdapter;
import com.jiazhe.youxiang.server.common.constant.CommonConstant;
import com.jiazhe.youxiang.server.common.enums.CodeStatusEnum;
import com.jiazhe.youxiang.server.domain.po.RechargeCardPO;
import com.jiazhe.youxiang.server.dto.rechargecard.rc.RCDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rc.RCEditDTO;
import com.jiazhe.youxiang.server.service.rechargecard.RCService;
import com.jiazhe.youxiang.server.vo.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tu
 * @description：
 * @date 2018/10/21
 */
@Service("rcBiz")
public class RCBiz {

    @Autowired
    private RCService rcService ;
    public void startUsing(Integer id) {
        rcService.changeStatus(id, CodeStatusEnum.START_USING.getId().byteValue());
    }

    public void stopUsing(Integer id) {
        rcService.changeStatus(id,CodeStatusEnum.STOP_USING.getId().byteValue());
    }


    public void changeExpiryTime(Integer id, Date expiryTime) {
        rcService.changeExpiryTime(id,expiryTime);
    }


    public void directCharge(String mobile , Integer batchId, BigDecimal faceValue) {
        rcService.directCharge(mobile,batchId,faceValue);
    }

    public List<RCDTO> getList(String mobile, Byte status,Byte expiry, Paging paging) {
        return rcService.getList(mobile,status,expiry,paging);
    }


    public List<RCDTO> findUnexpiredByCustomerId(Integer customerId) {
        List<RCDTO> rcdtoList =rcService.findUnexpiredByCustomerId(customerId);
        return rcdtoList;
    }

    public RCDTO getById(Integer id) {
        return rcService.getById(id);
    }

    public void editSave(RCEditDTO dto) {
        rcService.editSave(dto);
    }
}
