package com.jiazhe.youxiang.server.service.rechargecard;


import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchEditDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchSaveDTO;
import com.jiazhe.youxiang.server.vo.Paging;

import java.util.List;

/**
 * @author tu
 * @description：
 * @date 2018/10/21
 */
public interface RCExchangeCodeBatchService {

    /**
     * 按照项目、批次名称分页查询批次信息
     * @param projectId
     * @param name
     * @param paging
     * @return
     */
    List<RCExchangeCodeBatchDTO> getList(Integer projectId, String name, Paging paging);

    /**
     * 保存充值卡兑换码批次信息
     * @param rcExchangeCodeBatchSaveDTO
     * @return
     */
    int addSave(RCExchangeCodeBatchSaveDTO rcExchangeCodeBatchSaveDTO);

    /**
     * \根据批次id获取批次信息
     * @param id
     * @return
     */
    RCExchangeCodeBatchEditDTO getById(Integer id);

    /**
     * 保存批次信息
     * @param rcExchangeCodeBatchSaveDTO
     * @return
     */
    int editSave(RCExchangeCodeBatchSaveDTO rcExchangeCodeBatchSaveDTO);

    /**
     * 修改批次的启用禁用状态
     * @param id
     * @param status
     * @return
     */
    int changeBatchStatus(Integer id, Byte status);

    /**
     * 根据批次id生成充值卡兑换码
     * @param id
     * @return
     */
    int generateCode(Integer id);
}
