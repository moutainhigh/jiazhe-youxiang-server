package com.jiazhe.youxiang.server.service.impl.point;

import com.google.common.collect.Lists;
import com.jiazhe.youxiang.server.adapter.point.PointExchangeRecordAdapter;
import com.jiazhe.youxiang.server.common.enums.PointCodeEnum;
import com.jiazhe.youxiang.server.common.exceptions.PointException;
import com.jiazhe.youxiang.server.dao.mapper.PointExchangeRecordPOMapper;
import com.jiazhe.youxiang.server.dao.mapper.manual.point.PointExchangeRecordPOManualMapper;
import com.jiazhe.youxiang.server.domain.po.PointExchangeRecordPO;
import com.jiazhe.youxiang.server.domain.po.PointExchangeRecordPOExample;
import com.jiazhe.youxiang.server.dto.point.pointexchangerecord.PointExchangeRecordDTO;
import com.jiazhe.youxiang.server.service.point.PointExchangeRecordService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author TU
 * @description
 * @date 2018/10/23.
 */
@Service("pointExchangeRecordService")
public class PointExchangeRecordServiceImpl implements PointExchangeRecordService {

    @Autowired
    private PointExchangeRecordPOManualMapper pointExchangeRecordPOManualMapper;
    @Autowired
    private PointExchangeRecordPOMapper pointExchangeRecordPOMapper;

    @Override
    public List<PointExchangeRecordDTO> findByCodeIds(List<Integer> codeIds) {
        List<PointExchangeRecordPO> poList = pointExchangeRecordPOManualMapper.findByCodeIds(codeIds);
        return poList.stream().map(PointExchangeRecordAdapter::po2Dto).collect(Collectors.toList());
    }

    @Override
    public void insert(PointExchangeRecordPO pointRecordPO) {
        pointExchangeRecordPOManualMapper.insert(pointRecordPO);
    }

    @Override
    public PointExchangeRecordDTO findByPointId(Integer pointId) {
        PointExchangeRecordPOExample example = new PointExchangeRecordPOExample();
        PointExchangeRecordPOExample.Criteria criteria = example.createCriteria();
        criteria.andPointIdEqualTo(pointId);
        List<PointExchangeRecordPO> poList = pointExchangeRecordPOMapper.selectByExample(example);
        if (poList.isEmpty() || poList.size() > 1) {
            throw new PointException(PointCodeEnum.CARD_2_RECORD_EXCEPTION);
        }
        PointExchangeRecordDTO dto = PointExchangeRecordAdapter.po2Dto(poList.get(0));
        return dto;
    }

    @Override
    public PointExchangeRecordDTO findByCodeId(Integer id) {
        PointExchangeRecordPOExample example = new PointExchangeRecordPOExample();
        PointExchangeRecordPOExample.Criteria criteria = example.createCriteria();
        criteria.andExchangeCodeIdEqualTo(id);
        List<PointExchangeRecordPO> poList = pointExchangeRecordPOMapper.selectByExample(example);
        if (poList.isEmpty()) {
            throw new PointException(PointCodeEnum.EXCHANGE_CODE_HAS_NOT_USED);
        }
        if (poList.size() > 1) {
            throw new PointException(PointCodeEnum.CODE_2_RECORD_EXCEPTION);
        }
        PointExchangeRecordDTO dto = PointExchangeRecordAdapter.po2Dto(poList.get(0));
        return dto;
    }

    @Override
    public boolean hasCharged(String purchaseOrderStr) {
        PointExchangeRecordPOExample example = new PointExchangeRecordPOExample();
        PointExchangeRecordPOExample.Criteria criteria = example.createCriteria();
        criteria.andExtInfoEqualTo(purchaseOrderStr);
        List<PointExchangeRecordPO> poList = pointExchangeRecordPOMapper.selectByExample(example);
        if (poList.isEmpty()) {
            return false;
        } else if (poList.size() > 1) {
            throw new PointException(PointCodeEnum.CODE_2_RECORD_EXCEPTION);
        }
        return true;
    }

    @Override
    public List<PointExchangeRecordDTO> findByExtInfos(List<String> receiptInfos) {
        List<PointExchangeRecordDTO> result = Lists.newArrayList();
        PointExchangeRecordPOExample example = new PointExchangeRecordPOExample();
        if (CollectionUtils.isEmpty(receiptInfos)) {
            return result;
        }
        receiptInfos.stream().forEach(item -> {
            PointExchangeRecordPOExample.Criteria criteria = example.createCriteria();
            criteria.andExtInfoLike("%" + item + "%");
            example.or(criteria);
        });
        List<PointExchangeRecordPO> poList = pointExchangeRecordPOMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(poList)) {
            poList.stream().forEach(item -> {
                result.add(PointExchangeRecordAdapter.po2Dto(item));
            });
        }
        return result;
    }
}
