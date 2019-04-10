package com.jiazhe.youxiang.server.controller.message;

import com.jiazhe.youxiang.base.controller.BaseController;
import com.jiazhe.youxiang.base.util.CommonValidator;
import com.jiazhe.youxiang.server.adapter.message.MessageTemplateAdapter;
import com.jiazhe.youxiang.server.biz.message.MessageTemplateBiz;
import com.jiazhe.youxiang.server.common.annotation.CustomLog;
import com.jiazhe.youxiang.server.common.enums.LogLevelEnum;
import com.jiazhe.youxiang.server.common.enums.ModuleEnum;
import com.jiazhe.youxiang.server.dto.message.MessageTemplateDTO;
import com.jiazhe.youxiang.server.vo.ResponseFactory;
import com.jiazhe.youxiang.server.vo.req.IdReq;
import com.jiazhe.youxiang.server.vo.req.message.TemplateStatus;
import com.jiazhe.youxiang.server.vo.resp.message.MessageTemplateResp;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author TU
 * @description
 * @date 2019-04-08.
 */
@RestController
@RequestMapping("api/msgtemplate")
public class APIMessageTemplateController extends BaseController {

    @Autowired
    private MessageTemplateBiz msgTemplateBiz;

    @ApiOperation(value = "获取所有模板", httpMethod = "GET", response = MessageTemplateResp.class, responseContainer = "List", notes = "获取所有模板")
    @RequestMapping(value = "/getall", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.MESSAGE, operate = "获取所有模板", level = LogLevelEnum.LEVEL_1)
    public Object getAll(TemplateStatus req) {
        List<MessageTemplateDTO> msgTemplateDTOList = msgTemplateBiz.getAll(req.getStatus());
        List<MessageTemplateResp> msgTemplateRespList = msgTemplateDTOList.stream().map(MessageTemplateAdapter::dto2Resp).collect(Collectors.toList());
        return ResponseFactory.buildResponse(msgTemplateRespList);
    }

    @ApiOperation(value = "获取模板", httpMethod = "GET", response = MessageTemplateResp.class, notes = "获取模板")
    @RequestMapping(value = "/getbyid", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.MESSAGE, operate = "获取模板", level = LogLevelEnum.LEVEL_1)
    public Object getById(IdReq req) {
        CommonValidator.validateId(req.getId());
        MessageTemplateDTO msgTemplateDTO = msgTemplateBiz.getById(req.getId());
        MessageTemplateResp msgTemplateResp = MessageTemplateAdapter.dto2Resp(msgTemplateDTO);
        return ResponseFactory.buildResponse(msgTemplateResp);
    }

}
