package com.jiazhe.youxiang.server.dao.mapper.manual;

import com.jiazhe.youxiang.server.domain.po.AuditRecordPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author TU
 * @description
 * @date 2018/11/20.
 */
public interface AuditRecordPOManualMapper {
    /**
     * 计数
     *
     * @param customerMobile
     * @param submitterId
     * @param status
     * @return
     */
    Integer count(@Param("customerMobile") String customerMobile, @Param("submitterId") Integer submitterId, @Param("status") Byte status);

    /**
     * 分页查询
     *
     * @param customerMobile
     * @param status
     * @param offset
     * @param limit
     * @return
     */
    List<AuditRecordPO> query(@Param("customerMobile") String customerMobile, @Param("submitterId") Integer submitterId, @Param("status") Byte status, @Param("offset") Integer offset, @Param("limit") Integer limit);
}
