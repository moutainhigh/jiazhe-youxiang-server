package com.jiazhe.youxiang.server.biz.point;

import com.jiazhe.youxiang.server.common.constant.CommonConstant;
import com.jiazhe.youxiang.server.common.enums.CodeStatusEnum;
import com.jiazhe.youxiang.server.dto.point.pointexchangecode.PointExchangeCodeDTO;
import com.jiazhe.youxiang.server.dto.point.pointexchangecode.PointExchangeCodeEditDTO;
import com.jiazhe.youxiang.server.service.point.PointExchangeCodeService;
import com.jiazhe.youxiang.server.vo.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return pointExchangeCodeService.getList(batchId, code, keyt, status, used, paging);
    }

    /**
     * 根据兑换码id，单个停用、失效积分卡兑换码
     *
     * @param id
     */
    public void stopUsing(Integer id) {
        pointExchangeCodeService.changeCodeStatus(id, CommonConstant.CODE_STOP_USING);
    }

    /**
     * 根据兑换码id，单个启用、激活积分卡兑换码
     *
     * @param id
     */
    public void startUsing(Integer id) {
        pointExchangeCodeService.changeCodeStatus(id, CommonConstant.CODE_START_USING);
    }

    public PointExchangeCodeDTO getById(Integer id) {
        return pointExchangeCodeService.getById(id);
    }

    public void editSave(PointExchangeCodeEditDTO dto) {
        pointExchangeCodeService.editSave(dto);
    }

    public void customerSelfCharge(Integer customerId, String keyt) {
        pointExchangeCodeService.codeCharge(CommonConstant.EXCHANGETYPE_CUSTOMER_CODE_EXCHANGE, customerId, keyt);
    }

    public void backstageCodeCharge(Integer customerId, String keyt) {
        pointExchangeCodeService.codeCharge(CommonConstant.EXCHANGETYPE_USER_CODE_EXCHANGE, customerId, keyt);
    }

    /**
     * 根据批次id，启用批次下所有兑换码
     *
     * @param batchId
     */
    public void allStartUsing(Integer batchId) {
        pointExchangeCodeService.batchChangeStatus(batchId, CodeStatusEnum.START_USING.getId().byteValue());
    }

    /**
     * 根据批次id，启用批次下所有兑换码
     *
     * @param batchId
     */
    public void allStopUsing(Integer batchId) {
        pointExchangeCodeService.batchChangeStatus(batchId, CodeStatusEnum.STOP_USING.getId().byteValue());
    }
}
