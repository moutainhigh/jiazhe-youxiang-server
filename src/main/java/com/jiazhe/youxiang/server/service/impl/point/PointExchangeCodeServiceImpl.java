package com.jiazhe.youxiang.server.service.impl.point;

import com.jiazhe.youxiang.base.util.DateUtil;
import com.jiazhe.youxiang.base.util.ExchangeCodeCheckUtil;
import com.jiazhe.youxiang.server.adapter.point.PointExchangeCodeAdapter;
import com.jiazhe.youxiang.server.common.constant.CommonConstant;
import com.jiazhe.youxiang.server.common.enums.CodeStatusEnum;
import com.jiazhe.youxiang.server.common.enums.LoginCodeEnum;
import com.jiazhe.youxiang.server.common.enums.PointCodeEnum;
import com.jiazhe.youxiang.server.common.exceptions.LoginException;
import com.jiazhe.youxiang.server.common.exceptions.PointException;
import com.jiazhe.youxiang.server.dao.mapper.PointExchangeCodePOMapper;
import com.jiazhe.youxiang.server.dao.mapper.manual.point.PointExchangeCodePOManualMapper;
import com.jiazhe.youxiang.server.domain.po.PointExchangeCodePO;
import com.jiazhe.youxiang.server.domain.po.PointExchangeCodePOExample;
import com.jiazhe.youxiang.server.domain.po.PointExchangeRecordPO;
import com.jiazhe.youxiang.server.domain.po.PointPO;
import com.jiazhe.youxiang.server.dto.customer.CustomerDTO;
import com.jiazhe.youxiang.server.dto.point.pointexchangecode.PointExchangeCodeDTO;
import com.jiazhe.youxiang.server.dto.point.pointexchangecode.PointExchangeCodeEditDTO;
import com.jiazhe.youxiang.server.dto.point.pointexchangecodebatch.PointExchangeCodeBatchEditDTO;
import com.jiazhe.youxiang.server.dto.point.pointexchangecodebatch.PointExchangeCodeBatchSaveDTO;
import com.jiazhe.youxiang.server.dto.point.pointexchangerecord.PointExchangeRecordDTO;
import com.jiazhe.youxiang.server.dto.project.ProjectDTO;
import com.jiazhe.youxiang.server.dto.sysuser.SysUserDTO;
import com.jiazhe.youxiang.server.service.CustomerService;
import com.jiazhe.youxiang.server.service.ProjectService;
import com.jiazhe.youxiang.server.service.point.PointExchangeCodeBatchService;
import com.jiazhe.youxiang.server.service.point.PointExchangeCodeService;
import com.jiazhe.youxiang.server.service.point.PointExchangeRecordService;
import com.jiazhe.youxiang.server.service.point.PointService;
import com.jiazhe.youxiang.server.vo.Paging;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
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
    private PointExchangeCodeBatchService pointExchangeCodeBatchService;
    @Autowired
    private PointService pointService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ProjectService projectService;

    @Override
    public List<PointExchangeCodeDTO> getByBatchId(Integer id) {
        PointExchangeCodePOExample example = new PointExchangeCodePOExample();
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
        List<PointExchangeCodePO> poList = pointExchangeCodePOMapper.selectByExample(example);
        poList.stream().forEach(bean -> {
            if(bean.getStatus().equals(CommonConstant.CODE_NOT_USED)){
                bean.setBatchName(batchSaveDTO.getName());
                bean.setPointName(batchSaveDTO.getPointName());
                bean.setBatchDescription(batchSaveDTO.getDescription());
                bean.setProjectId(batchSaveDTO.getProjectId());
                bean.setCityCodes(batchSaveDTO.getCityCodes());
                bean.setProductIds(batchSaveDTO.getProductIds());
                bean.setExpiryTime(batchSaveDTO.getExpiryTime());
                bean.setExpiryType(batchSaveDTO.getExpiryType());
                bean.setPointEffectiveTime(batchSaveDTO.getPointEffectiveTime());
                bean.setPointExpiryTime(batchSaveDTO.getPointExpiryTime());
                bean.setValidityPeriod(batchSaveDTO.getValidityPeriod());
            }
        });
        pointExchangeCodePOManualMapper.batchUpdate(poList);
        List<Integer> usedIds = poList.stream().filter(bean -> bean.getUsed().equals(CommonConstant.CODE_HAS_USED)).map(PointExchangeCodePO::getId).collect(Collectors.toList());
        if (!usedIds.isEmpty()) {
            List<PointExchangeRecordDTO> recordDTOList = pointExchangeRecordService.findByCodeIds(usedIds);
            List<Integer> pointIds = recordDTOList.stream().map(PointExchangeRecordDTO::getPointId).collect(Collectors.toList());
            pointService.batchUpdate(pointIds, batchSaveDTO);
        }
    }

    @Override
    public void batchInsert(List<PointExchangeCodePO> pointExchangeCodePOList) {
        pointExchangeCodePOManualMapper.batchInsert(pointExchangeCodePOList);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void batchChangeStatus(Integer batchId, Byte status) {
        PointExchangeCodeBatchEditDTO dto = pointExchangeCodeBatchService.getById(batchId);
        if(status.equals(CommonConstant.CODE_START_USING)&&dto.getStatus().equals(CommonConstant.CODE_STOP_USING)){
            throw new PointException(PointCodeEnum.BATCH_HAS_STOPPED_USING);
        }
        PointExchangeCodePOExample example = new PointExchangeCodePOExample();
        PointExchangeCodePOExample.Criteria criteria = example.createCriteria();
        criteria.andBatchIdEqualTo(batchId);
        criteria.andIsDeletedEqualTo(CommonConstant.CODE_NOT_DELETED);
        criteria.andUsedEqualTo(CommonConstant.CODE_NOT_USED);
        List<PointExchangeCodePO> poList = pointExchangeCodePOMapper.selectByExample(example);
        poList.stream().forEach(bean->{
            if (bean.getExpiryType().equals(CommonConstant.POINT_ACTIVE_PERIOD)) {
                bean.setExpiryTime(new Timestamp(DateUtil.getLastSecond(System.currentTimeMillis()+bean.getValidityPeriod()*CommonConstant.ONE_DAY)));
                bean.setPointEffectiveTime(new Timestamp(DateUtil.getFirstSecond(System.currentTimeMillis())));
            }
            bean.setStatus(status);
        });
        pointExchangeCodePOManualMapper.batchUpdate(poList);
    }

    @Override
    public List<PointExchangeCodeDTO> getList(Integer batchId, String code, String keyt, Byte status, Byte used, Paging paging) {
        Integer count = pointExchangeCodePOManualMapper.count(batchId, code, keyt, status, used);
        List<PointExchangeCodePO> poList = pointExchangeCodePOManualMapper.query(batchId, code, keyt, status, used, paging.getOffset(), paging.getLimit());
        List<PointExchangeCodeDTO> dtoList = poList.stream().map(PointExchangeCodeAdapter::po2Dto).collect(Collectors.toList());
        dtoList.stream().forEach(bean -> {
            ProjectDTO projectDTO = projectService.getById(bean.getProjectId());
            bean.setProjectDTO(projectDTO);
            if (null != bean.getCustomerId()) {
                CustomerDTO customerDTO = customerService.getById(bean.getCustomerId());
                bean.setCustomerDTO(customerDTO);
            }
        });
        paging.setTotal(count);
        return dtoList;
    }

    @Override
    public void changeCodeStatus(Integer id, Byte status) {
        PointExchangeCodePO pointExchangeCodePO = pointExchangeCodePOMapper.selectByPrimaryKey(id);
        pointExchangeCodePO.setStatus(status);
        pointExchangeCodePO.setModTime(new Timestamp(System.currentTimeMillis()));
        //激活操作，判断兑换码过期类型，若为【激活之日XX天有效】修改相应的字段
        if (status.equals(CommonConstant.CODE_START_USING)) {
            PointExchangeCodeBatchEditDTO dto = pointExchangeCodeBatchService.getById(pointExchangeCodePO.getBatchId());
            if (dto.getStatus().equals(CommonConstant.CODE_STOP_USING)) {
                throw new PointException(PointCodeEnum.BATCH_HAS_STOPPED_USING);
            }
            if (pointExchangeCodePO.getExpiryType().equals(CommonConstant.POINT_ACTIVE_PERIOD)) {
                pointExchangeCodePO.setExpiryTime(new Timestamp(DateUtil.getLastSecond(System.currentTimeMillis()+pointExchangeCodePO.getValidityPeriod()*CommonConstant.ONE_DAY)));
                pointExchangeCodePO.setPointEffectiveTime(new Timestamp(DateUtil.getFirstSecond(System.currentTimeMillis())));
            }
        }
        pointExchangeCodePOMapper.updateByPrimaryKeySelective(pointExchangeCodePO);
    }

    @Override
    public PointExchangeCodeDTO getById(Integer id) {
        PointExchangeCodePO pointExchangeCodePO = pointExchangeCodePOMapper.selectByPrimaryKey(id);
        if (null == pointExchangeCodePO) {
            throw new PointException(PointCodeEnum.EXCHANGE_CODE_NOT_EXISTED);
        }
        PointExchangeCodeDTO pointExchangeCodeDTO = PointExchangeCodeAdapter.po2Dto(pointExchangeCodePO);
        return pointExchangeCodeDTO;
    }

    @Override
    public void editSave(PointExchangeCodeEditDTO dto) {
        PointExchangeCodePO po = pointExchangeCodePOMapper.selectByPrimaryKey(dto.getId());
        if (null == po) {
            throw new PointException(PointCodeEnum.EXCHANGE_CODE_NOT_EXISTED);
        }
        po.setPointName(dto.getPointName());
        po.setExpiryTime(dto.getExpiryTime());
        po.setPointEffectiveTime(dto.getPointEffectiveTime());
        po.setExpiryType(dto.getExpiryType());
        if (dto.getExpiryType().equals(CommonConstant.POINT_EXPIRY_TIME)) {
            po.setPointExpiryTime(dto.getPointExpiryTime());
        } else {
            po.setValidityPeriod(dto.getValidityPeriod());
        }
        po.setBatchDescription(dto.getBatchDescription());
        po.setCityCodes(dto.getCityCodes());
        po.setProductIds(dto.getProductIds());
        po.setModTime(new Date());
        pointExchangeCodePOMapper.updateByPrimaryKeySelective(po);
    }

    @Override
    public void codeCharge(Integer type, Integer id, String keyt) {
        if (!ExchangeCodeCheckUtil.keytCheck(CommonConstant.POINT_EXCHANGE_CODE_PREFIX, keyt)) {
            throw new PointException(PointCodeEnum.EXCHANGE_CODE_NOT_EXISTED);
        }
        PointExchangeCodePO pointExchangeCodePO = findByKeyt(keyt);
        if (null == pointExchangeCodePO) {
            throw new PointException(PointCodeEnum.EXCHANGE_CODE_NOT_EXISTED);
        }
        if (pointExchangeCodePO.getStatus().equals(CommonConstant.CODE_STOP_USING)) {
            throw new PointException(PointCodeEnum.EXCHANGE_CODE_HAS_STOPED_USING);
        }
        PointExchangeCodeBatchEditDTO pointExchangeCodeBatchEditDTO = pointExchangeCodeBatchService.getById(pointExchangeCodePO.getBatchId());
        if (pointExchangeCodeBatchEditDTO.getStatus().equals(CommonConstant.CODE_STOP_USING)) {
            throw new PointException(PointCodeEnum.BATCH_HAS_STOPPED_USING);
        }
        if (pointExchangeCodePO.getUsed().equals(CommonConstant.CODE_HAS_USED)) {
            throw new PointException(PointCodeEnum.EXCHANGE_CODE_HAS_USED);
        }
        if (pointExchangeCodePO.getExpiryTime().getTime() < System.currentTimeMillis()) {
            throw new PointException(PointCodeEnum.EXCHANGE_CODE_HAS_EXPIRIED);
        }
        CustomerDTO customerDTO = customerService.getById(id);
        if (null == customerDTO) {
            throw new PointException(PointCodeEnum.CUSTOMER_NOT_EXIST);
        }
        PointPO pointPO = new PointPO();
        //直接指定过期时间
        if (pointExchangeCodePO.getExpiryType().equals(CommonConstant.POINT_EXPIRY_TIME)) {
            pointPO.setEffectiveTime(pointExchangeCodePO.getPointEffectiveTime());
            pointPO.setExpiryTime(pointExchangeCodePO.getPointExpiryTime());
        }
        //自兑换时间起，有效期天数
        if (pointExchangeCodePO.getExpiryType().equals(CommonConstant.POINT_EXCHANGE_PERIOD)) {
            pointPO.setEffectiveTime(new Date(DateUtil.getFirstSecond(System.currentTimeMillis())));
            pointPO.setExpiryTime(new Date(DateUtil.getLastSecond(System.currentTimeMillis() + pointExchangeCodePO.getValidityPeriod() * CommonConstant.ONE_DAY)));
        }
        //自激活时间起，有效期天数
        if (pointExchangeCodePO.getExpiryType().equals(CommonConstant.POINT_ACTIVE_PERIOD)) {
            pointPO.setEffectiveTime(new Date(DateUtil.getFirstSecond(pointExchangeCodePO.getPointEffectiveTime().getTime())));
            pointPO.setExpiryTime(new Date(DateUtil.getLastSecond(pointExchangeCodePO.getExpiryTime().getTime())));
        }
        pointPO.setDescription(pointExchangeCodePO.getBatchDescription());
        pointPO.setFaceValue(pointExchangeCodePO.getFaceValue());
        pointPO.setBalance(pointExchangeCodePO.getFaceValue());
        //暂时置为0，等生成了兑换记录再修改
        pointPO.setExchangeRecordId(0);
        pointPO.setStatus(CodeStatusEnum.START_USING.getId().byteValue());
        pointPO.setProjectId(pointExchangeCodePO.getProjectId());
        pointPO.setName(pointExchangeCodePO.getPointName());
        pointPO.setCustomerId(customerDTO.getId());
        pointPO.setCityCodes(pointExchangeCodePO.getCityCodes());
        pointPO.setProductIds(pointExchangeCodePO.getProductIds());
        pointService.insert(pointPO);
        //插入兑换记录信息
        PointExchangeRecordPO pointRecordPO = new PointExchangeRecordPO();
        pointRecordPO.setExchangeCodeId(pointExchangeCodePO.getId());
        pointRecordPO.setExchangeType(type);
        pointRecordPO.setOperatorId(0);
        pointRecordPO.setOperatorName("");
        //如果后台用兑换码帮客户充值，同样记录操作人员的信息
        if (type.equals(CommonConstant.EXCHANGETYPE_USER_CODE_EXCHANGE)) {
            SysUserDTO sysUserDTO = (SysUserDTO) SecurityUtils.getSubject().getPrincipal();
            if (null == sysUserDTO) {
                throw new LoginException(LoginCodeEnum.LOGIN_NOT_SIGNIN_IN);
            }
            pointRecordPO.setOperatorId(sysUserDTO.getId());
            pointRecordPO.setOperatorName(sysUserDTO.getLoginName());
        }
        pointRecordPO.setPointId(pointPO.getId());
        pointRecordPO.setExtInfo("");
        pointRecordPO.setIsDeleted(Byte.valueOf("0"));
        pointRecordPO.setAddTime(new Date());
        pointRecordPO.setModTime(new Date());
        pointExchangeRecordService.insert(pointRecordPO);
        //修改充值卡对应的兑换记录id
        pointPO.setExchangeRecordId(pointRecordPO.getId());
        pointService.update(pointPO);
        //修改充值卡兑换码的使用状态
        pointExchangeCodePO.setUsed(CommonConstant.CODE_HAS_USED);
        pointExchangeCodePO.setCustomerId(id);
        pointExchangeCodePOMapper.updateByPrimaryKeySelective(pointExchangeCodePO);
    }

    @Override
    public PointExchangeCodePO findByKeyt(String keyt) {
        PointExchangeCodePO pointExchangeCodePO = pointExchangeCodePOManualMapper.findByKeyt(keyt);
        return pointExchangeCodePO;
    }

    @Override
    public Integer getMaxId() {
        return pointExchangeCodePOManualMapper.getMaxId();
    }

    @Override
    public void batchUpdateCodeAndKeyt(List<PointExchangeCodeDTO> pointExchangeCodeDTOS) {
        List<PointExchangeCodePO> poList = pointExchangeCodeDTOS.stream().map(PointExchangeCodeAdapter::dto2Po).collect(Collectors.toList());
        pointExchangeCodePOManualMapper.batchUpdateCodeAndKeyt(poList);
    }

    @Override
    public List<PointExchangeCodeDTO> findByCodes(List<String> codes) {
        List<PointExchangeCodePO> pointExchangeCodePOList = pointExchangeCodePOManualMapper.findByCodes(codes);
        return pointExchangeCodePOList.stream().map(PointExchangeCodeAdapter::po2Dto).collect(Collectors.toList());
    }

    @Override
    public void batchActive(List<PointExchangeCodeDTO> pointExchangeCodeDtoList) {
        pointExchangeCodePOManualMapper.batchActive(pointExchangeCodeDtoList);
    }
}
