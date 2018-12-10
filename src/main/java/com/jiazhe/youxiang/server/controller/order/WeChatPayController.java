package com.jiazhe.youxiang.server.controller.order;

import com.jiazhe.youxiang.base.util.IpAdrressUtil;
import com.jiazhe.youxiang.base.util.RandomUtil;
import com.jiazhe.youxiang.base.util.WeChatPayUtils;
import com.jiazhe.youxiang.server.common.annotation.AppApi;
import com.jiazhe.youxiang.server.common.annotation.CustomLog;
import com.jiazhe.youxiang.server.common.constant.WeChatPayConstant;
import com.jiazhe.youxiang.server.common.enums.LogLevelEnum;
import com.jiazhe.youxiang.server.common.enums.ModuleEnum;
import com.jiazhe.youxiang.server.vo.ResponseFactory;
import com.jiazhe.youxiang.server.vo.req.order.orderinfo.WeChatUnifiedOrderReq;
import com.jiazhe.youxiang.server.vo.resp.order.orderinfo.UnifiedOrderResp;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
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
public class WeChatPayController {

    @AppApi
    @ApiOperation(value = "微信统一下单", httpMethod = "POST", response = UnifiedOrderResp.class,notes = "微信统一下单")
    @RequestMapping(value = "/unifiedorder", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.ORDER, operate = "微信统一下单", level = LogLevelEnum.LEVEL_1)
    public Object unifiedOrder(@ModelAttribute WeChatUnifiedOrderReq req, HttpServletRequest request) {
        UnifiedOrderResp unifiedOrderResp = new UnifiedOrderResp();
        SortedMap<String, Object> wxparam = new TreeMap<String, Object>();
        wxparam.put("appid", WeChatPayConstant.APP_ID);
        wxparam.put("mch_id",WeChatPayConstant.MCH_ID);
        String nonceStr = RandomUtil.generateVerifyCode(20);
        wxparam.put("nonce_str",nonceStr);
        wxparam.put("body", req.getBody());
        wxparam.put("notify_url",WeChatPayConstant.NOTIFY_URL);
        wxparam.put("out_trade_no",req.getOut_trade_no());
        wxparam.put("total_fee",req.getTotal_fee());
        wxparam.put("trade_type","JSAPI");
        wxparam.put("openid",req.getOpen_id());
        String ip = IpAdrressUtil.getIpAdrress(request);
        String sign = WeChatPayUtils.createSign("UTF-8",wxparam,"apiKey");
        wxparam.put("sign",sign);
        String requestXml = WeChatPayUtils.getRequestXml(wxparam);
        String result = WeChatPayUtils.httpsRequest(WeChatPayConstant.URL,"POST",requestXml);
        Map<String, String> map ;
        SortedMap<String, Object> signParam = new TreeMap<String, Object>();
        try{
            map = WeChatPayUtils.doXMLParse(result);
            String return_code = map.get("return_code");
            String prepay_id = null;
            if (return_code.contains("SUCCESS")) {
                prepay_id = map.get("prepay_id");
                unifiedOrderResp.setPrepay_id(prepay_id);
            }
            String seconds = String.valueOf(System.currentTimeMillis()/1000);
            signParam.put("appId", WeChatPayConstant.APP_ID);
            signParam.put("package", "prepay_id=" + prepay_id);//默认sign=WXPay
            signParam.put("nonceStr", WeChatPayUtils.getRandomString(32));
            signParam.put("timeStamp", seconds);//北京时间时间戳
            signParam.put("signType", "MD5");//
            String signAgain = WeChatPayUtils.createSign("UTF-8", signParam, "apiKey");//再次生成签名
            signParam.put("paySign", signAgain);
            unifiedOrderResp.setSign(signAgain);
        }catch (Exception e){
        }
        return ResponseFactory.buildResponse(unifiedOrderResp);
    }





}
