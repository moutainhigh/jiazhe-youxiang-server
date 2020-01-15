package com.jiazhe.youxiang.server.service.impl.partnerorder;

import com.jiazhe.youxiang.server.adapter.partnerorder.PartnerOrderTrackAdapter;
import com.jiazhe.youxiang.server.dao.mapper.PartnerOrderTrackPOMapper;
import com.jiazhe.youxiang.server.dao.mapper.manual.partnerorder.PartnerOrderTrackPOManualMapper;
import com.jiazhe.youxiang.server.domain.po.PartnerOrderTrackPO;
import com.jiazhe.youxiang.server.dto.partnerorder.PartnerOrderTrackDTO;
import com.jiazhe.youxiang.server.dto.sysuser.SysUserDTO;
import com.jiazhe.youxiang.server.service.partnerorder.PartnerOrderTrackService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description: java类作用描述
 * @Author: zwx
 * @CreateDate: 2019/11/23 13:27
 */
@Service("partnerOrderTrackService")
public class PartnerOrderTrackServiceImpl implements PartnerOrderTrackService {

    @Autowired
    private PartnerOrderTrackPOMapper partnerOrderTrackPOMapper;
    @Autowired
    private PartnerOrderTrackPOManualMapper partnerOrderTrackPOManualMapper;

    @Override
    public List<PartnerOrderTrackDTO> getList(Integer orderId) {
        List<PartnerOrderTrackPO> list = partnerOrderTrackPOManualMapper.getList(orderId);
        return list.stream().map(PartnerOrderTrackAdapter::PO2DTO).collect(Collectors.toList());
    }

    @Override
    public void create(PartnerOrderTrackDTO orderTrackDTO) {
        PartnerOrderTrackPO orderTrackPO = PartnerOrderTrackAdapter.DTO2PO(orderTrackDTO);
        SysUserDTO sysUserDTO = (SysUserDTO) SecurityUtils.getSubject().getPrincipal();
        orderTrackPO.setUsername(sysUserDTO.getLoginName());
        partnerOrderTrackPOMapper.insertSelective(orderTrackPO);
    }
}
