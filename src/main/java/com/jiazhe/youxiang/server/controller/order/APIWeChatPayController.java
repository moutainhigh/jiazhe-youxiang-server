package com.jiazhe.youxiang.server.controller.order;

import com.jiazhe.youxiang.base.util.IpAdrressUtil;
import com.jiazhe.youxiang.base.util.RandomUtil;
import com.jiazhe.youxiang.base.util.WeChatPayUtils;
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
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

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

    @Value("${spring.boot.admin.client.instance.service-url}")
    private String DOMAIN;

    @Value("${wechat_public.appid}")
    private String APP_ID;

    @Value("${wechat_public.secret}")
    private String APP_SECRET;

    @Value("${wechat_public.mchid}")
    private String MCH_ID;

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(APIWeChatPayController.class);

    @AppApi
    @ApiOperation(value = "通过code换取网页授权openid", httpMethod = "GET", response = OpenIdResp.class, notes = "通过code换取网页授权openid")
    @RequestMapping(value = "/getopenid", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.WECHAT_PAY, operate = "通过code换取网页授权openid", level = LogLevelEnum.LEVEL_1)
    public Object getOpenId(@ModelAttribute OpenIdReq req) {
        Map<String, String> param = new LinkedHashMap<>();
        param.put("appid", APP_ID);
        param.put("secret", APP_SECRET);
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
        UnifiedOrderResp unifiedOrderResp = new UnifiedOrderResp();
        //需要保证参数的顺序
        Map<String, String> param = new LinkedHashMap<>();
        param.put("appid", APP_ID);
        param.put("body", req.getBody());
        param.put("device_info", WeChatPayConstant.DEVICE_INFO);
        param.put("mch_id", MCH_ID);
        String nonceStr = RandomUtil.generateCode(WeChatPayConstant.NONCE_STR_LENGTH);
        param.put("nonce_str", nonceStr);
        param.put("notify_url", DOMAIN + WeChatPayConstant.NOTIFY_URL);
        System.out.println(DOMAIN);
        param.put("openid", req.getOpenId());
        param.put("out_trade_no", req.getOrderNo());
        param.put("spbill_create_ip", IpAdrressUtil.getIpAddress(request));
        param.put("total_fee", String.valueOf(req.getTotalFee()));
        param.put("trade_type", WeChatPayConstant.TRADE_TYPE);
        String sign = WeChatPayUtils.createSign("UTF-8", param, WeChatPayConstant.API_KEY);
        param.put("sign", sign);
        String requestXml = WeChatPayUtils.getRequestXml(param);
        String result = WeChatPayUtils.httpsRequest(WeChatPayConstant.UNIFIEDORDER_URL, "POST", requestXml);
        System.out.println(result);
        try {
            Map<String, String> map = WeChatPayUtils.doXMLParse(result);
            String returnCode = map.get("return_code");
            if (!returnCode.equals(SUCCESS)) {
                logger.info("发起预支付失败，原因：" + map.get("return_msg"));
                throw new WeChatPayException(WeChatPayCodeEnum.PRE_PAY_ERROR);
            }
            String resultCode = map.get("result_code");
            if (!resultCode.equals(SUCCESS)) {
                logger.info("发起预支付失败，原因：" + map.get("err_code_des"));
                throw new WeChatPayException(WeChatPayCodeEnum.PRE_PAY_ERROR);
            }
            //二次签名
            String timeStamp = String.valueOf(System.currentTimeMillis() / 1000);
            SortedMap<String, String> finalPackage = new TreeMap<>();
            finalPackage.put("appId", APP_ID);
            finalPackage.put("timeStamp", timeStamp);
            finalPackage.put("nonceStr", nonceStr);
            finalPackage.put("package", "prepay_id=" + map.get("prepay_id"));
            finalPackage.put("signType", "MD5");
            String signAgain = WeChatPayUtils.createSign("UTF-8", finalPackage, WeChatPayConstant.API_KEY);
            unifiedOrderResp.setAppId(APP_ID);
            unifiedOrderResp.setTimeStamp(timeStamp);
            unifiedOrderResp.setNonceStr(nonceStr);
            unifiedOrderResp.setPrepayId(map.get("prepay_id"));
            unifiedOrderResp.setSignType("MD5");
            unifiedOrderResp.setPaySign(signAgain);
            return ResponseFactory.buildResponse(unifiedOrderResp);
        } catch (Exception e) {
            logger.info("发起预支付失败，异常信息：" + e.getMessage());
            throw new WeChatPayException(WeChatPayCodeEnum.PRE_PAY_ERROR);
        }
    }

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
