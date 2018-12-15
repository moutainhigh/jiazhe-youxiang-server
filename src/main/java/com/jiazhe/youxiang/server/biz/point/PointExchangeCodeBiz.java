package com.jiazhe.youxiang.server.biz.point;

import com.jiazhe.youxiang.server.common.constant.CommonConstant;
import com.jiazhe.youxiang.server.common.enums.CodeStatusEnum;
import com.jiazhe.youxiang.server.dto.point.pointexchangecode.PointExchangeCodeDTO;
import com.jiazhe.youxiang.server.dto.point.pointexchangecode.PointExchangeCodeEditDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecode.RCExchangeCodeDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecode.RCExchangeCodeEditDTO;
import com.jiazhe.youxiang.server.service.point.PointExchangeCodeService;
import com.jiazhe.youxiang.server.service.rechargecard.RCExchangeCodeService;
import com.jiazhe.youxiang.server.vo.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author tu
 * @description：
 * @date 2018/12/13
 */
@Service("pointExchangeCodeBiz")
public class PointExchangeCodeBiz {

    @Autowired
    private PointExchangeCodeService pointExchangeCodeService;

    public List<PointExchangeCodeDTO> getByBatchId(Integer id) {
        return pointExchangeCodeService.getByBatchId(id);
    }

    public List<PointExchangeCodeDTO> getList(Integer batchId, String code, String keyt, Byte status, Byte used, Paging paging) {
        return pointExchangeCodeService.getList(batchId,code,keyt,status,used,paging);
    }

    public void stopUsing(Integer id) {
        pointExchangeCodeService.changeCodeStatus(id, CodeStatusEnum.STOP_USING.getId().byteValue());
    }

    public void startUsing(Integer id) {
        pointExchangeCodeService.changeCodeStatus(id, CodeStatusEnum.START_USING.getId().byteValue());
    }

    public PointExchangeCodeDTO getById(Integer id) {
        return pointExchangeCodeService.getById(id);
    }

    public void editSave(PointExchangeCodeEditDTO dto) {
        pointExchangeCodeService.editSave(dto);
    }

    public void customerSelfCharge(Integer id, String keyt) {
        pointExchangeCodeService.codeCharge(CommonConstant.EXCHANGETYPE_CUSTOMER_CODE_EXCHANGE,id,keyt);
    }

    public void backstageCodeCharge(Integer id, String keyt) {
        pointExchangeCodeService.codeCharge(CommonConstant.EXCHANGETYPE_USER_CODE_EXCHANGE,id,keyt);
    }
}
