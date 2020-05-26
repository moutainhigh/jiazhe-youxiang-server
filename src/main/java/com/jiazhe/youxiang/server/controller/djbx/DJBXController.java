/*
 * Copyright (c) 2019
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.controller.djbx;

import com.alibaba.fastjson.JSONObject;
import com.jiazhe.youxiang.base.realm.AuthToken;
import com.jiazhe.youxiang.base.util.CommonValidator;
import com.jiazhe.youxiang.base.util.CookieUtil;
import com.jiazhe.youxiang.server.adapter.DJBXAdapter;
import com.jiazhe.youxiang.server.biz.djbx.DJBXBiz;
import com.jiazhe.youxiang.server.common.annotation.AppApi;
import com.jiazhe.youxiang.server.common.annotation.CustomLog;
import com.jiazhe.youxiang.server.common.constant.CommonConstant;
import com.jiazhe.youxiang.server.common.enums.DJBXCodeEnum;
import com.jiazhe.youxiang.server.common.enums.LogLevelEnum;
import com.jiazhe.youxiang.server.common.enums.LoginCodeEnum;
import com.jiazhe.youxiang.server.common.enums.LoginType;
import com.jiazhe.youxiang.server.common.enums.ModuleEnum;
import com.jiazhe.youxiang.server.common.exceptions.DJBXException;
import com.jiazhe.youxiang.server.common.exceptions.LoginException;
import com.jiazhe.youxiang.server.dto.djbx.AgentInfoDTO;
import com.jiazhe.youxiang.server.dto.djbx.DJBXPlaceOrderDTO;
import com.jiazhe.youxiang.server.dto.djbx.PointsQueryDTO;
import com.jiazhe.youxiang.server.vo.ResponseFactory;
import com.jiazhe.youxiang.server.vo.req.djbx.DJBXCancelOrderReq;
import com.jiazhe.youxiang.server.vo.req.djbx.DJBXPlaceOrderReq;
import com.jiazhe.youxiang.server.vo.req.djbx.ExternalLoginReq;
import com.jiazhe.youxiang.server.vo.resp.djbx.AgentInfoResp;
import com.jiazhe.youxiang.server.vo.resp.djbx.PointsQueryResp;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 在这里编写类的功能描述
 *
 * @author niexiao
 * @created 2020-05-19
 */
@RestController
@RequestMapping("api/djbx")
public class DJBXController {

    public static Logger LOGGER = LoggerFactory.getLogger(DJBXController.class);
    /**
     * 客户cookie失效时间1年【以秒计】
     */
    private static final int CUSTOMER_COOKIE_EXPIRY = 365 * 24 * 60 * 60;
    @Autowired
    private DJBXBiz djbxBiz;

    private final String APP_VALUE = "pointsshop";

    /**
     * 大家保险企业微信登录
     *
     * @param req
     * @return
     */
    @AppApi
    @ApiOperation(value = "大家保险企业微信登录", httpMethod = "GET", response = AgentInfoResp.class, notes = "大家保险企业微信登录")
    @RequestMapping(value = "/externallogin", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.DJBX, operate = "大家保险企业微信登录", level = LogLevelEnum.LEVEL_2)
    public Object externalLogin(@ModelAttribute ExternalLoginReq req, HttpServletResponse response) {
        LOGGER.error("HTTP调用[externalLogin]方法,参数为:{}", JSONObject.toJSON(req));
        // 参数校验
        CommonValidator.validateNull(req);
        if (!APP_VALUE.equals(req.getAppvalue())) {
            throw new DJBXException(DJBXCodeEnum.APPVALUE_ERROR);
        }
        if (StringUtils.isEmpty(req.getCode())) {
            throw new DJBXException(DJBXCodeEnum.CODE_IS_NULL);
        }
        AgentInfoDTO agentInfoDTO = djbxBiz.externalLogin(req.getAppvalue(), req.getCode());
        //进行用户登录
        Subject subject = SecurityUtils.getSubject();
        try {
            AuthToken authToken = new AuthToken(agentInfoDTO.getMobile(), "", LoginType.CUSTOMER.toString());
            subject.login(authToken);
        } catch (Exception e) {
            if (e instanceof IncorrectCredentialsException) {
                throw new LoginException(LoginCodeEnum.LOGIN_PASSWRLD_WRONG);
            }
        }
        subject.getSession().setTimeout(CommonConstant.NEVER);
        CookieUtil.addCookie(response, "JSESSIONID", subject.getSession().getId().toString(), CUSTOMER_COOKIE_EXPIRY);
        AgentInfoResp resp = DJBXAdapter.agentInfoDTO2Resp(agentInfoDTO);
        //返回数据设置sessionId
        resp.setSessionId(subject.getSession().getId().toString());
        return ResponseFactory.buildResponse(resp);
    }

