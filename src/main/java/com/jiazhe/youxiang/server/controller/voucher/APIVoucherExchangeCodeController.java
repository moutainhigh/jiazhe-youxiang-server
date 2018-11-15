package com.jiazhe.youxiang.server.controller.voucher;

import com.jiazhe.youxiang.base.controller.BaseController;
import com.jiazhe.youxiang.base.util.CommonValidator;
import com.jiazhe.youxiang.base.util.PagingParamUtil;
import com.jiazhe.youxiang.server.adapter.voucher.VoucherExchangeCodeAdapter;
import com.jiazhe.youxiang.server.biz.voucher.VoucherExchangeCodeBiz;
import com.jiazhe.youxiang.server.common.annotation.AppApi;
import com.jiazhe.youxiang.server.common.constant.CommonConstant;
import com.jiazhe.youxiang.server.common.enums.CustomerCodeEnum;
import com.jiazhe.youxiang.server.common.enums.VoucherCodeEnum;
import com.jiazhe.youxiang.server.common.exceptions.CustomerException;
import com.jiazhe.youxiang.server.common.exceptions.VoucherException;
import com.jiazhe.youxiang.server.dto.voucher.exchangecode.VoucherExchangeCodeDTO;
import com.jiazhe.youxiang.server.dto.voucher.exchangecode.VoucherExchangeCodeEditDTO;
import com.jiazhe.youxiang.server.vo.Paging;
import com.jiazhe.youxiang.server.vo.ResponseFactory;
import com.jiazhe.youxiang.server.vo.req.IdReq;
import com.jiazhe.youxiang.server.vo.req.voucher.exchangecode.CodeChargeReq;
import com.jiazhe.youxiang.server.vo.req.voucher.exchangecode.VoucherExchangeCodeEditReq;
import com.jiazhe.youxiang.server.vo.req.voucher.exchangecode.VoucherExchangeCodePageReq;
import com.jiazhe.youxiang.server.vo.resp.voucher.exchangecode.VoucherExchangeCodeResp;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.util.Strings;
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
@RequestMapping("api/voucherexchangecode")
public class APIVoucherExchangeCodeController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(APIVoucherExchangeCodeController.class);

    @Autowired
    private VoucherExchangeCodeBiz voucherExchangeCodeBiz;

    @ApiOperation(value = "分页查询代金券兑换码（根据批次id和兑换码的码和密钥查询）", httpMethod = "GET", response = VoucherExchangeCodeResp.class, responseContainer = "List",notes = "分页查询代金券兑换码（根据批次id和兑换码的码和密钥查询）")
    @RequestMapping(value = "/listpage", method = RequestMethod.GET)
    public Object listPage(@ModelAttribute VoucherExchangeCodePageReq req) {
        CommonValidator.validatePaging(req);
        Paging paging = PagingParamUtil.pagingParamSwitch(req);
        List<VoucherExchangeCodeDTO> voucherExchangeCodeDTOList = voucherExchangeCodeBiz.getList(req.getBatchId(),req.getCode(),req.getKeyt(),req.getStatus(),req.getUsed(),paging);
        List<VoucherExchangeCodeResp> voucherExchangeCodeRespList = voucherExchangeCodeDTOList.stream().map(VoucherExchangeCodeAdapter::DTO2Resp).collect(Collectors.toList());
        return ResponseFactory.buildPaginationResponse(voucherExchangeCodeRespList, paging);
    }

    @ApiOperation(value = "信息查询页查询代金券兑换码", httpMethod = "GET", response = VoucherExchangeCodeResp.class, responseContainer = "List",notes = "信息查询页查询代金券兑换码")
    @RequestMapping(value = "/searchlistpage", method = RequestMethod.GET)
    public Object searchListPage(@ModelAttribute VoucherExchangeCodePageReq req) {
        CommonValidator.validatePaging(req);
        Paging paging = PagingParamUtil.pagingParamSwitch(req);
        if(Strings.isBlank(req.getCode())&& Strings.isBlank(req.getKeyt())){
            req.setCode("xxxxxxxxxxxxxxxx");
            req.setKeyt("xxxxxxxxxxxxxxxx");
        }
        List<VoucherExchangeCodeDTO> rcExchangeCodeDTOList = voucherExchangeCodeBiz.getList(req.getBatchId(),req.getCode(),req.getKeyt(),req.getStatus(),req.getUsed(),paging);
        List<VoucherExchangeCodeResp> rcExchangeCodeBatchRespList = rcExchangeCodeDTOList.stream().map(VoucherExchangeCodeAdapter::DTO2Resp).collect(Collectors.toList());
        return ResponseFactory.buildPaginationResponse(rcExchangeCodeBatchRespList, paging);
    }

    @ApiOperation(value = "启用代金券兑换码", httpMethod = "POST",notes = "启用代金券兑换码")
    @RequestMapping(value = "/startusing", method = RequestMethod.POST)
    public Object startUsing(@ModelAttribute IdReq req) {
        //参数检查
        CommonValidator.validateId(req);
        voucherExchangeCodeBiz.startUsing(req.getId());
        return ResponseFactory.buildSuccess();
    }

    @ApiOperation(value = "停用代金券兑换码", httpMethod = "POST",notes = "停用代金券兑换码")
    @RequestMapping(value = "/stopusing", method = RequestMethod.POST)
    public Object stopUsing(@ModelAttribute IdReq req) {
        //参数检查
        CommonValidator.validateId(req);
        voucherExchangeCodeBiz.stopUsing(req.getId());
        return ResponseFactory.buildSuccess();
    }

    @ApiOperation(value = "获取兑换码信息", httpMethod = "GET",response = VoucherExchangeCodeResp.class,notes = "获取兑换码信息")
    @RequestMapping(value = "/getbyid", method = RequestMethod.GET)
    public Object getById(@ModelAttribute IdReq req) {
        //参数检查
        CommonValidator.validateId(req);
        VoucherExchangeCodeDTO voucherExchangeCodeDTO = voucherExchangeCodeBiz.getById(req.getId());
        VoucherExchangeCodeResp voucherExchangeCodeResp = VoucherExchangeCodeAdapter.DTO2Resp(voucherExchangeCodeDTO);
        return ResponseFactory.buildResponse(voucherExchangeCodeResp);
    }

    @ApiOperation(value = "修改兑换码信息", httpMethod = "POST",notes = "修改兑换码信息")
    @RequestMapping(value = "/editsave", method = RequestMethod.POST)
    public Object editSave(@ModelAttribute VoucherExchangeCodeEditReq req) {
        //参数检查
        CommonValidator.validateNull(req);
        CommonValidator.validateNull(req.getId());
        CommonValidator.validateNull(req.getVoucherName(),new VoucherException(VoucherCodeEnum.VOUCHER_NAME_IS_NULL));
        CommonValidator.validateNull(req.getExpiryTime(),new VoucherException(VoucherCodeEnum.EXCHANGE_CODE_EXPIRY_TIME_IS_NULL));
        if (req.getExpiryType().equals(CommonConstant.VOUCHER_EXPIRY_TIME)) {
            CommonValidator.validateNull(req.getVoucherExpiryTime(),new VoucherException(VoucherCodeEnum.VOUCHER_EXPIRY_TIME_IS_NULL));
        }
        if (req.getExpiryType().equals(CommonConstant.VOUCHER_EXPIRY_PERIOD)) {
            CommonValidator.validateNull(req.getValidityPeriod(),new VoucherException(VoucherCodeEnum.VOUCHER_EXPIRY_TIME_IS_NULL));
        }
        VoucherExchangeCodeEditDTO dto = VoucherExchangeCodeAdapter.EditReq2EditDTO(req);
        voucherExchangeCodeBiz.editSave(dto);
        return ResponseFactory.buildSuccess();
    }

    @AppApi
    @ApiOperation(value = "【APP端】客户用代金券兑换码兑换", httpMethod = "POST",notes = "【APP端】客户用代金券兑换码兑换，为了与后台共用接口，所以传客户电话")
    @RequestMapping(value = "/customerselfcodecharge", method = RequestMethod.POST)
    public Object customerSelfCodeCharge(@ModelAttribute CodeChargeReq req) {
        //参数检查
        CommonValidator.validateMobile(req.getMobile(),new CustomerException(CustomerCodeEnum.CUSTOMER_MOBILE_ERROR));
        voucherExchangeCodeBiz.customerSelfCharge(req.getMobile(),req.getKeyt());
        return ResponseFactory.buildSuccess();
    }

    @ApiOperation(value = "后台用兑换码进行充值", httpMethod = "POST",notes = "后台用兑换码进行充值")
    @RequestMapping(value = "/backstagecodecharge", method = RequestMethod.POST)
    public Object customerSelfCharge(@ModelAttribute CodeChargeReq req) {
        //参数检查
        CommonValidator.validateMobile(req.getMobile(),new CustomerException(CustomerCodeEnum.CUSTOMER_MOBILE_ERROR));
        voucherExchangeCodeBiz.backstageCodeCharge(req.getMobile(),req.getKeyt());
        return ResponseFactory.buildSuccess();
    }
}
