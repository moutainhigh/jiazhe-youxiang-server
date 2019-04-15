package com.jiazhe.youxiang.server.controller.message;

import com.jiazhe.youxiang.base.controller.BaseController;
import com.jiazhe.youxiang.base.util.CommonValidator;
import com.jiazhe.youxiang.base.util.DateUtil;
import com.jiazhe.youxiang.base.util.PagingParamUtil;
import com.jiazhe.youxiang.base.util.UploadUtil;
import com.jiazhe.youxiang.server.adapter.message.MessageAdapter;
import com.jiazhe.youxiang.server.biz.message.MessageBiz;
import com.jiazhe.youxiang.server.common.annotation.CustomLog;
import com.jiazhe.youxiang.server.common.constant.CommonConstant;
import com.jiazhe.youxiang.server.common.enums.LogLevelEnum;
import com.jiazhe.youxiang.server.common.enums.MessageCodeEnum;
import com.jiazhe.youxiang.server.common.enums.ModuleEnum;
import com.jiazhe.youxiang.server.common.exceptions.MessageException;
import com.jiazhe.youxiang.server.dto.message.MessageDTO;
import com.jiazhe.youxiang.server.vo.Paging;
import com.jiazhe.youxiang.server.vo.ResponseFactory;
import com.jiazhe.youxiang.server.vo.req.IdReq;
import com.jiazhe.youxiang.server.vo.req.message.BatchMsgSendReq;
import com.jiazhe.youxiang.server.vo.req.message.MessagePageReq;
import com.jiazhe.youxiang.server.vo.req.message.SingleMsgSendReq;
import com.jiazhe.youxiang.server.vo.resp.message.MessageResp;
import com.jiazhe.youxiang.server.vo.resp.message.UploadMsgExcelResp;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tu
 * @description：短信相关的接口
 * @date 2019-04-07
 */
@RestController
@RequestMapping("api/message")
public class APIMessageController extends BaseController {

    @Autowired
    private MessageBiz messageBiz;

    @Value("${web.upload.ele-product-code-excel-path}")
    private String excelpath;

    @ApiOperation(value = "短信记录列表", httpMethod = "GET", response = MessageResp.class, responseContainer = "List", notes = "短信记录列表")
    @RequestMapping(value = "/listpage", method = RequestMethod.GET)
    @CustomLog(moduleName = ModuleEnum.MESSAGE, operate = "短信记录列表", level = LogLevelEnum.LEVEL_1)
    public Object listPage(@ModelAttribute MessagePageReq req) {
        Paging paging = PagingParamUtil.pagingParamSwitch(req);
        Date sendStartTime = req.getSendStartTime() == CommonConstant.NULL_TIME ? null : new Date(DateUtil.getFirstSecond(req.getSendStartTime()));
        Date sendEndTime = req.getSendEndTime() == CommonConstant.NULL_TIME ? null : new Date(DateUtil.getLastSecond(req.getSendEndTime()));
        List<MessageDTO> messageDTOList = messageBiz.getList(req.getStatus(), req.getType(), req.getMobile(), req.getTopic(), sendStartTime, sendEndTime, paging);
        List<MessageResp> messageRespList = messageDTOList.stream().map(MessageAdapter::dto2Resp).collect(Collectors.toList());
        return ResponseFactory.buildPaginationResponse(messageRespList, paging);
    }

    @ApiOperation(value = "单条发送短信", httpMethod = "POST", notes = "单条发送短信")
    @RequestMapping(value = "/sendsingle", method = RequestMethod.POST)
    @CustomLog(moduleName = ModuleEnum.MESSAGE, operate = "单条发送短信", level = LogLevelEnum.LEVEL_3)
    public Object sendSingle(@ModelAttribute SingleMsgSendReq req) {
        CommonValidator.validateMobile(req.getMobile(), new MessageException(MessageCodeEnum.MOBILE_ILLEGAL));
        CommonValidator.validateNull(req.getMessageTemplateId(),new MessageException(MessageCodeEnum.TEMPLATE_IS_NOT_EXIST));
        CommonValidator.validateNull(req.getContent(), new MessageException(MessageCodeEnum.CONTENT_IS_NULL));
        messageBiz.sendSingle(req.getMobile(), req.getType(), req.getTopic(), req.getMessageTemplateId(), req.getContent());
        return ResponseFactory.buildSuccess();
    }

    @ApiOperation(value = "重新发送", httpMethod = "POST", notes = "重新发送")
    @RequestMapping(value = "/resend", method = RequestMethod.POST)
    @CustomLog(moduleName = ModuleEnum.MESSAGE, operate = "重新发送", level = LogLevelEnum.LEVEL_1)
    public Object resend(@ModelAttribute IdReq req) {
        messageBiz.resend(req.getId());
        return ResponseFactory.buildSuccess();
    }

    @ApiOperation(value = "上传短信excel并校验", httpMethod = "POST", response = UploadMsgExcelResp.class, notes = "上传短信excel并校验")
    @RequestMapping(value = "uploadexcel", method = RequestMethod.POST, headers = ("content-type=multipart/*"), consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @CustomLog(moduleName = ModuleEnum.MESSAGE, operate = "上传短信excel并校验", level = LogLevelEnum.LEVEL_2)
    public Object uploadExcel(@RequestParam("file") MultipartFile file, Integer templateId) {
        String url = UploadUtil.uploadImage(file, excelpath);
        UploadMsgExcelResp result = messageBiz.checkExcel(excelpath + "/" + url, templateId);
        result.setUrl(url);
        return ResponseFactory.buildResponse(result);
    }

    @ApiOperation(value = "批量发送短信", httpMethod = "POST", notes = "批量发送短信")
    @RequestMapping(value = "/sendbatch", method = RequestMethod.POST)
    @CustomLog(moduleName = ModuleEnum.MESSAGE, operate = "批量发送短信", level = LogLevelEnum.LEVEL_3)
    public Object sendBatch(@ModelAttribute BatchMsgSendReq req) {
        CommonValidator.validateNull(req.getMessageTemplateId(),new MessageException(MessageCodeEnum.TEMPLATE_IS_NOT_EXIST));
        CommonValidator.validateNull(req.getExcelUrl(),new MessageException(MessageCodeEnum.EXCEL_ERROR));
        messageBiz.sendBatch(req.getType(), req.getTopic(), req.getMessageTemplateId(), req.getExcelUrl());
        return ResponseFactory.buildSuccess();
    }
}
