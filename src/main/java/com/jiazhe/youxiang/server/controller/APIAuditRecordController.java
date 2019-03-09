package com.jiazhe.youxiang.server.controller;

import com.jiazhe.youxiang.base.controller.BaseController;
import com.jiazhe.youxiang.base.util.CommonValidator;
import com.jiazhe.youxiang.base.util.PagingParamUtil;
import com.jiazhe.youxiang.server.adapter.AuditRecordAdapter;
import com.jiazhe.youxiang.server.biz.AuditRecordBiz;
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
import com.jiazhe.youxiang.server.vo.req.auditrecord.AuditRecordCheckReq;
import com.jiazhe.youxiang.server.vo.req.auditrecord.AuditRecordPageReq;
import com.jiazhe.youxiang.server.vo.req.auditrecord.AuditRecordSaveReq;
import com.jiazhe.youxiang.server.vo.resp.auditrecord.AuditRecordResp;
import com.jiazhe.youxiang.server.vo.resp.order.orderinfo.WaitingDealCountResp;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
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

    @Autowired
    private AuditRecordBiz auditRecordBiz;


    @RequiresPermissions(PermissionConstant.AUDIT_RECORD_CHECK)
    @ApiOperation(value = "【后台】审核", httpMethod = "POST", notes = "审核")
    @RequestMapping(value = "/auditrecordcheck", method = RequestMethod.POST)
    @CustomLog(moduleName = ModuleEnum.AUDIT_RECORD, operate = "审核", level = LogLevelEnum.LEVEL_2)
    public Object auditRecordPass(@ModelAttribute AuditRecordCheckReq req) {
        if (req.getStatus().equals(CommonConstant.AUDIT_RECORD_REJECT)) {
            CommonValidator.validateNull(req.getAuditReason(), new AuditRecordException(AuditRecordCodeEnum.AUDIT_REASON_IS_NULL));
            auditRecordBiz.auditRecordUnpass(req.getId(), req.getVersion(), req.getAuditReason());
        }
        if (req.getStatus().equals(CommonConstant.AUDIT_RECORD_PASS)) {
            CommonValidator.validateNull(req.getPosCode(), new AuditRecordException(AuditRecordCodeEnum.POS_CODE_IS_NULL));
            CommonValidator.validateNull(req.getCardNo(), new AuditRecordException(AuditRecordCodeEnum.CARD_NO_IS_NULL));
            if (req.getTradeTime() == CommonConstant.NULL_TIME) {
                throw new AuditRecordException(AuditRecordCodeEnum.TRADE_TIME_IS_NULL);
            }
            auditRecordBiz.auditRecordPass(req.getId(), req.getVersion(), req.getExchangeBatchId(), req.getGivingBatchId(), req.getPosCode(), req.getCardNo(), req.getTradeTime());
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
        List<AuditRecordDTO> auditRecordDTOList = auditRecordBiz.getList(req.getCustomerMobile(),req.getStatus(), paging);
        List<AuditRecordResp> auditRecordRespList = auditRecordDTOList.stream().map(AuditRecordAdapter::DTO2Resp).collect(Collectors.toList());
        return ResponseFactory.buildPaginationResponse(auditRecordRespList, paging);
    }

    @ApiOperation(value = "【审核小程序】根据提交人id查询", httpMethod = "GET", response = AuditRecordResp.class, responseContainer = "List", notes = "【前台】根据提交人id查询")
    @RequestMapping(value = "/submitterlistpage", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.AUDIT_RECORD, operate = "根据提交人id查询", level = LogLevelEnum.LEVEL_1)
    public Object submitterListPage(@ModelAttribute AuditRecordPageReq req) {
        Paging paging = PagingParamUtil.pagingParamSwitch(req);
        SysUserDTO sysUserDTO = (SysUserDTO) SecurityUtils.getSubject().getPrincipal();
        if (null == sysUserDTO) {
            throw new LoginException(LoginCodeEnum.LOGIN_NOT_SIGNIN_IN);
        }
        List<AuditRecordDTO> auditRecordDTOList = auditRecordBiz.getSubmitterList(req.getStatus(), sysUserDTO.getId(), paging);
        List<AuditRecordResp> auditRecordRespList = auditRecordDTOList.stream().map(AuditRecordAdapter::DTO2Resp).collect(Collectors.toList());
        return ResponseFactory.buildPaginationResponse(auditRecordRespList, paging);
    }

    @ApiOperation(value = "【审核小程序】保存消费记录信息", httpMethod = "POST", notes = "保存消费记录信息")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @CustomLog(moduleName = ModuleEnum.AUDIT_RECORD, operate = "保存消费记录信息", level = LogLevelEnum.LEVEL_2)
    public Object save(@ModelAttribute AuditRecordSaveReq req) {
        CommonValidator.validateNull(req.getBankOutletsName(), new AuditRecordException(AuditRecordCodeEnum.BANK_NAME_IS_NULL));
        CommonValidator.validateNull(req.getExchangePoint(), new AuditRecordException(AuditRecordCodeEnum.EXCHANGE_POINT_IS_NULL));
        CommonValidator.validateNull(req.getImgUrls(), new AuditRecordException(AuditRecordCodeEnum.IMAGE_IS_NULL));
        //根据兑换类型，约束各种字段的填写条件
        if (req.getExchangeType().equals(CommonConstant.DIRECT_CHARGE)) {
            CommonValidator.validateNull(req.getCustomerName(), new AuditRecordException(AuditRecordCodeEnum.CUSTOMER_NAME_IS_NULL));
            CommonValidator.validateNull(req.getCustomerMobile(), new AuditRecordException(AuditRecordCodeEnum.CUSTOMER_MOBILE_IS_NULL));
            CommonValidator.validateMobile(req.getCustomerMobile(), new AuditRecordException(AuditRecordCodeEnum.CUSTOMER_MOBILE_IS_ILLEGAL));
        }
        if (req.getExchangeType().equals(CommonConstant.SELF_CHARGE)) {
            CommonValidator.validateNull(req.getPointCodes(), new AuditRecordException(AuditRecordCodeEnum.POINT_CODES_IS_NULL));
        }
        if (req.getExchangeType().equals(CommonConstant.EXCHANGE_ENTITY)) {
            CommonValidator.validateNull(req.getProductValue(), new AuditRecordException(AuditRecordCodeEnum.PRODUCT_VALUE_IS_NULL));
        }
        //判断是新建还是修改
        AuditRecordDTO auditRecordDTO = null;
        if (req.getId().equals(0)) {
            auditRecordDTO = new AuditRecordDTO();
            auditRecordDTO.setId(0);
            auditRecordDTO.setVersion(0);
        } else {
            auditRecordDTO = auditRecordBiz.getById(req.getId());
            if(auditRecordDTO.getStatus().equals(CommonConstant.AUDIT_RECORD_PASS)){
                throw new AuditRecordException(AuditRecordCodeEnum.RECORD_HASS_PASSED);
            }
            if (!auditRecordDTO.getVersion().equals(req.getVersion())) {
                throw new AuditRecordException(AuditRecordCodeEnum.VERSION_IS_CHANGED);
            }
            auditRecordDTO.setVersion(auditRecordDTO.getVersion() + 1);
            auditRecordDTO.setModTime(new Date(System.currentTimeMillis()));
        }
        auditRecordDTO.setStatus(req.getStatus());
        auditRecordDTO.setBankOutletsName(req.getBankOutletsName());
        auditRecordDTO.setExchangeType(req.getExchangeType());
        auditRecordDTO.setCustomerMobile(req.getCustomerMobile());
        auditRecordDTO.setCustomerName(req.getCustomerName());
        auditRecordDTO.setExchangePoint(req.getExchangePoint());
        auditRecordDTO.setPointCodes(req.getPointCodes());
        auditRecordDTO.setProductValue(req.getProductValue());
        auditRecordDTO.setImgUrls(req.getImgUrls());
        auditRecordDTO.setRemark(req.getRemark());
        auditRecordBiz.save(auditRecordDTO);
        return ResponseFactory.buildSuccess();
    }
}
