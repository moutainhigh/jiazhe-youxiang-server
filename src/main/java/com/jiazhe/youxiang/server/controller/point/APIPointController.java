package com.jiazhe.youxiang.server.controller.point;

import com.jiazhe.youxiang.base.controller.BaseController;
import com.jiazhe.youxiang.base.util.CommonValidator;
import com.jiazhe.youxiang.base.util.PagingParamUtil;
import com.jiazhe.youxiang.server.adapter.point.PointAdapter;
import com.jiazhe.youxiang.server.biz.point.PointBiz;
import com.jiazhe.youxiang.server.common.annotation.AppApi;
import com.jiazhe.youxiang.server.common.annotation.CustomLog;
import com.jiazhe.youxiang.server.common.enums.LogLevelEnum;
import com.jiazhe.youxiang.server.common.enums.ModuleEnum;
import com.jiazhe.youxiang.server.common.enums.PointCodeEnum;
import com.jiazhe.youxiang.server.common.enums.RechargeCardCodeEnum;
import com.jiazhe.youxiang.server.common.exceptions.PointException;
import com.jiazhe.youxiang.server.common.exceptions.RechargeCardException;
import com.jiazhe.youxiang.server.dto.point.point.PointDTO;
import com.jiazhe.youxiang.server.dto.point.point.PointEditDTO;
import com.jiazhe.youxiang.server.vo.Paging;
import com.jiazhe.youxiang.server.vo.ResponseFactory;
import com.jiazhe.youxiang.server.vo.req.IdReq;
import com.jiazhe.youxiang.server.vo.req.point.point.*;
import com.jiazhe.youxiang.server.vo.resp.point.point.PointResp;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tu
 * @description：积分
 * @date 2018/10/21
 */
@RestController
@RequestMapping("api/point")
public class APIPointController extends BaseController{

    @Autowired
    private PointBiz pointBiz;

    @ApiOperation(value = "【后台】积分卡列表（用于信息查询页面）", httpMethod = "GET", response = PointResp.class, responseContainer = "List",notes = "信息查询页查询积分卡")
    @RequestMapping(value = "/searchlistpage", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.POINT, operate = "积分卡列表", level = LogLevelEnum.LEVEL_1)
    public Object searchListPage(@ModelAttribute PointPageReq req) {
        CommonValidator.validatePaging(req);
        Paging paging = PagingParamUtil.pagingParamSwitch(req);
        List<PointDTO> pointDTOList = pointBiz.getList(req.getMobile(),req.getExchangeType(),req.getStatus(),req.getExpiry(),paging);
        List<PointResp> pointRespList = pointDTOList.stream().map(PointAdapter::dto2Resp).collect(Collectors.toList());
        return ResponseFactory.buildPaginationResponse(pointRespList, paging);
    }

    @AppApi
    @ApiOperation(value = "【APP端】客户查询所有积分卡（分页）", httpMethod = "GET", response = PointResp.class, responseContainer = "List",notes = "客户查询所有积分卡，分页")
    @RequestMapping(value = "/findbycustomeridpage", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.POINT, operate = "客户查询所有积分卡", level = LogLevelEnum.LEVEL_1)
    public Object findByCustomerIdPage(@ModelAttribute PointCustomerPageReq req) {
        CommonValidator.validatePaging(req);
        Paging paging = PagingParamUtil.pagingParamSwitch(req);
        List<PointDTO> pointDTOList = pointBiz.getListByCustomerId(req.getCustomerId(),req.getStatus(),paging);
        List<PointResp> pointRespList = pointDTOList.stream().map(PointAdapter::Dto2Resp).collect(Collectors.toList());
        return ResponseFactory.buildPaginationResponse(pointRespList, paging);
    }

