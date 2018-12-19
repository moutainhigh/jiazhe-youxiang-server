package com.jiazhe.youxiang.server.service.point;


import com.jiazhe.youxiang.server.dto.point.pointexchangecodebatch.PointExchangeCodeBatchDTO;
import com.jiazhe.youxiang.server.dto.point.pointexchangecodebatch.PointExchangeCodeBatchEditDTO;
import com.jiazhe.youxiang.server.dto.point.pointexchangecodebatch.PointExchangeCodeBatchSaveDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchEditDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchSaveDTO;
import com.jiazhe.youxiang.server.vo.Paging;

import java.util.List;

/**
 * @author tu
 * @description：
 * @date 2018/12/13
 */
public interface PointExchangeCodeBatchService {

    List<PointExchangeCodeBatchDTO> getList(Integer projectId, String name, Paging paging);

    List<PointExchangeCodeBatchDTO> getVirtualByProjectId(Integer id);

    void addSave(PointExchangeCodeBatchSaveDTO pointExchangeCodeBatchSaveDTO);

    void editSave(PointExchangeCodeBatchSaveDTO pointExchangeCodeBatchSaveDTO);

    PointExchangeCodeBatchEditDTO getById(Integer id);

    void generateCode(Integer id);

    void changeBatchStatus(Integer id, Byte b);
}
