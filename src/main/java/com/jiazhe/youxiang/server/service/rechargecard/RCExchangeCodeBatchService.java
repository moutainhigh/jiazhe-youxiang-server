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
    void addSave(RCExchangeCodeBatchSaveDTO rcExchangeCodeBatchSaveDTO);

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
    void editSave(RCExchangeCodeBatchSaveDTO rcExchangeCodeBatchSaveDTO);

    /**
     * 改变批次状态，业务重点：非虚拟批次需要改变批次下兑换码兑换的充值卡状态
     * @param id
     * @param status 【0停用 1启用】
     */
    void changeBatchStatus(Integer id, Byte status);

    /**
     * 根据批次id生成充值卡兑换码
     * @param id
     * @return
     */
    void generateCode(Integer id);

    /**
     * 根据项目id查找批次
     * @param projectId
     * @return
     */
    List<RCExchangeCodeBatchDTO> getVirtualByProjectId(Integer projectId);
}
