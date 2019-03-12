package com.jiazhe.youxiang.server.biz.rechargecard;

import com.jiazhe.youxiang.server.common.constant.CommonConstant;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchEditDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchSaveDTO;
import com.jiazhe.youxiang.server.service.rechargecard.RCExchangeCodeBatchService;
import com.jiazhe.youxiang.server.vo.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tu
 * @description：
 * @date 2018/10/21
 */
@Service("rcExchangeCodeBatchBiz")
public class RCExchangeCodeBatchBiz {

    @Autowired
    private RCExchangeCodeBatchService rcExchangeCodeBatchService;
    @Autowired
    private RCExchangeCodeBiz rcExchangeCodeBiz;

    public List<RCExchangeCodeBatchDTO> getList(Integer projectId, String name, Paging paging) {
        return rcExchangeCodeBatchService.getList(projectId, name, paging);
    }

    public void addSave(RCExchangeCodeBatchSaveDTO rcExchangeCodeBatchSaveDTO) {
        rcExchangeCodeBatchService.addSave(rcExchangeCodeBatchSaveDTO);
    }

    public void editSave(RCExchangeCodeBatchSaveDTO rcExchangeCodeBatchSaveDTO) {
        rcExchangeCodeBatchService.editSave(rcExchangeCodeBatchSaveDTO);
    }

    public RCExchangeCodeBatchEditDTO getById(Integer id) {
        RCExchangeCodeBatchEditDTO rcExchangeCodeBatchEditDTO = rcExchangeCodeBatchService.getById(id);
        return rcExchangeCodeBatchEditDTO;
    }

    /**
     * 根据id启用充值卡兑换码批次
     *
     * @param id
     */
    public void startUsing(Integer id) {
        rcExchangeCodeBatchService.changeBatchStatus(id, CommonConstant.CODE_STOP_USING);
    }

    /**
     * 根据id停用充值卡兑换码批次
     *
     * @param id
     */
    public void stopUsing(Integer id) {
        rcExchangeCodeBatchService.changeBatchStatus(id, CommonConstant.CODE_STOP_USING);
    }

    public List<RCExchangeCodeBatchDTO> getVirtualByProjectId(Integer projectId) {
        return rcExchangeCodeBatchService.getVirtualByProjectId(projectId);
    }

    public void generateCode(Integer id) {
        rcExchangeCodeBatchService.generateCode(id);
    }

}
