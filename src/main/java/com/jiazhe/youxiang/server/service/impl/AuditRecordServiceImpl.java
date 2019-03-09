package com.jiazhe.youxiang.server.service.impl;

import com.jiazhe.youxiang.base.util.CommonValidator;
import com.jiazhe.youxiang.base.util.DateUtil;
import com.jiazhe.youxiang.server.adapter.AuditRecordAdapter;
import com.jiazhe.youxiang.server.common.constant.CommonConstant;
import com.jiazhe.youxiang.server.common.enums.AuditRecordCodeEnum;
import com.jiazhe.youxiang.server.common.enums.CodeStatusEnum;
import com.jiazhe.youxiang.server.common.enums.LoginCodeEnum;
import com.jiazhe.youxiang.server.common.enums.PointCodeEnum;
import com.jiazhe.youxiang.server.common.exceptions.AuditRecordException;
import com.jiazhe.youxiang.server.common.exceptions.LoginException;
import com.jiazhe.youxiang.server.common.exceptions.PointException;
import com.jiazhe.youxiang.server.dao.mapper.AuditRecordPOMapper;
import com.jiazhe.youxiang.server.dao.mapper.CustomerPOMapper;
import com.jiazhe.youxiang.server.dao.mapper.manual.AuditRecordPOManualMapper;
import com.jiazhe.youxiang.server.domain.po.*;
import com.jiazhe.youxiang.server.dto.auditrecord.AuditRecordDTO;
import com.jiazhe.youxiang.server.dto.customer.CustomerAddDTO;
import com.jiazhe.youxiang.server.dto.customer.CustomerDTO;
import com.jiazhe.youxiang.server.dto.point.pointexchangecode.PointExchangeCodeDTO;
import com.jiazhe.youxiang.server.dto.point.pointexchangecodebatch.PointExchangeCodeBatchEditDTO;
import com.jiazhe.youxiang.server.dto.sysuser.SysUserDTO;
import com.jiazhe.youxiang.server.service.AuditRecordService;
import com.jiazhe.youxiang.server.service.CustomerService;
import com.jiazhe.youxiang.server.service.point.PointExchangeCodeBatchService;
import com.jiazhe.youxiang.server.service.point.PointExchangeCodeService;
import com.jiazhe.youxiang.server.service.point.PointExchangeRecordService;
import com.jiazhe.youxiang.server.service.point.PointService;
import com.jiazhe.youxiang.server.vo.Paging;
import org.apache.logging.log4j.util.Strings;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author TU
 * @description
 * @date 2018/11/20.
 */
@Service("auditRecordService")
public class AuditRecordServiceImpl implements AuditRecordService {

    @Autowired
    private AuditRecordPOMapper auditRecordPOMapper;
    @Autowired
    private AuditRecordPOManualMapper auditRecordPOManualMapper;
    @Autowired
    private PointExchangeCodeBatchService pointExchangeCodeBatchService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private PointExchangeCodeService pointExchangeCodeService;
    @Autowired
    private PointExchangeRecordService pointExchangeRecordService;
    @Autowired
    private PointService pointService;

    @Override
    public List<AuditRecordDTO> getList(String customerMobile, Integer submitterId, Byte status, Paging paging) {
        Integer count = auditRecordPOManualMapper.count(customerMobile, submitterId, status);
        List<AuditRecordPO> auditRecordPOList = auditRecordPOManualMapper.query(customerMobile, submitterId, status, paging.getOffset(), paging.getLimit());
        paging.setTotal(count);
        return auditRecordPOList.stream().map(AuditRecordAdapter::PO2DTO).collect(Collectors.toList());
    }

    @Override
    public AuditRecordDTO getById(Integer id) {
        AuditRecordPO po = auditRecordPOMapper.selectByPrimaryKey(id);
        return AuditRecordAdapter.PO2DTO(po);
    }

    @Override
    public Integer getCountByStatus(Byte status) {
        return auditRecordPOManualMapper.count(null, null, status);
    }

