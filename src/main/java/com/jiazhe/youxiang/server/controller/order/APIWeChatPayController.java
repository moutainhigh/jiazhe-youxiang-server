package com.jiazhe.youxiang.server.controller.order;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.jiazhe.youxiang.base.util.IpAdrressUtil;
import com.jiazhe.youxiang.base.util.RandomUtil;
import com.jiazhe.youxiang.base.util.WeChatPayUtils;
import com.jiazhe.youxiang.server.biz.order.OrderInfoBiz;
import com.jiazhe.youxiang.server.common.annotation.AppApi;
import com.jiazhe.youxiang.server.common.annotation.CustomLog;
import com.jiazhe.youxiang.server.common.constant.CommonConstant;
import com.jiazhe.youxiang.server.common.constant.WeChatPayConstant;
import com.jiazhe.youxiang.server.common.enums.LogLevelEnum;
import com.jiazhe.youxiang.server.common.enums.ModuleEnum;
import com.jiazhe.youxiang.server.common.enums.OrderCodeEnum;
import com.jiazhe.youxiang.server.common.enums.WeChatPayCodeEnum;
import com.jiazhe.youxiang.server.common.exceptions.OrderException;
import com.jiazhe.youxiang.server.common.exceptions.WeChatPayException;
import com.jiazhe.youxiang.server.controller.APISignInController;
import com.jiazhe.youxiang.server.dto.order.orderinfo.OrderInfoDTO;
import com.jiazhe.youxiang.server.vo.ResponseFactory;
import com.jiazhe.youxiang.server.vo.req.order.orderinfo.WeChatUnifiedOrderReq;
import com.jiazhe.youxiang.server.vo.resp.order.orderinfo.UnifiedOrderResp;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.RequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.logging.Logger;

/**
 * @author tu
 * @description：微信支付相关的api
 * @date 2018/12/9
 */

@RestController
@RequestMapping("api/wxpay")
public class APIWeChatPayController {

    @Autowired
    private OrderInfoBiz orderInfoBiz;

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(APIWeChatPayController.class);

    @AppApi
    @ApiOperation(value = "微信统一下单", httpMethod = "POST", response = UnifiedOrderResp.class, notes = "微信统一下单")
    @RequestMapping(value = "/unifiedorder", method = RequestMethod.POST)
    @CustomLog(moduleName = ModuleEnum.ORDER, operate = "微信统一下单", level = LogLevelEnum.LEVEL_1)
    public Object unifiedOrder(@ModelAttribute WeChatUnifiedOrderReq req, HttpServletRequest request) {
        UnifiedOrderResp unifiedOrderResp = new UnifiedOrderResp();
        SortedMap<String, Object> wxparam = new TreeMap<String, Object>();
        wxparam.put("appid", WeChatPayConstant.APP_ID);
        wxparam.put("mch_id", WeChatPayConstant.MCH_ID);
        String nonceStr = RandomUtil.generateVerifyCode(32);
        OrderInfoDTO orderInfoDTO = orderInfoBiz.getByOrderNo(req.getOutTradeNo());
        if (null == orderInfoDTO) {
            throw new OrderException(OrderCodeEnum.ORDER_NOT_EXIST);
        }
        wxparam.put("nonce_str", nonceStr);
        wxparam.put("body", req.getBody());
        wxparam.put("out_trade_no", req.getOutTradeNo());
        wxparam.put("total_fee", req.getTotalFee());
        wxparam.put("spbill_create_ip", IpAdrressUtil.getIpAddress(request));
        wxparam.put("notify_url", WeChatPayConstant.NOTIFY_URL);
        wxparam.put("trade_type", WeChatPayConstant.TRADE_TYPE);
        String sign = WeChatPayUtils.createSign("UTF-8", wxparam, WeChatPayConstant.API_KEY);
        wxparam.put("sign", sign);
        String requestXml = WeChatPayUtils.getRequestXml(wxparam);
        String result = WeChatPayUtils.httpsRequest(WeChatPayConstant.URL, "POST", requestXml);
        SortedMap<String, Object> signParam = new TreeMap<String, Object>();
        try {
            Map<String, String> map = WeChatPayUtils.doXMLParse(result);
            String returnCode = map.get("return_code");
            String resultCode = map.get("result_code");
            if (returnCode.contains("SUCCESS") && resultCode.contains("SUCCESS")) {
                unifiedOrderResp.setPrepayId(map.get("prepay_id"));
//                signParam.put("appid", WeChatPayConstant.APP_ID);
//                signParam.put("partnerid", WeChatPayConstant.MCH_ID);
//                signParam.put("timeStamp", String.valueOf(System.currentTimeMillis() / 1000));
//                signParam.put("nonceStr", map.get("nonce_str"));
//                signParam.put("prepayid", map.get("prepay_id"));
//                signParam.put("package", "Sign=WXPay");
//                String signAgain = WeChatPayUtils.createSign("UTF-8", signParam, WeChatPayConstant.API_KEY);
//                signParam.put("paySign", signAgain);
//                unifiedOrderResp.setSign(signAgain);
                unifiedOrderResp.setSign(map.get("sign"));
                return ResponseFactory.buildResponse(unifiedOrderResp);
            } else {
                logger.info("发起预支付失败，原因：" + map.get("return_msg"));
                throw new WeChatPayException(WeChatPayCodeEnum.PRE_PAY_ERROR);
            }
        } catch (Exception e) {
            logger.info("发起预支付失败，异常信息：" + e.getMessage());
            throw new WeChatPayException(WeChatPayCodeEnum.PRE_PAY_ERROR);
        }
    }

    @ApiOperation(value = "接收微信付款结果通知", httpMethod = "POST", notes = "接收微信付款结果通知")
    @RequestMapping(value = "/notify", method = RequestMethod.POST)
    @CustomLog(moduleName = ModuleEnum.ORDER, operate = "接收微信付款通知", level = LogLevelEnum.LEVEL_1)
    public Object notify(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String strXml = WeChatPayUtils.parseRequst(request);
        Map<String, String> paynotifyMap = WeChatPayUtils.doXMLParse(strXml);
        if (paynotifyMap.get("result_code").toString().equalsIgnoreCase("SUCCESS")) {
            // 验签
            if (WeChatPayUtils.isTenpaySign(paynotifyMap, WeChatPayConstant.API_KEY)) {
                String orderNo = paynotifyMap.get("out_trade_no").toString();
                String transactionId = paynotifyMap.get("transaction_id").toString();
                Integer wxPay = new Integer(paynotifyMap.get("total_fee").toString());
                orderInfoBiz.wxNotify(transactionId,orderNo,wxPay);
            } else {
                logger.info("微信支付回调验证失败");
            }
        }
        return null;
    }


}
