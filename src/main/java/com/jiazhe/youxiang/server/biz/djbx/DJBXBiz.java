/*
 * Copyright (c) 2019
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.biz.djbx;

import com.jiazhe.youxiang.base.util.HttpUtil;
import com.jiazhe.youxiang.server.adapter.CustomerAdapter;
import com.jiazhe.youxiang.server.biz.CustomerBiz;
import com.jiazhe.youxiang.server.dto.customer.CustomerAddDTO;
import com.jiazhe.youxiang.server.dto.customer.CustomerDTO;
import com.jiazhe.youxiang.server.dto.djbx.AgentInfoDTO;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jiazhe.youxiang.server.common.exceptions.BOCCCException;
import com.jiazhe.youxiang.server.dto.djbx.PointsQueryDTO;
import com.jiazhe.youxiang.server.common.enums.DJBXCodeEnum;
import com.jiazhe.youxiang.server.common.exceptions.DJBXException;
import com.jiazhe.youxiang.server.vo.resp.djbx.GetUserInfoResp;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;


/**
 * 在这里编写类的功能描述
 *
 * @author niexiao
 * @created 2020-05-19
 */
@Service
public class DJBXBiz {

    public static Logger LOGGER = LoggerFactory.getLogger(DJBXBiz.class);

    @Autowired
    private CustomerBiz customerBiz;

    @Value("${djbx.api.getuserinfo}")
    private String DJBX_API_GETUSERINFO;
    @Value("${djbx.api.pointsinfo}")
    private String DJBX_API_POINTSINFO;

    private final Integer SUCCESS_CODE = 0;

    /**
     * 企业微信外部登录
     *
     * @param appvalue
     * @param code
     * @return
     */
    public AgentInfoDTO externalLogin(String appvalue, String code) {
        LOGGER.info("Biz调用[externalLogin]方法，appvalue:{},code:{}", appvalue, code);
        //TODO niexiao 调用大家保险的接口，获得代理人信息
        String respString = HttpUtil.sendPost(DJBX_API_GETUSERINFO, createParams(appvalue, code));
        GetUserInfoResp resp = createGetUserInfoResp(respString);
        LOGGER.info("Biz调用[externalLogin]方法，resp:{}", resp);
        if (resp == null && !SUCCESS_CODE.equals(resp.getCode())) {
            throw new DJBXException(DJBXCodeEnum.GET_USER_INFO_ERROR, resp.getMsg());
        }
        //TODO zhaoweixin 利用代理人信息，创建自己的用户，并使该用户在登录状态
        String agentCode = resp.getAgentCode();
        CustomerDTO customerDTO = customerBiz.getByMobile(agentCode);
        //判断客户在本系统中是否已存在
        if (customerDTO == null) {
            //不存在则新建客户
            CustomerAddDTO customerAddDTO = CustomerAdapter.getUserInfoResp2DTO(resp);
            customerAddDTO.setRemark(respString);//把获取的全部信息当做备注，便于客户信息后续使用
            customerBiz.add(customerAddDTO);
            //查询新建的客户
            customerDTO = customerBiz.getByMobile(customerAddDTO.getMobile());
        }
        return CustomerAdapter.customerDTO2AgentInfoDTO(customerDTO);
    }

    @Async
    @Retryable(value = {BOCCCException.class},
            maxAttempts = 10, backoff = @Backoff(delay = 5000L, multiplier = 2))
    public PointsQueryDTO queryPoints(String agentCode) {
//        PointsQueryReq req =  new PointsQueryReq();
        return null;
    }

    private String createParams(String appvalue, String code) {
        return "appvalue=" + appvalue + "&code=" + code;
    }

    private GetUserInfoResp createGetUserInfoResp(String str) {
        if (StringUtils.isEmpty(str)) {
            return null;
        }
        JSONObject jsonObject = JSONObject.fromObject(str);
        return createGetUserInfoResp(jsonObject);

    }

    private GetUserInfoResp createGetUserInfoResp(JSONObject jsonObject) {
        if (jsonObject == null) {
            return null;
        }
        GetUserInfoResp result = new GetUserInfoResp();
        result.setCode(getInt(jsonObject, "code"));
        result.setMsg(getString(jsonObject, "msg"));
//        result.setPhone(getString(jsonObject, "phone"));
        result.setAgentCode(getString(jsonObject, "agentCode"));
        result.setCmsAgentFlag(getBoolean(jsonObject, "cmsAgentFlag"));
//        result.setAppId(getString(jsonObject, "appId"));
        result.setRedirectPath(getString(jsonObject, "redirectPath"));
        result.setMenuFlag(getBoolean(jsonObject, "menuFlag"));
        result.setHeaderFlag(getBoolean(jsonObject, "headerFlag"));
        result.setSourceCode(getString(jsonObject, "sourceCode"));
        return result;
    }

    private String getString(JSONObject jsonObject, String key) {
        return jsonObject != null && jsonObject.has(key) ? jsonObject.getString(key) : "";
    }

    private Boolean getBoolean(JSONObject jsonObject, String key) {
        return jsonObject != null && jsonObject.has(key) ? jsonObject.getBoolean(key) : false;
    }

    private Integer getInt(JSONObject jsonObject, String key) {
        return jsonObject != null && jsonObject.has(key) ? jsonObject.getInt(key) : null;
    }


}
