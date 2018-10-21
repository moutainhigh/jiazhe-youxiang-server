package com.jiazhe.youxiang.server.service.rechargecard;

import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchListDTO;
import com.jiazhe.youxiang.server.vo.Paging;

import java.util.List;

/**
 * @author tu
 * @description：
 * @date 2018/10/21
 */
public interface RCExchangeCodeBatchService {

    List<RCExchangeCodeBatchListDTO> getList(Integer projectId, String name, Paging paging);

}
