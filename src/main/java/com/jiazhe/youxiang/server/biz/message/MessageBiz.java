package com.jiazhe.youxiang.server.biz.message;

import com.jiazhe.youxiang.base.util.ExcelUtils;
import com.jiazhe.youxiang.server.dto.message.MessageDTO;
import com.jiazhe.youxiang.server.dto.message.MessageTemplateDTO;
import com.jiazhe.youxiang.server.service.message.MessageService;
import com.jiazhe.youxiang.server.vo.Paging;
import com.jiazhe.youxiang.server.vo.resp.message.MessageTextResp;
import com.jiazhe.youxiang.server.vo.resp.message.UploadMsgExcelResp;
import net.sf.json.JSONObject;
import org.apache.logging.log4j.util.Strings;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author tu
 * @description：
 * @date 2019-04-07
 */
@Service("messageBiz")
public class MessageBiz {

    @Autowired
    private MessageService messageService;
    @Autowired
    private MessageTemplateBiz msgTemplateBiz;

    public List<MessageDTO> getList(Byte status, Byte type, String mobile, String topic, Date sendStartTime, Date sendEndTime, Paging paging) {
        return messageService.getList(status, type, mobile, topic, sendStartTime, sendEndTime, paging);
    }

    public void insertVerCodeMsg(Byte serviceProvider, String phone, String code, Byte type) {
        messageService.insertVerCodeMsg(serviceProvider, phone, code, type);
    }

    public void resend(Integer id) {
        messageService.resend(id);
    }

    public void sendSingle(String mobile, Byte type, String topic, int messageTemplateId, String content) {
        messageService.sendSingle(mobile, type, topic, messageTemplateId, content);
    }

    public UploadMsgExcelResp checkExcel(String excelPath, Integer templateId) {
        String PHONE_NUMBER_REG = "^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\\d{8}$";
        Pattern p = Pattern.compile(PHONE_NUMBER_REG);
        List<MessageTextResp> msgTextRespList = new ArrayList<>();
        UploadMsgExcelResp resp = new UploadMsgExcelResp();
        resp.setSuccess(Byte.valueOf("1"));
        MessageTemplateDTO msgTemplateDTO = msgTemplateBiz.getById(templateId);
        Sheet sheet = ExcelUtils.excel2Sheet(excelPath);
        //短信总数量
        Integer count = 0;
        //短信合格数量
        Integer validCount = 0;
        //第一行不读取
        boolean isFirstRow = true;
        try {
            for (Row row : sheet) {
                MessageTextResp msgTextResp = new MessageTextResp();
                String errMsg = "";
                if (isFirstRow) {
                    isFirstRow = false;
                } else {
                    String mobile = ExcelUtils.getStringValue(row.getCell(0));
                    msgTextResp.setMobile(mobile);
                    if (!p.matcher(mobile).matches()) {
                        errMsg = errMsg + "电话号码不符合要求。";
                        msgTextResp.setValid(Byte.valueOf("0"));
                        resp.setSuccess(Byte.valueOf("0"));
                    }
                    count++;
                    JSONObject jsonObject = formatterMsg(row, Strings.isEmpty(msgTemplateDTO.getTencentTemplateContent()) ? msgTemplateDTO.getAliTemplateContent() : msgTemplateDTO.getTencentTemplateContent());
                    msgTextResp.setContent(jsonObject.getString("content"));
                    if (p.matcher(mobile).matches() && jsonObject.getBoolean("legal")) {
                        msgTextResp.setValid(Byte.valueOf("1"));
                        validCount++;
                    } else {
                        msgTextResp.setValid(Byte.valueOf("0"));
                        errMsg = errMsg + jsonObject.getString("errMsg");
                    }
                    msgTextResp.setErrMsg(errMsg);
                    msgTextRespList.add(msgTextResp);
                }
            }
            resp.setCount(count);
            resp.setValidCount(validCount);
            resp.setMsgTxtRespList(msgTextRespList);
        } catch (Exception e) {

        }
        return resp;
    }

    private JSONObject formatterMsg(Row row, String templateContent) {
        StringBuilder errMsg = new StringBuilder();
        JSONObject jsonObject = new JSONObject();
        String PARAM_REG = "\\$?\\{[0-9a-zA-Z]+}";
        Pattern p = Pattern.compile(PARAM_REG);
        Matcher matcher = p.matcher(templateContent);
        int i = 1;
        boolean legal = true;
        boolean hasNull = false;
        boolean hasSemicolon = false;
        while (matcher.find()) {
            String temp = matcher.group();
            String cellStr = ExcelUtils.getStringValue(row.getCell(i));
            templateContent = templateContent.replace(temp, cellStr);
            i++;
            if (Strings.isEmpty(cellStr)) {
                hasNull = true;
                legal = false;
            }
            if (cellStr.contains(";")) {
                hasSemicolon = true;
                legal = false;
            }
        }
        if (hasNull) {
            errMsg.append("excel中不能有空值单元格。");
        }
        if (hasSemicolon) {
            errMsg.append("excel中不能有英文分号。");
        }
        jsonObject.put("content", templateContent);
        jsonObject.put("legal", legal);
        jsonObject.put("errMsg", errMsg.toString());
        return jsonObject;
    }
}
