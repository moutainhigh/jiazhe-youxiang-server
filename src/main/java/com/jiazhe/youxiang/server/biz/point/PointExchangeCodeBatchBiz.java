package com.jiazhe.youxiang.server.biz.point;

import com.jiazhe.youxiang.server.common.constant.CommonConstant;
import com.jiazhe.youxiang.server.common.enums.CodeStatusEnum;
import com.jiazhe.youxiang.server.dto.point.pointexchangecodebatch.PointExchangeCodeBatchDTO;
import com.jiazhe.youxiang.server.dto.point.pointexchangecodebatch.PointExchangeCodeBatchSaveDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchEditDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchSaveDTO;
import com.jiazhe.youxiang.server.service.point.PointExchangeCodeBatchService;
import com.jiazhe.youxiang.server.service.rechargecard.RCExchangeCodeBatchService;
import com.jiazhe.youxiang.server.vo.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author tu
 * @description：
 * @date 2018/12/13
 */
@Service("pointExchangeCodeBatchBiz")
public class PointExchangeCodeBatchBiz {

    @Autowired
    private PointExchangeCodeBatchService pointExchangeCodeBatchService;

    public List<PointExchangeCodeBatchDTO> getList(Integer projectId, String name, Paging paging) {
        return pointExchangeCodeBatchService.getList(projectId, name, paging);
    }

    public List<PointExchangeCodeBatchDTO> getVirtualByProjectId(Integer id) {
        return pointExchangeCodeBatchService.getVirtualByProjectId(id);
    }

    public void addSave(PointExchangeCodeBatchSaveDTO pointExchangeCodeBatchSaveDTO) {
        if(pointExchangeCodeBatchSaveDTO.getIsVirtual().equals(CommonConstant.BATCH_IS_VIRTUAL)){
            pointExchangeCodeBatchSaveDTO.setAmount(0);
            pointExchangeCodeBatchSaveDTO.setFaceValue(new BigDecimal(0));
        }
        if(pointExchangeCodeBatchSaveDTO.getExpiryType().equals(CommonConstant.POINT_EXPIRY_TIME)){
            pointExchangeCodeBatchSaveDTO.setValidityPeriod(0);
        }else{
            pointExchangeCodeBatchSaveDTO.setPointExpiryTime(new Date());
        }
        pointExchangeCodeBatchService.addSave(pointExchangeCodeBatchSaveDTO);
    }

    public void editSave(PointExchangeCodeBatchSaveDTO pointExchangeCodeBatchSaveDTO) {
        pointExchangeCodeBatchService.editSave(rcExchangeCodeBatchSaveDTO);
    }
}
