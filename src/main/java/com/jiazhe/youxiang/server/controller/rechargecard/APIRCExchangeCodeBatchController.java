package com.jiazhe.youxiang.server.controller.rechargecard;

import com.alibaba.druid.sql.PagerUtils;
import com.jiazhe.youxiang.base.controller.BaseController;
import com.jiazhe.youxiang.base.util.CommonValidator;
import com.jiazhe.youxiang.base.util.ExportExcelUtils;
import com.jiazhe.youxiang.base.util.PagingParamUtil;
import com.jiazhe.youxiang.server.adapter.rechargecard.RCExchangeCodeBatchAdapter;
import com.jiazhe.youxiang.server.biz.rechargecard.RCExchangeCodeBatchBiz;
import com.jiazhe.youxiang.server.biz.rechargecard.RCExchangeCodeBiz;
import com.jiazhe.youxiang.server.common.constant.CommonConstant;
import com.jiazhe.youxiang.server.common.enums.RechargeCardCodeEnum;
import com.jiazhe.youxiang.server.common.exceptions.CommonException;
import com.jiazhe.youxiang.server.common.exceptions.RechargeCardException;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecode.RCExchangeCodeDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchEditDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchSaveDTO;
import com.jiazhe.youxiang.server.vo.Paging;
import com.jiazhe.youxiang.server.vo.ResponseFactory;
import com.jiazhe.youxiang.server.vo.req.IdReq;
import com.jiazhe.youxiang.server.vo.req.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchPageReq;
import com.jiazhe.youxiang.server.vo.req.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchSaveReq;
import com.jiazhe.youxiang.server.vo.resp.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchEditResp;
import com.jiazhe.youxiang.server.vo.resp.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchResp;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tu
 * @description：充值卡兑换码批次【rc是rechargecard缩写】
 * @date 2018/10/21
 */
