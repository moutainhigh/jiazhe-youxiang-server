package com.jiazhe.youxiang.server.controller.voucher;

import com.jiazhe.youxiang.base.controller.BaseController;
import com.jiazhe.youxiang.base.util.CommonValidator;
import com.jiazhe.youxiang.base.util.DateUtil;
import com.jiazhe.youxiang.base.util.PagingParamUtil;
import com.jiazhe.youxiang.server.adapter.voucher.VoucherAdapter;
import com.jiazhe.youxiang.server.biz.voucher.VoucherBiz;
import com.jiazhe.youxiang.server.common.annotation.AppApi;
import com.jiazhe.youxiang.server.common.annotation.CustomLog;
import com.jiazhe.youxiang.server.common.constant.PermissionConstant;
import com.jiazhe.youxiang.server.common.enums.LogLevelEnum;
import com.jiazhe.youxiang.server.common.enums.ModuleEnum;
import com.jiazhe.youxiang.server.common.enums.VoucherCodeEnum;
import com.jiazhe.youxiang.server.common.exceptions.VoucherException;
import com.jiazhe.youxiang.server.dto.voucher.voucher.VoucherDTO;
import com.jiazhe.youxiang.server.dto.voucher.voucher.VoucherEditDTO;
import com.jiazhe.youxiang.server.vo.Paging;
import com.jiazhe.youxiang.server.vo.ResponseFactory;
import com.jiazhe.youxiang.server.vo.req.IdReq;
import com.jiazhe.youxiang.server.vo.req.voucher.voucher.VoucherCustomerPageReq;
import com.jiazhe.youxiang.server.vo.req.voucher.voucher.VoucherEditReq;
import com.jiazhe.youxiang.server.vo.req.voucher.voucher.VoucherGoodsAttrPageReq;
import com.jiazhe.youxiang.server.vo.req.voucher.voucher.VoucherPageReq;
import com.jiazhe.youxiang.server.vo.resp.voucher.voucher.VoucherResp;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
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

    @RequiresPermissions(value = {PermissionConstant.CUSTOMER_VOUCHER_DETAIL,PermissionConstant.VOUCHER_SEARCH},logical = Logical.OR)
    @ApiOperation(value = "【后台】信息查询页查询代金券", httpMethod = "GET", response = VoucherResp.class, responseContainer = "List",notes = "【后台】信息查询页查询代金券")
    @RequestMapping(value = "/searchlistpage", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.VOUCHER, operate = "信息查询页查询代金券", level = LogLevelEnum.LEVEL_1)
    public Object searchListPage(@ModelAttribute VoucherPageReq req) {
        CommonValidator.validatePaging(req);
        Paging paging = PagingParamUtil.pagingParamSwitch(req);
        List<VoucherDTO> voucherDTOList = voucherBiz.getList(req.getMobile(),req.getExchangeType(),req.getStatus(),req.getExpiry(),paging);
        List<VoucherResp> rcRespList = voucherDTOList.stream().map(VoucherAdapter::DTO2Resp).collect(Collectors.toList());
        return ResponseFactory.buildPaginationResponse(rcRespList, paging);
    }

    @RequiresPermissions(PermissionConstant.CUSTOMER_PERMISSION)
    @AppApi
    @ApiOperation(value = "【APP端】查询客户的代金券", httpMethod = "GET", response = VoucherResp.class, responseContainer = "List",notes = "查询客户可用的代金券")
    @RequestMapping(value = "/findbycustomeridpage", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.VOUCHER, operate = "查询客户的代金券", level = LogLevelEnum.LEVEL_1)
    public Object findByCustomerIdPage(@ModelAttribute VoucherCustomerPageReq req) {
        CommonValidator.validatePaging(req);
        Paging paging = PagingParamUtil.pagingParamSwitch(req);
        List<VoucherDTO> voucherDTOList = voucherBiz.getListByCustomerId(req.getCustomerId(),req.getStatus(),paging);
        List<VoucherResp> rcRespList = voucherDTOList.stream().map(VoucherAdapter::DTO2Resp).collect(Collectors.toList());
        return ResponseFactory.buildPaginationResponse(rcRespList, paging);
    }

    @AppApi
    @ApiOperation(value = "【APP端、后台】根据购买物属性（商品和城市），查询客户可使用的代金券，分页", httpMethod = "GET", response = VoucherResp.class, responseContainer = "List",notes = "【APP端、后台】根据购买物属性（商品和城市），查询客户可使用的代金券，分页")
    @RequestMapping(value = "/findbygoodsattrpage", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.VOUCHER, operate = "根据购买物属性（商品和城市），查询客户可使用的代金券", level = LogLevelEnum.LEVEL_1)
    public Object findByGoodsAttrPage(@ModelAttribute VoucherGoodsAttrPageReq req) {
        CommonValidator.validatePaging(req);
        Paging paging = PagingParamUtil.pagingParamSwitch(req);
        List<VoucherDTO> voucherDTOList = voucherBiz.getListByGoodsAttr(req.getCustomerId(),req.getProductId(),req.getCityCode(),paging);
        List<VoucherResp> rcRespList = voucherDTOList.stream().map(VoucherAdapter::DTO2Resp).collect(Collectors.toList());
        return ResponseFactory.buildPaginationResponse(rcRespList, paging);
    }

    @RequiresPermissions(PermissionConstant.VOUCHER_SEARCH_STATUS_CHANGE)
    @ApiOperation(value = "启用代金券", httpMethod = "POST",notes = "启用代金券")
    @RequestMapping(value = "/startusing", method = RequestMethod.POST)
    @CustomLog(moduleName = ModuleEnum.VOUCHER, operate = "启用代金券", level = LogLevelEnum.LEVEL_2)
    public Object startUsing(@ModelAttribute IdReq req) {
        CommonValidator.validateId(req);
        voucherBiz.startUsing(req.getId());
        return ResponseFactory.buildSuccess();
    }

    @RequiresPermissions(PermissionConstant.VOUCHER_SEARCH_STATUS_CHANGE)
    @ApiOperation(value = "停用代金券", httpMethod = "POST",notes = "停用代金券")
    @RequestMapping(value = "/stopusing", method = RequestMethod.POST)
    @CustomLog(moduleName = ModuleEnum.VOUCHER, operate = "停用代金券", level = LogLevelEnum.LEVEL_2)
    public Object stopUsing(@ModelAttribute IdReq req) {
        CommonValidator.validateId(req);
        voucherBiz.stopUsing(req.getId());
        return ResponseFactory.buildSuccess();
    }

    @ApiOperation(value = "回显代金券信息", httpMethod = "GET",response = VoucherResp.class,notes = "回显代金券信息")
    @RequestMapping(value = "/getbyid", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.VOUCHER, operate = "回显代金券信息", level = LogLevelEnum.LEVEL_1)
    public Object getById(@ModelAttribute IdReq req) {
        CommonValidator.validateId(req);
        VoucherDTO voucher = voucherBiz.getById(req.getId());
        VoucherResp voucherResp = VoucherAdapter.DTO2Resp(voucher);
        return ResponseFactory.buildResponse(voucherResp);
    }

    @RequiresPermissions(PermissionConstant.VOUCHER_SEARCH_EDIT)
    @ApiOperation(value = "修改代金券信息", httpMethod = "POST",notes = "修改代金券信息")
    @RequestMapping(value = "/editsave", method = RequestMethod.POST)
    @CustomLog(moduleName = ModuleEnum.VOUCHER, operate = "修改代金券信息", level = LogLevelEnum.LEVEL_2)
    public Object editSave(@ModelAttribute VoucherEditReq req) throws ParseException {
        CommonValidator.validateNull(req);
        CommonValidator.validateNull(req.getId());
        CommonValidator.validateNull(req.getName(),new VoucherException(VoucherCodeEnum.VOUCHER_NAME_IS_NULL));
        if(req.getEffectiveTime()==0){
            throw new VoucherException(VoucherCodeEnum.VOUCHER_EFFECTIVE_TIME_IS_NULL);
        }
        req.setEffectiveTime(DateUtil.getFirstSecond(req.getEffectiveTime()));
        if(req.getExpiryTime()==0){
            throw new VoucherException(VoucherCodeEnum.VOUCHER_EXPIRY_TIME_IS_NULL);
        }
        req.setExpiryTime(DateUtil.getLastSecond(req.getExpiryTime()));
        VoucherEditDTO dto = VoucherAdapter.EditReq2EditDTO(req);
        voucherBiz.editSave(dto);
        return ResponseFactory.buildSuccess();
    }

}
