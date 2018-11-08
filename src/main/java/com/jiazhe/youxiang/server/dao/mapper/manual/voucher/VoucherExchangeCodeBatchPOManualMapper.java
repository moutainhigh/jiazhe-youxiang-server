package com.jiazhe.youxiang.server.dao.mapper.manual.voucher;

import com.jiazhe.youxiang.server.domain.po.RechargeCardExchangeCodeBatchPO;
import com.jiazhe.youxiang.server.domain.po.VoucherExchangeCodeBatchPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author tu
 * @description：
 * @date 2018/11/03
 */
public interface VoucherExchangeCodeBatchPOManualMapper {

    /**
     * 计数
     * @param projectId
     * @param name
     * @return
     */
    Integer count(@Param("projectId") Integer projectId, @Param("name") String name);

    /**
     * 分页查询
     * @param projectId
     * @param name
     * @param offset
     * @param limit
     * @return
     */
    List<VoucherExchangeCodeBatchPO> query(@Param("projectId") Integer projectId, @Param("name") String name, @Param("offset") Integer offset, @Param("limit") Integer limit);
}
