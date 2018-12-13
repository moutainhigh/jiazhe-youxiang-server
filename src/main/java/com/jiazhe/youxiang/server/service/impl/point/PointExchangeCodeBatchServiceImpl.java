package com.jiazhe.youxiang.server.service.impl.point;

import com.google.common.collect.Lists;
import com.jiazhe.youxiang.base.util.GenerateCode;
import com.jiazhe.youxiang.server.adapter.point.PointExchangeCodeBatchAdapter;
import com.jiazhe.youxiang.server.adapter.rechargecard.RCExchangeCodeAdapter;
import com.jiazhe.youxiang.server.adapter.rechargecard.RCExchangeCodeBatchAdapter;
import com.jiazhe.youxiang.server.common.constant.CommonConstant;
import com.jiazhe.youxiang.server.common.enums.RechargeCardCodeEnum;
import com.jiazhe.youxiang.server.common.exceptions.RechargeCardException;
import com.jiazhe.youxiang.server.dao.mapper.PointExchangeCodeBatchPOMapper;
import com.jiazhe.youxiang.server.dao.mapper.PointExchangeCodePOMapper;
import com.jiazhe.youxiang.server.dao.mapper.RechargeCardExchangeCodeBatchPOMapper;
import com.jiazhe.youxiang.server.dao.mapper.manual.point.PointExchangeCodeBatchPOManualMapper;
import com.jiazhe.youxiang.server.dao.mapper.manual.rechargecard.RCExchangeCodeBatchPOManualMapper;
import com.jiazhe.youxiang.server.domain.po.*;
import com.jiazhe.youxiang.server.dto.point.pointexchangecodebatch.PointExchangeCodeBatchDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecode.RCExchangeCodeDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecode.RCExchangeCodeSaveDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchEditDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchSaveDTO;
import com.jiazhe.youxiang.server.service.point.PointExchangeCodeBatchService;
import com.jiazhe.youxiang.server.service.rechargecard.RCExchangeCodeBatchService;
import com.jiazhe.youxiang.server.service.rechargecard.RCExchangeCodeService;
import com.jiazhe.youxiang.server.service.rechargecard.RCExchangeRecordService;
import com.jiazhe.youxiang.server.vo.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tu
 * @description：
 * @date 2018/12/13
 */
@Service("pointExchangeCodeBatchService")
public class PointExchangeCodeBatchServiceImpl implements PointExchangeCodeBatchService {

    @Autowired
    private PointExchangeCodeBatchPOManualMapper pointExchangeCodeBatchPOManualMapper;
    @Autowired
    private PointExchangeCodeBatchPOMapper pointExchangeCodeBatchPOMapper;

    @Override
    public List<PointExchangeCodeBatchDTO> getList(Integer projectId, String name, Paging paging) {
        Integer count = pointExchangeCodeBatchPOManualMapper.count(projectId, name);
        List<PointExchangeCodeBatchPO> poList = pointExchangeCodeBatchPOManualMapper.query(projectId, name, paging.getOffset(), paging.getLimit());
        paging.setTotal(count);
        return poList.stream().map(PointExchangeCodeBatchAdapter::po2Dto).collect(Collectors.toList());
    }

    @Override
    public List<PointExchangeCodeBatchDTO> getVirtualByProjectId(Integer id) {
        PointExchangeCodeBatchPOExample example = new PointExchangeCodeBatchPOExample();
        PointExchangeCodeBatchPOExample.Criteria criteria = example.createCriteria();
        criteria.andProjectIdEqualTo(id);
        criteria.andStatusEqualTo(Byte.valueOf("1"));
        criteria.andIsDeletedEqualTo(Byte.valueOf("0"));
        criteria.andIsVirtualEqualTo(CommonConstant.BATCH_IS_VIRTUAL);
        List<PointExchangeCodeBatchPO> poList = pointExchangeCodeBatchPOMapper.selectByExample(example);
        List<PointExchangeCodeBatchPO> validBatchList = Lists.newArrayList();
        //筛选出转为充值卡不过期的批次
        poList.stream().forEach(bean -> {
            if (bean.getExpiryTime().getTime() > System.currentTimeMillis()) {
                if (bean.getExpiryType().equals(CommonConstant.POINT_EXPIRY_TIME) && bean.getPointExpiryTime().getTime() > System.currentTimeMillis()) {
                    validBatchList.add(bean);
                }
                if (bean.getExpiryType().equals(CommonConstant.POINT_EXPIRY_PERIOD) && bean.getValidityPeriod() > 0) {
                    validBatchList.add(bean);
                }
            }
        });
        return validBatchList.stream().map(PointExchangeCodeBatchAdapter::po2Dto).collect(Collectors.toList());
    }
}
