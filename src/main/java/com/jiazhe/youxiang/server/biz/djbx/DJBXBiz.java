/*
 * Copyright (c) 2019
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.biz.djbx;

import com.jiazhe.youxiang.base.util.HttpUtil;
import com.jiazhe.youxiang.base.util.JacksonUtil;
import com.jiazhe.youxiang.base.util.RandomUtil;
import com.jiazhe.youxiang.server.common.constant.DJBXConstant;
import com.jiazhe.youxiang.server.common.enums.DJBXCodeEnum;
import com.jiazhe.youxiang.server.common.exceptions.BOCCCException;
import com.jiazhe.youxiang.server.common.exceptions.DJBXException;
import com.jiazhe.youxiang.server.dto.djbx.PointsQueryDTO;
import com.jiazhe.youxiang.server.vo.req.djbx.HeaderReq;
import com.jiazhe.youxiang.server.vo.req.djbx.PointsQueryReq;
import com.jiazhe.youxiang.server.vo.resp.djbx.GetUserInfoResp;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


/**
 * 在这里编写类的功能描述
 *
 * @author niexiao
 * @created 2020-05-19
 */
@Service
public class DJBXBiz {

    public static Logger LOGGER = LoggerFactory.getLogger(DJBXBiz.class);

    @Value("${djbx.api.getuserinfo}")
    private String DJBX_API_GETUSERINFO;
    @Value("${djbx.api.pointsinfo}")
    private String DJBX_API_POINTSINFO;
    @Value("${djbx.api.pointsdeduct}")
    private String DJBX_API_POINTSDEDUCT;

    private final Integer SUCCESS_CODE = 0;

    /**
     * 企业微信外部登录
     *
     * @param appvalue
     * @param code
     */
    public void externalLogin(String appvalue, String code) {
        LOGGER.info("Biz调用[externalLogin]方法，appvalue:{},code:{}", appvalue, code);
        //TODO niexiao 调用大家保险的接口，获得代理人信息
        String respString = HttpUtil.sendPost(DJBX_API_GETUSERINFO, createParams(appvalue, code));
        GetUserInfoResp resp = createGetUserInfoResp(respString);
        LOGGER.info("Biz调用[externalLogin]方法，resp:{}", resp);
        if (resp == null && !SUCCESS_CODE.equals(resp.getCode())) {
            throw new DJBXException(DJBXCodeEnum.GET_USER_INFO_ERROR, resp.getMsg());
        }
        //TODO zhaoweixin 利用代理人信息，创建自己的用户，并使该用户在登录状态

    }

    @Async
    @Retryable(value = {BOCCCException.class},
            maxAttempts = 10, backoff = @Backoff(delay = 5000L, multiplier = 2))
    public PointsQueryDTO queryPoints(String agentCode) {
        HeaderReq headerReq = new HeaderReq(RandomUtil.generateNumber(12), DJBXConstant.TRANS_CODE_POINTS_QUERY, DJBXConstant.SYS_CODE);
        PointsQueryReq req = new PointsQueryReq(headerReq, agentCode);
        String result;
        LOGGER.info("HTTP调用大家保险剩余积分接口，入参:{}", req);
        //result = HttpUtil.djbxHttpPost(DJBX_API_POINTSINFO, DJBXConstant.djbxTokenMap.get("DJBX_DEFAULT_TOKEN").toString(), JacksonUtil.toJSon(req));
        result = HttpUtil.djbxHttpPost("https://life-work-wechat.djbx.com/dev/points/pointsshop/pointsinfo", DJBXConstant.djbxTokenMap.get("DJBX_DEFAULT_TOKEN").toString(), JacksonUtil.toJSon(req));
        System.out.println(result);
        return null;
    }

    public static void main(String[] args) {
        DJBXBiz biz = new DJBXBiz();
        biz.queryPoints("GX02001210");
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
