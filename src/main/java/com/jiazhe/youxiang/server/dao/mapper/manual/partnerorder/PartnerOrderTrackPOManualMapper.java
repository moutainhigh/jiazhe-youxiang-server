package com.jiazhe.youxiang.server.dao.mapper.manual.partnerorder;

import com.jiazhe.youxiang.server.domain.po.PartnerOrderTrackPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: zwx
 * @CreateDate: 2019/11/23 13:39
 */
public interface PartnerOrderTrackPOManualMapper {
    /**
     * 获取订单跟踪列表
     *
     * @param orderId
     * @return
     */
    List<PartnerOrderTrackPO> getList(@Param("orderId") Integer orderId);
}
