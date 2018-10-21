package com.jiazhe.youxiang.server.biz.rechargecard;

import com.jiazhe.youxiang.base.util.GenerateCode;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecode.RCExchangeCodeSaveDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchListDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchSaveDTO;
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

    public List<RCExchangeCodeBatchListDTO> getList(Integer projectId, String name, Paging paging) {
        return rcExchangeCodeBatchService.getList(projectId,name,paging);
    }

    public int addSave(RCExchangeCodeBatchSaveDTO rcExchangeCodeBatchSaveDTO) {
        List<RCExchangeCodeSaveDTO> rcExchangeCodeSaveDTOS = new ArrayList<RCExchangeCodeSaveDTO>();
        String[][] codeAndKeyts = GenerateCode.generateCode("0",rcExchangeCodeBatchSaveDTO.getAmount());
        //保存批次信息，并保存批次下的兑换码
        return 1;
       /*return rcExchangeCodeBatchService.addSave(rcExchangeCodeBatchSaveDTO);*/
    }
}
