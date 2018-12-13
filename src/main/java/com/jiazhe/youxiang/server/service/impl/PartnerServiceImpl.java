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

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tu
 * @description：
 * @date 2018/12/9
 */
@Service("partnerService")
public class PartnerServiceImpl implements PartnerService {
    @Autowired
    private PartnerPOMapper partnerPOMapper;

    @Override
    public PartnerDTO getById(Integer partnerId) {
        PartnerPO po = partnerPOMapper.selectByPrimaryKey(partnerId);
        return PartnerAdapter.PO2DTO(po);
    }

    @Override
    public List<PartnerDTO> getList() {
        PartnerPOExample example = new PartnerPOExample();
        PartnerPOExample.Criteria criteria = example.createCriteria();
        criteria.andIsDeletedEqualTo(Byte.valueOf("0"));
        criteria.andStatusEqualTo(Byte.valueOf("1"));
        List<PartnerPO> poList = partnerPOMapper.selectByExample(example);
        return poList.stream().map(PartnerAdapter::PO2DTO).collect(Collectors.toList());
    }
}
