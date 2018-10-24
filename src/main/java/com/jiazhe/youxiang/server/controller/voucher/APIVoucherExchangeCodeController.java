package com.jiazhe.youxiang.server.controller.voucher;

import com.jiazhe.youxiang.base.controller.BaseController;
import com.jiazhe.youxiang.server.adapter.voucher.VoucherExchangeCodeAdapter;
import com.jiazhe.youxiang.server.biz.voucher.VoucherExchangeCodeBiz;
import com.jiazhe.youxiang.server.dto.voucher.exchangecode.VoucherExchangeCodeListDTO;
import com.jiazhe.youxiang.server.vo.Paging;
import com.jiazhe.youxiang.server.vo.ResponseFactory;
import com.jiazhe.youxiang.server.vo.req.IdReq;
import com.jiazhe.youxiang.server.vo.req.voucher.exchangecode.CodeChargeReq;
import com.jiazhe.youxiang.server.vo.req.voucher.exchangecode.ExpiryTimeEditReq;
import com.jiazhe.youxiang.server.vo.req.voucher.exchangecode.VoucherExchangeCodeListReq;
import com.jiazhe.youxiang.server.vo.resp.voucher.exchangecode.VoucherExchangeCodeListResp;
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
@RequestMapping("api/voucherexchangecode")
public class APIVoucherExchangeCodeController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(APIVoucherExchangeCodeController.class);

    @Autowired
    private VoucherExchangeCodeBiz voucherExchangeCodeBiz;

    @ApiOperation(value = "分页查询代金券兑换码（根据批次id和兑换码的码和密钥查询）", httpMethod = "GET", response = VoucherExchangeCodeListResp.class, responseContainer = "List",notes = "分页查询代金券兑换码（根据批次id和兑换码的码和密钥查询）")
    @RequestMapping(value = "/listpage", method = RequestMethod.GET)
    public Object listPage(@ModelAttribute VoucherExchangeCodeListReq req) {
        Paging paging = new Paging();
        paging.setOffset((req.getPageNum()-1)*req.getPageSize());
        paging.setLimit(req.getPageSize());
        List<VoucherExchangeCodeListDTO> voucherExchangeCodeListDTOS = voucherExchangeCodeBiz.getList(req.getBatchId(),req.getCode(),req.getKeyt(),paging);
        List<VoucherExchangeCodeListResp> voucherExchangeCodeBatchListResps = voucherExchangeCodeListDTOS.stream().map(VoucherExchangeCodeAdapter::DTOList2RespList).collect(Collectors.toList());
        return ResponseFactory.buildPaginationResponse(voucherExchangeCodeBatchListResps, paging);
    }

    @ApiOperation(value = "启用代金券兑换码", httpMethod = "POST",notes = "启用代金券兑换码")
    @RequestMapping(value = "/startusing", method = RequestMethod.POST)
    public Object startUsing(@ModelAttribute IdReq req) {
        //参数检查
        voucherExchangeCodeBiz.startUsing(req.getId());
        return ResponseFactory.buildSuccess();
    }

    @ApiOperation(value = "停用代金券兑换码", httpMethod = "POST",notes = "停用代金券兑换码")
    @RequestMapping(value = "/stopusing", method = RequestMethod.POST)
    public Object stopUsing(@ModelAttribute IdReq req) {
        //参数检查
        voucherExchangeCodeBiz.stopUsing(req.getId());
        return ResponseFactory.buildSuccess();
    }

    @ApiOperation(value = "修改代金券兑换码过期时间", httpMethod = "POST",notes = "修改代金券兑换码过期时间")
    @RequestMapping(value = "/changeexpirytime", method = RequestMethod.POST)
    public Object changeExpiryTime(@ModelAttribute ExpiryTimeEditReq req) {
        //参数检查
        voucherExchangeCodeBiz.changeExpiryTime(req.getId(),req.getExpiryTime());
        return ResponseFactory.buildSuccess();
    }

    @ApiOperation(value = "客户用兑换码自行充值", httpMethod = "POST",notes = "客户用兑换码自行充值")
    @RequestMapping(value = "/customerselfcodecharge", method = RequestMethod.POST)
    public Object customerSelfCodeCharge(@ModelAttribute CodeChargeReq req) {
        //参数检查
        voucherExchangeCodeBiz.customerSelfCharge(req.getCustomerId(),req.getKeyt());
        return ResponseFactory.buildSuccess();
    }

    @ApiOperation(value = "后台用兑换码进行充值", httpMethod = "POST",notes = "后台用兑换码进行充值")
    @RequestMapping(value = "/backstagecodecharge", method = RequestMethod.POST)
    public Object customerSelfCharge(@ModelAttribute CodeChargeReq req) {
        //参数检查
        voucherExchangeCodeBiz.backstageCodeCharge(req.getCustomerId(),req.getKeyt());
        return ResponseFactory.buildSuccess();
    }
}
