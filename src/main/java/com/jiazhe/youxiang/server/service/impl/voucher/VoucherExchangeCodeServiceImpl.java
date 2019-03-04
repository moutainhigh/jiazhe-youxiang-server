package com.jiazhe.youxiang.server.service.impl.voucher;

import com.jiazhe.youxiang.base.util.DateUtil;
import com.jiazhe.youxiang.base.util.ExchangeCodeCheckUtil;
import com.jiazhe.youxiang.server.adapter.voucher.VoucherExchangeCodeAdapter;
import com.jiazhe.youxiang.server.common.constant.CommonConstant;
import com.jiazhe.youxiang.server.common.enums.CodeStatusEnum;
import com.jiazhe.youxiang.server.common.enums.LoginCodeEnum;
import com.jiazhe.youxiang.server.common.enums.VoucherCodeEnum;
import com.jiazhe.youxiang.server.common.exceptions.LoginException;
import com.jiazhe.youxiang.server.common.exceptions.VoucherException;
import com.jiazhe.youxiang.server.dao.mapper.VoucherExchangeCodePOMapper;
import com.jiazhe.youxiang.server.dao.mapper.manual.voucher.VoucherExchangeCodePOManualMapper;
import com.jiazhe.youxiang.server.domain.po.VoucherExchangeCodePO;
import com.jiazhe.youxiang.server.domain.po.VoucherExchangeCodePOExample;
import com.jiazhe.youxiang.server.domain.po.VoucherExchangeRecordPO;
import com.jiazhe.youxiang.server.domain.po.VoucherPO;
import com.jiazhe.youxiang.server.dto.customer.CustomerDTO;
import com.jiazhe.youxiang.server.dto.sysuser.SysUserDTO;
import com.jiazhe.youxiang.server.dto.voucher.exchangecode.VoucherExchangeCodeDTO;
import com.jiazhe.youxiang.server.dto.voucher.exchangecode.VoucherExchangeCodeEditDTO;
import com.jiazhe.youxiang.server.dto.voucher.exchangecodebatch.VoucherExchangeCodeBatchEditDTO;
import com.jiazhe.youxiang.server.dto.voucher.exchangecodebatch.VoucherExchangeCodeBatchSaveDTO;
import com.jiazhe.youxiang.server.dto.voucher.exchangerecord.VoucherExchangeRecordDTO;
import com.jiazhe.youxiang.server.service.CustomerService;
import com.jiazhe.youxiang.server.service.voucher.VoucherExchangeCodeBatchService;
import com.jiazhe.youxiang.server.service.voucher.VoucherExchangeCodeService;
import com.jiazhe.youxiang.server.service.voucher.VoucherExchangeRecordService;
import com.jiazhe.youxiang.server.service.voucher.VoucherService;
import com.jiazhe.youxiang.server.vo.Paging;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tu
 * @description：
 * @date 2018/11/3
 */
@Service("voucherExchangeCodeService")
@Transactional(rollbackFor = Exception.class)
public class VoucherExchangeCodeServiceImpl implements VoucherExchangeCodeService {

    @Autowired
    private VoucherExchangeCodePOMapper voucherExchangeCodePOMapper;
    @Autowired
    private VoucherExchangeCodePOManualMapper voucherExchangeCodePOManualMapper;
    @Autowired
    private VoucherService voucherService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private VoucherExchangeRecordService voucherExchangeRecordService;
    @Autowired
    private VoucherExchangeCodeBatchService voucherExchangeCodeBatchService;

    @Override
    public List<VoucherExchangeCodeDTO> getByBatchId(Integer id) {
        VoucherExchangeCodePOExample example = new VoucherExchangeCodePOExample();
        VoucherExchangeCodePOExample.Criteria criteria = example.createCriteria();
        criteria.andIsDeletedEqualTo(Byte.valueOf("0"));
        criteria.andBatchIdEqualTo(id);
        List<VoucherExchangeCodePO> poList = voucherExchangeCodePOMapper.selectByExample(example);
        return poList.stream().map(VoucherExchangeCodeAdapter::PO2DTO).collect(Collectors.toList());
    }

    @Override
    public void batchInsert(List<VoucherExchangeCodePO> voucherExchangeCodePOList) {
        voucherExchangeCodePOManualMapper.batchInsert(voucherExchangeCodePOList);
    }