    @Override
    public void auditRecordUnpass(Integer auditRecordId, Integer version, String reason) {
        AuditRecordPO auditRecordPO = auditRecordPOMapper.selectByPrimaryKey(auditRecordId);
        if (!auditRecordPO.getVersion().equals(version)) {
            throw new AuditRecordException(AuditRecordCodeEnum.VERSION_IS_CHANGED);
        }
        SysUserDTO sysUserDTO = (SysUserDTO) SecurityUtils.getSubject().getPrincipal();
        if (null == sysUserDTO) {
            throw new LoginException(LoginCodeEnum.LOGIN_NOT_SIGNIN_IN);
        }
        auditRecordPO.setVersion(version + 1);
        auditRecordPO.setAuditorId(sysUserDTO.getId());
        auditRecordPO.setAuditorName(sysUserDTO.getDisplayName());
        auditRecordPO.setAuditTime(new Date());
        auditRecordPO.setStatus(CommonConstant.AUDIT_RECORD_REJECT);
        auditRecordPO.setAuditReason(reason);
        auditRecordPOMapper.updateByPrimaryKeySelective(auditRecordPO);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void auditRecordPass(Integer auditRecordId, Integer version, Integer exchangeBatchId) {
        AuditRecordPO auditRecordPO = auditRecordPOMapper.selectByPrimaryKey(auditRecordId);
        if (!auditRecordPO.getVersion().equals(version)) {
            throw new AuditRecordException(AuditRecordCodeEnum.VERSION_IS_CHANGED);
        }
        //插入兑换记录信息
        SysUserDTO sysUserDTO = (SysUserDTO) SecurityUtils.getSubject().getPrincipal();
        if (null == sysUserDTO) {
            throw new LoginException(LoginCodeEnum.LOGIN_NOT_SIGNIN_IN);
        }
        if (auditRecordPO.getExchangeType().equals(CommonConstant.DIRECT_CHARGE)) {//直接充值
            String pointIds = "";
            Integer customerId = 0;
            CustomerDTO customerDTO = customerService.getByMobile(auditRecordPO.getCustomerMobile());
            if (null == customerDTO) {
                CustomerAddDTO customerAddDTO = new CustomerAddDTO();
                customerAddDTO.setMobile(auditRecordPO.getCustomerMobile());
                customerAddDTO.setName(auditRecordPO.getCustomerName());
                customerService.add(customerAddDTO);
                customerDTO = customerService.getByMobile(auditRecordPO.getCustomerMobile());
                customerId = customerDTO.getId();
            } else {
                customerId = customerDTO.getId();
            }
            if (auditRecordPO.getExchangePoint().compareTo(BigDecimal.ZERO) == 1) {
                CommonValidator.validateNull(exchangeBatchId, new AuditRecordException(AuditRecordCodeEnum.EXCHANGE_BATCH_IS_NULL));
                PointExchangeCodeBatchEditDTO exchangeBatchEditDTO = pointExchangeCodeBatchService.getById(exchangeBatchId);
                PointPO pointPO = new PointPO();
                //直接指定过期时间
                if (exchangeBatchEditDTO.getExpiryType().equals(CommonConstant.POINT_EXPIRY_TIME)) {
                    pointPO.setEffectiveTime(exchangeBatchEditDTO.getPointEffectiveTime());
                    pointPO.setExpiryTime(exchangeBatchEditDTO.getPointExpiryTime());
                }
                //自兑换时间起，有效期天数
                if (exchangeBatchEditDTO.getExpiryType().equals(CommonConstant.POINT_EXCHANGE_PERIOD)) {
                    pointPO.setEffectiveTime(new Date(DateUtil.getFirstSecond(System.currentTimeMillis())));
                    pointPO.setExpiryTime(new Date(DateUtil.getLastSecond(System.currentTimeMillis() + exchangeBatchEditDTO.getValidityPeriod() * CommonConstant.ONE_DAY)));
                }
                //自激活时间起，有效期天数
                if (exchangeBatchEditDTO.getExpiryType().equals(CommonConstant.POINT_ACTIVE_PERIOD)) {
                    pointPO.setEffectiveTime(new Date(DateUtil.getFirstSecond(exchangeBatchEditDTO.getPointEffectiveTime().getTime())));
                    pointPO.setExpiryTime(new Date(DateUtil.getLastSecond(exchangeBatchEditDTO.getPointEffectiveTime().getTime() + exchangeBatchEditDTO.getValidityPeriod() * CommonConstant.ONE_DAY)));
                }
                pointPO.setDescription(exchangeBatchEditDTO.getDescription());
                pointPO.setFaceValue(auditRecordPO.getExchangePoint());
                pointPO.setBalance(auditRecordPO.getExchangePoint());
                //暂时置为0，等生成了兑换记录再修改
                pointPO.setExchangeRecordId(0);
                pointPO.setStatus(CodeStatusEnum.START_USING.getId().byteValue());
                pointPO.setProjectId(exchangeBatchEditDTO.getProjectId());
                pointPO.setName(exchangeBatchEditDTO.getPointName());
                pointPO.setCustomerId(customerId);
                pointPO.setCityCodes(exchangeBatchEditDTO.getCityCodes());
                pointPO.setProductIds(exchangeBatchEditDTO.getProductIds());
                pointService.insert(pointPO);
                PointExchangeRecordPO pointRecordPO = new PointExchangeRecordPO();
                pointRecordPO.setOperatorId(sysUserDTO.getId());
                pointRecordPO.setOperatorName(sysUserDTO.getLoginName());
                pointRecordPO.setExchangeType(CommonConstant.EXCHANGETYPE_AUDITRECORD_PASS);
                pointRecordPO.setPointId(pointPO.getId());
                pointRecordPO.setExtInfo("");
                pointRecordPO.setIsDeleted(Byte.valueOf("0"));
                pointRecordPO.setAddTime(new Date());
                pointRecordPO.setModTime(new Date());
                pointExchangeRecordService.insert(pointRecordPO);
                //修改充值卡对应的兑换记录id
                pointPO.setExchangeRecordId(pointRecordPO.getId());
                pointService.update(pointPO);
                pointIds = pointIds + pointPO.getId() + ",";
                auditRecordPO.setPointIds(pointIds);
            }
        }
        if (auditRecordPO.getExchangeType().equals(CommonConstant.SELF_CHARGE)) {//兑换积分码
            if (Strings.isEmpty(auditRecordPO.getPointCodes())) {
                throw new AuditRecordException(AuditRecordCodeEnum.POINT_CODES_IS_NULL);
            }
            List<String> pointCodes= Arrays.asList(auditRecordPO.getPointCodes().split(""));
            List<PointExchangeCodeDTO> pointExchangeCodeDtoList = pointExchangeCodeService.findByCodes(pointCodes);
            pointExchangeCodeDtoList.stream().forEach(bean -> {
                PointExchangeCodeBatchEditDTO dto = pointExchangeCodeBatchService.getById(bean.getBatchId());
                if (dto.getStatus().equals(CommonConstant.CODE_STOP_USING)) {
                    throw new PointException(PointCodeEnum.BATCH_HAS_STOPPED_USING);
                }
                //激活操作，判断兑换码过期类型，若为【激活之日XX天有效】修改相应的字段
                if (bean.getExpiryType().equals(CommonConstant.POINT_EXCHANGE_PERIOD)) {
                    bean.setExpiryTime(new Timestamp(System.currentTimeMillis()));
                    bean.setPointEffectiveTime(new Timestamp(System.currentTimeMillis()));
                }
                bean.setStatus(CommonConstant.CODE_START_USING);
            });
            pointExchangeCodeService.batchUpdate(pointExchangeCodeDtoList);
        }
        if (auditRecordPO.getExchangeType().equals(CommonConstant.EXCHANGE_ENTITY)) {//兑换实物
            //啥也不干
        }
        auditRecordPO.setVersion(version + 1);
        auditRecordPO.setAuditorId(sysUserDTO.getId());
        auditRecordPO.setAuditorName(sysUserDTO.getDisplayName());
        auditRecordPO.setAuditTime(new Date());
        auditRecordPO.setStatus(CommonConstant.AUDIT_RECORD_PASS);
        auditRecordPOMapper.updateByPrimaryKeySelective(auditRecordPO);
    }

    @Override
    public void save(AuditRecordDTO auditRecordDTO) {
        AuditRecordPO auditRecordPO = AuditRecordAdapter.dto2Po(auditRecordDTO);
        SysUserDTO sysUserDTO = (SysUserDTO) SecurityUtils.getSubject().getPrincipal();
        if (null == sysUserDTO) {
            throw new LoginException(LoginCodeEnum.LOGIN_NOT_SIGNIN_IN);
        }
        auditRecordPO.setSubmitterId(sysUserDTO.getId());
        auditRecordPO.setSubmitterName(sysUserDTO.getDisplayName());
        if (auditRecordDTO.getId().equals(0)) {
            auditRecordPO.setAuditorId(0);
            auditRecordPOMapper.insertSelective(auditRecordPO);
        } else {
            auditRecordPOMapper.updateByPrimaryKeySelective(auditRecordPO);
        }

    }
}
