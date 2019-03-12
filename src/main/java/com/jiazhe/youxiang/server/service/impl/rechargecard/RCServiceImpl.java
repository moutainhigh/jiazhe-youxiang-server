package com.jiazhe.youxiang.server.service.impl.rechargecard;

import com.jiazhe.youxiang.base.util.DateUtil;
import com.jiazhe.youxiang.server.adapter.rechargecard.RCAdapter;
import com.jiazhe.youxiang.server.common.constant.CommonConstant;
import com.jiazhe.youxiang.server.common.enums.LoginCodeEnum;
import com.jiazhe.youxiang.server.common.enums.RechargeCardCodeEnum;
import com.jiazhe.youxiang.server.common.exceptions.LoginException;
import com.jiazhe.youxiang.server.common.exceptions.RechargeCardException;
import com.jiazhe.youxiang.server.dao.mapper.RechargeCardPOMapper;
import com.jiazhe.youxiang.server.dao.mapper.manual.rechargecard.RCPOManualMapper;
import com.jiazhe.youxiang.server.domain.po.RechargeCardExchangeRecordPO;
import com.jiazhe.youxiang.server.domain.po.RechargeCardPO;
import com.jiazhe.youxiang.server.domain.po.RechargeCardPOExample;
import com.jiazhe.youxiang.server.dto.customer.CustomerDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rc.RCDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rc.RCEditDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchEditDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchSaveDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangerecord.RCExchangeRecordDTO;
import com.jiazhe.youxiang.server.dto.sysuser.SysUserDTO;
import com.jiazhe.youxiang.server.service.CustomerService;
import com.jiazhe.youxiang.server.service.rechargecard.RCExchangeCodeBatchService;
import com.jiazhe.youxiang.server.service.rechargecard.RCExchangeRecordService;
import com.jiazhe.youxiang.server.service.rechargecard.RCService;
import com.jiazhe.youxiang.server.vo.Paging;
import org.apache.logging.log4j.util.Strings;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author TU
 * @description
 * @date 2018/10/23.
 */
@Service("rcService")
public class RCServiceImpl implements RCService {

    @Autowired
    private RechargeCardPOMapper rechargeCardPOMapper;
    @Autowired
    private RCPOManualMapper rcPOManualMapper;
    @Autowired
    private RCExchangeCodeBatchService rcExchangeCodeBatchService;
    @Autowired
    private RCService rcService;
    @Autowired
    private RCExchangeRecordService rcExchangeRecordService;
    @Autowired
    private CustomerService customerService;

    @Override
    public void changeStatus(Integer id, Byte status) {
        RechargeCardPO rechargeCardPO = rechargeCardPOMapper.selectByPrimaryKey(id);
        rechargeCardPO.setStatus(status);
        rechargeCardPO.setModTime(new Date());
        rechargeCardPOMapper.updateByPrimaryKeySelective(rechargeCardPO);
    }

    @Override
    public void changeExpiryTime(Integer id, Date expiryTime) {
        RechargeCardPO rechargeCardPO = rechargeCardPOMapper.selectByPrimaryKey(id);
        rechargeCardPO.setExpiryTime(expiryTime);
        rechargeCardPO.setModTime(new Date());
        rechargeCardPOMapper.updateByPrimaryKeySelective(rechargeCardPO);
    }

    @Override
    public void insert(RechargeCardPO rechargeCardPO) {
        rcPOManualMapper.insert(rechargeCardPO);
    }

    @Override
    public void update(RechargeCardPO rechargeCardPO) {
        rechargeCardPOMapper.updateByPrimaryKeySelective(rechargeCardPO);
    }

