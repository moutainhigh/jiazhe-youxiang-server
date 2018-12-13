package com.jiazhe.youxiang.server.biz.partner;

import com.jiazhe.youxiang.server.dto.partner.PartnerDTO;
import com.jiazhe.youxiang.server.service.PartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author TU
 * @description
 * @date 2018/12/11.
 */
@Service("partnerBiz")
public class PartnerBiz {

    @Autowired
    private PartnerService partnerService;

    public List<PartnerDTO> getList() {
        return partnerService.getList();
    }
}
