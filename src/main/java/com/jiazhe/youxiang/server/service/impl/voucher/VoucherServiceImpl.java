package com.jiazhe.youxiang.server.service.impl.voucher;

import com.jiazhe.youxiang.server.adapter.voucher.VoucherAdapter;
import com.jiazhe.youxiang.server.common.constant.CommonConstant;
import com.jiazhe.youxiang.server.dao.mapper.VoucherPOMapper;
import com.jiazhe.youxiang.server.dao.mapper.manual.voucher.VoucherPOManualMapper;
import com.jiazhe.youxiang.server.domain.po.VoucherExchangeRecordPO;
import com.jiazhe.youxiang.server.domain.po.VoucherPO;
import com.jiazhe.youxiang.server.dto.customer.CustomerDTO;
import com.jiazhe.youxiang.server.dto.voucher.exchangecodebatch.VoucherExchangeCodeBatchSaveDTO;
import com.jiazhe.youxiang.server.dto.voucher.exchangerecord.VoucherExchangeRecordDTO;
import com.jiazhe.youxiang.server.dto.voucher.voucher.VoucherDTO;
import com.jiazhe.youxiang.server.dto.voucher.voucher.VoucherEditDTO;
import com.jiazhe.youxiang.server.service.CustomerService;
import com.jiazhe.youxiang.server.service.voucher.VoucherExchangeRecordService;
import com.jiazhe.youxiang.server.service.voucher.VoucherService;
import com.jiazhe.youxiang.server.vo.Paging;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author tu
 * @description：
 * @date 2018/11/3
 */
@Service("voucherService")
@Transactional(rollbackFor=Exception.class)
public class VoucherServiceImpl implements VoucherService {

    @Autowired
    private VoucherPOManualMapper voucherPOManualMapper;
    @Autowired
    private VoucherPOMapper voucherPOMapper;
    @Autowired
    private VoucherExchangeRecordService voucherExchangeRecordService;
    @Autowired
    private CustomerService customerService;
    @Override
    public void batchUpdate(List<Integer> ids, VoucherExchangeCodeBatchSaveDTO batchSaveDTO) {
        List<VoucherPO> rcPOList = voucherPOManualMapper.findByIds(ids);
        rcPOList.stream().forEach(bean -> {
            bean.setName(batchSaveDTO.getVoucherName());
            bean.setDescription(batchSaveDTO.getDescription());
            bean.setProjectId(batchSaveDTO.getProjectId());
            bean.setCityCodes(batchSaveDTO.getCityCodes());
            bean.setProductIds(batchSaveDTO.getProductIds());
            //直接指定过期时间
            if(batchSaveDTO.getExpiryType().equals(CommonConstant.RECHARGE_CARD_EXPIRY_TIME)){
                bean.setExpiryTime(batchSaveDTO.getVoucherExpiryTime());
            }else{
                bean.setExpiryTime(new Date(bean.getAddTime().getTime()+batchSaveDTO.getValidityPeriod()* CommonConstant.ONE_DAY));
            }
        });
        voucherPOManualMapper.batchUpdate(rcPOList);
    }

    @Override
    public void batchChangeStatus(List<Integer> ids, Byte status) {
        Map<String, Object> map = new HashMap<String, Object>(2);
        map.put("status",status);
        map.put("ids",ids);
        voucherPOManualMapper.batchChangeStatus(map);
    }

    @Override
    public List<VoucherDTO> getList(String mobile, Integer exchangeType, Byte status, Byte expiry, Paging paging) {
        final CustomerDTO customerDTO = customerService.getByMobile(mobile);
        List<VoucherPO> rechargeCardPOList = voucherPOManualMapper.query(mobile,exchangeType,status,expiry,paging.getOffset(),paging.getLimit());
        List<VoucherDTO> rcDTOList = rechargeCardPOList.stream().map(VoucherAdapter::PO2DTO).collect(Collectors.toList());
        Integer count = voucherPOManualMapper.count(mobile,exchangeType,status,expiry);
        paging.setTotal(count);
        rcDTOList.stream().forEach(bean -> {
            if(Strings.isBlank(mobile)){
                CustomerDTO customerDTO1 = customerService.getById(bean.getCustomerId());
                bean.setCustomerDTO(customerDTO1);
            }else{
                bean.setCustomerDTO(customerDTO);
            }
            VoucherExchangeRecordDTO voucherExchangeRecordDTO = voucherExchangeRecordService.findByVoucherId(bean.getId());
            bean.setVoucherExchangeRecordDTO(voucherExchangeRecordDTO);
        });
        return rcDTOList;
    }

    @Override
    public void changeStatus(Integer id, Byte status) {
        VoucherPO voucherPO = voucherPOMapper.selectByPrimaryKey(id);
        voucherPO.setStatus(status);
        voucherPO.setModTime(new Date());
        voucherPOMapper.updateByPrimaryKeySelective(voucherPO);
    }

    @Override
    public VoucherDTO getById(Integer id) {
        VoucherPO po = voucherPOMapper.selectByPrimaryKey(id);
        return VoucherAdapter.PO2DTO(po);
    }

    @Override
    public void editSave(VoucherEditDTO dto) {
        VoucherPO po = voucherPOMapper.selectByPrimaryKey(dto.getId());
        po.setProductIds(dto.getProductIds());
        po.setCityCodes(dto.getCityCodes());
        po.setName(dto.getName());
        po.setExpiryTime(dto.getExpiryTime());
        po.setDescription(dto.getDescription());
        voucherPOMapper.updateByPrimaryKeySelective(po);
    }

    @Override
    public void insert(VoucherPO voucherPO) {
        voucherPOManualMapper.insert(voucherPO);
    }

    @Override
    public void update(VoucherPO voucherPO) {
        voucherPOMapper.updateByPrimaryKeySelective(voucherPO);
    }

    @Override
    public void batchChangeUsed(List<Integer> ids, Byte used) {
        Map<String, Object> map = new HashMap<String, Object>(2);
        map.put("used",used);
        map.put("ids",ids);
        if(!ids.isEmpty()){
            voucherPOManualMapper.batchChangeUsed(map);
        }
    }

    @Override
    public List<VoucherDTO> findByIds(List<Integer> voucherIds) {
        List<VoucherPO> poList = voucherPOManualMapper.findByIds(voucherIds);
        return poList.stream().map(VoucherAdapter::PO2DTO).collect(Collectors.toList());
    }

    @Override
    public Integer totalValidVoucher(Integer customerId) {
        return voucherPOManualMapper.totalValidVoucher(customerId);
    }
}
