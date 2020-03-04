package com.jiazhe.youxiang.server.controller.chargeoff;

import com.jiazhe.youxiang.base.controller.BaseController;
import com.jiazhe.youxiang.base.util.PagingParamUtil;
import com.jiazhe.youxiang.base.validator.ChargeOffValidator;
import com.jiazhe.youxiang.server.biz.ChargeOffBiz;
import com.jiazhe.youxiang.server.common.annotation.CustomLog;
import com.jiazhe.youxiang.server.common.enums.LogLevelEnum;
import com.jiazhe.youxiang.server.common.enums.ModuleEnum;
import com.jiazhe.youxiang.server.vo.Paging;
import com.jiazhe.youxiang.server.vo.ResponseFactory;
import com.jiazhe.youxiang.server.vo.req.IdReq;
import com.jiazhe.youxiang.server.vo.req.chargeoff.ChargeOffAddReq;
import com.jiazhe.youxiang.server.vo.req.chargeoff.ChargeOffFuzzyQueryReq;
import com.jiazhe.youxiang.server.vo.req.chargeoff.ChargeOffQueryReq;
import com.jiazhe.youxiang.server.vo.req.chargeoff.ChargeOffUpdateReq;
import com.jiazhe.youxiang.server.vo.req.chargeoff.ValidateKeytReq;
import com.jiazhe.youxiang.server.vo.resp.chargeoff.ChargeOffResp;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 核销管理Controller
 *
 * @author niexiao
 * @created 2020/03/04
 */
@RestController
@RequestMapping("api/chargeoff")
public class APIChargeOffController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(APIChargeOffController.class);

    @Autowired
    private ChargeOffBiz chargeOffBiz;

    @ApiOperation(value = "添加核销记录", httpMethod = "POST", notes = "添加核销记录")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @CustomLog(moduleName = ModuleEnum.CHARGE_OFF, operate = "添加核销记录", level = LogLevelEnum.LEVEL_1)
    public Object add(@ModelAttribute ChargeOffAddReq req) {
        ChargeOffValidator.validateChargeOffAddReq(req);
        return ResponseFactory.buildSuccess();
    }

    @ApiOperation(value = "编辑核销记录", httpMethod = "POST", notes = "编辑核销记录")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @CustomLog(moduleName = ModuleEnum.CHARGE_OFF, operate = "编辑核销记录", level = LogLevelEnum.LEVEL_1)
    public Object update(@ModelAttribute ChargeOffUpdateReq req) {
        ChargeOffValidator.validateChargeOffUpdateReq(req);

        return ResponseFactory.buildSuccess();
    }

    @ApiOperation(value = "删除核销记录", httpMethod = "GET", notes = "删除核销记录")
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.CHARGE_OFF, operate = "删除核销记录", level = LogLevelEnum.LEVEL_1)
    public Object delete(@ModelAttribute IdReq req) {
        ChargeOffValidator.validateId(req);

        return ResponseFactory.buildSuccess();
    }

    @ApiOperation(value = "根据id查询核销记录", httpMethod = "GET", notes = "根据id查询核销记录", response = ChargeOffResp.class)
    @RequestMapping(value = "/querybyid", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.CHARGE_OFF, operate = "根据id查询核销记录", level = LogLevelEnum.LEVEL_1)
    public Object queryById(@ModelAttribute IdReq req) {
        ChargeOffValidator.validateId(req);
        return ResponseFactory.buildSuccess();
    }

    @ApiOperation(value = "模糊查询（小程序端）", httpMethod = "POST", notes = "模糊查询（小程序端）", response = ChargeOffResp.class, responseContainer = "List")
    @RequestMapping(value = "/fuzzyquery", method = RequestMethod.POST)
    @CustomLog(moduleName = ModuleEnum.CHARGE_OFF, operate = "模糊查询（小程序端）", level = LogLevelEnum.LEVEL_1)
    public Object fuzzyQuery(@ModelAttribute ChargeOffFuzzyQueryReq req) {
        ChargeOffValidator.validateChargeOffFuzzyQueryReq(req);
        Paging paging = PagingParamUtil.pagingParamSwitch(req);
        //用ResponseFactory将返回值包装
        return ResponseFactory.buildPaginationResponse(null, paging);
    }

    @ApiOperation(value = "条件查询（PC端）", httpMethod = "POST", notes = "条件查询（PC端）", response = ChargeOffResp.class, responseContainer = "List")
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    @CustomLog(moduleName = ModuleEnum.CHARGE_OFF, operate = "条件查询（PC端）", level = LogLevelEnum.LEVEL_1)
    public Object query(@ModelAttribute ChargeOffQueryReq req) {
        ChargeOffValidator.validateChargeOffQueryReq(req);
        Paging paging = PagingParamUtil.pagingParamSwitch(req);
        return ResponseFactory.buildPaginationResponse(null, paging);
    }

    @ApiOperation(value = "验证密码有效性", httpMethod = "POST", notes = "验证密码有效性")
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    @CustomLog(moduleName = ModuleEnum.CHARGE_OFF, operate = "验证密码有效性", level = LogLevelEnum.LEVEL_1)
    public Object validateKeyt(@ModelAttribute ValidateKeytReq req) {
        ChargeOffValidator.validateValidateKeytReq(req);
        return ResponseFactory.buildSuccess();
    }

    @ApiOperation(value = "导出核销详情（兑换密码粒度）", httpMethod = "POST", notes = "导出核销详情（兑换密码粒度）")
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    @CustomLog(moduleName = ModuleEnum.CHARGE_OFF, operate = "导出核销详情（兑换密码粒度）", level = LogLevelEnum.LEVEL_1)
    public Object exportDetail(@ModelAttribute ChargeOffQueryReq req) {
        ChargeOffValidator.validateChargeOffQueryReq(req);
        return ResponseFactory.buildSuccess();
    }
}
