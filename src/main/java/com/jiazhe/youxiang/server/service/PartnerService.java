package com.jiazhe.youxiang.server.service;

import com.jiazhe.youxiang.server.dto.partner.PartnerDTO;

import java.util.List;

/**
 * @author tu
 * @description：
 * @date 2018/12/9
 */
public interface PartnerService {

    PartnerDTO getById(Integer partnerId);

    List<PartnerDTO> getList();
}
