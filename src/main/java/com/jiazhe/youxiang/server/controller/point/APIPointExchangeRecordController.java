package com.jiazhe.youxiang.server.controller.point;

import com.jiazhe.youxiang.base.controller.BaseController;
import com.jiazhe.youxiang.base.util.CommonValidator;
import com.jiazhe.youxiang.base.util.PagingParamUtil;
import com.jiazhe.youxiang.server.adapter.order.OrderInfoAdapter;
import com.jiazhe.youxiang.server.adapter.rechargecard.RCExchangeRecordAdapter;
import com.jiazhe.youxiang.server.biz.order.OrderInfoBiz;
import com.jiazhe.youxiang.server.biz.rechargecard.RCExchangeRecordBiz;
import com.jiazhe.youxiang.server.common.annotation.CustomLog;
import com.jiazhe.youxiang.server.common.enums.LogLevelEnum;
import com.jiazhe.youxiang.server.common.enums.ModuleEnum;
import com.jiazhe.youxiang.server.dto.order.orderinfo.OrderInfoDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangerecord.RCExchangeRecordDTO;
import com.jiazhe.youxiang.server.vo.Paging;
import com.jiazhe.youxiang.server.vo.ResponseFactory;
import com.jiazhe.youxiang.server.vo.req.IdReq;
import com.jiazhe.youxiang.server.vo.req.rechargecard.rcexchangerecord.RCExchangeRecordPageReq;
import com.jiazhe.youxiang.server.vo.resp.order.orderinfo.OrderInfoResp;
import com.jiazhe.youxiang.server.vo.resp.rechargecard.rcexchangerecord.RCExchangeRecordResp;
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
 * @author tu
 * @description：积分兑换记录
 * @date 2018/10/21
 */
@RestController
@RequestMapping("api/pointexchangerecord")
public class APIPointExchangeRecordController extends BaseController {

}
