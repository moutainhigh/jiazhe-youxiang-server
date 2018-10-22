package com.jiazhe.youxiang.server.biz.rechargecard;

import com.jiazhe.youxiang.base.util.GenerateCode;
import com.jiazhe.youxiang.server.common.constant.CommonConstant;
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
        Integer amount = rcExchangeCodeBatchSaveDTO.getAmount();
        String[][] codeAndKeyts = GenerateCode.generateCode(CommonConstant.RC_EXCHANGE_CODE_PREFIX,amount);
        //保存批次信息，并保存批次下的兑换码
        for(int i=0;i<amount;i++){
            RCExchangeCodeSaveDTO rcExchangeCodeSaveDTO = new RCExchangeCodeSaveDTO();
            rcExchangeCodeSaveDTO.setBatchName(rcExchangeCodeBatchSaveDTO.getName());
            rcExchangeCodeSaveDTO.setCode(codeAndKeyts[0][i]);
            rcExchangeCodeSaveDTO.setKeyt(codeAndKeyts[1][i]);
        }
        return 1;
       /*return rcExchangeCodeBatchService.addSave(rcExchangeCodeBatchSaveDTO);*/
    }
}
