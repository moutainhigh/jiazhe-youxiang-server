package com.jiazhe.youxiang.server.service.impl;

import com.jiazhe.youxiang.server.adapter.PartnerAdapter;
import com.jiazhe.youxiang.server.dao.mapper.PartnerPOMapper;
import com.jiazhe.youxiang.server.domain.po.PartnerPO;
import com.jiazhe.youxiang.server.domain.po.PartnerPOExample;
import com.jiazhe.youxiang.server.dto.partner.PartnerDTO;
import com.jiazhe.youxiang.server.service.PartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author tu
 * @description：
 * @date 2018/12/9
 */
@Service("partnerService")
@Transactional(rollbackFor = Exception.class)
public class PartnerServiceImpl implements PartnerService {
    @Autowired
    private PartnerPOMapper partnerPOMapper;

    @Override
    public PartnerDTO getById(Integer partnerId) {
        PartnerPO po = partnerPOMapper.selectByPrimaryKey(partnerId);
        return PartnerAdapter.PO2DTO(po);
    }
}
