package com.jiazhe.youxiang.server.controller.voucher;

import com.jiazhe.youxiang.base.controller.BaseController;
import com.jiazhe.youxiang.base.util.CommonValidator;
import com.jiazhe.youxiang.base.util.PagingParamUtil;
import com.jiazhe.youxiang.server.adapter.voucher.VoucherExchangeCodeBatchAdapter;
import com.jiazhe.youxiang.server.biz.voucher.VoucherExchangeCodeBatchBiz;
import com.jiazhe.youxiang.server.common.constant.CommonConstant;
import com.jiazhe.youxiang.server.common.enums.RechargeCardCodeEnum;
import com.jiazhe.youxiang.server.common.enums.VoucherCodeEnum;
import com.jiazhe.youxiang.server.common.exceptions.VoucherException;
import com.jiazhe.youxiang.server.dto.voucher.exchangecodebatch.VoucherExchangeCodeBatchDTO;
import com.jiazhe.youxiang.server.dto.voucher.exchangecodebatch.VoucherExchangeCodeBatchEditDTO;
import com.jiazhe.youxiang.server.dto.voucher.exchangecodebatch.VoucherExchangeCodeBatchSaveDTO;
import com.jiazhe.youxiang.server.vo.Paging;
import com.jiazhe.youxiang.server.vo.ResponseFactory;
import com.jiazhe.youxiang.server.vo.req.IdReq;
import com.jiazhe.youxiang.server.vo.req.voucher.exchangecodebatch.VoucherExchangeCodeBatchPageReq;
import com.jiazhe.youxiang.server.vo.req.voucher.exchangecodebatch.VoucherExchangeCodeBatchSaveReq;
import com.jiazhe.youxiang.server.vo.resp.voucher.exchangecodebatch.VoucherExchangeCodeBatchEditResp;
import com.jiazhe.youxiang.server.vo.resp.voucher.exchangecodebatch.VoucherExchangeCodeBatchResp;
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
@RequestMapping("api/voucherexchangecodebatch")
public class APIVoucherExchangeCodeBatchController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(APIVoucherExchangeCodeBatchController.class);

    @Autowired
    private VoucherExchangeCodeBatchBiz voucherExchangeCodeBatchBiz;

    @ApiOperation(value = "分页查询代金券兑换码批次信息（根据项目和批次名称查询）", httpMethod = "GET", response = VoucherExchangeCodeBatchResp.class, responseContainer = "List", notes = "分页查询代金券兑换码批次信息（根据项目和批次名称查询）")
    @RequestMapping(value = "/listpage", method = RequestMethod.GET)
    public Object listPage(@ModelAttribute VoucherExchangeCodeBatchPageReq req) {
        CommonValidator.validatePaging(req);
        Paging paging = PagingParamUtil.pagingParamSwitch(req);
        List<VoucherExchangeCodeBatchDTO> voucherExchangeCodeBatchDTOList = voucherExchangeCodeBatchBiz.getList(req.getProjectId(), req.getName(), paging);
        List<VoucherExchangeCodeBatchResp> voucherExchangeCodeBatchRespList = voucherExchangeCodeBatchDTOList.stream().map(VoucherExchangeCodeBatchAdapter::DTO2Resp).collect(Collectors.toList());
        return ResponseFactory.buildPaginationResponse(voucherExchangeCodeBatchRespList, paging);
    }

    @ApiOperation(value = "【新建、修改】保存代金券兑换码批次信息", httpMethod = "POST", notes = "【新建、修改】保存代金券兑换码批次信息")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Object save(@ModelAttribute VoucherExchangeCodeBatchSaveReq req) {
        //参数检查
        CommonValidator.validateNull(req);
        CommonValidator.validateNull(req.getId());
        CommonValidator.validateNull(req.getName(), new VoucherException(VoucherCodeEnum.BATCH_NAME_IS_NULL));
        CommonValidator.validateNull(req.getVoucherName(), new VoucherException(VoucherCodeEnum.VOUCHER_NAME_IS_NULL));
        CommonValidator.validateNull(req.getAmount(), new VoucherException(VoucherCodeEnum.AMOUNT_IS_NULL));
        CommonValidator.validateNull(req.getCount(), new VoucherException(VoucherCodeEnum.COUNT_IS_NULL));
        CommonValidator.validateNull(req.getExpiryTime(), new VoucherException(VoucherCodeEnum.BATCH_EXPIRY_TIME_IS_NULL));
        if (req.getExpiryType().equals(CommonConstant.VOUCHER_EXPIRY_TIME)) {
            CommonValidator.validateNull(req.getVoucherExpiryTime(), new VoucherException(VoucherCodeEnum.VOUCHER_EXPIRY_TIME_IS_NULL));
        }
        if (req.getExpiryType().equals(CommonConstant.RECHARGE_CARD_EXPIRY_PERIOD)) {
            CommonValidator.validateNull(req.getValidityPeriod(), new VoucherException(VoucherCodeEnum.VOUCHER_EXPIRY_TIME_IS_NULL));
        }
        VoucherExchangeCodeBatchSaveDTO voucherExchangeCodeBatchSaveDTO = VoucherExchangeCodeBatchAdapter.ReqSave2DTOSave(req);
        if (req.getId() == 0) {
            voucherExchangeCodeBatchBiz.addSave(voucherExchangeCodeBatchSaveDTO);
        } else {
            voucherExchangeCodeBatchBiz.editSave(voucherExchangeCodeBatchSaveDTO);
        }
        return ResponseFactory.buildSuccess();
    }

    @ApiOperation(value = "生成代金券兑换码", httpMethod = "POST", notes = "生成代金券兑换码")
    @RequestMapping(value = "/generatecode", method = RequestMethod.POST)
    public Object generateCode(@ModelAttribute IdReq req) {
        //参数检查
        voucherExchangeCodeBatchBiz.generateCode(req.getId());
        return ResponseFactory.buildSuccess();
    }

    @ApiOperation(value = "代金券兑换码批次信息回显", httpMethod = "GET", response = VoucherExchangeCodeBatchEditResp.class, notes = "代金券兑换码批次信息回显")
    @RequestMapping(value = "/getbyid", method = RequestMethod.GET)
    public Object getById(@ModelAttribute IdReq req) {
        //参数检查
        CommonValidator.validateId(req);
        VoucherExchangeCodeBatchEditDTO voucherExchangeCodeBatchEditDTO = voucherExchangeCodeBatchBiz.getById(req.getId());
        VoucherExchangeCodeBatchEditResp voucherExchangeCodeBatchEditResp = VoucherExchangeCodeBatchAdapter.DTOEdit2RespEdit(voucherExchangeCodeBatchEditDTO);
        return ResponseFactory.buildResponse(voucherExchangeCodeBatchEditResp);
    }

    @ApiOperation(value = "启用批次【同时改变批次下兑换码状态】", httpMethod = "POST", notes = "启用批次【同时改变批次下兑换码状态】")
    @RequestMapping(value = "/startusing", method = RequestMethod.POST)
    public Object startUsing(@ModelAttribute IdReq req) {
        //参数检查
        voucherExchangeCodeBatchBiz.startUsing(req.getId());
        return ResponseFactory.buildSuccess();
    }

    @ApiOperation(value = "停用批次【同时改变批次下兑换码状态】", httpMethod = "POST", notes = "停用批次【同时改变批次下兑换码状态】")
    @RequestMapping(value = "/stopusing", method = RequestMethod.POST)
    public Object stopUsing(@ModelAttribute IdReq req) {
        //参数检查
        voucherExchangeCodeBatchBiz.stopUsing(req.getId());
        return ResponseFactory.buildSuccess();
    }
}
