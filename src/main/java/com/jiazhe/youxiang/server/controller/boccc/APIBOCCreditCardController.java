package com.jiazhe.youxiang.server.controller.boccc;

import com.jiazhe.youxiang.base.controller.BaseController;
import com.jiazhe.youxiang.base.util.JacksonUtil;
import com.jiazhe.youxiang.base.util.boccc.BOCCCUtils;
import com.jiazhe.youxiang.server.biz.voucher.VoucherExchangeCodeBiz;
import com.jiazhe.youxiang.server.dto.voucher.exchangecode.VoucherExchangeCodeDTO;
import com.jiazhe.youxiang.server.vo.ResponseFactory;
import com.jiazhe.youxiang.server.vo.req.boccc.BocccRefundReq;
import com.jiazhe.youxiang.server.vo.resp.boccc.BocccResp;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author TU
 * @description 中行信用卡接口  Bank Of China Credit Card
 * @date 2019-09-02.
 */
@RestController
@RequestMapping("boccc")
public class APIBOCCreditCardController extends BaseController {

    public static Logger logger = LoggerFactory.getLogger(APIBOCCreditCardController.class);

    @Autowired
    private VoucherExchangeCodeBiz voucherExchangeCodeBiz;

    /**
     * 中行退货前请求
     * @param data
     * @return
     */
    @ApiOperation(value = "退货信息接口、退货验证", httpMethod = "POST", response = BocccResp.class, notes = "退货信息接口、退货验证")
    @RequestMapping(value = "/refundcheck", method = RequestMethod.POST)
    public Object refundCheck(@RequestParam("data") String data) {
        BocccResp resp = new BocccResp();
        String reqJson = BOCCCUtils.publicDecrypt(data);
        if (null == reqJson) {

        } else {
            BocccRefundReq req = JacksonUtil.readValue(reqJson, BocccRefundReq.class);
            VoucherExchangeCodeDTO dto = voucherExchangeCodeBiz.findByKeyt(req.getwInfo());
            logger.info(req.getwInfo());
        }
        return ResponseFactory.buildResponse(resp);
    }

}
