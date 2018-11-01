package com.jiazhe.youxiang.server.biz.rechargecard;

import com.jiazhe.youxiang.server.common.enums.CodeStatusEnum;
import com.jiazhe.youxiang.server.common.enums.RechargeCardCodeEnum;
import com.jiazhe.youxiang.server.common.exceptions.RechargeCardException;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecode.RCExchangeCodeDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchEditDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchSaveDTO;
import com.jiazhe.youxiang.server.service.rechargecard.RCExchangeCodeBatchService;
import com.jiazhe.youxiang.server.vo.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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

    public void addSave(RCExchangeCodeBatchSaveDTO rcExchangeCodeBatchSaveDTO) {
        if(rcExchangeCodeBatchSaveDTO.getIsVirtual().equals(Byte.valueOf("1"))){
            rcExchangeCodeBatchSaveDTO.setAmount(0);
            rcExchangeCodeBatchSaveDTO.setFaceValue(new BigDecimal(0));
        }
        if(rcExchangeCodeBatchSaveDTO.getExpiryType().equals(Byte.valueOf("0"))){
            rcExchangeCodeBatchSaveDTO.setValidityPeriod(0);
        }else{
            rcExchangeCodeBatchSaveDTO.setRechargeCardExpiryTime(new Date());
        }
       rcExchangeCodeBatchService.addSave(rcExchangeCodeBatchSaveDTO);
    }

    public void editSave(RCExchangeCodeBatchSaveDTO rcExchangeCodeBatchSaveDTO) {
        rcExchangeCodeBatchService.editSave(rcExchangeCodeBatchSaveDTO);
    }

    public RCExchangeCodeBatchEditDTO getById(Integer id) {
        RCExchangeCodeBatchEditDTO rcExchangeCodeBatchEditDTO = rcExchangeCodeBatchService.getById(id);
        return rcExchangeCodeBatchEditDTO;
    }



    public void startUsing(Integer id) {
        rcExchangeCodeBatchService.changeBatchStatus(id, CodeStatusEnum.START_USING.getId().byteValue());
    }

    public void stopUsing(Integer id) {
         rcExchangeCodeBatchService.changeBatchStatus(id, CodeStatusEnum.STOP_USING.getId().byteValue());
    }

    public List<RCExchangeCodeBatchDTO> getList(Integer projectId, String name) {
        return null;
    }

    public void delete(Integer id) {

    }

    public List<RCExchangeCodeBatchDTO> getByProjectId(Integer projectId) {
        return null;
    }

    public void generateCode(Integer id) {
        rcExchangeCodeBatchService.generateCode(id);
    }

    public void export(Integer batchId) {
       List<RCExchangeCodeDTO> rcExchangeCodeDTOList= rcExchangeCodeBiz.getByBatchId(batchId);
       if(rcExchangeCodeDTOList.isEmpty()){
           throw new RechargeCardException(RechargeCardCodeEnum.NO_CODE_TO_EXPORT);
       }
    }
}
