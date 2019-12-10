package com.jiazhe.youxiang.server.service.partnerorder;

import com.jiazhe.youxiang.server.dto.partnerorder.PartnerOrderTrackDTO;

import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: zwx
 * @CreateDate: 2019/11/23 13:00
 */
public interface PartnerOrderTrackService {

    List<PartnerOrderTrackDTO> getList(Integer orderId);

    void create(PartnerOrderTrackDTO orderTrackDTO);

}
