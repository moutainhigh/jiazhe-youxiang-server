package com.jiazhe.youxiang.server.biz.point;

import com.jiazhe.youxiang.server.common.constant.CommonConstant;
import com.jiazhe.youxiang.server.dto.point.pointexchangecodebatch.PointExchangeCodeBatchDTO;
import com.jiazhe.youxiang.server.dto.point.pointexchangecodebatch.PointExchangeCodeBatchEditDTO;
import com.jiazhe.youxiang.server.dto.point.pointexchangecodebatch.PointExchangeCodeBatchSaveDTO;
import com.jiazhe.youxiang.server.service.point.PointExchangeCodeBatchService;
import com.jiazhe.youxiang.server.vo.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        pointExchangeCodeBatchService.addSave(pointExchangeCodeBatchSaveDTO);
    }

    /**
     * 修改批次信息
     * @param pointExchangeCodeBatchSaveDTO
     */
    public void editSave(PointExchangeCodeBatchSaveDTO pointExchangeCodeBatchSaveDTO) {
        pointExchangeCodeBatchService.editSave(pointExchangeCodeBatchSaveDTO);
    }

    public PointExchangeCodeBatchEditDTO getById(Integer id) {
        PointExchangeCodeBatchEditDTO pointExchangeCodeBatchEditDTO = pointExchangeCodeBatchService.getById(id);
        return pointExchangeCodeBatchEditDTO;
    }

    public void generateCode(Integer id) {
        pointExchangeCodeBatchService.generateCode(id);
    }

    /**
     * 根据id启用批次
     * @param id
     */
    public void startUsing(Integer id) {
        pointExchangeCodeBatchService.changeBatchStatus(id, CommonConstant.CODE_START_USING);
    }

    /**
     * 根据id停用批次
     * @param id
     */
    public void stopUsing(Integer id) {
        pointExchangeCodeBatchService.changeBatchStatus(id, CommonConstant.CODE_STOP_USING);
    }

    public boolean merchantNoIsRepeat(Integer batchId,String merchantNo) {
        return pointExchangeCodeBatchService.merchantNoIsRepeat(batchId,merchantNo);
    }
}
