package com.jiazhe.youxiang.server.controller.rechargecard;

import com.jiazhe.youxiang.base.controller.BaseController;
import com.jiazhe.youxiang.server.adapter.rechargecard.RCAdapter;
import com.jiazhe.youxiang.server.biz.rechargecard.RCBiz;
import com.jiazhe.youxiang.server.dto.rechargecard.rc.RCDTO;
import com.jiazhe.youxiang.server.vo.ResponseFactory;
import com.jiazhe.youxiang.server.vo.req.IdReq;
import com.jiazhe.youxiang.server.vo.req.rechargecard.rc.DirectChargeReq;
import com.jiazhe.youxiang.server.vo.req.rechargecard.rc.ExpiryTimeEditReq;
import com.jiazhe.youxiang.server.vo.resp.rechargecard.rc.RCResp;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tu
 * @description：充值卡【rc是rechargecard缩写】
 * @date 2018/10/21
 */
@RestController
@RequestMapping("api/rc")
public class APIRCController extends BaseController{

    @Autowired
    private RCBiz rcBiz;

    @ApiOperation(value = "startusing", httpMethod = "POST",notes = "启用充值卡兑换码")
    @RequestMapping(value = "/startusing", method = RequestMethod.POST)
    public Object startUsing(@ModelAttribute IdReq req) {
        //参数检查
        rcBiz.startUsing(req.getId());
        return ResponseFactory.buildSuccess();
    }

    @ApiOperation(value = "stopusing", httpMethod = "POST",notes = "停用充值卡兑换码")
    @RequestMapping(value = "/stopusing", method = RequestMethod.POST)
    public Object stopUsing(@ModelAttribute IdReq req) {
        //参数检查
        rcBiz.stopUsing(req.getId());
        return ResponseFactory.buildSuccess();
    }

    @ApiOperation(value = "changeexpirytime", httpMethod = "POST",notes = "修改充值卡兑换码过期时间")
    @RequestMapping(value = "/changeexpirytime", method = RequestMethod.POST)
    public Object changeExpiryTime(@ModelAttribute ExpiryTimeEditReq req) {
        //参数检查
        rcBiz.changeExpiryTime(req.getId(),req.getExpiryTime());
        return ResponseFactory.buildSuccess();
    }

    @ApiOperation(value = "findbycustomerid", httpMethod = "GET",notes = "根据客户id查询所有【未过期】充值卡")
    @RequestMapping(value = "/findbycustomerid", method = RequestMethod.POST)
    public Object changeExpiryTime(@ModelAttribute IdReq req) {
        //参数检查
        List<RCDTO> rcDTOList = rcBiz.findAllByCustomerId(req.getId());
        List<RCResp> rcRespList = rcDTOList.stream().map(RCAdapter::DTO2Resp).collect(Collectors.toList());
        return ResponseFactory.buildResponse(rcRespList);
    }

    @ApiOperation(value = "directcharge", httpMethod = "POST",notes = "后台直接给客户充值")
    @RequestMapping(value = "/directcharge", method = RequestMethod.POST)
    public Object directCharge(@ModelAttribute DirectChargeReq req) {
        //参数检查
        rcBiz.directCharge(req.getCustomerId(),req.getBatchId(),req.getFaceValue());
        return ResponseFactory.buildSuccess();
    }
}
