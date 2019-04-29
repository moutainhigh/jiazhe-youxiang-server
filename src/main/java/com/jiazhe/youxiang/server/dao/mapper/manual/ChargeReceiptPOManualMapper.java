package com.jiazhe.youxiang.server.dao.mapper.manual;

import com.jiazhe.youxiang.server.domain.po.ChargeReceiptPO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author TU
 * @description
 * @date 2019-03-19.
 */
public interface ChargeReceiptPOManualMapper {

    /**
     *
     * @param auditRecordId
     * @param customerName
     * @param cardNo
     * @param posCode
     * @param tradeStartTime
     * @param tradeEndTime
     * @return
     */
    Integer count(
            @Param("auditRecordId") Integer auditRecordId,
            @Param("customerName") String customerName,
            @Param("cardNo") String cardNo,
            @Param("posCode") String posCode,
            @Param("tradeStartTime") Date tradeStartTime,
            @Param("tradeEndTime") Date tradeEndTime
    );

    /**
     *
     * @param auditRecordId
     * @param customerName
     * @param cardNo
     * @param posCode
     * @param tradeStartTime
     * @param tradeEndTime
     * @param offset
     * @param limit
     * @return
     */
    List<ChargeReceiptPO> query(
            @Param("auditRecordId") Integer auditRecordId,
            @Param("customerName") String customerName,
            @Param("cardNo") String cardNo,
            @Param("posCode") String posCode,
            @Param("tradeStartTime") Date tradeStartTime,
            @Param("tradeEndTime") Date tradeEndTime,
            @Param("offset") Integer offset,
            @Param("limit") Integer limit);

    /**
     * 根据消费记录ids查询消费凭证
     * @param auditRecordIds
     * @return
     */
    List<ChargeReceiptPO> finByAuditRecordIds(List<Integer> auditRecordIds);
}
