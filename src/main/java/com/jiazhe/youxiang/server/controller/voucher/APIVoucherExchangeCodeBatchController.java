package com.jiazhe.youxiang.server.controller.voucher;

import com.jiazhe.youxiang.base.controller.BaseController;
import com.jiazhe.youxiang.base.util.CommonValidator;
import com.jiazhe.youxiang.base.util.ExportExcelUtils;
import com.jiazhe.youxiang.base.util.PagingParamUtil;
import com.jiazhe.youxiang.server.adapter.voucher.VoucherExchangeCodeBatchAdapter;
import com.jiazhe.youxiang.server.biz.voucher.VoucherExchangeCodeBatchBiz;
import com.jiazhe.youxiang.server.biz.voucher.VoucherExchangeCodeBiz;
import com.jiazhe.youxiang.server.common.annotation.CustomLog;
import com.jiazhe.youxiang.server.common.constant.CommonConstant;
import com.jiazhe.youxiang.server.common.constant.PermissionConstant;
import com.jiazhe.youxiang.server.common.enums.LogLevelEnum;
import com.jiazhe.youxiang.server.common.enums.ModuleEnum;
import com.jiazhe.youxiang.server.common.enums.VoucherCodeEnum;
import com.jiazhe.youxiang.server.common.exceptions.VoucherException;
import com.jiazhe.youxiang.server.dto.voucher.exchangecode.VoucherExchangeCodeDTO;
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
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
    @Autowired
    private VoucherExchangeCodeBiz voucherExchangeCodeBiz;

    @RequiresPermissions(value = {PermissionConstant.VOUCHER_BATCH_MANAGEMENT, PermissionConstant.VOUCHER_BATCH_SEARCH}, logical = Logical.OR)
    @ApiOperation(value = "分页查询代金券兑换码批次信息", httpMethod = "GET", response = VoucherExchangeCodeBatchResp.class, responseContainer = "List", notes = "分页查询代金券兑换码批次信息（根据项目和批次名称查询）")
    @RequestMapping(value = "/listpage", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.VOUCHER, operate = "分页查询代金券兑换码批次信息", level = LogLevelEnum.LEVEL_1)
    public Object listPage(@ModelAttribute VoucherExchangeCodeBatchPageReq req) {
        CommonValidator.validatePaging(req);
        Paging paging = PagingParamUtil.pagingParamSwitch(req);
        List<VoucherExchangeCodeBatchDTO> voucherExchangeCodeBatchDTOList = voucherExchangeCodeBatchBiz.getList(req.getProjectId(), req.getName(), paging);
        List<VoucherExchangeCodeBatchResp> voucherExchangeCodeBatchRespList = voucherExchangeCodeBatchDTOList.stream().map(VoucherExchangeCodeBatchAdapter::DTO2Resp).collect(Collectors.toList());
        return ResponseFactory.buildPaginationResponse(voucherExchangeCodeBatchRespList, paging);
    }

    @RequiresPermissions(value = {PermissionConstant.VOUCHER_BATCH_ADD, PermissionConstant.VOUCHER_BATCH_EDIT}, logical = Logical.OR)
    @ApiOperation(value = "【新建、修改】保存代金券兑换码批次信息", httpMethod = "POST", notes = "【新建、修改】保存代金券兑换码批次信息")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @CustomLog(moduleName = ModuleEnum.VOUCHER, operate = "保存代金券兑换码批次信息", level = LogLevelEnum.LEVEL_2)
    public Object save(@ModelAttribute VoucherExchangeCodeBatchSaveReq req) {
        CommonValidator.validateNull(req);
        CommonValidator.validateNull(req.getId());
        CommonValidator.validateNull(req.getName(), new VoucherException(VoucherCodeEnum.BATCH_NAME_IS_NULL));
        CommonValidator.validateNull(req.getVoucherName(), new VoucherException(VoucherCodeEnum.VOUCHER_NAME_IS_NULL));
        CommonValidator.validateNull(req.getAmount(), new VoucherException(VoucherCodeEnum.AMOUNT_IS_NULL));
        CommonValidator.validateNull(req.getCount(), new VoucherException(VoucherCodeEnum.COUNT_IS_NULL));
        CommonValidator.validateNull(req.getProjectId(), new VoucherException(VoucherCodeEnum.PROJECT_IS_NULL));
        CommonValidator.validateNull(req.getCityCodes(), new VoucherException(VoucherCodeEnum.CITY_IS_NULL));
        CommonValidator.validateNull(req.getProductIds(), new VoucherException(VoucherCodeEnum.PRODUCT_IS_NULL));
        //批次过期时间不为空
        if (req.getExpiryTime() == 0) {
            throw new VoucherException(VoucherCodeEnum.BATCH_EXPIRY_TIME_IS_NULL);
        }
        //代金券过期时间为指定的时间
        if (req.getExpiryType().equals(CommonConstant.VOUCHER_EXPIRY_TIME)) {
            if (req.getVoucherExpiryTime() == 0) {
                throw new VoucherException(VoucherCodeEnum.VOUCHER_EXPIRY_TIME_IS_NULL);
            }
            req.setValidityPeriod(0);
        }
        if (req.getExpiryType().equals(CommonConstant.RECHARGE_CARD_EXPIRY_PERIOD)) {
            CommonValidator.validateNull(req.getValidityPeriod(), new VoucherException(VoucherCodeEnum.VOUCHER_EXPIRY_TIME_IS_NULL));
            req.setVoucherExpiryTime(System.currentTimeMillis());
        }
        VoucherExchangeCodeBatchSaveDTO voucherExchangeCodeBatchSaveDTO = VoucherExchangeCodeBatchAdapter.ReqSave2DTOSave(req);
        if (req.getId() == 0) {
            voucherExchangeCodeBatchBiz.addSave(voucherExchangeCodeBatchSaveDTO);
        } else {
            voucherExchangeCodeBatchBiz.editSave(voucherExchangeCodeBatchSaveDTO);
        }
        return ResponseFactory.buildSuccess();
    }

    @RequiresPermissions(PermissionConstant.VOUCHER_CODE_GENERATE)
    @ApiOperation(value = "生成代金券兑换码", httpMethod = "POST", notes = "生成代金券兑换码")
    @RequestMapping(value = "/generatecode", method = RequestMethod.POST)
    @CustomLog(moduleName = ModuleEnum.VOUCHER, operate = "生成代金券兑换码", level = LogLevelEnum.LEVEL_2)
    public Object generateCode(@ModelAttribute IdReq req) {
        CommonValidator.validateId(req);
        voucherExchangeCodeBatchBiz.generateCode(req.getId());
        return ResponseFactory.buildSuccess();
    }

    @ApiOperation(value = "代金券兑换码批次信息回显", httpMethod = "GET", response = VoucherExchangeCodeBatchEditResp.class, notes = "代金券兑换码批次信息回显")
    @RequestMapping(value = "/getbyid", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.VOUCHER, operate = "代金券兑换码批次信息回显", level = LogLevelEnum.LEVEL_1)
    public Object getById(@ModelAttribute IdReq req) {
        CommonValidator.validateId(req);
        VoucherExchangeCodeBatchEditDTO voucherExchangeCodeBatchEditDTO = voucherExchangeCodeBatchBiz.getById(req.getId());
        VoucherExchangeCodeBatchEditResp voucherExchangeCodeBatchEditResp = VoucherExchangeCodeBatchAdapter.DTOEdit2RespEdit(voucherExchangeCodeBatchEditDTO);
        return ResponseFactory.buildResponse(voucherExchangeCodeBatchEditResp);
    }

    @RequiresPermissions(PermissionConstant.VOUCHER_BATCH_STATUS_CHANGE)
    @ApiOperation(value = "启用批次【同时改变批次下兑换码状态】", httpMethod = "POST", notes = "启用批次【同时改变批次下兑换码状态】")
    @RequestMapping(value = "/startusing", method = RequestMethod.POST)
    @CustomLog(moduleName = ModuleEnum.VOUCHER, operate = "启用批次", level = LogLevelEnum.LEVEL_2)
    public Object startUsing(@ModelAttribute IdReq req) {
        CommonValidator.validateId(req);
        voucherExchangeCodeBatchBiz.startUsing(req.getId());
        return ResponseFactory.buildSuccess();
    }

    @RequiresPermissions(PermissionConstant.VOUCHER_BATCH_STATUS_CHANGE)
    @ApiOperation(value = "停用批次【同时改变批次下兑换码状态】", httpMethod = "POST", notes = "停用批次【同时改变批次下兑换码状态】")
    @RequestMapping(value = "/stopusing", method = RequestMethod.POST)
    @CustomLog(moduleName = ModuleEnum.VOUCHER, operate = "停用批次", level = LogLevelEnum.LEVEL_2)
    public Object stopUsing(@ModelAttribute IdReq req) {
        CommonValidator.validateId(req);
        voucherExchangeCodeBatchBiz.stopUsing(req.getId());
        return ResponseFactory.buildSuccess();
    }

    @RequiresPermissions(PermissionConstant.VOUCHER_CODE_EXPORT)
    @ApiOperation(value = "导出批次下兑换码", httpMethod = "GET", notes = "导出批次下兑换码")
    @RequestMapping(value = "/export", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.VOUCHER, operate = "导出批次下兑换码", level = LogLevelEnum.LEVEL_3)
    public void export(@ModelAttribute IdReq req, HttpServletResponse response) throws IOException {
        List<VoucherExchangeCodeDTO> voucherExchangeCodeDTOList = voucherExchangeCodeBiz.getByBatchId(req.getId());
        ExportExcelUtils.exportVoucherCode(response, voucherExchangeCodeDTOList);
    }

    @RequiresPermissions(PermissionConstant.VOUCHER_CODE_EXPORT)
    @ApiOperation(value = "导出前检查", httpMethod = "GET", notes = "导出前检查")
    @RequestMapping(value = "/exportcheck", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.VOUCHER, operate = "导出前检查", level = LogLevelEnum.LEVEL_1)
    public Object exportCheck(@ModelAttribute IdReq req) {
        CommonValidator.validateId(req);
        List<VoucherExchangeCodeDTO> rcExchangeCodeDTOList = voucherExchangeCodeBiz.getByBatchId(req.getId());
        if (rcExchangeCodeDTOList.isEmpty()) {
            throw new VoucherException(VoucherCodeEnum.NO_CODE_TO_EXPORT);
        }
        return ResponseFactory.buildSuccess();
    }
}
