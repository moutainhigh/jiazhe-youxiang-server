package com.jiazhe.youxiang.server.service.impl.point;

import com.jiazhe.youxiang.server.adapter.point.PointAdapter;
import com.jiazhe.youxiang.server.common.constant.CommonConstant;
import com.jiazhe.youxiang.server.common.enums.CodeStatusEnum;
import com.jiazhe.youxiang.server.common.enums.LoginCodeEnum;
import com.jiazhe.youxiang.server.common.enums.RechargeCardCodeEnum;
import com.jiazhe.youxiang.server.common.exceptions.LoginException;
import com.jiazhe.youxiang.server.common.exceptions.RechargeCardException;
import com.jiazhe.youxiang.server.dao.mapper.PointPOMapper;
import com.jiazhe.youxiang.server.dao.mapper.manual.point.PointPOManualMapper;
import com.jiazhe.youxiang.server.domain.po.PointExchangeRecordPO;
import com.jiazhe.youxiang.server.domain.po.PointPO;
import com.jiazhe.youxiang.server.dto.customer.CustomerDTO;
import com.jiazhe.youxiang.server.dto.point.point.PointDTO;
import com.jiazhe.youxiang.server.dto.point.point.PointEditDTO;
import com.jiazhe.youxiang.server.dto.point.pointexchangecodebatch.PointExchangeCodeBatchEditDTO;
import com.jiazhe.youxiang.server.dto.point.pointexchangecodebatch.PointExchangeCodeBatchSaveDTO;
import com.jiazhe.youxiang.server.dto.point.pointexchangerecord.PointExchangeRecordDTO;
import com.jiazhe.youxiang.server.dto.sysuser.SysUserDTO;
import com.jiazhe.youxiang.server.service.CustomerService;
import com.jiazhe.youxiang.server.service.point.PointExchangeCodeBatchService;
import com.jiazhe.youxiang.server.service.point.PointExchangeRecordService;
import com.jiazhe.youxiang.server.service.point.PointService;
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
@Service("pointService")
public class PointServiceImpl implements PointService {

    @Autowired
    private PointPOManualMapper pointPOManualMapper;
    @Autowired
    private PointPOMapper pointPOMapper;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private PointService pointService;
    @Autowired
    private PointExchangeRecordService pointExchangeRecordService;
    @Autowired
    private PointExchangeCodeBatchService pointExchangeCodeBatchService;

    @Override
    public void batchUpdate(List<Integer> ids, PointExchangeCodeBatchSaveDTO batchSaveDTO) {
        List<PointPO> pointPOList = pointPOManualMapper.findByIds(ids);
        pointPOList.stream().forEach(bean -> {
            bean.setName(batchSaveDTO.getPointName());
            bean.setDescription(batchSaveDTO.getDescription());
            bean.setProjectId(batchSaveDTO.getProjectId());
            bean.setCityCodes(batchSaveDTO.getCityCodes());
            bean.setProductIds(batchSaveDTO.getProductIds());
            //直接指定过期时间
            if(batchSaveDTO.getExpiryType().equals(CommonConstant.POINT_EXPIRY_TIME)){
                bean.setExpiryTime(batchSaveDTO.getPointExpiryTime());
            }else{
                bean.setExpiryTime(new Date(bean.getAddTime().getTime()+batchSaveDTO.getValidityPeriod()* CommonConstant.ONE_DAY));
            }
        });
        pointPOManualMapper.batchUpdate(pointPOList);
    }

    @Override
    public void batchChangeStatus(List<Integer> ids, Byte status) {
        Map<String, Object> map = new HashMap<String, Object>(2);
        map.put("status",status);
        map.put("ids",ids);
        pointPOManualMapper.batchChangeStatus(map);
    }

    @Override
    public void insert(PointPO pointPO) {
        pointPOManualMapper.insert(pointPO);
    }

    @Override
    public void update(PointPO pointPO) {
        pointPOMapper.updateByPrimaryKeySelective(pointPO);
    }

