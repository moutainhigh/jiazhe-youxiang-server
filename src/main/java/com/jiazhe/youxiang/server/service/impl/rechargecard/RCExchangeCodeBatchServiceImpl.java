package com.jiazhe.youxiang.server.service.impl.rechargecard;

import com.jiazhe.youxiang.server.adapter.rechargecard.RCExchangeCodeBatchAdapter;
import com.jiazhe.youxiang.server.dao.mapper.manual.rechargecard.RCExchangeCodeBatchPOManualMapper;
import com.jiazhe.youxiang.server.domain.po.RechargeCardExchangeCodeBatchPO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchListDTO;
import com.jiazhe.youxiang.server.service.rechargecard.RCExchangeCodeBatchService;
import com.jiazhe.youxiang.server.vo.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tu
 * @description：
 * @date 2018/10/21
 */
@Service("rcExchangeCodeBatchService")
public class RCExchangeCodeBatchServiceImpl implements RCExchangeCodeBatchService{

    @Autowired
    private RCExchangeCodeBatchPOManualMapper rcExchangeCodeBatchPOManualMapper;

    @Override
    public List<RCExchangeCodeBatchListDTO> getList(Integer projectId, String name, Paging paging) {
        Integer count = rcExchangeCodeBatchPOManualMapper.count(projectId,name);
        List<RechargeCardExchangeCodeBatchPO> rechargeCardExchangeCodeBatchPOList = rcExchangeCodeBatchPOManualMapper.query(projectId,name,paging.getOffset(),paging.getLimit());
        paging.setTotal(count);
        if (paging.getLimit() + paging.getOffset() >= count) {
            paging.setHasMore(false);
        }
        return rechargeCardExchangeCodeBatchPOList.stream().map(RCExchangeCodeBatchAdapter::PO2DTOList).collect(Collectors.toList());
    }
}
