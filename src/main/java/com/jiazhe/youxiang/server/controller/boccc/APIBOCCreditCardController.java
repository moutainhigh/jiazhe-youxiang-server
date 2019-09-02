package com.jiazhe.youxiang.server.controller.boccc;

import com.jiazhe.youxiang.base.controller.BaseController;
import com.jiazhe.youxiang.server.biz.boccc.BOCCCBiz;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author TU
 * @description  中行信用卡接口  Bank Of China Credit Card
 * @date 2019-09-02.
 */
@RestController
@RequestMapping("boccc")
public class APIBOCCreditCardController extends BaseController{

    private BOCCCBiz bocccBiz ;

}
