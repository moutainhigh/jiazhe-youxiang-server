package com.jiazhe.youxiang.server.controller;

import com.jiazhe.youxiang.base.controller.BaseController;
import com.jiazhe.youxiang.base.util.CommonValidator;
import com.jiazhe.youxiang.base.util.PagingParamUtil;
import com.jiazhe.youxiang.server.adapter.AuditRecordAdapter;
import com.jiazhe.youxiang.server.biz.AuditRecordBiz;
import com.jiazhe.youxiang.server.common.annotation.AppApi;
import com.jiazhe.youxiang.server.common.annotation.CustomLog;
import com.jiazhe.youxiang.server.common.constant.CommonConstant;
import com.jiazhe.youxiang.server.common.constant.PermissionConstant;
import com.jiazhe.youxiang.server.common.enums.AuditRecordCodeEnum;
import com.jiazhe.youxiang.server.common.enums.LogLevelEnum;
import com.jiazhe.youxiang.server.common.enums.LoginCodeEnum;
import com.jiazhe.youxiang.server.common.enums.ModuleEnum;
import com.jiazhe.youxiang.server.common.exceptions.AuditRecordException;
import com.jiazhe.youxiang.server.common.exceptions.LoginException;
import com.jiazhe.youxiang.server.dto.auditrecord.AuditRecordDTO;
import com.jiazhe.youxiang.server.dto.sysuser.SysUserDTO;
import com.jiazhe.youxiang.server.vo.Paging;
import com.jiazhe.youxiang.server.vo.ResponseFactory;
import com.jiazhe.youxiang.server.vo.req.IdReq;
import com.jiazhe.youxiang.server.vo.req.PageSizeNumReq;
import com.jiazhe.youxiang.server.vo.req.auditrecord.*;
import com.jiazhe.youxiang.server.vo.resp.auditrecord.AuditRecordResp;
import com.jiazhe.youxiang.server.vo.resp.order.orderinfo.WaitingDealCountResp;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Security;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author TU
 * @description
 * @date 2018/10/24.
 */
