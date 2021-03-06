package com.jiazhe.youxiang.server.controller.voucher;

import com.jiazhe.youxiang.server.adapter.voucher.VoucherExchangeRecordAdapter;
import com.jiazhe.youxiang.server.biz.voucher.VoucherExchangeRecordBiz;
import com.jiazhe.youxiang.server.common.annotation.CustomLog;
import com.jiazhe.youxiang.server.common.constant.CommonConstant;
import com.jiazhe.youxiang.server.common.enums.LogLevelEnum;
import com.jiazhe.youxiang.server.common.enums.ModuleEnum;
import com.jiazhe.youxiang.server.dto.voucher.exchangerecord.VoucherExchangeRecordDTO;
import com.jiazhe.youxiang.server.vo.Paging;
import com.jiazhe.youxiang.server.vo.ResponseFactory;
import com.jiazhe.youxiang.server.vo.req.voucher.exchangerecord.VoucherExchangeRecordPageReq;
import com.jiazhe.youxiang.server.vo.resp.voucher.exchangerecord.VoucherExchangeRecordResp;
import io.swagger.annotations.ApiOperation;
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
@RequestMapping("api/voucherexchangerecord")
public class APIVoucherExchangeRecordController {

    @Autowired
    private VoucherExchangeRecordBiz voucherExchangeRecordBiz;

    @ApiOperation(value = "【后台、暂时无用】分页查询代金券兑换记录", httpMethod = "GET", response = VoucherExchangeRecordResp.class, responseContainer = "List", notes = "【组合条件】分页查询代金券兑换记录")
    @RequestMapping(value = "/listpage", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.VOUCHER, operate = "分页查询代金券兑换记录", level = LogLevelEnum.LEVEL_1)
    public Object listPage(@ModelAttribute VoucherExchangeRecordPageReq req) {
        Paging paging = new Paging();
        paging.setOffset((req.getPageNum() - 1) * req.getPageSize());
        paging.setLimit(req.getPageSize());
        Date beginDate = req.getBeginDate() == CommonConstant.NULL_TIME ? null : new Date(req.getBeginDate());
        Date endDate = req.getEndDate() == CommonConstant.NULL_TIME ? null : new Date(req.getEndDate());
        List<VoucherExchangeRecordDTO> voucherExchangeCodeBatchDTOList = voucherExchangeRecordBiz.getList(beginDate, endDate, req.getCode(), req.getKeyt(), paging);
        List<VoucherExchangeRecordResp> voucherExchangeCodeBatchRespList = voucherExchangeCodeBatchDTOList.stream().map(VoucherExchangeRecordAdapter::DTO2Resp).collect(Collectors.toList());
        return ResponseFactory.buildPaginationResponse(voucherExchangeCodeBatchRespList, paging);
    }
}
