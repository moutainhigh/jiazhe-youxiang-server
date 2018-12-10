package com.jiazhe.youxiang.server.service.impl.advancepay;

import com.jiazhe.youxiang.server.adapter.advancepay.AdvancePayAdapter;
import com.jiazhe.youxiang.server.dao.mapper.AdvancePayPOMapper;
import com.jiazhe.youxiang.server.domain.po.AdvancePayPO;
import com.jiazhe.youxiang.server.domain.po.AdvancePayPOExample;
import com.jiazhe.youxiang.server.dto.advancepay.AdvancePayDTO;
import com.jiazhe.youxiang.server.service.advancepay.AdvancePayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author TU
 * @description
 * @date 2018/12/10.
 */
@Service("advancePayService")
@Transactional(rollbackFor = Exception.class)
public class AdvancePayServiceImpl implements AdvancePayService {
    @Autowired
    private AdvancePayPOMapper advancePayPOMapper;
    @Override
    public List<AdvancePayDTO> getList(Date timeStart, Date timeEnd) {
        AdvancePayPOExample aExample = new AdvancePayPOExample();
        AdvancePayPOExample.Criteria aCriteria = aExample.createCriteria();
        aCriteria.andIsDeletedEqualTo(Byte.valueOf("0"));
        if (timeStart != null) {
            aCriteria.andAdvanceTimeGreaterThan(timeStart);
        }
        if (timeEnd != null) {
            aCriteria.andAdvanceTimeLessThanOrEqualTo(timeEnd);
        }
        List<AdvancePayPO> poList = advancePayPOMapper.selectByExample(aExample);
        return poList.stream().map(AdvancePayAdapter::PO2DTO).collect(Collectors.toList());
    }
}
