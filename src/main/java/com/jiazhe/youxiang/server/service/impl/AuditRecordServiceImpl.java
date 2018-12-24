package com.jiazhe.youxiang.server.service.impl;

import com.jiazhe.youxiang.server.adapter.AuditRecordAdapter;
import com.jiazhe.youxiang.server.common.constant.CommonConstant;
import com.jiazhe.youxiang.server.common.enums.AuditRecordCodeEnum;
import com.jiazhe.youxiang.server.common.enums.CodeStatusEnum;
import com.jiazhe.youxiang.server.common.enums.LoginCodeEnum;
import com.jiazhe.youxiang.server.common.exceptions.AuditRecordException;
import com.jiazhe.youxiang.server.common.exceptions.LoginException;
import com.jiazhe.youxiang.server.dao.mapper.AuditRecordPOMapper;
import com.jiazhe.youxiang.server.dao.mapper.CustomerPOMapper;
import com.jiazhe.youxiang.server.dao.mapper.manual.AuditRecordPOManualMapper;
import com.jiazhe.youxiang.server.domain.po.*;
import com.jiazhe.youxiang.server.dto.auditrecord.AuditRecordDTO;
import com.jiazhe.youxiang.server.dto.customer.CustomerAddDTO;
import com.jiazhe.youxiang.server.dto.customer.CustomerDTO;
import com.jiazhe.youxiang.server.dto.point.pointexchangecodebatch.PointExchangeCodeBatchEditDTO;
import com.jiazhe.youxiang.server.dto.sysuser.SysUserDTO;
import com.jiazhe.youxiang.server.service.AuditRecordService;
import com.jiazhe.youxiang.server.service.CustomerService;
import com.jiazhe.youxiang.server.service.point.PointExchangeCodeBatchService;
import com.jiazhe.youxiang.server.service.point.PointExchangeRecordService;
import com.jiazhe.youxiang.server.service.point.PointService;
import com.jiazhe.youxiang.server.vo.Paging;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
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
    private CustomerPOMapper customerPOMapper;
    @Autowired
    private PointExchangeRecordService pointExchangeRecordService;
    @Autowired
    private PointService pointService;

    @Override
    public List<AuditRecordDTO> getList(Integer submitterId, Byte status, Paging paging) {
        Integer count = auditRecordPOManualMapper.count(submitterId, status);
        List<AuditRecordPO> auditRecordPOList = auditRecordPOManualMapper.query(submitterId, status, paging.getOffset(), paging.getLimit());
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
        return auditRecordPOManualMapper.count(null, status);
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
        auditRecordPO.setStatus(Byte.valueOf("1"));
        auditRecordPO.setRemark(reason);
        auditRecordPOMapper.updateByPrimaryKeySelective(auditRecordPO);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void auditRecordPass(Integer auditRecordId, Integer version, Integer batchId) {
        AuditRecordPO auditRecordPO = auditRecordPOMapper.selectByPrimaryKey(auditRecordId);
        if (!auditRecordPO.getVersion().equals(version)) {
            throw new AuditRecordException(AuditRecordCodeEnum.VERSION_IS_CHANGED);
        }
        Integer customerId = 0;
        CustomerDTO customerDTO = customerService.getByMobile(auditRecordPO.getCustomerMobile());
        if (null == customerDTO) {
            //throw new AuditRecordException(AuditRecordCodeEnum.CUSTOMER_NOT_EXIST);
            CustomerAddDTO customerAddDTO = new CustomerAddDTO();
            customerAddDTO.setMobile(auditRecordPO.getCustomerMobile());
            customerAddDTO.setName(auditRecordPO.getCustomerName());
            customerService.add(customerAddDTO);
            customerDTO = customerService.getByMobile(auditRecordPO.getCustomerMobile());
            customerId = customerDTO.getId();
        } else {
            customerId = customerDTO.getId();
        }
        PointExchangeCodeBatchEditDTO pointExchangeCodeBatchEditDTO = pointExchangeCodeBatchService.getById(batchId);
        PointPO pointPO = new PointPO();
        //直接指定过期时间
        if (pointExchangeCodeBatchEditDTO.getExpiryType().equals(CommonConstant.POINT_EXPIRY_TIME)) {
            pointPO.setExpiryTime(pointExchangeCodeBatchEditDTO.getPointExpiryTime());
        } else {
            pointPO.setExpiryTime(new Date(System.currentTimeMillis() + pointExchangeCodeBatchEditDTO.getValidityPeriod() * CommonConstant.ONE_DAY));
        }
        pointPO.setDescription(pointExchangeCodeBatchEditDTO.getDescription());
        pointPO.setFaceValue(auditRecordPO.getExchangeMoney());
        pointPO.setBalance(auditRecordPO.getExchangeMoney());
        //暂时置为0，等生成了兑换记录再修改
        pointPO.setExchangeRecordId(0);
        pointPO.setStatus(CodeStatusEnum.START_USING.getId().byteValue());
        pointPO.setProjectId(pointExchangeCodeBatchEditDTO.getProjectId());
        pointPO.setName(pointExchangeCodeBatchEditDTO.getPointName());
        pointPO.setCustomerId(customerId);
        pointPO.setCityCodes(pointExchangeCodeBatchEditDTO.getCityCodes());
        pointPO.setProductIds(pointExchangeCodeBatchEditDTO.getProductIds());
        pointService.insert(pointPO);
        //插入兑换记录信息
        SysUserDTO sysUserDTO = (SysUserDTO) SecurityUtils.getSubject().getPrincipal();
        if (null == sysUserDTO) {
            throw new LoginException(LoginCodeEnum.LOGIN_NOT_SIGNIN_IN);
        }
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
        auditRecordPO.setVersion(version + 1);
        auditRecordPO.setAuditorId(sysUserDTO.getId());
        auditRecordPO.setAuditorName(sysUserDTO.getDisplayName());
        auditRecordPO.setAuditTime(new Date());
        auditRecordPO.setPointId(pointPO.getId());
        auditRecordPO.setStatus(Byte.valueOf("2"));
        auditRecordPOMapper.updateByPrimaryKeySelective(auditRecordPO);
    }

    @Override
    public void addSave(String customerName, String customerMobile, BigDecimal exchangeMoney, String imgUrls) {
        AuditRecordPO auditRecordPO = new AuditRecordPO();
        auditRecordPO.setPointId(0);
        auditRecordPO.setCustomerName(customerName);
        auditRecordPO.setCustomerMobile(customerMobile);
        auditRecordPO.setExchangeMoney(exchangeMoney);
        auditRecordPO.setImgUrls(imgUrls);
        auditRecordPO.setAddTime(new Date());
        auditRecordPO.setModTime(new Date());
        auditRecordPO.setExtInfo("");
        auditRecordPO.setIsDeleted(Byte.valueOf("0"));
        auditRecordPO.setStatus(Byte.valueOf("0"));
        auditRecordPO.setVersion(0);
        auditRecordPO.setAuditorId(0);
        auditRecordPO.setAuditorName("");
        auditRecordPO.setAuditTime(new Date());
        SysUserDTO sysUserDTO = (SysUserDTO) SecurityUtils.getSubject().getPrincipal();
        if (null == sysUserDTO) {
            throw new LoginException(LoginCodeEnum.LOGIN_NOT_SIGNIN_IN);
        }
        auditRecordPO.setSubmitterId(sysUserDTO.getId());
        auditRecordPO.setSubmitterName(sysUserDTO.getDisplayName());
        auditRecordPO.setSubmitterRemark("");
        auditRecordPO.setRemark("");
        auditRecordPOMapper.insert(auditRecordPO);
    }

    @Override
    public void editSave(Integer id, Integer version, String customerName, String customerMobile, BigDecimal exchangeMoney, String imgUrls) {
        AuditRecordPO auditRecordPO = auditRecordPOMapper.selectByPrimaryKey(id);
        if (auditRecordPO.getStatus().equals(Byte.valueOf("2"))) {
            throw new AuditRecordException(AuditRecordCodeEnum.RECORD_HASS_PASSED);
        }
        if (!auditRecordPO.getVersion().equals(version)) {
            throw new AuditRecordException(AuditRecordCodeEnum.VERSION_IS_CHANGED);
        }
        auditRecordPO.setVersion(version + 1);
        auditRecordPO.setStatus(Byte.valueOf(("0")));
        auditRecordPO.setCustomerName(customerName);
        auditRecordPO.setCustomerMobile(customerMobile);
        auditRecordPO.setExchangeMoney(exchangeMoney);
        auditRecordPO.setImgUrls(imgUrls);
        auditRecordPO.setModTime(new Date());
        auditRecordPOMapper.updateByPrimaryKey(auditRecordPO);
    }
}
