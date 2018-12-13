package com.jiazhe.youxiang.server.dao.mapper.manual.partnerorder;

import com.jiazhe.youxiang.server.domain.po.PartnerOrderInfoPO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author tu
 * @description：
 * @date 2018/12/9
 */
public interface PartnerOrderInfoPOManualMapper {
    
    Integer count(@Param("status") Byte status, @Param("customerCityCode")String customerCityCode, @Param("partnerId") Integer partnerId, @Param("serviceItemId")Integer serviceItemId, @Param("serviceTimeStart") Date serviceTimeStart, @Param("serviceTimeEnd")Date serviceTimeEnd, @Param("customerMobile") String customerMobile);

    List<PartnerOrderInfoPO> query(@Param("status")Byte status, @Param("customerCityCode")String customerCityCode,  @Param("partnerId")Integer partnerId,@Param("serviceItemId") Integer serviceItemId, @Param("serviceTimeStart")Date serviceTimeStart, @Param("serviceTimeEnd")Date serviceTimeEnd, @Param("customerMobile")String customerMobile, @Param("offset")Integer offset, @Param("limit")Integer limit);
}