    @Override
    public List<VoucherExchangeCodeDTO> getList(Integer batchId, String code, String keyt, Byte status, Byte used, Paging paging) {
        Integer count = voucherExchangeCodePOManualMapper.count(batchId, code, keyt, status, used);
        List<VoucherExchangeCodePO> poList = voucherExchangeCodePOManualMapper.query(batchId, code, keyt, status, used, paging.getOffset(), paging.getLimit());
        List<VoucherExchangeCodeDTO> dtoList = poList.stream().map(VoucherExchangeCodeAdapter::PO2DTO).collect(Collectors.toList());
        dtoList.stream().forEach(bean -> {
            if (null != bean.getCustomerId()) {
                CustomerDTO customerDTO = customerService.getById(bean.getCustomerId());
                bean.setCustomerDTO(customerDTO);
            }
        });
        paging.setTotal(count);
        return dtoList;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateWithBatch(VoucherExchangeCodeBatchSaveDTO batchSaveDTO) {
        VoucherExchangeCodePOExample example = new VoucherExchangeCodePOExample();
        VoucherExchangeCodePOExample.Criteria criteria = example.createCriteria();
        criteria.andBatchIdEqualTo(batchSaveDTO.getId());
        List<VoucherExchangeCodePO> poList = voucherExchangeCodePOMapper.selectByExample(example);
        poList.stream().forEach(bean -> {
            bean.setBatchName(batchSaveDTO.getName());
            bean.setVoucherName(batchSaveDTO.getVoucherName());
            bean.setBatchDescription(batchSaveDTO.getDescription());
            bean.setProjectId(batchSaveDTO.getProjectId());
            bean.setCityCodes(batchSaveDTO.getCityCodes());
            bean.setProductIds(batchSaveDTO.getProductIds());
            bean.setExpiryTime(batchSaveDTO.getExpiryTime());
            bean.setVoucherEffectiveTime(batchSaveDTO.getVoucherEffectiveTime());
            bean.setExpiryType(batchSaveDTO.getExpiryType());
            bean.setVoucherExpiryTime(batchSaveDTO.getVoucherExpiryTime());
            bean.setValidityPeriod(batchSaveDTO.getValidityPeriod());
        });
        voucherExchangeCodePOManualMapper.batchUpdate(poList);
        List<Integer> usedIds = poList.stream().filter(bean -> bean.getUsed().equals(Byte.valueOf("1"))).map(VoucherExchangeCodePO::getId).collect(Collectors.toList());
        if (!usedIds.isEmpty()) {
            List<VoucherExchangeRecordDTO> recordDTOList = voucherExchangeRecordService.findByCodeIds(usedIds);
            List<Integer> voucherIds = recordDTOList.stream().map(VoucherExchangeRecordDTO::getVoucherId).collect(Collectors.toList());
            voucherService.batchUpdate(voucherIds, batchSaveDTO);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void batchChangeStatus(Integer batchId, Byte status) {
        voucherExchangeCodePOManualMapper.batchChangeStatus(batchId, status);
        VoucherExchangeCodePOExample example = new VoucherExchangeCodePOExample();
        VoucherExchangeCodePOExample.Criteria criteria = example.createCriteria();
        criteria.andBatchIdEqualTo(batchId);
        List<VoucherExchangeCodePO> poList = voucherExchangeCodePOMapper.selectByExample(example);
        List<Integer> usedIds = poList.stream().filter(bean -> bean.getUsed().equals(Byte.valueOf("1"))).map(VoucherExchangeCodePO::getId).collect(Collectors.toList());
        if (!usedIds.isEmpty()) {
            List<VoucherExchangeRecordDTO> recordDTOList = voucherExchangeRecordService.findByCodeIds(usedIds);
            List<Integer> voucherIds = recordDTOList.stream().map(VoucherExchangeRecordDTO::getVoucherId).collect(Collectors.toList());
            voucherService.batchChangeStatus(voucherIds, status);
        }
    }

    @Override
    public VoucherExchangeCodeDTO getById(Integer id) {
        VoucherExchangeCodePO voucherExchangeCodePO = voucherExchangeCodePOMapper.selectByPrimaryKey(id);
        if (null == voucherExchangeCodePO) {
            throw new VoucherException(VoucherCodeEnum.EXCHANGE_CODE_NOT_EXISTED);
        }
        VoucherExchangeCodeDTO voucherExchangeCodeDTO = VoucherExchangeCodeAdapter.PO2DTO(voucherExchangeCodePO);
        return voucherExchangeCodeDTO;
    }

    @Override
    public void editSave(VoucherExchangeCodeEditDTO dto) {
        VoucherExchangeCodePO po = voucherExchangeCodePOMapper.selectByPrimaryKey(dto.getId());
        if (null == po) {
            throw new VoucherException(VoucherCodeEnum.EXCHANGE_CODE_NOT_EXISTED);
        }
        po.setVoucherName(dto.getVoucherName());
        po.setExpiryTime(dto.getExpiryTime());
        po.setVoucherEffectiveTime(dto.getVoucherEffectiveTime());
        po.setExpiryType(dto.getExpiryType());
        if (dto.getExpiryType().equals(CommonConstant.VOUCHER_EXPIRY_TIME)) {
            po.setVoucherExpiryTime(dto.getVoucherExpiryTime());
        } else {
            po.setValidityPeriod(dto.getValidityPeriod());
        }
        po.setBatchDescription(dto.getBatchDescription());
        po.setCityCodes(dto.getCityCodes());
        po.setProductIds(dto.getProductIds());
        po.setAddTime(new Date());
        voucherExchangeCodePOMapper.updateByPrimaryKeySelective(po);
    }

    @Override
    public void changeCodeStatus(Integer id, Byte status) {
        VoucherExchangeCodePO voucherExchangeCodePO = voucherExchangeCodePOMapper.selectByPrimaryKey(id);
        voucherExchangeCodePO.setStatus(status);
        voucherExchangeCodePOMapper.updateByPrimaryKeySelective(voucherExchangeCodePO);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void codeCharge(Integer exchangeType, Integer id, String keyt) {
        if (!ExchangeCodeCheckUtil.keytCheck(CommonConstant.VOUCHER_EXCHANGE_CODE_PREFIX, keyt)) {
            throw new VoucherException(VoucherCodeEnum.EXCHANGE_CODE_NOT_EXISTED);
        }
        VoucherExchangeCodePO voucherExchangeCodePO = findByKeyt(keyt);
        if (null == voucherExchangeCodePO) {
            throw new VoucherException(VoucherCodeEnum.EXCHANGE_CODE_NOT_EXISTED);
        }
        if (voucherExchangeCodePO.getStatus().equals(CommonConstant.CODE_STOP_USING)) {
            throw new VoucherException(VoucherCodeEnum.EXCHANGE_CODE_HAS_STOPED_USING);
        }
        VoucherExchangeCodeBatchEditDTO voucherExchangeCodeBatchEditDTO = voucherExchangeCodeBatchService.getById(voucherExchangeCodePO.getBatchId());
        if (voucherExchangeCodeBatchEditDTO.getStatus().equals(CommonConstant.CODE_STOP_USING)) {
            throw new VoucherException(VoucherCodeEnum.BATCH_HAS_STOPPED_USING);
        }
        if (voucherExchangeCodePO.getUsed().equals(CommonConstant.CODE_HAS_USED)) {
            throw new VoucherException(VoucherCodeEnum.EXCHANGE_CODE_HAS_USED);
        }
        if (voucherExchangeCodePO.getExpiryTime().getTime() < System.currentTimeMillis()) {
            throw new VoucherException(VoucherCodeEnum.EXCHANGE_CODE_HAS_EXPIRIED);
        }
        CustomerDTO customerDTO = customerService.getById(id);
        if (null == customerDTO) {
            throw new VoucherException(VoucherCodeEnum.CUSTOMER_NOT_EXIST);
        }
        VoucherPO voucherPO = new VoucherPO();
        //直接指定过期时间
        if (voucherExchangeCodePO.getExpiryType().equals(CommonConstant.RECHARGE_CARD_EXPIRY_TIME)) {
            voucherPO.setEffectiveTime(voucherExchangeCodePO.getVoucherEffectiveTime());
            voucherPO.setExpiryTime(voucherExchangeCodePO.getVoucherExpiryTime());
        }
        if (voucherExchangeCodePO.getExpiryType().equals(CommonConstant.RECHARGE_CARD_EXCHANGE_PERIOD)) {
            voucherPO.setEffectiveTime(new Date());
            voucherPO.setExpiryTime(new Date(DateUtil.getLastSecond(System.currentTimeMillis() + voucherExchangeCodePO.getValidityPeriod() * CommonConstant.ONE_DAY)));
        }
        if (voucherExchangeCodePO.getExpiryType().equals(CommonConstant.RECHARGE_CARD_ACTIVE_PERIOD)) {
            voucherPO.setEffectiveTime(new Date());
            voucherPO.setExpiryTime(new Date(DateUtil.getLastSecond(voucherExchangeCodePO.getModTime().getTime() + voucherExchangeCodePO.getValidityPeriod() * CommonConstant.ONE_DAY)));
        }
        voucherPO.setEffectiveTime(voucherExchangeCodePO.getVoucherEffectiveTime());
        voucherPO.setDescription(voucherExchangeCodePO.getBatchDescription());
        voucherPO.setCount(voucherExchangeCodePO.getCount());
        //暂时置为0，等生成了兑换记录再修改
        voucherPO.setExchangeRecordId(0);
        voucherPO.setStatus(CodeStatusEnum.START_USING.getId().byteValue());
        voucherPO.setProjectId(voucherExchangeCodePO.getProjectId());
        voucherPO.setName(voucherExchangeCodePO.getVoucherName());
        voucherPO.setCustomerId(customerDTO.getId());
        voucherPO.setCityCodes(voucherExchangeCodePO.getCityCodes());
        voucherPO.setProductIds(voucherExchangeCodePO.getProductIds());
        voucherService.insert(voucherPO);
        //插入兑换记录信息
        VoucherExchangeRecordPO voucherExchangeRecordPO = new VoucherExchangeRecordPO();
        voucherExchangeRecordPO.setExchangeCodeId(voucherExchangeCodePO.getId());
        voucherExchangeRecordPO.setExchangeType(exchangeType);
        voucherExchangeRecordPO.setOperatorId(0);
        voucherExchangeRecordPO.setOperatorName("");
        //如果后台用兑换码帮客户充值，同样记录操作人员的信息
        if (exchangeType.equals(CommonConstant.EXCHANGETYPE_USER_CODE_EXCHANGE)) {
            SysUserDTO sysUserDTO = (SysUserDTO) SecurityUtils.getSubject().getPrincipal();
            if (null == sysUserDTO) {
                throw new LoginException(LoginCodeEnum.LOGIN_NOT_SIGNIN_IN);
            }
            voucherExchangeRecordPO.setOperatorId(sysUserDTO.getId());
            voucherExchangeRecordPO.setOperatorName(sysUserDTO.getLoginName());
        }
        voucherExchangeRecordPO.setVoucherId(voucherPO.getId());
        voucherExchangeRecordPO.setExtInfo("");
        voucherExchangeRecordPO.setIsDeleted(Byte.valueOf("0"));
        voucherExchangeRecordPO.setAddTime(new Date());
        voucherExchangeRecordPO.setModTime(new Date());
        voucherExchangeRecordService.insert(voucherExchangeRecordPO);
        //修改充值卡对应的兑换记录id
        voucherPO.setExchangeRecordId(voucherExchangeRecordPO.getId());
        voucherService.update(voucherPO);
        //修改充值卡兑换码的使用状态
        voucherExchangeCodePO.setUsed(Byte.valueOf("1"));
        voucherExchangeCodePO.setCustomerId(customerDTO.getId());
        voucherExchangeCodePOMapper.updateByPrimaryKeySelective(voucherExchangeCodePO);
    }

    @Override
    public VoucherExchangeCodePO findByKeyt(String keyt) {
        VoucherExchangeCodePO voucherExchangeCodePO = voucherExchangeCodePOManualMapper.findByKeyt(keyt);
        return voucherExchangeCodePO;
    }

    @Override
    public void batchUpdateCodeAndKeyt(List<VoucherExchangeCodeDTO> voucherExchangeCodeDTOS) {
        List<VoucherExchangeCodePO> poList = voucherExchangeCodeDTOS.stream().map(VoucherExchangeCodeAdapter::dto2Po).collect(Collectors.toList());
        voucherExchangeCodePOManualMapper.batchUpdateCodeAndKeyt(poList);
    }
}
