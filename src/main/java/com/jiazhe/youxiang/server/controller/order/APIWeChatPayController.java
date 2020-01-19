package com.jiazhe.youxiang.server.controller.order;

import com.jiazhe.youxiang.base.util.WeChatPayUtils;
import com.jiazhe.youxiang.server.biz.WeChatPayBiz;
import com.jiazhe.youxiang.server.biz.order.OrderInfoBiz;
import com.jiazhe.youxiang.server.common.annotation.AppApi;
import com.jiazhe.youxiang.server.common.annotation.CustomLog;
import com.jiazhe.youxiang.server.common.constant.WeChatPayConstant;
import com.jiazhe.youxiang.server.common.enums.LogLevelEnum;
import com.jiazhe.youxiang.server.common.enums.ModuleEnum;
import com.jiazhe.youxiang.server.common.enums.OrderCodeEnum;
import com.jiazhe.youxiang.server.common.enums.WeChatPayCodeEnum;
import com.jiazhe.youxiang.server.common.exceptions.OrderException;
import com.jiazhe.youxiang.server.common.exceptions.WeChatPayException;
import com.jiazhe.youxiang.server.dto.order.orderinfo.OrderInfoDTO;
import com.jiazhe.youxiang.server.vo.ResponseFactory;
import com.jiazhe.youxiang.server.vo.req.order.orderinfo.OpenIdReq;
import com.jiazhe.youxiang.server.vo.req.order.orderinfo.WeChatUnifiedOrderReq;
import com.jiazhe.youxiang.server.vo.resp.order.orderinfo.OpenIdResp;
import com.jiazhe.youxiang.server.vo.resp.order.orderinfo.UnifiedOrderResp;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author tu
 * @description：微信支付相关的api
 * @date 2018/12/9
 */

@RestController
@RequestMapping("api/wxpay")
public class APIWeChatPayController {

    private static String SUCCESS = "SUCCESS";

    @Autowired
    private OrderInfoBiz orderInfoBiz;
    @Autowired
    private WeChatPayBiz weChatPayBiz;

    private static final Logger logger = LoggerFactory.getLogger(APIWeChatPayController.class);

