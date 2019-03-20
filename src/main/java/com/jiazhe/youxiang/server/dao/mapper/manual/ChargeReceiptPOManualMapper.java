package com.jiazhe.youxiang.server.dao.mapper.manual;

import com.jiazhe.youxiang.server.domain.po.ChargeReceiptPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author TU
 * @description
 * @date 2019-03-19.
 */
public interface ChargeReceiptPOManualMapper {

    /**
     * @param auditRecordId
     * @return
     */
    Integer count(@Param("auditRecordId") Integer auditRecordId);

    /**
     * @param auditRecordId
     * @param offset
     * @param limit
     * @return
     */
    List<ChargeReceiptPO> query(@Param("auditRecordId") Integer auditRecordId, @Param("offset") Integer offset, @Param("limit") Integer limit);

    /**
     * 根据消费记录ids查询消费凭证
     * @param auditRecordIds
     * @return
     */
    List<ChargeReceiptPO> finByAuditRecordIds(List<Integer> auditRecordIds);
}
