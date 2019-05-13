package com.jiazhe.youxiang.server.service.impl.material;

import com.jiazhe.youxiang.server.dao.mapper.manual.material.MaterialInfoPOManualMapper;
import com.jiazhe.youxiang.server.dto.material.MaterialSummaryDto;
import com.jiazhe.youxiang.server.service.SysUserService;
import com.jiazhe.youxiang.server.service.material.MaterialService;
import com.jiazhe.youxiang.server.vo.Paging;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author TU
 * @description
 * @date 2019-05-13.
 */
@Service("materialService")
public class MaterialServiceImpl implements MaterialService {

    @Autowired
    private MaterialInfoPOManualMapper materialInfoPOManualMapper;
    @Autowired
    private SysUserService sysUserService;

    @Override
    public List<MaterialSummaryDto> getSummaryList(String payerIds, String payeeIds, Paging paging) {
        if(!Strings.isEmpty(payerIds)){
            payerIds = "(" + payerIds +")";
        }
        if(!Strings.isEmpty(payeeIds)){
            payeeIds = "(" + payeeIds +")";
        }
        Integer count = sysUserService.getCountByIds(payeeIds);
        paging.setTotal(count);
        List<MaterialSummaryDto> dtoList = materialInfoPOManualMapper.getSummaryList(payerIds,payeeIds,paging.getOffset(),paging.getLimit());
        return dtoList;
    }
}
