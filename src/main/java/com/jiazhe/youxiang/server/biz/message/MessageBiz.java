package com.jiazhe.youxiang.server.biz.message;

import com.jiazhe.youxiang.base.util.ExcelUtils;
import com.jiazhe.youxiang.server.dto.message.MessageDTO;
import com.jiazhe.youxiang.server.dto.message.MessageTemplateDTO;
import com.jiazhe.youxiang.server.service.message.MessageService;
import com.jiazhe.youxiang.server.vo.Paging;
import com.jiazhe.youxiang.server.vo.resp.message.MessageTextResp;
import com.jiazhe.youxiang.server.vo.resp.message.UploadMsgExcelResp;
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
        MessageTemplateDTO msgTemplateDTO = msgTemplateBiz.getById(templateId);
        Sheet sheet = ExcelUtils.excel2Sheet(excelPath);
        //错误信息
        StringBuilder errMsg = new StringBuilder();
        //短信总数量
        Integer count = 0;
        //模板参数个数
        Integer paramCount = msgTemplateDTO.getParamCount();
        //第一行不读取
        boolean isFirstRow = true;
        try {
            for (Row row : sheet) {
                MessageTextResp msgTextResp = null;

                if (isFirstRow) {
                    isFirstRow = false;
                } else {
                    //第一列为电话
                    String mobile = row.getCell(0).getStringCellValue();
                    if (!p.matcher(mobile).matches()) {
                        errMsg.append("第" + count + "行电话号码不符合要求");
                        resp.setSuccess(Byte.valueOf("0"));
                    } else {
                        msgTextResp.setContent(formatterMsg(row, Strings.isEmpty(msgTemplateDTO.getTencentTemplateContent()) ? msgTemplateDTO.getAliTemplateContent() : msgTemplateDTO.getTencentTemplateContent()));
                        msgTextRespList.add(msgTextResp);
                    }
                    count++;
                }
            }
            resp.setErrMsg(errMsg.toString());
            resp.setCount(count);
            resp.setMsgTxtRespList(msgTextRespList);
        } catch (Exception e) {

        }
        return resp;
    }

    private String formatterMsg(Row row, String templateContent) {
        String PARAM_REG = "\\$?\\{[0-9a-zA-Z]+}";
        Pattern p = Pattern.compile(PARAM_REG);
        Matcher matcher = p.matcher(templateContent);
        int i = 1;
        while (matcher.find()) {
            String temp = matcher.group();
            templateContent.replace(temp, row.getCell(i).toString());
            i++;
        }
        return templateContent;
    }


}