    @AppApi
    @ApiOperation(value = "通过code换取网页授权openid", httpMethod = "GET", response = OpenIdResp.class, notes = "通过code换取网页授权openid")
    @RequestMapping(value = "/getopenid", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.WECHAT_PAY, operate = "通过code换取网页授权openid", level = LogLevelEnum.LEVEL_1)
    public Object getOpenId(@ModelAttribute OpenIdReq req) {
        Map<String, String> param = new LinkedHashMap<>();
        param.put("appid", WeChatPayConstant.APP_ID);
        param.put("secret", WeChatPayConstant.APP_SECRET);
        param.put("code", req.getCode());
        param.put("grant_type", "authorization_code");
        StringBuilder url = new StringBuilder(WeChatPayConstant.AUTH_URL);
        for (String k : param.keySet()) {
            url.append(k).append("=").append(param.get(k)).append("&");
        }
        url.deleteCharAt(url.length() - 1);
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url.toString(), String.class);
        if (result.contains("errcode")) {
            throw new WeChatPayException(WeChatPayCodeEnum.GET_OPENID_ERROR.getCode(), WeChatPayCodeEnum.GET_OPENID_ERROR.getType(), result);
        } else {
            return ResponseFactory.buildResponse(result);
        }
    }

    @AppApi
    @ApiOperation(value = "微信统一下单", httpMethod = "POST", response = UnifiedOrderResp.class, notes = "微信统一下单")
    @RequestMapping(value = "/unifiedorder", method = RequestMethod.POST)
    @CustomLog(moduleName = ModuleEnum.WECHAT_PAY, operate = "微信统一下单", level = LogLevelEnum.LEVEL_2)
    public Object unifiedOrder(@ModelAttribute WeChatUnifiedOrderReq req, HttpServletRequest request) {
        OrderInfoDTO orderInfoDTO = orderInfoBiz.getById(req.getOrderId());
        if (null == orderInfoDTO) {
            throw new OrderException(OrderCodeEnum.ORDER_NOT_EXIST);
        }
        UnifiedOrderResp unifiedOrderResp = weChatPayBiz.unifiedOrder(req, request);
        return ResponseFactory.buildResponse(unifiedOrderResp);

    }

    /**
     * 支付成功/失败通知
     *
     * @param request
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "接收微信付款结果通知", httpMethod = "POST", notes = "接收微信付款结果通知")
    @RequestMapping(value = "/notify", method = RequestMethod.POST)
    @CustomLog(moduleName = ModuleEnum.WECHAT_PAY, operate = "接收微信付款通知", level = LogLevelEnum.LEVEL_2)
    public String notify(HttpServletRequest request) throws Exception {
        try {
            String strXml = WeChatPayUtils.parseRequest(request);
            Map<String, String> payNotifyMap = WeChatPayUtils.doXMLParse(strXml);
            if (payNotifyMap.get("return_code").equalsIgnoreCase(SUCCESS)) {
                // 支付成功后验签
                if (WeChatPayUtils.isTenpaySign(payNotifyMap, WeChatPayConstant.API_KEY)) {
                    String orderNo = payNotifyMap.get("out_trade_no").toString();
                    String transactionId = payNotifyMap.get("transaction_id").toString();
                    Integer wxPay = new Integer(payNotifyMap.get("cash_fee").toString());
                    if (payNotifyMap.get("result_code").equals(SUCCESS)) {
                        orderInfoBiz.wxNotify(transactionId, orderNo, wxPay);
                    } else {
                        logger.info("微信付款通知成功，业务失败：" + payNotifyMap.get("result_code"));
                    }
                } else {
                    logger.info("微信付款通知成功，业务失败：验签失败");
                }
            } else {
                logger.info("微信付款通知成功，业务失败：" + payNotifyMap.get("return_msg"));
            }
        } catch (Exception e) {
            logger.error("微信付款通知成功，业务失败：异常信息" + e.getMessage());
        } finally {
            return generateNotifyXml();
        }
    }

    /**
     * 退款通知url
     *
     * @param request
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "接收微信退款结果通知", httpMethod = "POST", notes = "接收微信退款结果通知")
    @RequestMapping(value = "/refundnotify", method = RequestMethod.POST)
    @CustomLog(moduleName = ModuleEnum.WECHAT_PAY, operate = "接收微信退款通知", level = LogLevelEnum.LEVEL_2)
    public String refundNotify(HttpServletRequest request) throws Exception {
        try {
            String strXml = WeChatPayUtils.parseRequest(request);
            Map<String, String> refundNotifyMap = WeChatPayUtils.doXMLParse(strXml);
            if (SUCCESS.equalsIgnoreCase(refundNotifyMap.get("return_code"))) {
                // 退款成功后解析字符串
                String reqInfo = refundNotifyMap.get("req_info");
                Map<String, String> reqInfoMap = WeChatPayUtils.refundResultDecrypt(reqInfo);
                if (null != reqInfo) {
                    String refundStatus = reqInfoMap.get("refund_status");
                    if (SUCCESS.equalsIgnoreCase(refundStatus)) {
                        String orderNo = reqInfoMap.get("out_trade_no").toString();
                        Integer wxRefund = new Integer(reqInfoMap.get("settlement_refund_fee").toString());
                        String refundId = reqInfoMap.get("refundId").toString();
                        orderInfoBiz.wxRefundNotify(refundId, orderNo, wxRefund);
                    }
                } else {
                    logger.info("微信退款通知成功，业务失败：原因未知");
                }
            } else {
                logger.info("微信退款通知成功，业务失败：" + refundNotifyMap.get("return_msg"));
            }
        } catch (Exception e) {
            logger.error("微信付款通知成功，业务失败：异常信息" + e.getMessage());
        } finally {
            return generateNotifyXml();
        }
    }

    private String generateNotifyXml() {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("return_code", "SUCCESS");
        map.put("return_msg", "OK");
        return WeChatPayUtils.getRequestXml(map);
    }


}
