package com.jiazhe.youxiang.server.service.impl.material;

import com.jiazhe.youxiang.server.adapter.material.MaterialAdapter;
import com.jiazhe.youxiang.server.common.constant.CommonConstant;
import com.jiazhe.youxiang.server.common.enums.LoginCodeEnum;
import com.jiazhe.youxiang.server.common.enums.MaterialCodeEnum;
import com.jiazhe.youxiang.server.common.exceptions.LoginException;
import com.jiazhe.youxiang.server.common.exceptions.MaterialException;
import com.jiazhe.youxiang.server.dao.mapper.MaterialInfoPOMapper;
import com.jiazhe.youxiang.server.dao.mapper.manual.material.MaterialInfoPOManualMapper;
import com.jiazhe.youxiang.server.domain.po.MaterialInfoPO;
import com.jiazhe.youxiang.server.dto.material.MaterialDto;
import com.jiazhe.youxiang.server.dto.material.MaterialSummaryDto;
import com.jiazhe.youxiang.server.dto.sysuser.SysUserDTO;
import com.jiazhe.youxiang.server.service.SysUserService;
import com.jiazhe.youxiang.server.service.material.MaterialService;
import com.jiazhe.youxiang.server.vo.Paging;
import org.apache.logging.log4j.util.Strings;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author TU
 * @description
 * @date 2019-05-13.
 */
@Service("materialService")
public class MaterialServiceImpl implements MaterialService {

    @Autowired
    private MaterialInfoPOManualMapper materialInfoPOManualMapper;
    @Autowired
    private MaterialInfoPOMapper materialInfoPOMapper;
    @Autowired
    private SysUserService sysUserService;

    @Override
    public List<MaterialSummaryDto> getSummaryList(String payerIds, String payeeIds, Paging paging) {
        if (!Strings.isEmpty(payerIds)) {
            payerIds = "(" + payerIds + ")";
        }
        if (!Strings.isEmpty(payeeIds)) {
            payeeIds = "(" + payeeIds + ")";
        }
        Integer count = materialInfoPOManualMapper.getSummaryCount(payerIds, payeeIds);
        paging.setTotal(count);
        List<MaterialSummaryDto> dtoList = materialInfoPOManualMapper.getSummaryList(payerIds, payeeIds, paging.getOffset(), paging.getLimit());
        return dtoList;
    }

    @Override
    public MaterialSummaryDto calculateSummary(String payerIds, String payeeIds) {
        if (!Strings.isEmpty(payerIds)) {
            payerIds = "(" + payerIds + ")";
        }
        if (!Strings.isEmpty(payeeIds)) {
            payeeIds = "(" + payeeIds + ")";
        }
        MaterialSummaryDto dto = materialInfoPOManualMapper.calculateSummary(payerIds, payeeIds);
        if (null == dto) {
            dto = new MaterialSummaryDto();
            dto.setReceiveTotal(BigDecimal.ZERO);
            dto.setProductValueTotal(BigDecimal.ZERO);
            dto.setUsedProductValueTotal(BigDecimal.ZERO);
        }
        return dto;
    }

    @Override
    public void save(Integer id ,Integer payeeId, BigDecimal transferAmount, BigDecimal materialValue, Date transferTime, String remark) {
        SysUserDTO payerDto = (SysUserDTO) SecurityUtils.getSubject().getPrincipal();
        if (null == payerDto) {
            throw new LoginException(LoginCodeEnum.LOGIN_NOT_SIGNIN_IN);
        }
        MaterialInfoPO po ;
        if(id == 0){
            po = new MaterialInfoPO();
            po.setPayerId(payerDto.getId());
            po.setPayerName(payerDto.getDisplayName());
        }else{
            po = materialInfoPOMapper.selectByPrimaryKey(id);
            if(!po.getPayerId().equals(payerDto.getId())){
                throw new MaterialException(MaterialCodeEnum.CANNOT_CHANGE_OTHERS_PAY);
            }
        }
        SysUserDTO payeeDto = sysUserService.findById(payeeId);
        if (null == payeeDto) {
            throw new MaterialException(MaterialCodeEnum.PAYEE_NOT_EXIST);
        }
        po.setPayeeId(payeeDto.getId());
        po.setPayeeName(payeeDto.getDisplayName());
        po.setTransferAmount(transferAmount);
        po.setMaterialValue(materialValue);
        po.setTransferTime(transferTime);
        po.setRemark(remark);
        if(id == 0){
            materialInfoPOMapper.insertSelective(po);
        }else{
            materialInfoPOMapper.updateByPrimaryKeySelective(po);
        }

    }

    @Override
    public List<MaterialDto> getList(String payerIds, String payeeIds, Date transferTimeBegin, Date transferTimeEnd, Paging paging) {
        if (!Strings.isEmpty(payerIds)) {
            payerIds = "(" + payerIds + ")";
        }
        if (!Strings.isEmpty(payeeIds)) {
            payeeIds = "(" + payeeIds + ")";
        }
        Integer count = materialInfoPOManualMapper.count(payerIds, payeeIds, transferTimeBegin, transferTimeEnd);
        paging.setTotal(count);
        List<MaterialInfoPO> poList = materialInfoPOManualMapper.query(payerIds, payeeIds, transferTimeBegin, transferTimeEnd, paging.getOffset(), paging.getLimit());
        return poList.stream().map(MaterialAdapter::po2Dto).collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        MaterialInfoPO po = materialInfoPOMapper.selectByPrimaryKey(id);
        if (po == null) {
            throw new MaterialException(MaterialCodeEnum.MATERIAL_INFO_IS_NOT_EXIST);
        }
        po.setIsDeleted(CommonConstant.CODE_DELETED);
        po.setModTime(new Date(System.currentTimeMillis()));
        materialInfoPOMapper.updateByPrimaryKeySelective(po);
    }

    @Override
    public MaterialDto getById(Integer id) {
        MaterialInfoPO po = materialInfoPOMapper.selectByPrimaryKey(id);
        return MaterialAdapter.po2Dto(po);
    }
}
