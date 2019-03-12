package com.jiazhe.youxiang.server.service.impl.rechargecard;

import com.jiazhe.youxiang.base.util.DateUtil;
import com.jiazhe.youxiang.base.util.ExchangeCodeCheckUtil;
import com.jiazhe.youxiang.server.adapter.rechargecard.RCExchangeCodeAdapter;
import com.jiazhe.youxiang.server.common.constant.CommonConstant;
import com.jiazhe.youxiang.server.common.enums.LoginCodeEnum;
import com.jiazhe.youxiang.server.common.enums.RechargeCardCodeEnum;
import com.jiazhe.youxiang.server.common.exceptions.LoginException;
import com.jiazhe.youxiang.server.common.exceptions.RechargeCardException;
import com.jiazhe.youxiang.server.dao.mapper.RechargeCardExchangeCodePOMapper;
import com.jiazhe.youxiang.server.dao.mapper.manual.rechargecard.RCExchangeCodePOManualMapper;
import com.jiazhe.youxiang.server.domain.po.RechargeCardExchangeCodePO;
import com.jiazhe.youxiang.server.domain.po.RechargeCardExchangeCodePOExample;
import com.jiazhe.youxiang.server.domain.po.RechargeCardExchangeRecordPO;
import com.jiazhe.youxiang.server.domain.po.RechargeCardPO;
import com.jiazhe.youxiang.server.dto.customer.CustomerDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecode.RCExchangeCodeDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecode.RCExchangeCodeEditDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchEditDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchSaveDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangerecord.RCExchangeRecordDTO;
import com.jiazhe.youxiang.server.dto.sysuser.SysUserDTO;
import com.jiazhe.youxiang.server.service.CustomerService;
import com.jiazhe.youxiang.server.service.rechargecard.RCExchangeCodeBatchService;
import com.jiazhe.youxiang.server.service.rechargecard.RCExchangeCodeService;
import com.jiazhe.youxiang.server.service.rechargecard.RCExchangeRecordService;
import com.jiazhe.youxiang.server.service.rechargecard.RCService;
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
 * @date 2018/10/22.
 */
@Service("rcExchangeCodeService")
public class RCExchangeCodeServiceImpl implements RCExchangeCodeService {

    @Autowired
    private RCExchangeCodePOManualMapper rcExchangeCodePOManualMapper;
    @Autowired
    private RechargeCardExchangeCodePOMapper rechargeCardExchangeCodePOMapper;
    @Autowired
    private RCService rcService;
    @Autowired
    private RCExchangeRecordService rcExchangeRecordService;
    @Autowired
    private RCExchangeCodeBatchService rcExchangeCodeBatchService;
    @Autowired
    private CustomerService customerService;

    @Override
    public void batchInsert(List<RechargeCardExchangeCodePO> rechargeCardExchangeCodePOList) {
        rcExchangeCodePOManualMapper.batchInsert(rechargeCardExchangeCodePOList);
    }

    @Override
    public void changeCodeStatus(Integer id, Byte status) {
        RechargeCardExchangeCodePO rechargeCardExchangeCodePO = rechargeCardExchangeCodePOMapper.selectByPrimaryKey(id);
        if (null == rechargeCardExchangeCodePO) {
            throw new RechargeCardException(RechargeCardCodeEnum.EXCHANGE_CODE_NOT_EXISTED);
        }
        rechargeCardExchangeCodePO.setStatus(status);
        rechargeCardExchangeCodePO.setModTime(new Timestamp(System.currentTimeMillis()));
        //激活操作，判断兑换码过期类型，若为【激活之日XX天有效】修改相应的字段
        if (status.equals(CommonConstant.CODE_START_USING)) {
            RCExchangeCodeBatchEditDTO dto = rcExchangeCodeBatchService.getById(rechargeCardExchangeCodePO.getBatchId());
            if (dto.getStatus().equals(CommonConstant.CODE_STOP_USING)) {
                throw new RechargeCardException(RechargeCardCodeEnum.BATCH_HAS_STOPPED_USING);
            }
            if (rechargeCardExchangeCodePO.getExpiryType().equals(CommonConstant.RECHARGE_CARD_ACTIVE_PERIOD)) {
                rechargeCardExchangeCodePO.setExpiryTime(new Timestamp(DateUtil.getLastSecond(System.currentTimeMillis() + rechargeCardExchangeCodePO.getValidityPeriod() * CommonConstant.ONE_DAY)));
                rechargeCardExchangeCodePO.setRechargeCardExpiryTime(new Timestamp(DateUtil.getLastSecond(System.currentTimeMillis() + rechargeCardExchangeCodePO.getValidityPeriod() * CommonConstant.ONE_DAY)));
                rechargeCardExchangeCodePO.setRechargeCardEffectiveTime(new Timestamp(DateUtil.getFirstSecond(System.currentTimeMillis())));
            }
        }
        rechargeCardExchangeCodePOMapper.updateByPrimaryKeySelective(rechargeCardExchangeCodePO);
    }