    @Override
    public List<PointDTO> getList(String mobile, Integer exchangeType, Byte status, Byte expiry, Paging paging) {
        final CustomerDTO customerDTO = customerService.getByMobile(mobile);
        List<PointPO> pointPOList = pointPOManualMapper.query(mobile,exchangeType,status,expiry,paging.getOffset(),paging.getLimit());
        List<PointDTO> pointDTOList = pointPOList.stream().map(PointAdapter::po2Dto).collect(Collectors.toList());
        Integer count = pointPOManualMapper.count(mobile,exchangeType,status,expiry);
        paging.setTotal(count);
        pointDTOList.stream().forEach(bean -> {
            if(Strings.isBlank(mobile)){
                CustomerDTO customerDTO1 = customerService.getById(bean.getCustomerId());
                bean.setCustomerDTO(customerDTO1);
            }else{
                bean.setCustomerDTO(customerDTO);
            }
            PointExchangeRecordDTO pointExchangeRecordDTO = pointExchangeRecordService.findByPointId(bean.getId());
            bean.setPointExchangeRecordDTO(pointExchangeRecordDTO);
        });
        return pointDTOList;
    }

    @Override
    public void changeStatus(Integer id, Byte status) {
        PointPO pointCardPO = pointPOMapper.selectByPrimaryKey(id);
        pointCardPO.setStatus(status);
        pointCardPO.setModTime(new Date());
        pointPOMapper.updateByPrimaryKeySelective(pointCardPO);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void directCharge(Integer id, Integer batchId, BigDecimal faceValue) {
        CustomerDTO customerDTO = customerService.getById(id);
        if(null == customerDTO){
            throw new RechargeCardException(RechargeCardCodeEnum.CUSTOMER_NOT_EXIST);
        }
        PointExchangeCodeBatchEditDTO pointExchangeCodeBatchEditDTO = pointExchangeCodeBatchService.getById(batchId);
        PointPO pointPO = new PointPO();
        //直接指定过期时间
        if(pointExchangeCodeBatchEditDTO.getExpiryType().equals(CommonConstant.POINT_EXPIRY_TIME)){
            pointPO.setExpiryTime(pointExchangeCodeBatchEditDTO.getPointExpiryTime());
        }else{
            pointPO.setExpiryTime(new Date(System.currentTimeMillis()+pointExchangeCodeBatchEditDTO.getValidityPeriod()* CommonConstant.ONE_DAY));
        }
        pointPO.setDescription(pointExchangeCodeBatchEditDTO.getDescription());
        pointPO.setFaceValue(faceValue);
        pointPO.setBalance(faceValue);
        //暂时置为0，等生成了兑换记录再修改
        pointPO.setExchangeRecordId(0);
        pointPO.setStatus(CodeStatusEnum.START_USING.getId().byteValue());
        pointPO.setProjectId(pointExchangeCodeBatchEditDTO.getProjectId());
        pointPO.setName(pointExchangeCodeBatchEditDTO.getPointName());
        pointPO.setCustomerId(customerDTO.getId());
        pointPO.setCityCodes(pointExchangeCodeBatchEditDTO.getCityCodes());
        pointPO.setProductIds(pointExchangeCodeBatchEditDTO.getProductIds());
        pointService.insert(pointPO);
        //插入兑换记录信息
        SysUserDTO sysUserDTO = (SysUserDTO) SecurityUtils.getSubject().getPrincipal();
        if(null == sysUserDTO){
            throw new LoginException(LoginCodeEnum.LOGIN_NOT_SIGNIN_IN);
        }
        PointExchangeRecordPO pointRecordPO = new PointExchangeRecordPO();
        pointRecordPO.setOperatorId(sysUserDTO.getId());
        pointRecordPO.setOperatorName(sysUserDTO.getLoginName());
        pointRecordPO.setExchangeType(CommonConstant.EXCHANGETYPE_USER_DIRECTCHARGE);
        pointRecordPO.setPointId(pointPO.getId());
        pointRecordPO.setExtInfo("");
        pointRecordPO.setIsDeleted(Byte.valueOf("0"));
        pointRecordPO.setAddTime(new Date());
        pointRecordPO.setModTime(new Date());
        pointExchangeRecordService.insert(pointRecordPO);
        //修改充值卡对应的兑换记录id
        pointPO.setExchangeRecordId(pointRecordPO.getId());
        pointService.update(pointPO);
    }

    @Override
    public PointDTO getById(Integer id) {
        PointPO po = pointPOMapper.selectByPrimaryKey(id);
        return PointAdapter.po2Dto(po);
    }

    @Override
    public void editSave(PointEditDTO dto) {
        PointPO po = pointPOMapper.selectByPrimaryKey(dto.getId());
        po.setProductIds(dto.getProductIds());
        po.setCityCodes(dto.getCityCodes());
        po.setName(dto.getName());
        po.setExpiryTime(dto.getExpiryTime());
        po.setDescription(dto.getDescription());
        pointPOMapper.updateByPrimaryKeySelective(po);
    }
}
