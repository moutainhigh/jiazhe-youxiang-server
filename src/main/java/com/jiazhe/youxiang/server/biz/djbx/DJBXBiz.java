/*
 * Copyright (c) 2019
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.biz.djbx;

import com.jiazhe.youxiang.base.util.HttpUtil;
import com.jiazhe.youxiang.base.util.JacksonUtil;
import com.jiazhe.youxiang.base.util.RandomUtil;
import com.jiazhe.youxiang.server.adapter.CustomerAdapter;
import com.jiazhe.youxiang.server.biz.CustomerBiz;
import com.jiazhe.youxiang.server.common.constant.DJBXConstant;
import com.jiazhe.youxiang.server.common.enums.DJBXCodeEnum;
import com.jiazhe.youxiang.server.common.exceptions.DJBXException;
import com.jiazhe.youxiang.server.dto.customer.CustomerAddDTO;
import com.jiazhe.youxiang.server.dto.customer.CustomerDTO;
import com.jiazhe.youxiang.server.dto.djbx.AgentInfoDTO;
import com.jiazhe.youxiang.server.dto.djbx.DJBXPlaceOrderDTO;
import com.jiazhe.youxiang.server.dto.djbx.PointsQueryDTO;
import com.jiazhe.youxiang.server.service.order.OrderInfoService;
import com.jiazhe.youxiang.server.vo.req.djbx.HeaderReq;
import com.jiazhe.youxiang.server.vo.req.djbx.PointsConsumeParam;
import com.jiazhe.youxiang.server.vo.req.djbx.PointsConsumeReq;
import com.jiazhe.youxiang.server.vo.req.djbx.PointsQueryParam;
import com.jiazhe.youxiang.server.vo.req.djbx.PointsQueryReq;
import com.jiazhe.youxiang.server.vo.req.djbx.PointsTokenReq;
import com.jiazhe.youxiang.server.vo.resp.djbx.GetUserInfoResp;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


/**
 * 在这里编写类的功能描述
 *
 * @author niexiao
 * @created 2020-05-19
 */
@Service("djbxBiz")
public class DJBXBiz {

    public static Logger LOGGER = LoggerFactory.getLogger(DJBXBiz.class);

    @Autowired
    private CustomerBiz customerBiz;

    @Autowired
    private OrderInfoService orderInfoService;

    @Value("${djbx.api.getuserinfo}")
    private String DJBX_API_GETUSERINFO;

    @Value("${djbx.api.getPointsToken}")
    private String DJBX_API_GETPOINTSTOKEN;

    @Value("${djbx.api.pointsinfo}")
    private String DJBX_API_POINTSINFO;

    @Value("${djbx.api.pointsdeduct}")
    private String DJBX_API_POINTSDEDUCT;

    @Value("${djbx.api.sendverificode}")
    private String DJBX_API_SENDVERIFICODE;

