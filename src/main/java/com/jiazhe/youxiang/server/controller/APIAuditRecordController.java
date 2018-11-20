package com.jiazhe.youxiang.server.controller;

import com.jiazhe.youxiang.base.controller.BaseController;
import com.jiazhe.youxiang.base.util.CommonValidator;
import com.jiazhe.youxiang.base.util.PagingParamUtil;
import com.jiazhe.youxiang.server.adapter.AuditRecordAdapter;
import com.jiazhe.youxiang.server.biz.AuditRecordBiz;
import com.jiazhe.youxiang.server.common.annotation.AppApi;
import com.jiazhe.youxiang.server.dto.auditrecord.AuditRecordDTO;
import com.jiazhe.youxiang.server.vo.Paging;
import com.jiazhe.youxiang.server.vo.ResponseFactory;
import com.jiazhe.youxiang.server.vo.req.IdReq;
import com.jiazhe.youxiang.server.vo.req.auditrecord.*;
import com.jiazhe.youxiang.server.vo.resp.auditrecord.AuditRecordResp;
import com.jiazhe.youxiang.server.vo.resp.order.orderinfo.WaitingDealCountResp;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author TU
 * @description
 * @date 2018/10/24.
 */
@RestController
@RequestMapping("api/auditrecord")
public class APIAuditRecordController extends BaseController{

    private static final Logger LOGGER = LoggerFactory.getLogger(APIAuditRecordController.class);

    @Autowired
    private AuditRecordBiz auditRecordBiz;

    @ApiOperation(value = "审核通过", httpMethod = "POST",notes = "审核通过")
    @RequestMapping(value = "/auditrecordpass", method = RequestMethod.POST)
    public Object auditRecordPass(@ModelAttribute AuditRecordCheckReq req) {
        //参数检查
        auditRecordBiz.auditRecordPass(req.getId(),req.getRechargeCardCodeBatchId(),req.getReason());
        return ResponseFactory.buildSuccess();
    }

    @ApiOperation(value = "审核不通过", httpMethod = "POST",notes = "审核不通过")
    @RequestMapping(value = "/auditrecordunpass", method = RequestMethod.POST)
    public Object auditRecordUnpass(@ModelAttribute AuditRecordCheckReq req) {
        //参数检查
        auditRecordBiz.auditRecordUnpass(req.getId(),req.getReason());
        return ResponseFactory.buildSuccess();
    }

    @ApiOperation(value = "待审核消费记录条数", httpMethod = "GET",response = WaitingDealCountResp.class ,notes = "待审核消费记录条数")
    @RequestMapping(value = "/getwaitcheckcount", method = RequestMethod.GET)
    public Object getWaitCheckCount() {
        Integer count = auditRecordBiz.getWaitCheckCount();
        WaitingDealCountResp waitingDealCountResp = new WaitingDealCountResp();
        waitingDealCountResp.setCount(count);
        return ResponseFactory.buildResponse(waitingDealCountResp);
    }

    @ApiOperation(value = "获取消费记录详情", httpMethod = "GET",response = AuditRecordResp.class,notes = "获取消费记录详情")
    @RequestMapping(value = "/getbyid", method = RequestMethod.GET)
    public Object getById(@ModelAttribute IdReq req) {
        CommonValidator.validateId(req.getId());
        AuditRecordDTO auditRecordDTO = auditRecordBiz.getById(req.getId());
        AuditRecordResp auditRecordResp = AuditRecordAdapter.DTO2Resp(auditRecordDTO);
        return ResponseFactory.buildResponse(auditRecordResp);
    }

    @ApiOperation(value = "【后台】消费记录列表", httpMethod = "GET",response = AuditRecordResp.class,responseContainer = "List" ,notes = "【后台】消费记录列表")
    @RequestMapping(value = "/listpage", method = RequestMethod.GET)
    public Object listPage(@ModelAttribute AuditRecordPageReq req) {
        Paging paging = PagingParamUtil.pagingParamSwitch(req);
        List<AuditRecordDTO> auditRecordDTOList = auditRecordBiz.getList(req.getStatus(),paging);
        List<AuditRecordResp> auditRecordRespList = auditRecordDTOList.stream().map(AuditRecordAdapter::DTO2Resp).collect(Collectors.toList());
        return ResponseFactory.buildPaginationResponse(auditRecordRespList,paging);
    }

    @AppApi
    @ApiOperation(value = "【前台】根据提交人id查询", httpMethod = "GET",response = AuditRecordResp.class,responseContainer = "List",notes = "【前台】根据提交人id查询")
    @RequestMapping(value = "/submitterlistpage", method = RequestMethod.GET)
    public Object submitterListPage(@ModelAttribute AuditRecordWeChatPageReq req) {
        Paging paging = PagingParamUtil.pagingParamSwitch(req);;
        List<AuditRecordDTO> auditRecordDTOList = auditRecordBiz.getSubmitterList(req.getId(),paging);
        List<AuditRecordResp> auditRecordRespList = auditRecordDTOList.stream().map(AuditRecordAdapter::DTO2Resp).collect(Collectors.toList());
        return ResponseFactory.buildPaginationResponse(auditRecordRespList,paging);
    }

    @ApiOperation(value = "提交消费记录信息", httpMethod = "POST",notes = "提交消费记录信息")
    @RequestMapping(value = "/addsave", method = RequestMethod.POST)
    public Object addSave(@ModelAttribute AuditRecordAddReq req) {
        //参数检查
        auditRecordBiz.addSave(req);
        return ResponseFactory.buildSuccess();
    }

    @ApiOperation(value = "修改消费记录信息", httpMethod = "POST",notes = "修改消费记录信息")
    @RequestMapping(value = "/editsave", method = RequestMethod.POST)
    public Object editSave(@ModelAttribute AuditRecordEditReq req) {
        //参数检查
        auditRecordBiz.editSave(req);
        return ResponseFactory.buildSuccess();
    }


}
