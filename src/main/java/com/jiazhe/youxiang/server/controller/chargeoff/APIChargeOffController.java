package com.jiazhe.youxiang.server.controller.chargeoff;

import com.jiazhe.youxiang.base.controller.BaseController;
import com.jiazhe.youxiang.base.util.ExportExcelUtils;
import com.jiazhe.youxiang.base.util.JacksonUtil;
import com.jiazhe.youxiang.base.util.PagingParamUtil;
import com.jiazhe.youxiang.base.validator.ChargeOffValidator;
import com.jiazhe.youxiang.server.adapter.ChargeOffAdapter;
import com.jiazhe.youxiang.server.biz.ChargeOffBiz;
import com.jiazhe.youxiang.server.common.annotation.CustomLog;
import com.jiazhe.youxiang.server.common.enums.LogLevelEnum;
import com.jiazhe.youxiang.server.common.enums.ModuleEnum;
import com.jiazhe.youxiang.server.dto.chargeoff.ChargeOffAddDTO;
import com.jiazhe.youxiang.server.dto.chargeoff.ChargeOffFuzzyQueryDTO;
import com.jiazhe.youxiang.server.dto.chargeoff.ChargeOffInfoDTO;
import com.jiazhe.youxiang.server.dto.chargeoff.ChargeOffPointDTO;
import com.jiazhe.youxiang.server.dto.chargeoff.ChargeOffQueryDTO;
import com.jiazhe.youxiang.server.dto.chargeoff.ChargeOffUpdateDTO;
import com.jiazhe.youxiang.server.dto.chargeoff.QuerySummaryDTO;
import com.jiazhe.youxiang.server.vo.Paging;
import com.jiazhe.youxiang.server.vo.ResponseFactory;
import com.jiazhe.youxiang.server.vo.req.IdReq;
import com.jiazhe.youxiang.server.vo.req.chargeoff.ChargeOffAddReq;
import com.jiazhe.youxiang.server.vo.req.chargeoff.ChargeOffFuzzyQueryReq;
import com.jiazhe.youxiang.server.vo.req.chargeoff.ChargeOffQueryReq;
import com.jiazhe.youxiang.server.vo.req.chargeoff.ChargeOffUpdateReq;
import com.jiazhe.youxiang.server.vo.req.chargeoff.ValidateKeytReq;
import com.jiazhe.youxiang.server.vo.resp.chargeoff.ChargeOffInfoResp;
import com.jiazhe.youxiang.server.vo.resp.chargeoff.ChargeOffPointResp;
import com.jiazhe.youxiang.server.vo.resp.chargeoff.QuerySummaryResp;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 核销管理Controller
 *
 * @author niexiao
 * @created 2020/03/04
 */
