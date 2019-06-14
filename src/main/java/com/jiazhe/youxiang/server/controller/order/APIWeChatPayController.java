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
import com.jiazhe.youxiang.server.vo.req.order.orderinfo.WeChatUnifiedOrderReq;
import com.jiazhe.youxiang.server.vo.resp.order.orderinfo.UnifiedOrderResp;
import io.swagger.annotations.ApiOperation;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    @Autowired
    private OrderInfoBiz orderInfoBiz;

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(APIWeChatPayController.class);

    @AppApi
    @ApiOperation(value = "微信统一下单", httpMethod = "GET", response = UnifiedOrderResp.class, notes = "微信统一下单")
    @RequestMapping(value = "/unifiedorder", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.ORDER, operate = "微信统一下单", level = LogLevelEnum.LEVEL_1)
    public Object unifiedOrder(@ModelAttribute WeChatUnifiedOrderReq req, HttpServletRequest request) {
        OrderInfoDTO orderInfoDTO = orderInfoBiz.getById(req.getOrderId());
        if (null == orderInfoDTO) {
            throw new OrderException(OrderCodeEnum.ORDER_NOT_EXIST);
        }
        UnifiedOrderResp unifiedOrderResp = new UnifiedOrderResp();
        //需要保证参数的顺序
        Map<String, String> param = new LinkedHashMap<>();
        param.put("appid", WeChatPayConstant.APP_ID);
        param.put("body", req.getBody());
        param.put("device_info",WeChatPayConstant.DEVICE_INFO);
        param.put("mch_id", WeChatPayConstant.MCH_ID);
        String nonceStr = RandomUtil.generateCode(WeChatPayConstant.NONCE_STR_LENGTH);
        param.put("nonce_str", nonceStr);
        param.put("notify_url", WeChatPayConstant.NOTIFY_URL);
        param.put("out_trade_no", req.getOrderNo());
        param.put("spbill_create_ip", IpAdrressUtil.getIpAddress(request));
        param.put("total_fee", String.valueOf(req.getTotalFee()));
        param.put("trade_type", WeChatPayConstant.TRADE_TYPE);
        String sign = WeChatPayUtils.createSign("UTF-8", param, WeChatPayConstant.API_KEY);
        param.put("sign", sign);
        String requestXml = WeChatPayUtils.getRequestXml(param);
        String result = WeChatPayUtils.httpsRequest(WeChatPayConstant.URL, "POST", requestXml);
        System.out.println(result);
        try {
            Map<String, String> map = WeChatPayUtils.doXMLParse(result);
            String returnCode = map.get("return_code");
            if(!returnCode.equals("SUCCESS")){
                logger.info("发起预支付失败，原因：" + map.get("return_msg"));
                throw new WeChatPayException(WeChatPayCodeEnum.PRE_PAY_ERROR);
            }
            String resultCode = map.get("result_code");
            if (!resultCode.equals("SUCCESS")) {
                logger.info("发起预支付失败，原因：" + map.get("err_code_des"));
                throw new WeChatPayException(WeChatPayCodeEnum.PRE_PAY_ERROR);
            }
            unifiedOrderResp.setPrepayId(map.get("prepay_id"));
            unifiedOrderResp.setPaySign(map.get("sign"));
            return ResponseFactory.buildResponse(unifiedOrderResp);
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
