package com.jiazhe.youxiang.server.service.impl;

import com.jiazhe.youxiang.server.adapter.ChargeReceiptAdapter;
import com.jiazhe.youxiang.server.common.constant.CommonConstant;
import com.jiazhe.youxiang.server.common.enums.ChargeReceiptCodeEnum;
import com.jiazhe.youxiang.server.common.enums.LoginCodeEnum;
import com.jiazhe.youxiang.server.common.exceptions.ChargeReceiptException;
import com.jiazhe.youxiang.server.common.exceptions.LoginException;
import com.jiazhe.youxiang.server.dao.mapper.ChargeReceiptPOMapper;
import com.jiazhe.youxiang.server.dao.mapper.manual.ChargeReceiptPOManualMapper;
import com.jiazhe.youxiang.server.domain.po.ChargeReceiptPO;
import com.jiazhe.youxiang.server.domain.po.ChargeReceiptPOExample;
import com.jiazhe.youxiang.server.dto.auditrecord.AuditRecordDTO;
import com.jiazhe.youxiang.server.dto.chargereceipt.ChargeReceiptDTO;
import com.jiazhe.youxiang.server.dto.chargereceipt.ChargeReceiptSaveDTO;
import com.jiazhe.youxiang.server.dto.sysuser.SysUserDTO;
import com.jiazhe.youxiang.server.service.AuditRecordService;
import com.jiazhe.youxiang.server.service.ChargeReceiptService;
import com.jiazhe.youxiang.server.vo.Paging;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author TU
 * @description
 * @date 2019-03-19.
 */
@Service("chargeReceiptService")
public class ChargeReceiptServiceImpl implements ChargeReceiptService {

    @Autowired
    private ChargeReceiptPOManualMapper chargeReceiptPOManualMapper;
    @Autowired
    private ChargeReceiptPOMapper chargeReceiptPOMapper;
    @Autowired
    private AuditRecordService auditRecordService;

    @Override
    public List<ChargeReceiptDTO> getList(Integer auditRecordId, Paging paging) {
        AuditRecordDTO auditRecordDTO = auditRecordService.getById(auditRecordId);
        Integer count = chargeReceiptPOManualMapper.count(auditRecordId);
        List<ChargeReceiptPO> chargeReceiptPOList = chargeReceiptPOManualMapper.query(auditRecordId, paging.getOffset(), paging.getLimit());
        paging.setTotal(count);
        List<ChargeReceiptDTO> chargeReceiptDTOList = chargeReceiptPOList.stream().map(ChargeReceiptAdapter::po2Dto).collect(Collectors.toList());
        chargeReceiptDTOList.stream().forEach(bean->{
            bean.setChargeReceiptStatus(auditRecordDTO.getChargeReceiptStatus());
        });
        return chargeReceiptDTOList;
    }

    @Override
    public void delete(Integer id) {
        ChargeReceiptPO po = chargeReceiptPOMapper.selectByPrimaryKey(id);
        if(po == null){
            throw new ChargeReceiptException(ChargeReceiptCodeEnum.CHARGE_RECEIPT_IS_NOT_EXIST);
        }
        AuditRecordDTO dto = auditRecordService.getById(po.getAuditRecordId());
        if(CommonConstant.CHARGE_RECEIPT_COMPLETE.equals(dto.getChargeReceiptStatus())){
            throw new ChargeReceiptException(ChargeReceiptCodeEnum.CHARGE_RECEIPT_HAS_FINISHED);
        }
        po.setIsDeleted(CommonConstant.CODE_DELETED);
        po.setModTime(new Date(System.currentTimeMillis()));
        chargeReceiptPOMapper.updateByPrimaryKeySelective(po);
    }

    @Override
    public void save(ChargeReceiptSaveDTO dto) {
        AuditRecordDTO auditRecordDTO = auditRecordService.getById(dto.getAuditRecordId());
        if(CommonConstant.CHARGE_RECEIPT_COMPLETE.equals(auditRecordDTO.getChargeReceiptStatus())){
            throw new ChargeReceiptException(ChargeReceiptCodeEnum.CHARGE_RECEIPT_HAS_FINISHED);
        }
        ChargeReceiptPO po = null ;
        Integer id = dto.getId();
        if(0 == id){
            po = new ChargeReceiptPO();
        }else{
            po = chargeReceiptPOMapper.selectByPrimaryKey(id);
            if(null == po){
                throw new ChargeReceiptException(ChargeReceiptCodeEnum.CHARGE_RECEIPT_IS_NOT_EXIST);
            }
        }
        SysUserDTO sysUserDTO = (SysUserDTO) SecurityUtils.getSubject().getPrincipal();
        if (null == sysUserDTO) {
            throw new LoginException(LoginCodeEnum.LOGIN_NOT_SIGNIN_IN);
        }
        po.setInputerId(sysUserDTO.getId());
        po.setInputerName(sysUserDTO.getDisplayName());
        po.setAuditRecordId(dto.getAuditRecordId());
        po.setExchangePoint(dto.getExchangePoint());
        po.setCustomerName(dto.getCustomerName());
        po.setPosCode(dto.getPosCode());
        po.setCardNo(dto.getCardNo());
        po.setTradeTime(dto.getTradeTime());
        po.setModTime(new Date());
        if(0 == id){
            chargeReceiptPOMapper.insertSelective(po);
        }else{
            chargeReceiptPOMapper.updateByPrimaryKeySelective(po);
        }
    }

    @Override
    public ChargeReceiptDTO getById(Integer id) {
        ChargeReceiptPO po = chargeReceiptPOMapper.selectByPrimaryKey(id);
        if(null == po){
            throw new ChargeReceiptException(ChargeReceiptCodeEnum.CHARGE_RECEIPT_IS_NOT_EXIST);
        }
        return ChargeReceiptAdapter.po2Dto(po);
    }

    @Override
    public List<ChargeReceiptDTO> getList(String customerMobile, Byte status,Byte chargeReceiptStatus) {
        List<AuditRecordDTO> auditRecordDTOList = auditRecordService.getList(customerMobile,status,chargeReceiptStatus);
        List<Integer> auditRecordIds = auditRecordDTOList.stream().map(AuditRecordDTO::getId).collect(Collectors.toList());
        List<ChargeReceiptPO> poList = chargeReceiptPOManualMapper.finByAuditRecordIds(auditRecordIds);
        return poList.stream().map(ChargeReceiptAdapter::po2Dto).collect(Collectors.toList());
    }

    @Override
    public List<ChargeReceiptDTO> getByAuditRecordId(Integer auditRecordId) {
        ChargeReceiptPOExample example = new ChargeReceiptPOExample();
        ChargeReceiptPOExample.Criteria criteria = example.createCriteria();
        criteria.andIsDeletedEqualTo(CommonConstant.CODE_NOT_DELETED);
        criteria.andAuditRecordIdEqualTo(auditRecordId);
        List<ChargeReceiptPO> poList = chargeReceiptPOMapper.selectByExample(example);
        return poList.stream().map(ChargeReceiptAdapter::po2Dto).collect(Collectors.toList());
    }
}