@RestController
@RequestMapping("api/chargeoff")
public class APIChargeOffController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(APIChargeOffController.class);

    @Autowired
    private ChargeOffBiz chargeOffBiz;

    @ApiOperation(value = "添加核销记录", httpMethod = "POST", notes = "添加核销记录")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @CustomLog(moduleName = ModuleEnum.CHARGE_OFF, operate = "添加核销记录", level = LogLevelEnum.LEVEL_1)
    public Object add(@ModelAttribute ChargeOffAddReq req) {
        LOGGER.info("Controller调用[add]方法,入参:{}", JacksonUtil.toJSon(req));
        ChargeOffValidator.validateChargeOffAddReq(req);
        ChargeOffAddDTO dto = ChargeOffAdapter.chargeOffAddReq2DTO(req);
        chargeOffBiz.add(dto);
        return ResponseFactory.buildSuccess();
    }

    @ApiOperation(value = "编辑核销记录", httpMethod = "POST", notes = "编辑核销记录")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @CustomLog(moduleName = ModuleEnum.CHARGE_OFF, operate = "编辑核销记录", level = LogLevelEnum.LEVEL_1)
    public Object update(@ModelAttribute ChargeOffUpdateReq req) {
        LOGGER.info("Controller调用[update]方法,入参:{}", JacksonUtil.toJSon(req));
        ChargeOffValidator.validateChargeOffUpdateReq(req);
        ChargeOffUpdateDTO dto = ChargeOffAdapter.chargeOffUpdateReq(req);
        chargeOffBiz.update(dto);
        return ResponseFactory.buildSuccess();
    }

    @ApiOperation(value = "删除核销记录", httpMethod = "GET", notes = "删除核销记录")
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.CHARGE_OFF, operate = "删除核销记录", level = LogLevelEnum.LEVEL_1)
    public Object delete(@ModelAttribute IdReq req) {
        LOGGER.info("Controller调用[delete]方法,入参:{}", JacksonUtil.toJSon(req));
        ChargeOffValidator.validateId(req);
        chargeOffBiz.delete(req.getId());
        return ResponseFactory.buildSuccess();
    }

    @ApiOperation(value = "根据id查询核销记录", httpMethod = "GET", notes = "根据id查询核销记录", response = ChargeOffInfoResp.class)
    @RequestMapping(value = "/querybyid", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.CHARGE_OFF, operate = "根据id查询核销记录", level = LogLevelEnum.LEVEL_1)
    public Object queryById(@ModelAttribute IdReq req) {
        LOGGER.info("Controller调用[queryById]方法,入参:{}", JacksonUtil.toJSon(req));
        ChargeOffValidator.validateId(req);
        ChargeOffInfoDTO dto = chargeOffBiz.queryById(req.getId());
        ChargeOffInfoResp resp = ChargeOffAdapter.chargeOffInfoDTO2Resp(dto);
        return ResponseFactory.buildResponse(resp);
    }

    @ApiOperation(value = "模糊查询（小程序端）", httpMethod = "GET", notes = "模糊查询（小程序端）", response = ChargeOffInfoResp.class, responseContainer = "List")
    @RequestMapping(value = "/fuzzyquery", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.CHARGE_OFF, operate = "模糊查询（小程序端）", level = LogLevelEnum.LEVEL_1)
    public Object fuzzyQuery(@ModelAttribute ChargeOffFuzzyQueryReq req) {
        LOGGER.info("Controller调用[fuzzyQuery]方法,入参:{}", JacksonUtil.toJSon(req));
        ChargeOffValidator.validateChargeOffFuzzyQueryReq(req);
        Paging paging = PagingParamUtil.pagingParamSwitch(req);
        ChargeOffFuzzyQueryDTO dto = ChargeOffAdapter.ChargeOffFuzzyQueryReq2DTO(req);
        List<ChargeOffInfoDTO> dtoList = chargeOffBiz.fuzzyQuery(dto, paging);
        List<ChargeOffInfoResp> respList = dtoList.stream().map(ChargeOffAdapter::chargeOffInfoDTO2Resp).collect(Collectors.toList());
        //用ResponseFactory将返回值包装
        return ResponseFactory.buildPaginationResponse(respList, paging);
    }

    @ApiOperation(value = "条件查询（PC端）", httpMethod = "GET", notes = "条件查询（PC端）", response = ChargeOffInfoResp.class, responseContainer = "List")
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.CHARGE_OFF, operate = "条件查询（PC端）", level = LogLevelEnum.LEVEL_1)
    public Object query(@ModelAttribute ChargeOffQueryReq req) {
        LOGGER.info("Controller调用[query]方法,入参:{}", JacksonUtil.toJSon(req));
        ChargeOffValidator.validateChargeOffQueryReq(req, true);
        Paging paging = PagingParamUtil.pagingParamSwitch(req);
        ChargeOffQueryDTO dto = ChargeOffAdapter.ChargeOffQueryReq2DTO(req);
        List<ChargeOffInfoDTO> dtoList = chargeOffBiz.query(dto, paging);
        List<ChargeOffInfoResp> respList = dtoList.stream().map(ChargeOffAdapter::chargeOffInfoDTO2Resp).collect(Collectors.toList());
        //用ResponseFactory将返回值包装
        return ResponseFactory.buildPaginationResponse(respList, paging);
    }

    @ApiOperation(value = "条件查询汇总数据（PC端）", httpMethod = "GET", notes = "条件查询汇总数据（PC端）", response = QuerySummaryResp.class, responseContainer = "List")
    @RequestMapping(value = "/querysummary", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.CHARGE_OFF, operate = "条件查询汇总数据（PC端）", level = LogLevelEnum.LEVEL_1)
    public Object querySummary(@ModelAttribute ChargeOffQueryReq req) {
        LOGGER.info("Controller调用[queryTotalPoint]方法,入参:{}", JacksonUtil.toJSon(req));
        ChargeOffValidator.validateChargeOffQueryReq(req, false);
        ChargeOffQueryDTO dto = ChargeOffAdapter.ChargeOffQueryReq2DTO(req);
        QuerySummaryDTO querySummaryDTO = chargeOffBiz.querySummary(dto);
        return ResponseFactory.buildResponse(ChargeOffAdapter.querySummaryDTO2Resq(querySummaryDTO));
    }

    @ApiOperation(value = "验证密码有效性", httpMethod = "GET", notes = "验证密码有效性", response = ChargeOffPointResp.class)
    @RequestMapping(value = "/validatekeyt", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.CHARGE_OFF, operate = "验证密码有效性", level = LogLevelEnum.LEVEL_1)
    public Object validateKeyt(@ModelAttribute ValidateKeytReq req) {
        LOGGER.info("Controller调用[validateKeyt]方法,入参:{}", JacksonUtil.toJSon(req));
        ChargeOffValidator.validateValidateKeytReq(req);
        ChargeOffPointDTO dto = chargeOffBiz.validateKeyt(req.getKeyt());
        return ResponseFactory.buildResponse(ChargeOffAdapter.chargeOffPointDTO2Resp(dto));
    }

    @ApiOperation(value = "导出核销详情（兑换密码粒度）", httpMethod = "GET", notes = "导出核销详情（兑换密码粒度）")
    @RequestMapping(value = "/exportdetail", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.CHARGE_OFF, operate = "导出核销详情（兑换密码粒度）", level = LogLevelEnum.LEVEL_1)
    public Object exportDetail(@ModelAttribute ChargeOffQueryReq req, HttpServletResponse response) {
        LOGGER.info("Controller调用[exportDetail]方法,入参:{}", JacksonUtil.toJSon(req));
        ChargeOffValidator.validateChargeOffQueryReq(req, false);
        ChargeOffQueryDTO dto = ChargeOffAdapter.ChargeOffQueryReq2DTO(req);
        List<ChargeOffInfoDTO> dtoList = chargeOffBiz.query(dto, null);
        ExportExcelUtils.exportChargeOffDetail(response, dtoList);
        return ResponseFactory.buildSuccess();
    }
}
