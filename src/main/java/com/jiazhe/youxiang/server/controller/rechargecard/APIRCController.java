package com.jiazhe.youxiang.server.controller.rechargecard;

import com.jiazhe.youxiang.base.controller.BaseController;
import com.jiazhe.youxiang.server.adapter.rechargecard.RCAdapter;
import com.jiazhe.youxiang.server.biz.rechargecard.RCBiz;
import com.jiazhe.youxiang.server.dto.rechargecard.rc.RCDTO;
import com.jiazhe.youxiang.server.vo.Paging;
import com.jiazhe.youxiang.server.vo.ResponseFactory;
import com.jiazhe.youxiang.server.vo.req.IdReq;
import com.jiazhe.youxiang.server.vo.req.PageSizeNumReq;
import com.jiazhe.youxiang.server.vo.req.rechargecard.rc.DirectChargeReq;
import com.jiazhe.youxiang.server.vo.req.ExpiryTimeEditReq;
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

    @ApiOperation(value = "启用充值卡", httpMethod = "POST",notes = "启用充值卡")
    @RequestMapping(value = "/startusing", method = RequestMethod.POST)
    public Object startUsing(@ModelAttribute IdReq req) {
        //参数检查
        rcBiz.startUsing(req.getId());
        return ResponseFactory.buildSuccess();
    }

    @ApiOperation(value = "停用充值卡", httpMethod = "POST",notes = "停用充值卡")
    @RequestMapping(value = "/stopusing", method = RequestMethod.POST)
    public Object stopUsing(@ModelAttribute IdReq req) {
        //参数检查
        rcBiz.stopUsing(req.getId());
        return ResponseFactory.buildSuccess();
    }

    @ApiOperation(value = "修改充值卡过期时间", httpMethod = "POST",notes = "修改充值卡过期时间")
    @RequestMapping(value = "/changeexpirytime", method = RequestMethod.POST)
    public Object changeExpiryTime(@ModelAttribute ExpiryTimeEditReq req) {
        //参数检查
        rcBiz.changeExpiryTime(req.getId(),req.getExpiryTime());
        return ResponseFactory.buildSuccess();
    }

    @ApiOperation(value = "【后台展示】根据客户id查询所有充值卡，【分页】", httpMethod = "GET",response = RCResp.class, responseContainer = "List",notes = "【后台展示】根据客户id查询所有充值卡，【分页】")
    @RequestMapping(value = "/listpage", method = RequestMethod.POST)
    public Object listPage(@ModelAttribute IdReq idReq, @ModelAttribute PageSizeNumReq pageReq) {
        //参数检查
        Paging paging = new Paging();
        paging.setOffset((pageReq.getPageNum()-1)*pageReq.getPageSize());
        paging.setLimit(pageReq.getPageSize());
        List<RCDTO> rcDTOList = rcBiz.getList(idReq.getId(),paging);
        List<RCResp> rcRespList = rcDTOList.stream().map(RCAdapter::DTO2Resp).collect(Collectors.toList());
        return ResponseFactory.buildResponse(rcRespList);
    }

    @ApiOperation(value = "【app端使用】根据客户id查询所有【未过期】充值卡", httpMethod = "GET",response = RCResp.class, responseContainer = "List",notes = "根据客户id查询所有【未过期】充值卡")
    @RequestMapping(value = "/findunexpiredbycustomerid", method = RequestMethod.POST)
    public Object changeExpiryTime(@ModelAttribute IdReq req) {
        //参数检查
        List<RCDTO> rcDTOList = rcBiz.findUnexpiredByCustomerId(req.getId());
        List<RCResp> rcRespList = rcDTOList.stream().map(RCAdapter::DTO2Resp).collect(Collectors.toList());
        return ResponseFactory.buildResponse(rcRespList);
    }

    @ApiOperation(value = "后台直接给客户充值任意分数", httpMethod = "POST",notes = "后台直接给客户充值任意分数")
    @RequestMapping(value = "/directcharge", method = RequestMethod.POST)
    public Object directCharge(@ModelAttribute DirectChargeReq req) {
        //参数检查
        rcBiz.directCharge(req.getMobile(),req.getBatchId(),req.getFaceValue());
        return ResponseFactory.buildSuccess();
    }
}
