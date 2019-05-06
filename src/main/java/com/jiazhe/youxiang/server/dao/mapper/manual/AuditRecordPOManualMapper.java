package com.jiazhe.youxiang.server.dao.mapper.manual;

import com.jiazhe.youxiang.server.domain.po.AuditRecordPO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author TU
 * @description
 * @date 2018/11/20.
 */
public interface AuditRecordPOManualMapper {
    /**
     *
     * @param submitterId
     * @param customerInfo
     * @param submitterName
     * @param status
     * @param chargeReceiptStatus
     * @param submitterName
     * @param pointCodes
     * @param submitStartTime
     * @param submitEndTime
     * @return
     */
    Integer count(
            @Param("submitterId") Integer submitterId,
            @Param("customerInfo") String customerInfo,
            @Param("submitterName") String submitterName,
            @Param("status") Byte status,
            @Param("chargeReceiptStatus") Byte chargeReceiptStatus,
            @Param("pointCodes") String pointCodes,
            @Param("submitStartTime") Date submitStartTime,
            @Param("submitEndTime") Date submitEndTime
    );

    /**
     *
     * @param submitterId
     * @param customerInfo
     * @param submitterName
     * @param status
     * @param chargeReceiptStatus
     * @param pointCodes
     * @param submitStartTime
     * @param submitEndTime
     * @param offset
     * @param limit
     * @return
     */
    List<AuditRecordPO> query(
            @Param("submitterId") Integer submitterId,
            @Param("customerInfo") String customerInfo,
            @Param("submitterName") String submitterName,
            @Param("status") Byte status,
            @Param("chargeReceiptStatus") Byte chargeReceiptStatus,
            @Param("pointCodes") String pointCodes,
            @Param("submitStartTime") Date submitStartTime,
            @Param("submitEndTime") Date submitEndTime,
            @Param("offset") Integer offset,
            @Param("limit") Integer limit
    );
}
