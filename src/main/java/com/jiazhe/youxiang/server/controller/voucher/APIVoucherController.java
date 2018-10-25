package com.jiazhe.youxiang.server.controller.voucher;

import com.jiazhe.youxiang.base.controller.BaseController;
import com.jiazhe.youxiang.server.adapter.voucher.VoucherAdapter;

import com.jiazhe.youxiang.server.biz.voucher.VoucherBiz;
import com.jiazhe.youxiang.server.dto.voucher.voucher.VoucherDTO;
import com.jiazhe.youxiang.server.vo.Paging;
import com.jiazhe.youxiang.server.vo.ResponseFactory;
import com.jiazhe.youxiang.server.vo.req.IdReq;
import com.jiazhe.youxiang.server.vo.req.PageSizeNumReq;
import com.jiazhe.youxiang.server.vo.req.ExpiryTimeEditReq;
import com.jiazhe.youxiang.server.vo.resp.voucher.voucher.VoucherResp;
import io.swagger.annotations.ApiOperation;
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
@RequestMapping("api/voucher")
public class APIVoucherController extends BaseController{

    @Autowired
    private VoucherBiz voucherBiz;

    @ApiOperation(value = "启用代金券", httpMethod = "POST",notes = "启用代金券")
    @RequestMapping(value = "/startusing", method = RequestMethod.POST)
    public Object startUsing(@ModelAttribute IdReq req) {
        //参数检查
        voucherBiz.startUsing(req.getId());
        return ResponseFactory.buildSuccess();
    }

    @ApiOperation(value = "停用代金券", httpMethod = "POST",notes = "停用代金券")
    @RequestMapping(value = "/stopusing", method = RequestMethod.POST)
    public Object stopUsing(@ModelAttribute IdReq req) {
        //参数检查
        voucherBiz.stopUsing(req.getId());
        return ResponseFactory.buildSuccess();
    }

    @ApiOperation(value = "修改代金券过期时间", httpMethod = "POST",notes = "修改代金券过期时间")
    @RequestMapping(value = "/changeexpirytime", method = RequestMethod.POST)
    public Object changeExpiryTime(@ModelAttribute ExpiryTimeEditReq req) {
        //参数检查
        voucherBiz.changeExpiryTime(req.getId(),req.getExpiryTime());
        return ResponseFactory.buildSuccess();
    }

    @ApiOperation(value = "根据客户id分页查询所有代金券", httpMethod = "GET",notes = "根据客户id分页查询所有代金券")
    @RequestMapping(value = "/getlist", method = RequestMethod.POST)
    public Object getList(@ModelAttribute IdReq reqId, @ModelAttribute PageSizeNumReq req) {
        //参数检查
        Paging paging = new Paging();
        paging.setOffset((req.getPageNum()-1)*req.getPageSize());
        paging.setLimit(req.getPageSize());
        List<VoucherDTO> rcDTOList = voucherBiz.getList(reqId.getId(),paging);
        List<VoucherResp> rcRespList = rcDTOList.stream().map(VoucherAdapter::DTO2Resp).collect(Collectors.toList());
        return ResponseFactory.buildResponse(rcRespList);
    }

    @ApiOperation(value = "根据客户id查询所有【未过期】代金券", httpMethod = "GET",notes = "根据客户id查询所有【未过期】代金券")
    @RequestMapping(value = "/findunexpiredbycustomerid", method = RequestMethod.POST)
    public Object changeExpiryTime(@ModelAttribute IdReq req) {
        //参数检查
        List<VoucherDTO> rcDTOList = voucherBiz.findUnexpiredByCustomerId(req.getId());
        List<VoucherResp> rcRespList = rcDTOList.stream().map(VoucherAdapter::DTO2Resp).collect(Collectors.toList());
        return ResponseFactory.buildResponse(rcRespList);
    }

}