@RestController
@RequestMapping("api/auditrecord")
public class APIAuditRecordController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(APIAuditRecordController.class);

    /**
     * 审核消费记录 【0未审核，1未通过，2已通过】
     */
    private static Byte AUDIT_UNPASS = Byte.valueOf("1");
    private static Byte AUDIT_PASS = Byte.valueOf("2");

    @Autowired
    private AuditRecordBiz auditRecordBiz;


    @RequiresPermissions(PermissionConstant.AUDIT_RECORD_CHECK)
    @ApiOperation(value = "【后台】审核", httpMethod = "POST", notes = "审核")
    @RequestMapping(value = "/auditrecordcheck", method = RequestMethod.POST)
    @CustomLog(moduleName = ModuleEnum.AUDIT_RECORD, operate = "审核", level = LogLevelEnum.LEVEL_2)
    public Object auditRecordPass(@ModelAttribute AuditRecordCheckReq req) {
        if (req.getStatus().equals(AUDIT_UNPASS)) {
            CommonValidator.validateNull(req.getAuditReason(), new AuditRecordException(AuditRecordCodeEnum.AUDIT_REASON_IS_NULL));
            auditRecordBiz.auditRecordUnpass(req.getId(), req.getVersion(), req.getAuditReason());
        }
        if (req.getStatus().equals(AUDIT_PASS)) {
            CommonValidator.validateNull(req.getExchangeBatchId(),new AuditRecordException(AuditRecordCodeEnum.EXCHANGE_BATCH_IS_NULL));
                    CommonValidator.validateNull(req.getExchangeBatchId(),new AuditRecordException(AuditRecordCodeEnum.GIVING_BATCH_IS_NULL));
            CommonValidator.validateNull(req.getPosCode(), new AuditRecordException(AuditRecordCodeEnum.POS_CODE_IS_NULL));
            CommonValidator.validateNull(req.getCardNo(), new AuditRecordException(AuditRecordCodeEnum.CARD_NO_IS_NULL));
            if(null == req.getTradeTime() ||req.getTradeTime() == 0){
                throw new AuditRecordException(AuditRecordCodeEnum.TRADE_TIME_IS_NULL);
            }
            auditRecordBiz.auditRecordPass(req.getId(), req.getVersion(), req.getExchangeBatchId(),req.getGivingBatchId(),req.getPosCode(),req.getCardNo(),req.getTradeTime());
        }
        return ResponseFactory.buildSuccess();
    }

    @RequiresPermissions(PermissionConstant.AUDIT_RECORD_CHECK)
    @ApiOperation(value = "【后台】待审核消费记录条数", httpMethod = "GET", response = WaitingDealCountResp.class, notes = "待审核消费记录条数")
    @RequestMapping(value = "/getwaitcheckcount", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.AUDIT_RECORD, operate = "待审核消费记录条数", level = LogLevelEnum.LEVEL_1)
    public Object getWaitCheckCount() {
        Integer count = auditRecordBiz.getWaitCheckCount();
        WaitingDealCountResp waitingDealCountResp = new WaitingDealCountResp();
        waitingDealCountResp.setCount(count);
        return ResponseFactory.buildResponse(waitingDealCountResp);
    }

    @ApiOperation(value = "【后台、审核小程序】获取消费记录详情", httpMethod = "GET", response = AuditRecordResp.class, notes = "获取消费记录详情")
    @RequestMapping(value = "/getbyid", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.AUDIT_RECORD, operate = "获取消费记录详情", level = LogLevelEnum.LEVEL_1)
    public Object getById(@ModelAttribute IdReq req) {
        CommonValidator.validateId(req.getId());
        AuditRecordDTO auditRecordDTO = auditRecordBiz.getById(req.getId());
        AuditRecordResp auditRecordResp = AuditRecordAdapter.DTO2Resp(auditRecordDTO);
        return ResponseFactory.buildResponse(auditRecordResp);
    }

    @RequiresPermissions(PermissionConstant.AUDIT_RECORD_MANAGEMENT)
    @ApiOperation(value = "【后台】消费记录列表", httpMethod = "GET", response = AuditRecordResp.class, responseContainer = "List", notes = "【后台】消费记录列表")
    @RequestMapping(value = "/listpage", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.AUDIT_RECORD, operate = "消费记录列表", level = LogLevelEnum.LEVEL_1)
    public Object listPage(@ModelAttribute AuditRecordPageReq req) {
        Paging paging = PagingParamUtil.pagingParamSwitch(req);
        List<AuditRecordDTO> auditRecordDTOList = auditRecordBiz.getList(req.getStatus(), paging);
        List<AuditRecordResp> auditRecordRespList = auditRecordDTOList.stream().map(AuditRecordAdapter::DTO2Resp).collect(Collectors.toList());
        return ResponseFactory.buildPaginationResponse(auditRecordRespList, paging);
    }

    @ApiOperation(value = "【审核小程序】根据提交人id查询", httpMethod = "GET", response = AuditRecordResp.class, responseContainer = "List", notes = "【前台】根据提交人id查询")
    @RequestMapping(value = "/submitterlistpage", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.AUDIT_RECORD, operate = "根据提交人id查询", level = LogLevelEnum.LEVEL_1)
    public Object submitterListPage(@ModelAttribute PageSizeNumReq req) {
        Paging paging = PagingParamUtil.pagingParamSwitch(req);
        SysUserDTO sysUserDTO = (SysUserDTO) SecurityUtils.getSubject().getPrincipal();
        if (null == sysUserDTO) {
            throw new LoginException(LoginCodeEnum.LOGIN_NOT_SIGNIN_IN);
        }
        List<AuditRecordDTO> auditRecordDTOList = auditRecordBiz.getSubmitterList(sysUserDTO.getId(), paging);
        List<AuditRecordResp> auditRecordRespList = auditRecordDTOList.stream().map(AuditRecordAdapter::DTO2Resp).collect(Collectors.toList());
        return ResponseFactory.buildPaginationResponse(auditRecordRespList, paging);
    }

    @ApiOperation(value = "【审核小程序】提交消费记录信息", httpMethod = "POST", notes = "提交消费记录信息")
    @RequestMapping(value = "/addsave", method = RequestMethod.POST)
    @CustomLog(moduleName = ModuleEnum.AUDIT_RECORD, operate = "提交消费记录信息", level = LogLevelEnum.LEVEL_2)
    public Object addSave(@ModelAttribute AuditRecordAddReq req) {
        CommonValidator.validateNull(req.getCustomerName(), new AuditRecordException(AuditRecordCodeEnum.CUSTOMER_NAME_IS_NULL));
        CommonValidator.validateNull(req.getCustomerMobile(), new AuditRecordException(AuditRecordCodeEnum.CUSTOMER_MOBILE_IS_NULL));
        CommonValidator.validateNull(req.getExchangePoint(), new AuditRecordException(AuditRecordCodeEnum.EXCHANGE_POINT_IS_NULL));
        CommonValidator.validateNull(req.getGivingPoint(), new AuditRecordException(AuditRecordCodeEnum.GIVING_POINT_IS_NULL));
        CommonValidator.validateMobile(req.getCustomerMobile(), new AuditRecordException(AuditRecordCodeEnum.CUSTOMER_MOBILE_IS_ILLEGAL));
        auditRecordBiz.addSave(req.getCustomerName(), req.getCustomerMobile(), req.getExchangePoint(), req.getExchangeType(), req.getGivingPoint(), req.getGivingType(), req.getRemark(), req.getImgUrls());
        return ResponseFactory.buildSuccess();
    }

    @ApiOperation(value = "【审核小程序】修改消费记录信息", httpMethod = "POST", notes = "修改消费记录信息")
    @RequestMapping(value = "/editsave", method = RequestMethod.POST)
    @CustomLog(moduleName = ModuleEnum.AUDIT_RECORD, operate = "修改消费记录信息", level = LogLevelEnum.LEVEL_2)
    public Object editSave(@ModelAttribute AuditRecordEditReq req) {
        CommonValidator.validateNull(req.getCustomerName(), new AuditRecordException(AuditRecordCodeEnum.CUSTOMER_NAME_IS_NULL));
        CommonValidator.validateNull(req.getCustomerMobile(), new AuditRecordException(AuditRecordCodeEnum.CUSTOMER_MOBILE_IS_NULL));
        CommonValidator.validateNull(req.getExchangePoint(), new AuditRecordException(AuditRecordCodeEnum.EXCHANGE_POINT_IS_NULL));
        CommonValidator.validateNull(req.getGivingPoint(), new AuditRecordException(AuditRecordCodeEnum.GIVING_POINT_IS_NULL));
        CommonValidator.validateMobile(req.getCustomerMobile(), new AuditRecordException(AuditRecordCodeEnum.CUSTOMER_MOBILE_IS_ILLEGAL));
        auditRecordBiz.editSave(req.getId(), req.getVersion(), req.getCustomerName(), req.getCustomerMobile(), req.getExchangePoint(), req.getExchangeType(), req.getGivingPoint(), req.getGivingType(), req.getRemark(), req.getImgUrls());
        return ResponseFactory.buildSuccess();
    }


}
