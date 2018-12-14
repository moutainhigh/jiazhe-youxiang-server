package com.jiazhe.youxiang.server.service.impl;

import com.jiazhe.youxiang.server.adapter.AuditRecordAdapter;
import com.jiazhe.youxiang.server.common.constant.CommonConstant;
import com.jiazhe.youxiang.server.common.enums.AuditRecordCodeEnum;
import com.jiazhe.youxiang.server.common.enums.CodeStatusEnum;
import com.jiazhe.youxiang.server.common.enums.LoginCodeEnum;
import com.jiazhe.youxiang.server.common.enums.RechargeCardCodeEnum;
import com.jiazhe.youxiang.server.common.exceptions.AuditRecordException;
import com.jiazhe.youxiang.server.common.exceptions.LoginException;
import com.jiazhe.youxiang.server.common.exceptions.RechargeCardException;
import com.jiazhe.youxiang.server.dao.mapper.AuditRecordPOMapper;
import com.jiazhe.youxiang.server.dao.mapper.CustomerPOMapper;
import com.jiazhe.youxiang.server.dao.mapper.manual.AuditRecordPOManualMapper;
import com.jiazhe.youxiang.server.domain.po.*;
import com.jiazhe.youxiang.server.dto.auditrecord.AuditRecordDTO;
import com.jiazhe.youxiang.server.dto.customer.CustomerAddDTO;
import com.jiazhe.youxiang.server.dto.customer.CustomerDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchEditDTO;
import com.jiazhe.youxiang.server.dto.sysuser.SysUserDTO;
import com.jiazhe.youxiang.server.service.AuditRecordService;
import com.jiazhe.youxiang.server.service.CustomerService;
import com.jiazhe.youxiang.server.service.rechargecard.RCExchangeCodeBatchService;
import com.jiazhe.youxiang.server.service.rechargecard.RCExchangeRecordService;
import com.jiazhe.youxiang.server.service.rechargecard.RCService;
import com.jiazhe.youxiang.server.vo.Paging;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.security.auth.Subject;
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
    private RCExchangeCodeBatchService rcExchangeCodeBatchService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerPOMapper customerPOMapper;
    @Autowired
    private RCExchangeRecordService rcExchangeRecordService;
    @Autowired
    private RCService rcService;

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
            CustomerPO customerPO = new CustomerPO();
            customerPO.setMobile(auditRecordPO.getCustomerMobile());
            customerPO.setName(auditRecordPO.getCustomerName());
            customerPO.setRemark("");
            customerId = customerPOMapper.insertSelective(customerPO);
        } else {
            customerId = customerDTO.getId();
        }
        RCExchangeCodeBatchEditDTO rcExchangeCodeBatchEditDTO = rcExchangeCodeBatchService.getById(batchId);
        RechargeCardPO rechargeCardPO = new RechargeCardPO();
        //直接指定过期时间
        if (rcExchangeCodeBatchEditDTO.getExpiryType().equals(CommonConstant.RECHARGE_CARD_EXPIRY_TIME)) {
            rechargeCardPO.setExpiryTime(rcExchangeCodeBatchEditDTO.getRechargeCardExpiryTime());
        } else {
            rechargeCardPO.setExpiryTime(new Date(System.currentTimeMillis() + rcExchangeCodeBatchEditDTO.getValidityPeriod() * CommonConstant.ONE_DAY));
        }
        rechargeCardPO.setDescription(rcExchangeCodeBatchEditDTO.getDescription());
        rechargeCardPO.setFaceValue(auditRecordPO.getExchangeMoney().multiply(CommonConstant.exchangeRate));
        rechargeCardPO.setBalance(auditRecordPO.getExchangeMoney().multiply(CommonConstant.exchangeRate));
        //暂时置为0，等生成了兑换记录再修改
        rechargeCardPO.setExchangeRecordId(0);
        rechargeCardPO.setStatus(CodeStatusEnum.START_USING.getId().byteValue());
        rechargeCardPO.setProjectId(rcExchangeCodeBatchEditDTO.getProjectId());
        rechargeCardPO.setName(rcExchangeCodeBatchEditDTO.getRechargeCardName());
        rechargeCardPO.setCustomerId(customerId);
        rechargeCardPO.setCityCodes(rcExchangeCodeBatchEditDTO.getCityCodes());
        rechargeCardPO.setProductIds(rcExchangeCodeBatchEditDTO.getProductIds());
        rcService.insert(rechargeCardPO);
        //插入兑换记录信息
        SysUserDTO sysUserDTO = (SysUserDTO) SecurityUtils.getSubject().getPrincipal();
        if (null == sysUserDTO) {
            throw new LoginException(LoginCodeEnum.LOGIN_NOT_SIGNIN_IN);
        }
        RechargeCardExchangeRecordPO rechargeCardRecordPO = new RechargeCardExchangeRecordPO();
        rechargeCardRecordPO.setOperatorId(sysUserDTO.getId());
        rechargeCardRecordPO.setOperatorName(sysUserDTO.getLoginName());
        rechargeCardRecordPO.setExchangeType(CommonConstant.EXCHANGETYPE_AUDITRECORD_PASS);
        rechargeCardRecordPO.setRechargeCardId(rechargeCardPO.getId());
        rechargeCardRecordPO.setExtInfo("");
        rechargeCardRecordPO.setIsDeleted(Byte.valueOf("0"));
        rechargeCardRecordPO.setAddTime(new Date());
        rechargeCardRecordPO.setModTime(new Date());
        rcExchangeRecordService.insert(rechargeCardRecordPO);
        //修改充值卡对应的兑换记录id
        rechargeCardPO.setExchangeRecordId(rechargeCardRecordPO.getId());
        rcService.update(rechargeCardPO);
        auditRecordPO.setVersion(version + 1);
        auditRecordPO.setAuditorId(sysUserDTO.getId());
        auditRecordPO.setAuditorName(sysUserDTO.getDisplayName());
        auditRecordPO.setAuditTime(new Date());
        auditRecordPO.setRechargeCardId(rechargeCardPO.getId());
        auditRecordPO.setStatus(Byte.valueOf("2"));
        auditRecordPOMapper.updateByPrimaryKeySelective(auditRecordPO);
    }

    @Override
    public void addSave(String customerName, String customerMobile, BigDecimal exchangeMoney, String imgUrls) {
        AuditRecordPO auditRecordPO = new AuditRecordPO();
        auditRecordPO.setRechargeCardId(0);
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