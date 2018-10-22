package com.jiazhe.youxiang.server.service.rechargecard;


import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchAddDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchListDTO;
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
    List<RCExchangeCodeBatchListDTO> getList(Integer projectId, String name, Paging paging);

    /**
     * 保存充值卡兑换码批次信息，并保存批次下的兑换码
     * @param rcExchangeCodeBatchAddDTO
     * @return
     */
    int addSave(RCExchangeCodeBatchAddDTO rcExchangeCodeBatchAddDTO);
}
