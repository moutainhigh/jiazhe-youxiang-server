package com.jiazhe.youxiang.server.controller.voucher;

import com.jiazhe.youxiang.base.controller.BaseController;
import com.jiazhe.youxiang.base.util.CommonValidator;
import com.jiazhe.youxiang.base.util.PagingParamUtil;
import com.jiazhe.youxiang.server.adapter.voucher.VoucherAdapter;
import com.jiazhe.youxiang.server.biz.voucher.VoucherBiz;
import com.jiazhe.youxiang.server.common.enums.VoucherCodeEnum;
import com.jiazhe.youxiang.server.common.exceptions.VoucherException;
import com.jiazhe.youxiang.server.dto.voucher.voucher.VoucherDTO;
import com.jiazhe.youxiang.server.dto.voucher.voucher.VoucherEditDTO;
import com.jiazhe.youxiang.server.vo.Paging;
import com.jiazhe.youxiang.server.vo.ResponseFactory;
import com.jiazhe.youxiang.server.vo.req.IdReq;
import com.jiazhe.youxiang.server.vo.req.voucher.voucher.VoucherEditReq;
import com.jiazhe.youxiang.server.vo.req.voucher.voucher.VoucherPageReq;
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

    @ApiOperation(value = "【后台】信息查询页查询代金券", httpMethod = "GET", response = VoucherResp.class, responseContainer = "List",notes = "【后台】信息查询页查询代金券")
    @RequestMapping(value = "/searchlistpage", method = RequestMethod.GET)
    public Object searchListPage(@ModelAttribute VoucherPageReq req) {
        CommonValidator.validatePaging(req);
        Paging paging = PagingParamUtil.pagingParamSwitch(req);
        List<VoucherDTO> voucherDTOList = voucherBiz.getList(req.getMobile(),req.getExchangeType(),req.getStatus(),req.getExpiry(),paging);
        List<VoucherResp> rcRespList = voucherDTOList.stream().map(VoucherAdapter::DTO2Resp).collect(Collectors.toList());
        return ResponseFactory.buildPaginationResponse(rcRespList, paging);
    }

    @ApiOperation(value = "启用代金券", httpMethod = "POST",notes = "启用代金券")
    @RequestMapping(value = "/startusing", method = RequestMethod.POST)
    public Object startUsing(@ModelAttribute IdReq req) {
        //参数检查
        CommonValidator.validateId(req);
        voucherBiz.startUsing(req.getId());
        return ResponseFactory.buildSuccess();
    }

    @ApiOperation(value = "停用代金券", httpMethod = "POST",notes = "停用代金券")
    @RequestMapping(value = "/stopusing", method = RequestMethod.POST)
    public Object stopUsing(@ModelAttribute IdReq req) {
        //参数检查
        CommonValidator.validateId(req);
        voucherBiz.stopUsing(req.getId());
        return ResponseFactory.buildSuccess();
    }

    @ApiOperation(value = "回显代金券信息", httpMethod = "GET",response = VoucherResp.class,notes = "回显代金券信息")
    @RequestMapping(value = "/getbyid", method = RequestMethod.GET)
    public Object getById(@ModelAttribute IdReq req) {
        //参数检查
        CommonValidator.validateId(req);
        VoucherDTO voucher = voucherBiz.getById(req.getId());
        VoucherResp voucherResp = VoucherAdapter.DTO2Resp(voucher);
        return ResponseFactory.buildResponse(voucherResp);
    }

    @ApiOperation(value = "修改代金券信息", httpMethod = "POST",notes = "修改代金券信息")
    @RequestMapping(value = "/editsave", method = RequestMethod.POST)
    public Object editSave(@ModelAttribute VoucherEditReq req) {
        //参数检查
        CommonValidator.validateNull(req);
        CommonValidator.validateNull(req.getId());
        CommonValidator.validateNull(req.getName(),new VoucherException(VoucherCodeEnum.VOUCHER_NAME_IS_NULL));
        CommonValidator.validateNull(req.getExpiryTime(),new VoucherException(VoucherCodeEnum.EXCHANGE_CODE_EXPIRY_TIME_IS_NULL));
        VoucherEditDTO dto = VoucherAdapter.EditReq2EditDTO(req);
        voucherBiz.editSave(dto);
        return ResponseFactory.buildSuccess();
    }

}