    @Value("${djbx.secret}")
    private String DJBX_SECRET;

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
        if (resp == null || !SUCCESS_CODE.equals(resp.getCode())) {
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
            customerBiz.addDJBXAgent(customerAddDTO);
            //查询新建的客户
            customerDTO = customerBiz.getByMobile(customerAddDTO.getMobile());
        }
        return CustomerAdapter.customerDTO2AgentInfoDTO(customerDTO);
    }

    @Retryable(value = {DJBXException.class},
            maxAttempts = 3, backoff = @Backoff(delay = 5000L, multiplier = 2))
    public PointsQueryDTO queryPoints(String agentCode) {
        HeaderReq headerReq = new HeaderReq(RandomUtil.generateNumber(12), DJBXConstant.TRANS_CODE_QUERY_POINTS, DJBXConstant.SYS_CODE);
        PointsQueryParam pointsQueryParam = new PointsQueryParam(agentCode);
        PointsQueryReq req = new PointsQueryReq(headerReq, pointsQueryParam);
        String token = DJBXConstant.djbxTokenMap.get(DJBXConstant.DJBX_TOKEN_DEFAULT_KEY).toString();
        String reqStr = JacksonUtil.toJSon(req);
        String result;
        LOGGER.info("HTTP调用大家保险剩余积分接口，使用token:{},入参:{}", token, reqStr);
        result = HttpUtil.djbxHttpPost(DJBX_API_POINTSINFO, token, reqStr);
        LOGGER.info("HTTP调用大家保险剩余积分接口，返回值:{}", result);
        JSONObject json = JSONObject.fromObject(result);
        firstCheckResult(json);
        PointsQueryDTO dto = new PointsQueryDTO();
        if (null != json.get("resultInfo")) {
            String resultInfoStr = json.get("resultInfo").toString();
            JSONObject resultInfoJson = JSONObject.fromObject(resultInfoStr);
            dto.setAgentCode(resultInfoJson.get("agentCode").toString());
            dto.setPoints(new BigDecimal(resultInfoJson.get("points").toString()));
        }
        return dto;
    }

    public boolean consumePoints(PointsConsumeParam pointsConsumeParam) {
        HeaderReq headerReq = new HeaderReq(RandomUtil.generateNumber(12), DJBXConstant.TRANS_CODE_CONSUME_POINTS, DJBXConstant.SYS_CODE);
        PointsConsumeReq req = new PointsConsumeReq(headerReq, pointsConsumeParam);
        String token = DJBXConstant.djbxTokenMap.get(DJBXConstant.DJBX_TOKEN_DEFAULT_KEY).toString();
        String reqStr = JacksonUtil.toJSon(req);
        String result;
        LOGGER.info("HTTP调用大家保险核销积分接口，使用token:{},入参:{}", token, reqStr);
        result = HttpUtil.djbxHttpPost(DJBX_API_POINTSDEDUCT, token, reqStr);
        LOGGER.info("HTTP调用大家保险核销积分接口，返回值:{}", result);
        JSONObject json = JSONObject.fromObject(result);
        firstCheckResult(json);
        if (null != json.get("header")) {
            String headerStr = json.get("header").toString();
            JSONObject headerJson = JSONObject.fromObject(headerStr);
            if ("00".equals(headerJson.get("resultCode").toString())) {
                return true;
            } else {
                if (pointsConsumeParam.getTransactionType().equals(DJBXConstant.DJBX_TRANSACTIONTYPE_CONSUME)) {
                    //如果是扣分，则是下单，抛出下单失败，并给出原因
                    throw new DJBXException(DJBXCodeEnum.PLACE_ORDER_ERROR, headerJson.get("resultMessage").toString());
                } else {
                    //如果是退分，则是退单，抛出退单失败，并给出原因
                    throw new DJBXException(DJBXCodeEnum.PLACE_ORDER_ERROR, headerJson.get("resultMessage").toString());
                }

            }
        }
        return false;
    }

    @Async
    @Retryable(value = {DJBXException.class},
            maxAttempts = 3, backoff = @Backoff(delay = 5000L, multiplier = 2))
    public void getPointsToken() {
        PointsTokenReq req = new PointsTokenReq(DJBX_SECRET);
        String reqStr = JacksonUtil.toJSon(req);
        String result;
        LOGGER.info("HTTP调用大家保险token获取接口，入参:{}", reqStr);
        result = HttpUtil.djbxHttpPost(DJBX_API_GETPOINTSTOKEN, "", reqStr);
        LOGGER.info("HTTP调用大家保险token获取接口，返回值:{}", result);
        JSONObject json = JSONObject.fromObject(result);
        if (null != json.get("code") && "0".equals(json.get("code").toString())) {
            String token = json.get("token").toString();
            DJBXConstant.djbxTokenMap.put(DJBXConstant.DJBX_TOKEN_DEFAULT_KEY, token);
        } else {
            throw new DJBXException(DJBXCodeEnum.GET_TOKEN_ERROR);
        }
    }

    public void sendVerifiCode(String agentCode) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("agentCode", agentCode);
        String token = DJBXConstant.djbxTokenMap.get(DJBXConstant.DJBX_TOKEN_DEFAULT_KEY).toString();
        String reqStr = JacksonUtil.toJSon(jsonObject);
        String result;
        LOGGER.info("HTTP调用大家保险发送短信接口，使用token：{}，入参:{}", token, reqStr);
        result = HttpUtil.djbxHttpPost(DJBX_API_SENDVERIFICODE, token, reqStr);
        LOGGER.info("HTTP调用大家保险发送短信接口，返回值:{}", result);
        JSONObject json = JSONObject.fromObject(result);
        firstCheckResult(json);
        if (null == json.get("resultCode") || "1".equals(json.get("resultCode").toString())) {
            LOGGER.info("获取验证码失败，请稍候再试");
            throw new DJBXException(DJBXCodeEnum.GET_VERIFICODE_ERROR);
        }
    }

    //初次校验返回结果，判断token是否过期
    public void firstCheckResult(JSONObject json) {
        if (null != json.get("code") && DJBXConstant.TOKEN_INVALID_CODE.equals(json.get("code").toString())) {
            LOGGER.info("token过期，立马获取新的token重试");
            getPointsToken();
            throw new DJBXException(DJBXCodeEnum.TOKEN_INVALID);
        }
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

    /**
     * 大家保险代理人 下单
     *
     * @param dto
     */
    public void placeOrder(DJBXPlaceOrderDTO dto) {
        orderInfoService.djbxPlaceOrder(dto);
    }

    /**
     * 大家保险代理人 取消订单
     *
     * @param id         订单id
     * @param verifiCode 验证码
     */
    public void cancelOrder(Integer id, String verifiCode) {
        orderInfoService.djbxCancelOrder(id, verifiCode);
    }
}