    @Override
    public void changeExpiryTime(Integer id, Date expiryTime) {
        RechargeCardExchangeCodePO rechargeCardExchangeCodePO = rechargeCardExchangeCodePOMapper.selectByPrimaryKey(id);
        rechargeCardExchangeCodePO.setExpiryTime(expiryTime);
        rechargeCardExchangeCodePO.setModTime(new Date());
        rechargeCardExchangeCodePOMapper.updateByPrimaryKeySelective(rechargeCardExchangeCodePO);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void codeCharge(Integer type, Integer customerId, String keyt) {
        if (!ExchangeCodeCheckUtil.keytCheck(CommonConstant.RC_EXCHANGE_CODE_PREFIX, keyt)) {
            throw new RechargeCardException(RechargeCardCodeEnum.EXCHANGE_CODE_NOT_EXISTED);
        }
        RechargeCardExchangeCodePO rechargeCardExchangeCodePO = findByKeyt(keyt);
        if (null == rechargeCardExchangeCodePO) {
            throw new RechargeCardException(RechargeCardCodeEnum.EXCHANGE_CODE_NOT_EXISTED);
        }
        if (rechargeCardExchangeCodePO.getStatus().equals(CommonConstant.CODE_STOP_USING)) {
            throw new RechargeCardException(RechargeCardCodeEnum.EXCHANGE_CODE_HAS_STOPED_USING);
        }
        RCExchangeCodeBatchEditDTO rcExchangeCodeBatchEditDTO = rcExchangeCodeBatchService.getById(rechargeCardExchangeCodePO.getBatchId());
        if (rcExchangeCodeBatchEditDTO.getStatus().equals(CommonConstant.CODE_STOP_USING)) {
            throw new RechargeCardException(RechargeCardCodeEnum.BATCH_HAS_STOPPED_USING);
        }
        if (rechargeCardExchangeCodePO.getUsed().equals(CommonConstant.CODE_HAS_USED)) {
            throw new RechargeCardException(RechargeCardCodeEnum.EXCHANGE_CODE_HAS_USED);
        }
        if (rechargeCardExchangeCodePO.getExpiryTime().getTime() < System.currentTimeMillis()) {
            throw new RechargeCardException(RechargeCardCodeEnum.EXCHANGE_CODE_HAS_EXPIRIED);
        }
        CustomerDTO customerDTO = customerService.getById(customerId);
        if (null == customerDTO) {
            throw new RechargeCardException(RechargeCardCodeEnum.CUSTOMER_NOT_EXIST);
        }
        RechargeCardPO rechargeCardPO = new RechargeCardPO();
        //直接指定过期时间
        if (rechargeCardExchangeCodePO.getExpiryType().equals(CommonConstant.RECHARGE_CARD_EXPIRY_TIME)) {
            rechargeCardPO.setEffectiveTime(rechargeCardExchangeCodePO.getRechargeCardEffectiveTime());
            rechargeCardPO.setExpiryTime(rechargeCardExchangeCodePO.getRechargeCardExpiryTime());
        }
        if (rechargeCardExchangeCodePO.getExpiryType().equals(CommonConstant.RECHARGE_CARD_EXCHANGE_PERIOD)) {
            rechargeCardPO.setEffectiveTime(new Date(DateUtil.getFirstSecond(System.currentTimeMillis())));
            rechargeCardPO.setExpiryTime(new Date(DateUtil.getLastSecond(System.currentTimeMillis() + rechargeCardExchangeCodePO.getValidityPeriod() * CommonConstant.ONE_DAY)));
        }
        if (rechargeCardExchangeCodePO.getExpiryType().equals(CommonConstant.RECHARGE_CARD_ACTIVE_PERIOD)) {
            rechargeCardPO.setEffectiveTime(new Date(DateUtil.getFirstSecond(rechargeCardExchangeCodePO.getRechargeCardEffectiveTime().getTime())));
            rechargeCardPO.setExpiryTime(new Date(DateUtil.getLastSecond(rechargeCardExchangeCodePO.getExpiryTime().getTime())));
        }
        rechargeCardPO.setDescription(rechargeCardExchangeCodePO.getBatchDescription());
        rechargeCardPO.setFaceValue(rechargeCardExchangeCodePO.getFaceValue());
        rechargeCardPO.setBalance(rechargeCardExchangeCodePO.getFaceValue());
        //暂时置为0，等生成了兑换记录再修改
        rechargeCardPO.setExchangeRecordId(0);
        rechargeCardPO.setStatus(CommonConstant.CODE_START_USING);
        rechargeCardPO.setName(rechargeCardExchangeCodePO.getRechargeCardName());
        rechargeCardPO.setProjectId(rechargeCardExchangeCodePO.getProjectId());
        rechargeCardPO.setCustomerId(customerId);
        rechargeCardPO.setCityCodes(rechargeCardExchangeCodePO.getCityCodes());
        rechargeCardPO.setProductIds(rechargeCardExchangeCodePO.getProductIds());
        rcService.insert(rechargeCardPO);
        //插入兑换记录信息
        RechargeCardExchangeRecordPO rechargeCardRecordPO = new RechargeCardExchangeRecordPO();
        rechargeCardRecordPO.setExchangeCodeId(rechargeCardExchangeCodePO.getId());
        rechargeCardRecordPO.setExchangeType(type);
        rechargeCardRecordPO.setOperatorId(0);
        rechargeCardRecordPO.setOperatorName("");
        //如果后台用兑换码帮客户充值，同样记录操作人员的信息
        if (type.equals(CommonConstant.EXCHANGETYPE_USER_CODE_EXCHANGE)) {
            SysUserDTO sysUserDTO = (SysUserDTO) SecurityUtils.getSubject().getPrincipal();
            if (null == sysUserDTO) {
                throw new LoginException(LoginCodeEnum.LOGIN_NOT_SIGNIN_IN);
            }
            rechargeCardRecordPO.setOperatorId(sysUserDTO.getId());
            rechargeCardRecordPO.setOperatorName(sysUserDTO.getLoginName());
        }
        rechargeCardRecordPO.setRechargeCardId(rechargeCardPO.getId());
        rechargeCardRecordPO.setExtInfo("");
        rechargeCardRecordPO.setIsDeleted(CommonConstant.CODE_NOT_DELETED);
        rechargeCardRecordPO.setAddTime(new Date());
        rechargeCardRecordPO.setModTime(new Date());
        rcExchangeRecordService.insert(rechargeCardRecordPO);
        //修改充值卡对应的兑换记录id
        rechargeCardPO.setExchangeRecordId(rechargeCardRecordPO.getId());
        rcService.update(rechargeCardPO);
        //修改充值卡兑换码的使用状态
        rechargeCardExchangeCodePO.setUsed(CommonConstant.CODE_HAS_USED);
        rechargeCardExchangeCodePO.setCustomerId(customerId);
        rechargeCardExchangeCodePOMapper.updateByPrimaryKeySelective(rechargeCardExchangeCodePO);
    }

    @Override
    public List<RCExchangeCodeDTO> getByBatchId(Integer id) {
        RechargeCardExchangeCodePOExample example = new RechargeCardExchangeCodePOExample();
        RechargeCardExchangeCodePOExample.Criteria criteria = example.createCriteria();
        criteria.andIsDeletedEqualTo(Byte.valueOf("0"));
        criteria.andBatchIdEqualTo(id);
        List<RechargeCardExchangeCodePO> poList = rechargeCardExchangeCodePOMapper.selectByExample(example);
        return poList.stream().map(RCExchangeCodeAdapter::PO2DTO).collect(Collectors.toList());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateWithBatch(RCExchangeCodeBatchSaveDTO batchSaveDTO) {
        RechargeCardExchangeCodePOExample example = new RechargeCardExchangeCodePOExample();
        RechargeCardExchangeCodePOExample.Criteria criteria = example.createCriteria();
        criteria.andBatchIdEqualTo(batchSaveDTO.getId());
        criteria.andIsDeletedEqualTo(CommonConstant.CODE_NOT_DELETED);
        List<RechargeCardExchangeCodePO> poList = rechargeCardExchangeCodePOMapper.selectByExample(example);
        poList.stream().forEach(bean -> {
            bean.setBatchName(batchSaveDTO.getName());
            bean.setRechargeCardName(batchSaveDTO.getRechargeCardName());
            bean.setProjectId(batchSaveDTO.getProjectId());
            bean.setCityCodes(batchSaveDTO.getCityCodes());
            bean.setProductIds(batchSaveDTO.getProductIds());
            bean.setExpiryTime(batchSaveDTO.getExpiryTime());
            bean.setRechargeCardEffectiveTime(batchSaveDTO.getRechargeCardEffectiveTime());
            bean.setExpiryType(batchSaveDTO.getExpiryType());
            bean.setRechargeCardExpiryTime(batchSaveDTO.getRechargeCardExpiryTime());
            bean.setValidityPeriod(batchSaveDTO.getValidityPeriod());
        });
        rcExchangeCodePOManualMapper.batchUpdate(poList);
        List<Integer> usedIds = poList.stream().filter(bean -> bean.getUsed().equals(Byte.valueOf("1"))).map(RechargeCardExchangeCodePO::getId).collect(Collectors.toList());
        if (!usedIds.isEmpty()) {
            List<RCExchangeRecordDTO> recordDTOList = rcExchangeRecordService.findByCodeIds(usedIds);
            List<Integer> cardIds = recordDTOList.stream().map(RCExchangeRecordDTO::getRechargeCardId).collect(Collectors.toList());
            rcService.updateWithBatch(cardIds, batchSaveDTO);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void batchChangeStatus(Integer batchId, Byte status) {
        RCExchangeCodeBatchEditDTO dto = rcExchangeCodeBatchService.getById(batchId);
        if (null == dto) {
            throw new RechargeCardException(RechargeCardCodeEnum.BATCH_NOT_EXISTED);
        }
        //启用操作需要批次也是启用状态
        if (status.equals(CommonConstant.CODE_START_USING) && dto.getStatus().equals(CommonConstant.CODE_STOP_USING)) {
            throw new RechargeCardException(RechargeCardCodeEnum.BATCH_HAS_STOPPED_USING);
        }
        RechargeCardExchangeCodePOExample example = new RechargeCardExchangeCodePOExample();
        RechargeCardExchangeCodePOExample.Criteria criteria = example.createCriteria();
        criteria.andBatchIdEqualTo(batchId);
        criteria.andIsDeletedEqualTo(CommonConstant.CODE_NOT_DELETED);
        criteria.andUsedEqualTo(CommonConstant.CODE_NOT_USED);
        List<RechargeCardExchangeCodePO> poList = rechargeCardExchangeCodePOMapper.selectByExample(example);
        poList.stream().forEach(bean -> {
            if (bean.getExpiryType().equals(CommonConstant.RECHARGE_CARD_ACTIVE_PERIOD)) {
                bean.setExpiryTime(new Timestamp(DateUtil.getLastSecond(System.currentTimeMillis() + bean.getValidityPeriod() * CommonConstant.ONE_DAY)));
                bean.setRechargeCardEffectiveTime(new Timestamp(DateUtil.getFirstSecond(System.currentTimeMillis())));
                bean.setRechargeCardExpiryTime(new Timestamp(DateUtil.getLastSecond(System.currentTimeMillis() + bean.getValidityPeriod() * CommonConstant.ONE_DAY)));
            }
            bean.setStatus(status);
        });
        rcExchangeCodePOManualMapper.batchUpdate(poList);
    }

    @Override
    public List<RCExchangeCodeDTO> getList(Integer batchId, String code, String keyt, Byte status, Byte used, Paging paging) {
        Integer count = rcExchangeCodePOManualMapper.count(batchId, code, keyt, status, used);
        List<RechargeCardExchangeCodePO> poList = rcExchangeCodePOManualMapper.query(batchId, code, keyt, status, used, paging.getOffset(), paging.getLimit());
        List<RCExchangeCodeDTO> dtoList = poList.stream().map(RCExchangeCodeAdapter::PO2DTO).collect(Collectors.toList());
        dtoList.stream().forEach(bean -> {
            if (null != bean.getCustomerId()) {
                CustomerDTO customerDTO = customerService.getById(bean.getCustomerId());
                bean.setCustomerDTO(customerDTO);
            }
        });
        paging.setTotal(count);
        return dtoList;
    }

    @Override
    public RCExchangeCodeDTO getById(Integer id) {
        RechargeCardExchangeCodePO rechargeCardExchangeCodePO = rechargeCardExchangeCodePOMapper.selectByPrimaryKey(id);
        if (null == rechargeCardExchangeCodePO) {
            throw new RechargeCardException(RechargeCardCodeEnum.EXCHANGE_CODE_NOT_EXISTED);
        }
        RCExchangeCodeDTO rcExchangeCodeDTO = RCExchangeCodeAdapter.PO2DTO(rechargeCardExchangeCodePO);
        return rcExchangeCodeDTO;
    }

    @Override
    public void editSave(RCExchangeCodeEditDTO dto) {
        RechargeCardExchangeCodePO po = rechargeCardExchangeCodePOMapper.selectByPrimaryKey(dto.getId());
        if (null == po) {
            throw new RechargeCardException(RechargeCardCodeEnum.EXCHANGE_CODE_NOT_EXISTED);
        }
        po.setRechargeCardName(dto.getRechargeCardName());
        po.setExpiryTime(dto.getExpiryTime());
        po.setExpiryType(dto.getExpiryType());
        if (dto.getExpiryType().equals(CommonConstant.RECHARGE_CARD_EXPIRY_TIME)) {
            po.setRechargeCardEffectiveTime(dto.getRechargeCardEffectiveTime());
            po.setRechargeCardExpiryTime(dto.getRechargeCardExpiryTime());
        } else {
            po.setValidityPeriod(dto.getValidityPeriod());
        }
        po.setCityCodes(dto.getCityCodes());
        po.setProductIds(dto.getProductIds());
        po.setBatchDescription(dto.getBatchDescription());
        po.setModTime(new Date());
        rechargeCardExchangeCodePOMapper.updateByPrimaryKeySelective(po);
    }

    @Override
    public void batchUpdateCodeAndKeyt(List<RCExchangeCodeDTO> rcExchangeCodeDTOS) {
        List<RechargeCardExchangeCodePO> poList = rcExchangeCodeDTOS.stream().map(RCExchangeCodeAdapter::dto2Po).collect(Collectors.toList());
        rcExchangeCodePOManualMapper.batchUpdateCodeAndKeyt(poList);
    }

    @Override
    public RechargeCardExchangeCodePO findByKeyt(String keyt) {
        RechargeCardExchangeCodePO rechargeCardExchangeCodePO = rcExchangeCodePOManualMapper.findByKeyt(keyt);
        return rechargeCardExchangeCodePO;
    }
}
