package com.jiazhe.youxiang.server.dao.mapper.manual.message;

import com.jiazhe.youxiang.server.domain.po.MessagePO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author tu
 * @description：
 * @date 2019-04-07
 */
public interface MessagePOManualMapper {

    Integer count(
            @Param("status") Byte status,
            @Param("type") Byte type,
            @Param("mobile") String mobile,
            @Param("topic") String topic,
            @Param("sendStartTime") Date sendStartTime,
            @Param("sendEndTime") Date sendEndTime);

    List<MessagePO> query(
            @Param("status") Byte status,
            @Param("type") Byte type,
            @Param("mobile") String mobile,
            @Param("topic") String topic,
            @Param("sendStartTime") Date sendStartTime,
            @Param("sendEndTime") Date sendEndTime,
            @Param("offset") Integer offset,
            @Param("limit") Integer limit);
}
