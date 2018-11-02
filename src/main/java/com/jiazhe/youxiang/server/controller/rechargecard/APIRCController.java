package com.jiazhe.youxiang.server.controller.rechargecard;

import com.jiazhe.youxiang.base.controller.BaseController;
import com.jiazhe.youxiang.base.util.CommonValidator;
import com.jiazhe.youxiang.base.util.PagingParamUtil;
import com.jiazhe.youxiang.server.adapter.rechargecard.RCAdapter;
import com.jiazhe.youxiang.server.adapter.rechargecard.RCExchangeCodeAdapter;
import com.jiazhe.youxiang.server.biz.rechargecard.RCBiz;
import com.jiazhe.youxiang.server.common.constant.CommonConstant;
import com.jiazhe.youxiang.server.common.enums.RechargeCardCodeEnum;
import com.jiazhe.youxiang.server.common.exceptions.RechargeCardException;
import com.jiazhe.youxiang.server.dto.rechargecard.rc.RCDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rc.RCEditDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecode.RCExchangeCodeEditDTO;
import com.jiazhe.youxiang.server.vo.Paging;
import com.jiazhe.youxiang.server.vo.ResponseFactory;
import com.jiazhe.youxiang.server.vo.req.IdReq;
import com.jiazhe.youxiang.server.vo.req.PageSizeNumReq;
import com.jiazhe.youxiang.server.vo.req.rechargecard.rc.DirectChargeReq;
import com.jiazhe.youxiang.server.vo.req.ExpiryTimeEditReq;
import com.jiazhe.youxiang.server.vo.req.rechargecard.rc.RCEditReq;
import com.jiazhe.youxiang.server.vo.req.rechargecard.rc.RCPageReq;
import com.jiazhe.youxiang.server.vo.req.rechargecard.rcexchangecode.RCExchangeCodeEditReq;
import com.jiazhe.youxiang.server.vo.resp.rechargecard.rc.RCResp;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.util.Strings;
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

    @ApiOperation(value = "【后台】信息查询页查询充值卡", httpMethod = "GET", response = RCResp.class, responseContainer = "List",notes = "【后台】信息查询页查询充值卡")
    @RequestMapping(value = "/searchlistpage", method = RequestMethod.GET)
    public Object searchListPage(@ModelAttribute RCPageReq req) {
        CommonValidator.validatePaging(req);
        Paging paging = PagingParamUtil.pagingParamSwitch(req);
        if(Strings.isBlank(req.getMobile())){
            req.setMobile("xxxxxxxxxxx");
        }
        List<RCDTO> rcDTOList = rcBiz.getList(req.getMobile(),req.getStatus(),req.getExpiry(),paging);
        List<RCResp> rcRespList = rcDTOList.stream().map(RCAdapter::DTO2Resp).collect(Collectors.toList());
        return ResponseFactory.buildPaginationResponse(rcRespList, paging);
    }

    @ApiOperation(value = "启用充值卡", httpMethod = "POST",notes = "启用充值卡")
    @RequestMapping(value = "/startusing", method = RequestMethod.POST)
    public Object startUsing(@ModelAttribute IdReq req) {
        //参数检查
        CommonValidator.validateId(req);
        rcBiz.startUsing(req.getId());
        return ResponseFactory.buildSuccess();
    }

    @ApiOperation(value = "停用充值卡", httpMethod = "POST",notes = "停用充值卡")
    @RequestMapping(value = "/stopusing", method = RequestMethod.POST)
    public Object stopUsing(@ModelAttribute IdReq req) {
        //参数检查
        CommonValidator.validateId(req);
        rcBiz.stopUsing(req.getId());
        return ResponseFactory.buildSuccess();
    }

    @ApiOperation(value = "修改充值卡过期时间", httpMethod = "POST",notes = "修改充值卡过期时间")
    @RequestMapping(value = "/changeexpirytime", method = RequestMethod.POST)
    public Object changeExpiryTime(@ModelAttribute ExpiryTimeEditReq req) {
        //参数检查
        CommonValidator.validateId(req);
        CommonValidator.validateNull(req.getExpiryTime(),new RechargeCardException(RechargeCardCodeEnum.RECHARGE_CARD_EXPIRT_TIME_IS_NULL));
        rcBiz.changeExpiryTime(req.getId(),req.getExpiryTime());
        return ResponseFactory.buildSuccess();
    }

    @ApiOperation(value = "后台直接给客户充值任意分数", httpMethod = "POST",notes = "后台直接给客户充值任意分数")
    @RequestMapping(value = "/directcharge", method = RequestMethod.POST)
    public Object directCharge(@ModelAttribute DirectChargeReq req) {
        //参数检查
        rcBiz.directCharge(req.getMobile(),req.getBatchId(),req.getFaceValue());
        return ResponseFactory.buildSuccess();
    }

    @ApiOperation(value = "回显充值卡信息", httpMethod = "GET",response = RCResp.class,notes = "回显充值卡信息")
    @RequestMapping(value = "/getbyid", method = RequestMethod.GET)
    public Object getById(@ModelAttribute IdReq req) {
        //参数检查
        CommonValidator.validateId(req);
        RCDTO rcDTO = rcBiz.getById(req.getId());
        RCResp rcResp = RCAdapter.DTO2Resp(rcDTO);
        return ResponseFactory.buildResponse(rcResp);
    }

    @ApiOperation(value = "修改充值卡信息", httpMethod = "POST",notes = "修改充值卡信息")
    @RequestMapping(value = "/editsave", method = RequestMethod.POST)
    public Object editSave(@ModelAttribute RCEditReq req) {
        //参数检查
        CommonValidator.validateNull(req);
        CommonValidator.validateNull(req.getId());
        CommonValidator.validateNull(req.getName(),new RechargeCardException(RechargeCardCodeEnum.RECHARGE_CARD_NAME_IS_NULL));
        CommonValidator.validateNull(req.getExpiryTime(),new RechargeCardException(RechargeCardCodeEnum.EXCHANGE_CODE_EXPIRT_TIME_IS_NULL));
        RCEditDTO dto = RCAdapter.EditReq2EditDTO(req);
        rcBiz.editSave(dto);
        return ResponseFactory.buildSuccess();
    }

}