@RestController
@RequestMapping("api/rcexchangecodebatch")
public class APIRCExchangeCodeBatchController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(APIRCExchangeCodeBatchController.class);

    @Autowired
    private RCExchangeCodeBatchBiz rcExchangeCodeBatchBiz;
    @Autowired
    private RCExchangeCodeBiz rcExchangeCodeBiz;

    @ApiOperation(value = "【后台】充值卡兑换码批次信息列表（分页）", httpMethod = "GET", response = RCExchangeCodeBatchResp.class, responseContainer = "List", notes = "查询充值卡兑换码批次信息（根据项目id和批次名称查询）")
    @RequestMapping(value = "/listpage", method = RequestMethod.GET)
    public Object listPage(@ModelAttribute RCExchangeCodeBatchPageReq req) {
        CommonValidator.validatePaging(req);
        Paging paging = PagingParamUtil.pagingParamSwitch(req);
        List<RCExchangeCodeBatchDTO> rcExchangeCodeBatchDTOList = rcExchangeCodeBatchBiz.getList(req.getProjectId(), req.getName(), paging);
        List<RCExchangeCodeBatchResp> rcExchangeCodeBatchRespList = rcExchangeCodeBatchDTOList.stream().map(RCExchangeCodeBatchAdapter::DTO2Resp).collect(Collectors.toList());
        return ResponseFactory.buildPaginationResponse(rcExchangeCodeBatchRespList, paging);
    }

    @ApiOperation(value = "【后台】根据项目id查询充值卡兑换码虚拟批次", httpMethod = "GET", response = RCExchangeCodeBatchResp.class, responseContainer = "List", notes = "根据项目id查询充值卡兑换码虚拟批次")
    @RequestMapping(value = "/findvirtualbyprojectid", method = RequestMethod.GET)
    public Object findVirtualByProjectId(@ModelAttribute IdReq req) {
        List<RCExchangeCodeBatchDTO> rcExchangeCodeBatchDTOList = rcExchangeCodeBatchBiz.getByProjectId(req.getId());
        List<RCExchangeCodeBatchDTO> virtualList = rcExchangeCodeBatchDTOList.stream().filter(bean-> bean.getStatus().equals(CommonConstant.BATCH_IS_VIRTUAL)).collect(Collectors.toList());
        List<RCExchangeCodeBatchResp> rcExchangeCodeBatchRespList = virtualList.stream().map(RCExchangeCodeBatchAdapter::DTO2Resp).collect(Collectors.toList());
        return ResponseFactory.buildResponse(rcExchangeCodeBatchRespList);
    }

    @ApiOperation(value = "【后台】保存充值卡兑换码批次信息", httpMethod = "POST", notes = "【新建、修改】保存充值卡兑换码批次信息")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Object save(@ModelAttribute RCExchangeCodeBatchSaveReq req) {
        //参数检查
        CommonValidator.validateNull(req);
        CommonValidator.validateNull(req.getId());
        CommonValidator.validateNull(req.getName(),new RechargeCardException(RechargeCardCodeEnum.BATCH_NAME_IS_NULL));
        CommonValidator.validateNull(req.getRechargeCardName(),new RechargeCardException(RechargeCardCodeEnum.RECHARGE_CARD_NAME_IS_NULL));
        if (!req.getIsVirtual().equals(CommonConstant.BATCH_IS_VIRTUAL)) {
           CommonValidator.validateNull(req.getAmount(),new RechargeCardException(RechargeCardCodeEnum.NOT_VIRTUAL_NEED_AMOUNT));
           CommonValidator.validateNull(req.getFaceValue(),new RechargeCardException(RechargeCardCodeEnum.NOT_VIRTUAL_NEED_FACE_VALUE));
        }
        CommonValidator.validateNull(req.getExpiryTime(),new RechargeCardException(RechargeCardCodeEnum.BATCH_EXPIRY_TIME_IS_NULL));
        if (req.getExpiryType().equals(CommonConstant.RECHARGE_CARD_EXPIRY_TIME)) {
            CommonValidator.validateNull(req.getRechargeCardExpiryTime(),new RechargeCardException(RechargeCardCodeEnum.RECHARGE_CARD_EXPIRY_TIME_IS_NULL));
        }
        if (req.getExpiryType().equals(CommonConstant.RECHARGE_CARD_EXPIRY_PERIOD)) {
            CommonValidator.validateNull(req.getValidityPeriod(),new RechargeCardException(RechargeCardCodeEnum.RECHARGE_CARD_EXPIRY_TIME_IS_NULL));
        }
        RCExchangeCodeBatchSaveDTO rcExchangeCodeBatchSaveDTO = RCExchangeCodeBatchAdapter.ReqSave2DTOSave(req);
        if (req.getId() == 0) {
            rcExchangeCodeBatchBiz.addSave(rcExchangeCodeBatchSaveDTO);
        } else {
            rcExchangeCodeBatchBiz.editSave(rcExchangeCodeBatchSaveDTO);
        }
        return ResponseFactory.buildSuccess();
    }

    @ApiOperation(value = "【后台】生成充值卡兑换码", httpMethod = "POST", notes = "生成批次下的充值卡兑换码")
    @RequestMapping(value = "/generatecode", method = RequestMethod.POST)
    public Object generateCode(@ModelAttribute IdReq req) {
        CommonValidator.validateId(req);
        //参数检查,检查是否是虚拟批次，检查该批次是否已经生成过兑换码
        RCExchangeCodeBatchEditDTO dto = rcExchangeCodeBatchBiz.getById(req.getId());
        if(null == dto){
            throw new RechargeCardException(RechargeCardCodeEnum.BATCH_NOT_EXISTED);
        }
        if (dto.getIsVirtual().equals(CommonConstant.BATCH_IS_VIRTUAL)) {
            throw new RechargeCardException(RechargeCardCodeEnum.VIRTUAL_BATCH_CANNOT_GENERATE);
        }
        if(dto.getIsMade().equals(CommonConstant.EXCHANGE_CODE_HAS_MADE)){
            throw new RechargeCardException(RechargeCardCodeEnum.CODE_GENERATED);
        }
        rcExchangeCodeBatchBiz.generateCode(req.getId());
        return ResponseFactory.buildSuccess();
    }

    @ApiOperation(value = "【后台】充值卡兑换码批次信息回显", httpMethod = "GET", response = RCExchangeCodeBatchEditResp.class, notes = "充值卡兑换码批次信息回显")
    @RequestMapping(value = "/getbyid", method = RequestMethod.GET)
    public Object getById(@ModelAttribute IdReq req) {
        //参数检查
        CommonValidator.validateId(req);
        RCExchangeCodeBatchEditDTO rcExchangeCodeBatchEditDTO = rcExchangeCodeBatchBiz.getById(req.getId());
        RCExchangeCodeBatchEditResp rcExchangeCodeBatchEditResp = RCExchangeCodeBatchAdapter.DTOEdit2RespEdit(rcExchangeCodeBatchEditDTO);
        return ResponseFactory.buildResponse(rcExchangeCodeBatchEditResp);
    }

    @ApiOperation(value = "【后台】启用批次", httpMethod = "POST", notes = "启用批次，同时改变批次下兑换码和已经兑换成充值卡的状态")
    @RequestMapping(value = "/startusing", method = RequestMethod.POST)
    public Object startUsing(@ModelAttribute IdReq req) {
        //参数检查
        CommonValidator.validateId(req);
        rcExchangeCodeBatchBiz.startUsing(req.getId());
        return ResponseFactory.buildSuccess();
    }

    @ApiOperation(value = "【后台】停用批次", httpMethod = "POST", notes = "停用批次，同时改变批次下兑换码和已经兑换成充值卡的状态")
    @RequestMapping(value = "/stopusing", method = RequestMethod.POST)
    public Object stopUsing(@ModelAttribute IdReq req) {
        //参数检查
        CommonValidator.validateId(req);
        rcExchangeCodeBatchBiz.stopUsing(req.getId());
        return ResponseFactory.buildSuccess();
    }

    @ApiOperation(value = "【后台】导出批次下兑换码", httpMethod = "GET", notes = "导出批次下兑换码")
    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public void export(@ModelAttribute IdReq req, HttpServletResponse response) throws IOException {
        List<RCExchangeCodeDTO> rcExchangeCodeDTOList= rcExchangeCodeBiz.getByBatchId(req.getId());
        ExportExcelUtils.exportRechargeCardCode(response,rcExchangeCodeDTOList);
    }

    @ApiOperation(value = "【后台】导出前检查", httpMethod = "GET", notes = "导出前检查，看批次下是否有兑换码")
    @RequestMapping(value = "/exportcheck", method = RequestMethod.GET)
    public Object exportCheck(@ModelAttribute IdReq req){
        CommonValidator.validateId(req);
        List<RCExchangeCodeDTO> rcExchangeCodeDTOList= rcExchangeCodeBiz.getByBatchId(req.getId());
        if(rcExchangeCodeDTOList.isEmpty()){
            throw new RechargeCardException(RechargeCardCodeEnum.NO_CODE_TO_EXPORT);
        }
        return ResponseFactory.buildSuccess();
    }

}
