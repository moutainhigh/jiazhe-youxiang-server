/*
 * Copyright (c) 2019
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.biz;

import com.google.common.collect.Lists;
import com.jiazhe.youxiang.base.util.JacksonUtil;
import com.jiazhe.youxiang.server.common.constant.CommonConstant;
import com.jiazhe.youxiang.server.common.enums.ChargeOffCodeEnum;
import com.jiazhe.youxiang.server.common.enums.ChargeOffStatusEnum;
import com.jiazhe.youxiang.server.common.enums.ChargeOffTypeEnum;
import com.jiazhe.youxiang.server.common.enums.LoginCodeEnum;
import com.jiazhe.youxiang.server.common.exceptions.ChargeOffException;
import com.jiazhe.youxiang.server.common.exceptions.LoginException;
import com.jiazhe.youxiang.server.common.exceptions.PointException;
import com.jiazhe.youxiang.server.domain.po.PointExchangeCodePO;
import com.jiazhe.youxiang.server.domain.po.SysCityPO;
import com.jiazhe.youxiang.server.dto.chargeoff.ChargeOffAddDTO;
import com.jiazhe.youxiang.server.dto.chargeoff.ChargeOffDTO;
import com.jiazhe.youxiang.server.dto.chargeoff.ChargeOffFuzzyQueryDTO;
import com.jiazhe.youxiang.server.dto.chargeoff.ChargeOffInfoDTO;
import com.jiazhe.youxiang.server.dto.chargeoff.ChargeOffPointDTO;
import com.jiazhe.youxiang.server.dto.chargeoff.ChargeOffQueryDTO;
import com.jiazhe.youxiang.server.dto.chargeoff.ChargeOffUpdateDTO;
import com.jiazhe.youxiang.server.dto.customer.CustomerAddDTO;
import com.jiazhe.youxiang.server.dto.customer.CustomerDTO;
import com.jiazhe.youxiang.server.dto.sysuser.SysUserDTO;
import com.jiazhe.youxiang.server.service.ChargeOffService;
import com.jiazhe.youxiang.server.service.CustomerService;
import com.jiazhe.youxiang.server.service.SysCityService;
import com.jiazhe.youxiang.server.service.point.PointExchangeCodeService;
import com.jiazhe.youxiang.server.vo.Paging;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.toMap;


/**
 * 核销管理Biz
 *
 * @author niexiao
 * @created 2020-03-04
 */
