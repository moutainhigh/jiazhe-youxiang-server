package com.jiazhe.youxiang.server.biz.rechargecard;

import com.jiazhe.youxiang.server.common.enums.CodeStatusEnum;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchAddDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchEditDTO;
import com.jiazhe.youxiang.server.service.rechargecard.RCExchangeCodeBatchService;
import com.jiazhe.youxiang.server.vo.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        return rcExchangeCodeBatchService.getList(projectId,name,paging);
    }

    public int addSave(RCExchangeCodeBatchAddDTO rcExchangeCodeBatchAddDTO) {
       return rcExchangeCodeBatchService.addSave(rcExchangeCodeBatchAddDTO);
    }

    public RCExchangeCodeBatchEditDTO getById(Integer id) {
        return rcExchangeCodeBatchService.getById(id);
    }

    public int editSave(RCExchangeCodeBatchEditDTO rcExchangeCodeBatchEditDTO) {
        return rcExchangeCodeBatchService.editSave(rcExchangeCodeBatchEditDTO);
    }

    public int startUsing(Integer id) {
        return rcExchangeCodeBatchService.changeBatchStatus(id, CodeStatusEnum.START_USING.getId().byteValue());
    }

    public int stopUsing(Integer id) {
        return rcExchangeCodeBatchService.changeBatchStatus(id, CodeStatusEnum.STOP_USING.getId().byteValue());
    }

    public List<RCExchangeCodeBatchDTO> getList(Integer projectId, String name) {
        return null;
    }

    public int delete(Integer id) {
        return 0;
    }
}