    @Override
    public List<RCDTO> findUnexpiredByCustomerId(Integer customerId) {
        RechargeCardPOExample rechargeCardPOExample = new RechargeCardPOExample();
        RechargeCardPOExample.Criteria criteria = rechargeCardPOExample.createCriteria();
        criteria.andCustomerIdEqualTo(customerId);
        criteria.andExpiryTimeGreaterThanOrEqualTo(new Date());
        List<RechargeCardPO> rechargeCardPOList = rechargeCardPOMapper.selectByExample(rechargeCardPOExample);
        List<RCDTO> rcdtoList = rechargeCardPOList.stream().map(RCAdapter::PO2DTO).collect(Collectors.toList());
        return rcdtoList;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void directCharge(Integer customerId, Integer batchId, BigDecimal faceValue) {
        CustomerDTO customerDTO = customerService.getById(customerId);
        if (null == customerDTO) {
            throw new RechargeCardException(RechargeCardCodeEnum.CUSTOMER_NOT_EXIST);
        }
        RCExchangeCodeBatchEditDTO rcExchangeCodeBatchEditDTO = rcExchangeCodeBatchService.getById(batchId);
        if(null == rcExchangeCodeBatchEditDTO){
            throw new RechargeCardException(RechargeCardCodeEnum.BATCH_NOT_EXISTED);
        }
        RechargeCardPO rechargeCardPO = new RechargeCardPO();
        //直接指定过期时间
        if (rcExchangeCodeBatchEditDTO.getExpiryType().equals(CommonConstant.RECHARGE_CARD_EXPIRY_TIME)) {
            rechargeCardPO.setEffectiveTime(rcExchangeCodeBatchEditDTO.getRechargeCardEffectiveTime());
            rechargeCardPO.setExpiryTime(rcExchangeCodeBatchEditDTO.getRechargeCardExpiryTime());
        } else {
            rechargeCardPO.setEffectiveTime(new Date(DateUtil.getFirstSecond(System.currentTimeMillis())));
            rechargeCardPO.setExpiryTime(new Date(DateUtil.getLastSecond(System.currentTimeMillis() + rcExchangeCodeBatchEditDTO.getValidityPeriod() * CommonConstant.ONE_DAY)));
        }
        rechargeCardPO.setDescription(rcExchangeCodeBatchEditDTO.getDescription());
        rechargeCardPO.setFaceValue(faceValue);
        rechargeCardPO.setBalance(faceValue);
        //暂时置为0，等生成了兑换记录再修改
        rechargeCardPO.setExchangeRecordId(0);
        rechargeCardPO.setStatus(CommonConstant.CODE_START_USING);
        rechargeCardPO.setProjectId(rcExchangeCodeBatchEditDTO.getProjectId());
        rechargeCardPO.setName(rcExchangeCodeBatchEditDTO.getRechargeCardName());
        rechargeCardPO.setCustomerId(customerDTO.getId());
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
        rechargeCardRecordPO.setExchangeType(CommonConstant.EXCHANGETYPE_USER_DIRECTCHARGE);
        rechargeCardRecordPO.setRechargeCardId(rechargeCardPO.getId());
        rechargeCardRecordPO.setExtInfo("");
        rechargeCardRecordPO.setIsDeleted(CommonConstant.CODE_NOT_DELETED);
        rechargeCardRecordPO.setAddTime(new Date());
        rechargeCardRecordPO.setModTime(new Date());
        rcExchangeRecordService.insert(rechargeCardRecordPO);
        //修改充值卡对应的兑换记录id
        rechargeCardPO.setExchangeRecordId(rechargeCardRecordPO.getId());
        rcService.update(rechargeCardPO);
    }

    @Override
    public void updateWithBatch(List<Integer> ids, RCExchangeCodeBatchSaveDTO batchSaveDTO) {
        List<RechargeCardPO> rcPOList = rcPOManualMapper.findByIds(ids);
        rcPOList.stream().forEach(bean -> {
            bean.setName(batchSaveDTO.getRechargeCardName());
            bean.setProjectId(batchSaveDTO.getProjectId());
            bean.setCityCodes(batchSaveDTO.getCityCodes());
            bean.setProductIds(batchSaveDTO.getProductIds());
        });
        rcPOManualMapper.batchUpdate(rcPOList);
    }

    @Override
    public void batchChangeStatus(List<Integer> ids, Byte status) {
        Map<String, Object> map = new HashMap<String, Object>(2);
        map.put("status", status);
        map.put("ids", ids);
        rcPOManualMapper.batchChangeStatus(map);
    }

    @Override
    public List<RCDTO> getList(String mobile, Integer exchangeType, Byte status, Byte expiry, Paging paging) {
        final CustomerDTO defaultCustomerDTO = customerService.getByMobile(mobile);
        List<RechargeCardPO> rechargeCardPOList = rcPOManualMapper.query(mobile, exchangeType, status, expiry, paging.getOffset(), paging.getLimit());
        List<RCDTO> rcDTOList = rechargeCardPOList.stream().map(RCAdapter::PO2DTO).collect(Collectors.toList());
        Integer count = rcPOManualMapper.count(mobile, exchangeType, status, expiry);
        paging.setTotal(count);
        rcDTOList.stream().forEach(bean -> {
            if (Strings.isBlank(mobile)) {
                CustomerDTO customerDTO = customerService.getById(bean.getCustomerId());
                bean.setCustomerDTO(customerDTO);
            } else {
                bean.setCustomerDTO(defaultCustomerDTO);
            }
            RCExchangeRecordDTO rcExchangeRecordDTO = rcExchangeRecordService.findByCardId(bean.getId());
            bean.setRcExchangeRecordDTO(rcExchangeRecordDTO);
        });
        return rcDTOList;
    }

    @Override
    public RCDTO getById(Integer id) {
        RechargeCardPO po = rechargeCardPOMapper.selectByPrimaryKey(id);
        return RCAdapter.PO2DTO(po);
    }

    @Override
    public void editSave(RCEditDTO dto) {
        RechargeCardPO po = rechargeCardPOMapper.selectByPrimaryKey(dto.getId());
        po.setProductIds(dto.getProductIds());
        po.setCityCodes(dto.getCityCodes());
        po.setName(dto.getName());
        po.setEffectiveTime(dto.getEffectiveTime());
        po.setExpiryTime(dto.getExpiryTime());
        po.setDescription(dto.getDescription());
        rechargeCardPOMapper.updateByPrimaryKeySelective(po);
    }

    @Override
    public void batchUpdate(List<RCDTO> rcDTOList) {
        if (!rcDTOList.isEmpty()) {
            List<RechargeCardPO> rcPOList = rcDTOList.stream().map(RCAdapter::DTO2PO).collect(Collectors.toList());
            rcPOManualMapper.batchUpdate(rcPOList);
        }
    }

    @Override
    public List<RCDTO> findByIds(List<Integer> ids) {
        List<RechargeCardPO> poList = rcPOManualMapper.findByIds(ids);
        return poList.stream().map(RCAdapter::PO2DTO).collect(Collectors.toList());
    }

    @Override
    public List<RCDTO> findByIdsInOrder(List<Integer> ids) {
        List<RechargeCardPO> poList = rcPOManualMapper.findByIdsInOrder(ids);
        return poList.stream().map(RCAdapter::PO2DTO).collect(Collectors.toList());
    }

    @Override
    public BigDecimal totalValidBalance(Integer customerId) {
        return rcPOManualMapper.totalValidBalance(customerId);
    }
}
