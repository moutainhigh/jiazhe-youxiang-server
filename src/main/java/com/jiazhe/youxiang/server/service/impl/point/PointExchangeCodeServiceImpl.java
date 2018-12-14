package com.jiazhe.youxiang.server.service.impl.point;

import com.jiazhe.youxiang.server.adapter.point.PointExchangeCodeAdapter;
import com.jiazhe.youxiang.server.dao.mapper.PointExchangeCodePOMapper;
import com.jiazhe.youxiang.server.dao.mapper.manual.point.PointExchangeCodePOManualMapper;
import com.jiazhe.youxiang.server.domain.po.*;
import com.jiazhe.youxiang.server.dto.point.pointexchangecode.PointExchangeCodeDTO;
import com.jiazhe.youxiang.server.dto.point.pointexchangecodebatch.PointExchangeCodeBatchSaveDTO;
import com.jiazhe.youxiang.server.dto.point.pointexchangerecord.PointExchangeRecordDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangerecord.RCExchangeRecordDTO;
import com.jiazhe.youxiang.server.service.point.PointExchangeCodeService;
import com.jiazhe.youxiang.server.service.point.PointExchangeRecordService;
import com.jiazhe.youxiang.server.service.point.PointService;
import com.jiazhe.youxiang.server.vo.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author TU
 * @description
 * @date 2018/12/13.
 */
@Service("pointExchangeCodeService")
public class PointExchangeCodeServiceImpl implements PointExchangeCodeService {

    @Autowired
    private PointExchangeCodePOMapper pointExchangeCodePOMapper;
    @Autowired
    private PointExchangeCodePOManualMapper pointExchangeCodePOManualMapper;
    @Autowired
    private PointExchangeRecordService pointExchangeRecordService;
    @Autowired
    private PointService pointService;

    @Override
    public List<PointExchangeCodeDTO> getByBatchId(Integer id) {
        PointExchangeCodePOExample example = new  PointExchangeCodePOExample();
        PointExchangeCodePOExample.Criteria criteria = example.createCriteria();
        criteria.andIsDeletedEqualTo(Byte.valueOf("0"));
        criteria.andBatchIdEqualTo(id);
        List<PointExchangeCodePO> poList = pointExchangeCodePOMapper.selectByExample(example);
        return poList.stream().map(PointExchangeCodeAdapter::po2Dto).collect(Collectors.toList());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateWithBatch(PointExchangeCodeBatchSaveDTO batchSaveDTO) {
        PointExchangeCodePOExample example = new PointExchangeCodePOExample();
        PointExchangeCodePOExample.Criteria criteria = example.createCriteria();
        criteria.andBatchIdEqualTo(batchSaveDTO.getId());
        List<PointExchangeCodePO> poList =pointExchangeCodePOMapper.selectByExample(example);
        poList.stream().forEach(bean -> {
            bean.setBatchName(batchSaveDTO.getName());
            bean.setPointName(batchSaveDTO.getPointName());
            bean.setBatchDescription(batchSaveDTO.getDescription());
            bean.setProjectId(batchSaveDTO.getProjectId());
            bean.setCityCodes(batchSaveDTO.getCityCodes());
            bean.setProductIds(batchSaveDTO.getProductIds());
            bean.setExpiryTime(batchSaveDTO.getExpiryTime());
            bean.setExpiryType(batchSaveDTO.getExpiryType());
            bean.setPointExpiryTime(batchSaveDTO.getPointExpiryTime());
            bean.setValidityPeriod(batchSaveDTO.getValidityPeriod());
        });
        pointExchangeCodePOManualMapper.batchUpdate(poList);
        List<Integer> usedIds = poList.stream().filter(bean -> bean.getUsed().equals(Byte.valueOf("1"))).map(PointExchangeCodePO::getId).collect(Collectors.toList());
        if(!usedIds.isEmpty()){
            List<PointExchangeRecordDTO> recordDTOList = pointExchangeRecordService.findByCodeIds(usedIds);
            List<Integer> cardIds = recordDTOList.stream().map(PointExchangeRecordDTO::getPointId).collect(Collectors.toList());
            pointService.batchUpdate(cardIds,batchSaveDTO);
        }
    }

    @Override
    public void batchInsert(List<PointExchangeCodePO> pointExchangeCodePOList) {
        pointExchangeCodePOManualMapper.batchInsert(pointExchangeCodePOList);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void batchChangeStatus(Integer batchId, Byte status) {
        pointExchangeCodePOManualMapper.batchChangeStatus(batchId,status);
        PointExchangeCodePOExample example = new PointExchangeCodePOExample();
        PointExchangeCodePOExample.Criteria criteria = example.createCriteria();
        criteria.andBatchIdEqualTo(batchId);
        List<PointExchangeCodePO> poList = pointExchangeCodePOMapper.selectByExample(example);
        List<Integer> usedIds = poList.stream().filter(bean -> bean.getUsed().equals(Byte.valueOf("1"))).map(PointExchangeCodePO::getId).collect(Collectors.toList());
        if(!usedIds.isEmpty()){
            List<PointExchangeRecordDTO> recordDTOList = pointExchangeRecordService.findByCodeIds(usedIds);
            List<Integer> cardIds = recordDTOList.stream().map(PointExchangeRecordDTO::getPointId).collect(Collectors.toList());
            pointService.batchChangeStatus(cardIds,status);
        }
    }

    @Override
    public List<PointExchangeCodeDTO> getList(Integer batchId, String code, String keyt, Byte status, Byte used, Paging paging) {
        Integer count = pointExchangeCodePOManualMapper.count(batchId, code,keyt,status,used);
        List<PointExchangeCodePO> pointExchangeCodePOList = pointExchangeCodePOManualMapper.query(batchId, code,keyt,status,used, paging.getOffset(), paging.getLimit());
        paging.setTotal(count);
        return pointExchangeCodePOList.stream().map(PointExchangeCodeAdapter::po2Dto).collect(Collectors.toList());
    }
}
