package com.jiazhe.youxiang.server.controller.boc;

import com.jiazhe.youxiang.base.controller.BaseController;
import com.jiazhe.youxiang.base.util.DateUtil;
import com.jiazhe.youxiang.base.util.boccc.BOCCCUtils;
import com.jiazhe.youxiang.server.biz.voucher.VoucherExchangeCodeBiz;
import com.jiazhe.youxiang.server.vo.resp.boc.BOCCCResp;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author TU
 * @description 中行信用卡接口  Bank Of China Credit Card
 * @date 2019-09-02.
 */
@RestController
@RequestMapping("boccc")
public class BOCCCController extends BaseController {

    public static Logger logger = LoggerFactory.getLogger(BOCCCController.class);

    @Autowired
    private VoucherExchangeCodeBiz voucherExchangeCodeBiz;

    /**
     * 中行退货前请求
     *
     * @param data
     * @return
     */
    @ApiOperation(value = "退货信息接口、退货验证", httpMethod = "POST", response = BOCCCResp.class, notes = "退货信息接口、退货验证")
    @RequestMapping(value = "/refundcheck", method = RequestMethod.POST)
    public Object refundCheck(@RequestParam("data") String data) {
        logger.info(data);
        BOCCCResp resp = new BOCCCResp();
        try {
            String reqJson = BOCCCUtils.publicDecrypt(data);
            if (null == reqJson) {
                resp.setStat("05");
                resp.setResult("解密异常");
            } else {
//                BOCCCRefundReq req = JacksonUtil.readValue(reqJson, BOCCCRefundReq.class);
//                VoucherExchangeCodeDTO dto = voucherExchangeCodeBiz.findByKeyt(req.getwInfo());
//                if (null == dto) {
//                    resp.setStat("03");
//                    resp.setResult("券码不存在");
//                }else{
//                    if (dto.getUsed().equals(CommonConstant.CODE_HAS_USED)) {
//                        resp.setStat("02");
//                        resp.setResult("券码已使用");
//                    }
//                    if (dto.getUsed().equals(CommonConstant.CODE_HAS_REFUND)) {
//                        resp.setStat("01");
//                        resp.setResult("重复退货");
//                    }
//                }
            }
        } catch (Exception e) {
            resp.setStat("99");
            resp.setResult("操作失败");
            logger.error("中行请求第三方退货验证接口失败，异常信息：" + e.getMessage());
        }
        resp.setDate(DateUtil.secondToStr(new Date()));
        return JSONObject.fromObject(resp);
    }

}