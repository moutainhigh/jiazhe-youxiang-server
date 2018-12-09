package com.jiazhe.youxiang.server.service;

import com.jiazhe.youxiang.server.dto.partner.PartnerDTO;

/**
 * @author tu
 * @description：
 * @date 2018/12/9
 */
public interface PartnerService {

    PartnerDTO getById(Integer partnerId);
}
