package com.jiazhe.youxiang.server.biz.material;

import com.jiazhe.youxiang.server.dto.material.MaterialSummaryDto;
import com.jiazhe.youxiang.server.service.material.MaterialService;
import com.jiazhe.youxiang.server.vo.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author TU
 * @description
 * @date 2019-05-13.
 */
@Service("materialBiz")
public class MaterialBiz {

    @Autowired
    private MaterialService materialService;

    public List<MaterialSummaryDto> getSummaryList(String payerIds, String payeeIds, Paging paging) {
        return materialService.getSummaryList(payerIds,payeeIds,paging);
    }
}