    @AppApi
    @ApiOperation(value = "【后台、APP端】根据购买物属性（商品和城市），查询客户可使用的积分卡，分页", httpMethod = "GET", response = PointResp.class, responseContainer = "List",notes = "根据购买物属性（商品和城市），查询客户可使用的积分卡，分页")
    @RequestMapping(value = "/findbygoodsattrpage", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.POINT, operate = "根据购买物属性（商品和城市），查询客户可使用的积分卡，分页", level = LogLevelEnum.LEVEL_1)
    public Object findByGoodsAttrPage(@ModelAttribute PointGoodsAttrPageReq req) {
        CommonValidator.validatePaging(req);
        Paging paging = PagingParamUtil.pagingParamSwitch(req);
        List<PointDTO> pointDTOList = pointBiz.getListByGoodsAttr(req.getCustomerId(),req.getProductId(),req.getCityCode(),paging);
        List<PointResp> pointRespList = pointDTOList.stream().map(PointAdapter::Dto2Resp).collect(Collectors.toList());
        return ResponseFactory.buildPaginationResponse(pointRespList, paging);
    }

    @ApiOperation(value = "【后台】启用积分卡", httpMethod = "POST",notes = "启用积分卡")
    @RequestMapping(value = "/startusing", method = RequestMethod.POST)
    @CustomLog(moduleName = ModuleEnum.POINT, operate = "启用积分卡", level = LogLevelEnum.LEVEL_2)
    public Object startUsing(@ModelAttribute IdReq req) {
        //参数检查
        CommonValidator.validateId(req);
        pointBiz.startUsing(req.getId());
        return ResponseFactory.buildSuccess();
    }

    @ApiOperation(value = "【后台】停用积分卡", httpMethod = "POST",notes = "停用积分卡")
    @RequestMapping(value = "/stopusing", method = RequestMethod.POST)
    @CustomLog(moduleName = ModuleEnum.POINT, operate = "停用积分卡", level = LogLevelEnum.LEVEL_2)
    public Object stopUsing(@ModelAttribute IdReq req) {
        //参数检查
        CommonValidator.validateId(req);
        pointBiz.stopUsing(req.getId());
        return ResponseFactory.buildSuccess();
    }

    @ApiOperation(value = "【后台】直接给客户充值任意分数", httpMethod = "POST",notes = "直接给客户充值任意分数")
    @RequestMapping(value = "/directcharge", method = RequestMethod.POST)
    @CustomLog(moduleName = ModuleEnum.POINT, operate = "直接给客户充值任意分数", level = LogLevelEnum.LEVEL_3)
    public Object directCharge(@ModelAttribute DirectChargeReq req) {
        //参数检查
        CommonValidator.validateId(req.getId());
        CommonValidator.validateId(req.getBatchId());
        CommonValidator.validateNull(req.getFaceValue());
        pointBiz.directCharge(req.getId(),req.getBatchId(),req.getFaceValue());
        return ResponseFactory.buildSuccess();
    }

    @ApiOperation(value = "【后台】回显积分卡信息", httpMethod = "GET",response = PointResp.class,notes = "回显积分卡信息")
    @RequestMapping(value = "/getbyid", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.POINT, operate = "回显积分卡信息", level = LogLevelEnum.LEVEL_1)
    public Object getById(@ModelAttribute IdReq req) {
        //参数检查
        CommonValidator.validateId(req);
        PointDTO pointDTO = pointBiz.getById(req.getId());
        PointResp pointResp = PointAdapter.Dto2Resp(pointDTO);
        return ResponseFactory.buildResponse(pointResp);
    }

    @ApiOperation(value = "【后台】修改积分卡信息", httpMethod = "POST",notes = "修改积分卡信息")
    @RequestMapping(value = "/editsave", method = RequestMethod.POST)
    @CustomLog(moduleName = ModuleEnum.POINT, operate = "修改积分卡信息", level = LogLevelEnum.LEVEL_2)
    public Object editSave(@ModelAttribute PointEditReq req) {
        //参数检查
        CommonValidator.validateNull(req);
        CommonValidator.validateNull(req.getId());
        CommonValidator.validateNull(req.getName(),new RechargeCardException(RechargeCardCodeEnum.RECHARGE_CARD_NAME_IS_NULL));
        if(req.getExpiryTime()==0){
            throw new PointException(PointCodeEnum.EXCHANGE_CODE_EXPIRY_TIME_IS_NULL);
        }
        PointEditDTO dto = PointAdapter.editReq2EditDTO(req);
        pointBiz.editSave(dto);
        return ResponseFactory.buildSuccess();
    }
}
