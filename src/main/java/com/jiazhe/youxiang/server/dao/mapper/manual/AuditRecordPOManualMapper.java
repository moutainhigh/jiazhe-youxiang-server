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
     * @param customerMobile
     * @param customerName
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
            @Param("customerMobile") String customerMobile,
            @Param("customerName") String customerName,
            @Param("status") Byte status,
            @Param("chargeReceiptStatus") Byte chargeReceiptStatus,
            @Param("submitterName") String submitterName,
            @Param("pointCodes") String pointCodes,
            @Param("submitStartTime") Date submitStartTime,
            @Param("submitEndTime") Date submitEndTime
    );

    /**
     *
     * @param submitterId
     * @param customerMobile
     * @param customerName
     * @param status
     * @param chargeReceiptStatus
     * @param submitterName
     * @param pointCodes
     * @param submitStartTime
     * @param submitEndTime
     * @param offset
     * @param limit
     * @return
     */
    List<AuditRecordPO> query(
            @Param("submitterId") Integer submitterId,
            @Param("customerMobile") String customerMobile,
            @Param("customerName") String customerName,
            @Param("status") Byte status,
            @Param("chargeReceiptStatus") Byte chargeReceiptStatus,
            @Param("submitterName") String submitterName,
            @Param("pointCodes") String pointCodes,
            @Param("submitStartTime") Date submitStartTime,
            @Param("submitEndTime") Date submitEndTime,
            @Param("offset") Integer offset,
            @Param("limit") Integer limit
    );
}
