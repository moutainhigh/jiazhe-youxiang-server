package com.jiazhe.youxiang.server.service.impl.point;

import com.google.common.collect.Lists;
import com.jiazhe.youxiang.base.util.GenerateCode;
import com.jiazhe.youxiang.server.adapter.point.PointExchangeCodeAdapter;
import com.jiazhe.youxiang.server.adapter.point.PointExchangeCodeBatchAdapter;
import com.jiazhe.youxiang.server.common.constant.CommonConstant;
import com.jiazhe.youxiang.server.common.enums.PointCodeEnum;
import com.jiazhe.youxiang.server.common.enums.RechargeCardCodeEnum;
import com.jiazhe.youxiang.server.common.exceptions.PointException;
import com.jiazhe.youxiang.server.common.exceptions.RechargeCardException;
import com.jiazhe.youxiang.server.dao.mapper.PointExchangeCodeBatchPOMapper;
import com.jiazhe.youxiang.server.dao.mapper.manual.point.PointExchangeCodeBatchPOManualMapper;
import com.jiazhe.youxiang.server.domain.po.PointExchangeCodeBatchPO;
import com.jiazhe.youxiang.server.domain.po.PointExchangeCodeBatchPOExample;
import com.jiazhe.youxiang.server.domain.po.PointExchangeCodePO;
import com.jiazhe.youxiang.server.dto.point.pointexchangecode.PointExchangeCodeDTO;
import com.jiazhe.youxiang.server.dto.point.pointexchangecode.PointExchangeCodeSaveDTO;
import com.jiazhe.youxiang.server.dto.point.pointexchangecodebatch.PointExchangeCodeBatchDTO;
import com.jiazhe.youxiang.server.dto.point.pointexchangecodebatch.PointExchangeCodeBatchEditDTO;
import com.jiazhe.youxiang.server.dto.point.pointexchangecodebatch.PointExchangeCodeBatchSaveDTO;
import com.jiazhe.youxiang.server.dto.project.ProjectDTO;
import com.jiazhe.youxiang.server.service.ProjectService;
import com.jiazhe.youxiang.server.service.point.PointExchangeCodeBatchService;
import com.jiazhe.youxiang.server.service.point.PointExchangeCodeService;
import com.jiazhe.youxiang.server.vo.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
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
    @Autowired
    private PointExchangeCodeService pointExchangeCodeService;
    @Autowired
    private ProjectService projectService;

    @Override
    public List<PointExchangeCodeBatchDTO> getList(Integer projectId, String name, Paging paging) {
        Integer count = pointExchangeCodeBatchPOManualMapper.count(projectId, name);
        List<PointExchangeCodeBatchPO> poList = pointExchangeCodeBatchPOManualMapper.query(projectId, name, paging.getOffset(), paging.getLimit());
        List<PointExchangeCodeBatchDTO> dtoList = poList.stream().map(PointExchangeCodeBatchAdapter::po2Dto).collect(Collectors.toList());
        dtoList.stream().forEach(bean -> {
            ProjectDTO projectDTO = projectService.getById(bean.getProjectId());
            bean.setProjectDTO(projectDTO);
        });
        paging.setTotal(count);
        return dtoList;
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
        //筛选出转为积分卡不过期的批次
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

    @Override
    public void addSave(PointExchangeCodeBatchSaveDTO pointExchangeCodeBatchSaveDTO) {
        PointExchangeCodeBatchPO po = PointExchangeCodeBatchAdapter.dtoSave2Po(pointExchangeCodeBatchSaveDTO);
        po.setIsMade(Byte.valueOf("0"));
        po.setStatus(Byte.valueOf("1"));
        pointExchangeCodeBatchPOMapper.insertSelective(po);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void editSave(PointExchangeCodeBatchSaveDTO batchSaveDTO) {
        PointExchangeCodeBatchPO batchPO = pointExchangeCodeBatchPOMapper.selectByPrimaryKey(batchSaveDTO.getId());
        if (null == batchPO) {
            throw new PointException(PointCodeEnum.BATCH_NOT_EXISTED);
        }
        //批次肯定要修改的信息
        batchPO.setName(batchSaveDTO.getName());
        batchPO.setPointName(batchSaveDTO.getPointName());
        batchPO.setProjectId(batchSaveDTO.getProjectId());
        batchPO.setCityCodes(batchSaveDTO.getCityCodes());
        batchPO.setProductIds(batchSaveDTO.getProductIds());
        batchPO.setExpiryTime(batchSaveDTO.getExpiryTime());
        batchPO.setExpiryType(batchSaveDTO.getExpiryType());
        batchPO.setPointEffectiveTime(batchSaveDTO.getPointEffectiveTime());
        batchPO.setPointExpiryTime(batchSaveDTO.getPointExpiryTime());
        batchPO.setValidityPeriod(batchSaveDTO.getValidityPeriod());
        batchPO.setDescription(batchSaveDTO.getDescription());
        batchPO.setExtInfo(batchSaveDTO.getExtInfo());
        //不是虚拟批次，要修改批次、码、充值卡的信息
        if (!batchPO.getIsVirtual().equals(CommonConstant.BATCH_IS_VIRTUAL)) {
            //批次下面是否有码，有则为true
            List<PointExchangeCodeDTO> codeDTOList = pointExchangeCodeService.getByBatchId(batchSaveDTO.getId());
            boolean batchEmpty = codeDTOList.isEmpty();
            //没有码则可以修改面额和数量
            if (batchEmpty) {
                batchPO.setAmount(batchSaveDTO.getAmount());
                batchPO.setFaceValue(batchSaveDTO.getFaceValue());
            } else {
                pointExchangeCodeService.updateWithBatch(batchSaveDTO);
            }
        }
        pointExchangeCodeBatchPOMapper.updateByPrimaryKeySelective(batchPO);
    }

    @Override
    public PointExchangeCodeBatchEditDTO getById(Integer id) {
        PointExchangeCodeBatchPO pointExchangeCodeBatchPO = pointExchangeCodeBatchPOMapper.selectByPrimaryKey(id);
        if (null == pointExchangeCodeBatchPO) {
            throw new PointException(PointCodeEnum.BATCH_NOT_EXISTED);
        }
        PointExchangeCodeBatchEditDTO pointExchangeCodeBatchEditDTO = PointExchangeCodeAdapter.po2DtoEdit(pointExchangeCodeBatchPO);
        return pointExchangeCodeBatchEditDTO;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void generateCode(Integer id) {
        PointExchangeCodeBatchPO batchPO = pointExchangeCodeBatchPOMapper.selectByPrimaryKey(id);
        if (null == batchPO) {
            throw new PointException(PointCodeEnum.BATCH_NOT_EXISTED);
        }
        if (batchPO.getIsVirtual().equals(CommonConstant.BATCH_IS_VIRTUAL)) {
            throw new PointException(PointCodeEnum.VIRTUAL_BATCH_CANNOT_GENERATE);
        }
        if (batchPO.getIsMade().equals(CommonConstant.EXCHANGE_CODE_HAS_MADE)) {
            throw new PointException(PointCodeEnum.CODE_GENERATED);
        }
        //实际去查一下，批次下是否有兑换码
        List<PointExchangeCodeDTO> pointExchangeCodeDTOList = pointExchangeCodeService.getByBatchId(id);
        if (!pointExchangeCodeDTOList.isEmpty()) {
            throw new RechargeCardException(RechargeCardCodeEnum.CODE_GENERATED);
        }
        batchPO.setIsMade(CommonConstant.EXCHANGE_CODE_HAS_MADE);
        pointExchangeCodeBatchPOMapper.updateByPrimaryKeySelective(batchPO);
        List<PointExchangeCodeSaveDTO> pointExchangeCodeSaveDTOS = Lists.newArrayList();
        Integer amount = batchPO.getAmount();
//        Integer maxId = pointExchangeCodeService.getMaxId();
//        String[][] codeAndKeyts = GenerateCode.generateCode(maxId, CommonConstant.POINT_EXCHANGE_CODE_PREFIX, amount);
        for (int i = 0; i < amount; i++) {
            PointExchangeCodeSaveDTO pointExchangeCodeSaveDTO = new PointExchangeCodeSaveDTO();
            pointExchangeCodeSaveDTO.setBatchId(batchPO.getId());
            pointExchangeCodeSaveDTO.setBatchName(batchPO.getName());
            pointExchangeCodeSaveDTO.setPointName(batchPO.getPointName());
            pointExchangeCodeSaveDTO.setBatchDescription(batchPO.getDescription());
            pointExchangeCodeSaveDTO.setProjectId(batchPO.getProjectId());
            pointExchangeCodeSaveDTO.setCityCodes(batchPO.getCityCodes());
            pointExchangeCodeSaveDTO.setProductIds(batchPO.getProductIds());
            pointExchangeCodeSaveDTO.setCode("");
            pointExchangeCodeSaveDTO.setKeyt("");
            pointExchangeCodeSaveDTO.setFaceValue(batchPO.getFaceValue());
            pointExchangeCodeSaveDTO.setExpiryTime(batchPO.getExpiryTime());
            pointExchangeCodeSaveDTO.setPointExpiryTime(batchPO.getPointExpiryTime());
            pointExchangeCodeSaveDTO.setValidityPeriod(batchPO.getValidityPeriod());
            pointExchangeCodeSaveDTO.setPointEffectiveTime(batchPO.getPointEffectiveTime());
            pointExchangeCodeSaveDTO.setExpiryType(batchPO.getExpiryType());
            pointExchangeCodeSaveDTO.setStatus(batchPO.getStatus());
            pointExchangeCodeSaveDTO.setUsed(Byte.valueOf("0"));
            pointExchangeCodeSaveDTOS.add(pointExchangeCodeSaveDTO);
        }
        List<PointExchangeCodePO> pointExchangeCodePOList = pointExchangeCodeSaveDTOS.stream().map(PointExchangeCodeAdapter::DtoSave2Po).collect(Collectors.toList());
        pointExchangeCodeService.batchInsert(pointExchangeCodePOList);
        List<PointExchangeCodeDTO> pointExchangeCodeDTOS = pointExchangeCodeService.getByBatchId(batchPO.getId());
        pointExchangeCodeDTOS.stream().forEach(bean -> {
            Map map = GenerateCode.generateOneCode(CommonConstant.POINT_EXCHANGE_CODE_PREFIX, bean.getId());
            bean.setCode(map.get("code").toString());
            bean.setKeyt(map.get("keyt").toString());
        });
        //此处更新code和keyt
        pointExchangeCodeService.batchUpdateCodeAndKeyt(pointExchangeCodeDTOS);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void changeBatchStatus(Integer id, Byte status) {
        PointExchangeCodeBatchPO batchPO = pointExchangeCodeBatchPOMapper.selectByPrimaryKey(id);
        if (null == batchPO) {
            throw new RechargeCardException(RechargeCardCodeEnum.BATCH_NOT_EXISTED);
        }
        batchPO.setStatus(status);
        batchPO.setModTime(new Date());
        pointExchangeCodeBatchPOMapper.updateByPrimaryKeySelective(batchPO);
        //修改改批次下的兑换码启用停用状态
        if (!batchPO.getIsVirtual().equals(CommonConstant.BATCH_IS_VIRTUAL)) {
            List<PointExchangeCodeDTO> codeDTOList = pointExchangeCodeService.getByBatchId(id);
            boolean batchEmpty = codeDTOList.isEmpty();
            //有码则修改对应的码信息
            if (!batchEmpty) {
                pointExchangeCodeService.batchChangeStatus(id, status);
            }
        }
    }

    @Override
    public Integer getBatchIdByMerchantNo(String merchantNo) {
        PointExchangeCodeBatchPOExample example = new PointExchangeCodeBatchPOExample();
        PointExchangeCodeBatchPOExample.Criteria criteria = example.createCriteria();
        criteria.andExtInfoEqualTo(merchantNo);
        criteria.andIsVirtualEqualTo(CommonConstant.BATCH_IS_VIRTUAL);
        criteria.andIsDeletedEqualTo(CommonConstant.CODE_NOT_DELETED);
        criteria.andStatusEqualTo(CommonConstant.CODE_START_USING);
        List<PointExchangeCodeBatchPO> poList = pointExchangeCodeBatchPOMapper.selectByExample(example);
        if (poList.isEmpty()) {
            throw new PointException(PointCodeEnum.QRCODE_HAS_NOT_BATCH);
        } else if (poList.size() > 1) {
            throw new PointException(PointCodeEnum.QRCODE_BATCH_ERROR);
        }
        return poList.get(0).getId();
    }

    @Override
    public BigDecimal getFaceValue(Integer batchId, String bonus) {
        PointExchangeCodeBatchPO pointExchangeCodeBatchPO = pointExchangeCodeBatchPOMapper.selectByPrimaryKey(batchId);
        if (pointExchangeCodeBatchPO == null) {
            throw new PointException(PointCodeEnum.QRCODE_HAS_NOT_BATCH);
        }
        ProjectDTO projectDTO = projectService.getById(pointExchangeCodeBatchPO.getProjectId());
        if (projectDTO == null) {
            throw new PointException(PointCodeEnum.PROJECT_IS_NULL);
        }
        BigDecimal result = new BigDecimal(bonus);
        return result.multiply(new BigDecimal(projectDTO.getPointConversionRate()));
    }

    @Override
    public boolean merchantNoIsRepeat(Integer batchId, String merchantNo) {
        if (merchantNo.isEmpty()) {
            return false;
        }
        PointExchangeCodeBatchPOExample example = new PointExchangeCodeBatchPOExample();
        PointExchangeCodeBatchPOExample.Criteria criteria = example.createCriteria();
        criteria.andExtInfoEqualTo(merchantNo);
        criteria.andIsVirtualEqualTo(CommonConstant.BATCH_IS_VIRTUAL);
        criteria.andIsDeletedEqualTo(CommonConstant.CODE_NOT_DELETED);
        criteria.andStatusEqualTo(CommonConstant.CODE_START_USING);
        List<PointExchangeCodeBatchPO> poList = pointExchangeCodeBatchPOMapper.selectByExample(example);
        if (poList.isEmpty()) {
            return false;
        } else if (poList.size() == 1) {
            if (poList.get(0).getId().equals(batchId)) {
                return false;
            } else {
                return true;
            }
        } else if (poList.size() > 1) {
            return true;
        }
        return false;
    }
}