    @AppApi
    @ApiOperation(value = "查询剩余积分", httpMethod = "GET", response = PointsQueryResp.class, notes = "查询剩余积分")
    @RequestMapping(value = "/querypoints")
    @CustomLog(moduleName = ModuleEnum.DJBX, operate = "查询剩余积分", level = LogLevelEnum.LEVEL_2)
    public Object queryPoints(@RequestParam("agentCode") String agentCode) {
        CommonValidator.validateNull(agentCode, new DJBXException(DJBXCodeEnum.AGENTCODE_IS_NULL));
        PointsQueryDTO dto = djbxBiz.queryPoints(agentCode);
        return ResponseFactory.buildResponse(DJBXAdapter.PointQueryDTO2Resp(dto));
    }

    @AppApi
    @ApiOperation(value = "发送验证码", httpMethod = "GET", response = PointsQueryResp.class, notes = "发送验证码")
    @RequestMapping(value = "/sendverficode")
    @CustomLog(moduleName = ModuleEnum.DJBX, operate = "发送验证码", level = LogLevelEnum.LEVEL_2)
    public Object sendVerifiCode(@RequestParam("agentCode") String agentCode) {
        CommonValidator.validateNull(agentCode, new DJBXException(DJBXCodeEnum.AGENTCODE_IS_NULL));
        djbxBiz.sendVerifiCode(agentCode);
        return ResponseFactory.buildSuccess();
    }

    @AppApi
    @ApiOperation(value = "下单", httpMethod = "POST", notes = "下单")
    @RequestMapping(value = "/placeorder")
    @CustomLog(moduleName = ModuleEnum.DJBX, operate = "下单", level = LogLevelEnum.LEVEL_2)
    public Object placeOrder(@ModelAttribute DJBXPlaceOrderReq req) {
        CommonValidator.validateNull(req.getAgentCode(), new DJBXException(DJBXCodeEnum.AGENTCODE_IS_NULL));
        CommonValidator.validateNull(req.getVerifiCode(), new DJBXException(DJBXCodeEnum.VERIFICODE_IS_NULL));
        //大家保险暂不考虑支持微信支付
        req.setCashSupport("false");
        DJBXPlaceOrderDTO djbxPlaceOrderDTO = DJBXAdapter.ReqDJBXPlaceOrder2DTOPlaceOrder(req);
        djbxPlaceOrderDTO.setServiceProductId(req.getProductId());
        djbxPlaceOrderDTO.setWorkerMobile("");
        djbxPlaceOrderDTO.setWorkerName("");
        djbxPlaceOrderDTO.setCost(BigDecimal.ZERO);
        djbxPlaceOrderDTO.setType(CommonConstant.DJBX_PLACE_ORDER);
        djbxPlaceOrderDTO.setRealServiceTime(new Date(req.getServiceTime()));
        djbxPlaceOrderDTO.setComments("");
        djbxBiz.placeOrder(djbxPlaceOrderDTO);
        return ResponseFactory.buildSuccess();
    }

    @AppApi
    @ApiOperation(value = "取消订单", httpMethod = "POST", notes = "取消订单")
    @RequestMapping(value = "/cancelorder")
    @CustomLog(moduleName = ModuleEnum.DJBX, operate = "取消订单", level = LogLevelEnum.LEVEL_2)
    public Object cancelOrder(@ModelAttribute DJBXCancelOrderReq req) {
        CommonValidator.validateNull(req.getVerifiCode(), new DJBXException(DJBXCodeEnum.VERIFICODE_IS_NULL));
        djbxBiz.cancelOrder(req.getId(), req.getVerifiCode());
        return ResponseFactory.buildSuccess();
    }

}