@Service
public class ChargeOffBiz {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChargeOffBiz.class);

    @Autowired
    private ChargeOffService chargeOffService;

    @Autowired
    private PointExchangeCodeService pointExchangeCodeService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private SysCityService sysCityService;

    /**
     * 消分专用手机
     */
    private String chargeOffSpecifiedMobile;

    @Value("${chargeoff.specified-mobile}")
    public void setChargeOffSpecifiedMobile(String mobile) {
        chargeOffSpecifiedMobile = mobile;
    }

    @Transactional
    public void add(ChargeOffAddDTO dto) {
        LOGGER.info("Biz调用[add]方法,入参:{}", JacksonUtil.toJSon(dto));
        //验证密码有效性，获得兑换码信息
        List<ChargeOffPointDTO> chargeOffPointDTOList = validateKeytList(dto.getKeytList());
        //填充城市信息
        SysCityPO sysCityPO = sysCityService.getCityByCityCode(dto.getCityCode());
        if (sysCityPO == null) {
            throw new ChargeOffException(ChargeOffCodeEnum.CUSTOMER_MOBILE_IS_NULL);
        }
        dto.setCityName(sysCityPO.getCityName());
        //填充提交人信息
        SysUserDTO sysUserDTO = (SysUserDTO) SecurityUtils.getSubject().getPrincipal();
        if (null == sysUserDTO) {
            throw new LoginException(LoginCodeEnum.LOGIN_NOT_SIGNIN_IN);
        }
        dto.setSubmitterId(sysUserDTO.getId());
        dto.setSubmitterName(sysUserDTO.getLoginName());
        //当状态时已提交时,否则只是保存一下
        if (ChargeOffStatusEnum.COMMITTED.equals(ChargeOffStatusEnum.getByCode(dto.getStatus()))) {
            //进行实际核销
            doChargeOff(dto);
        }
        //保存核销信息
        chargeOffService.add(dto);
        //保存核销详情
        chargeOffService.addDetail(chargeOffPointDTOList);
    }

    @Transactional
    public void update(ChargeOffUpdateDTO dto) {
        LOGGER.info("Biz调用[update]方法,入参:{}", JacksonUtil.toJSon(dto));
        //验证密码有效性，获得兑换码信息
        List<ChargeOffPointDTO> chargeOffPointDTOList = validateKeytList(dto.getKeytList());
        //当状态时已提交时,否则只是保存一下
        if (ChargeOffStatusEnum.COMMITTED.equals(ChargeOffStatusEnum.getByCode(dto.getStatus()))) {
            //进行实际核销
            doChargeOff(dto);
        }
        //更新核销信息
        chargeOffService.update(dto);
        //更新核销详情
        chargeOffService.updateDetail(chargeOffPointDTOList);
    }

    public void delete(Integer chargeOffId) {
        LOGGER.info("Biz调用[delete]方法,chargeOffId:{}", chargeOffId);
        chargeOffService.delete(chargeOffId);
    }

    public ChargeOffInfoDTO queryById(Integer chargeOffId) {
        LOGGER.info("Biz调用[queryById]方法,chargeOffId:{}", chargeOffId);
        return chargeOffService.queryById(chargeOffId);
    }

    public List<ChargeOffInfoDTO> fuzzyQuery(ChargeOffFuzzyQueryDTO dto, Paging paging) {
        LOGGER.info("Biz调用[fuzzyQuery]方法,入参:{}", JacksonUtil.toJSon(dto));
        //如果提交人信息没有填，则默认使用当前登录用户
        if (null == dto.getSubmitterId()) {
            SysUserDTO sysUserDTO = (SysUserDTO) SecurityUtils.getSubject().getPrincipal();
            if (null == sysUserDTO) {
                throw new LoginException(LoginCodeEnum.LOGIN_NOT_SIGNIN_IN);
            }
            dto.setSubmitterId(sysUserDTO.getId());
        }
        return chargeOffService.fuzzyQuery(dto, paging);
    }

    public List<ChargeOffInfoDTO> query(ChargeOffQueryDTO dto, Paging paging) {
        LOGGER.info("Biz调用[query]方法,入参:{}", JacksonUtil.toJSon(dto));
        return chargeOffService.query(dto, paging);
    }

    public ChargeOffPointDTO validateKeyt(String keyt) {
        LOGGER.info("Biz调用[validateKeyt]方法,keyt:{}", keyt);
        List<ChargeOffPointDTO> dtoList = validateKeytList(Lists.newArrayList(keyt));
        if (CollectionUtils.isEmpty(dtoList)) {
            throw new ChargeOffException(ChargeOffCodeEnum.KEYT_ERROR, "兑换码不存在" + ",密码:" + keyt);
        }
        return dtoList.get(0);
    }

    public void exportDetail(ChargeOffQueryDTO dto) {
        LOGGER.info("Biz调用[exportDetail]方法,入参:{}", JacksonUtil.toJSon(dto));
        //TODO niexiao
    }

    public List<ChargeOffPointDTO> validateKeytList(List<String> keytList) {
        if (CollectionUtils.isEmpty(keytList)) {
            return null;
        }
        List<ChargeOffPointDTO> result = Lists.newArrayList();
        List<PointExchangeCodePO> pointExchangeCodePOList = pointExchangeCodeService.batchFindByKeyt(keytList);
        //建立一个map key是密码，value是兑换码对象
        Map<String, PointExchangeCodePO> pointExchangeCodePOMap = pointExchangeCodePOList.stream().collect(toMap(PointExchangeCodePO::getKeyt, Function.identity()));
        keytList.stream().forEach(item -> {
            PointExchangeCodePO pointExchangeCodePO = pointExchangeCodePOMap.get(item);
            if (pointExchangeCodePO == null) {
                throw new ChargeOffException(ChargeOffCodeEnum.KEYT_ERROR, "兑换码不存在" + ",密码:" + item);
            }
            try {
                pointExchangeCodeService.check(pointExchangeCodePO);
            } catch (PointException e) {
                throw new ChargeOffException(ChargeOffCodeEnum.KEYT_ERROR, e.getMessage() + ",密码:" + item);
            }
            ChargeOffPointDTO chargeOffPointDTO = new ChargeOffPointDTO();
            chargeOffPointDTO.setPointExchangeCodeId(pointExchangeCodePO.getId());
            chargeOffPointDTO.setPointExchangeCodeCode(pointExchangeCodePO.getCode());
            chargeOffPointDTO.setPointExchangeCodeKeyt(pointExchangeCodePO.getKeyt());
            chargeOffPointDTO.setPointName(pointExchangeCodePO.getPointName());
            chargeOffPointDTO.setPointValue(pointExchangeCodePO.getFaceValue());
            result.add(chargeOffPointDTO);
        });
        return result;
    }

    public void chargeOffKeytList(List<String> keytList, Integer customerId) {
        if (CollectionUtils.isEmpty(keytList)) {
            return;
        }
        keytList.stream().forEach(keyt -> {
            pointExchangeCodeService.codeCharge(CommonConstant.EXCHANGETYPE_QRCODE_CHARGE_OFF, customerId, keyt);
        });

    }

    /**
     * 进行实际的核销
     *
     * @param dto
     */
    private void doChargeOff(ChargeOffDTO dto) {
        //真正充积分的手机
        String chargeOffMobile = chargeOffSpecifiedMobile;
        if (ChargeOffTypeEnum.POINT.equals(ChargeOffTypeEnum.getByCode(dto.getChargeOffType()))) {
            chargeOffMobile = dto.getCustomerMobile();
        }
        CustomerDTO customerDTO = customerService.getByMobile(chargeOffMobile);
        if (customerDTO == null) {
            CustomerAddDTO customerAddDTO = new CustomerAddDTO();
            customerAddDTO.setName(dto.getCustomerName());
            customerAddDTO.setMobile(dto.getCustomerMobile());
            customerAddDTO.setRemark("核销积分自动创建");
            customerService.add(customerAddDTO);
            //创建后反查id
            customerDTO = customerService.getByMobile(dto.getCustomerMobile());
        }
        chargeOffKeytList(dto.getKeytList(), customerDTO.getId());
    }
}
