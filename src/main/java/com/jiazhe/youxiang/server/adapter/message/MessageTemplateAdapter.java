package com.jiazhe.youxiang.server.adapter.message;

import com.jiazhe.youxiang.server.domain.po.MessageTemplatePO;
import com.jiazhe.youxiang.server.dto.message.MessageTemplateDTO;
import com.jiazhe.youxiang.server.vo.resp.message.MessageTemplateResp;

/**
 * @author TU
 * @description
 * @date 2019-04-08.
 */
public class MessageTemplateAdapter {
    public static MessageTemplateResp dto2Resp(MessageTemplateDTO messageTemplateDTO) {
        if (messageTemplateDTO == null) {
            return null;
        }
        MessageTemplateResp messageTemplateResp = new MessageTemplateResp();
        messageTemplateResp.setId(messageTemplateDTO.getId());
        messageTemplateResp.setName(messageTemplateDTO.getName());
        messageTemplateResp.setParamCount(messageTemplateDTO.getParamCount());
        messageTemplateResp.setAliTemplateCode(messageTemplateDTO.getAliTemplateCode());
        messageTemplateResp.setAliTemplateContent(messageTemplateDTO.getAliTemplateContent());
        messageTemplateResp.setTencentTemplateId(messageTemplateDTO.getTencentTemplateId());
        messageTemplateResp.setTencentTemplateContent(messageTemplateDTO.getTencentTemplateContent());
        messageTemplateResp.setStatus(messageTemplateDTO.getStatus());
        messageTemplateResp.setExcelTemplate(messageTemplateDTO.getExcelTemplate());
        messageTemplateResp.setRemark(messageTemplateDTO.getRemark());
        return messageTemplateResp;
    }


    public static MessageTemplateDTO po2Dto(MessageTemplatePO messageTemplatePO) {
        if (messageTemplatePO == null) {
            return null;
        }
        MessageTemplateDTO messageTemplateDTO = new MessageTemplateDTO();
        messageTemplateDTO.setId(messageTemplatePO.getId());
        messageTemplateDTO.setName(messageTemplatePO.getName());
        messageTemplateDTO.setParamCount(messageTemplatePO.getParamCount());
        messageTemplateDTO.setAliTemplateCode(messageTemplatePO.getAliTemplateCode());
        messageTemplateDTO.setAliTemplateContent(messageTemplatePO.getAliTemplateContent());
        messageTemplateDTO.setTencentTemplateId(messageTemplatePO.getTencentTemplateId());
        messageTemplateDTO.setTencentTemplateContent(messageTemplatePO.getTencentTemplateContent());
        messageTemplateDTO.setStatus(messageTemplatePO.getStatus());
        messageTemplateDTO.setExcelTemplate(messageTemplatePO.getExcelTemplate());
        messageTemplateDTO.setRemark(messageTemplatePO.getRemark());
        messageTemplateDTO.setExtInfo(messageTemplatePO.getExtInfo());
        messageTemplateDTO.setIsDeleted(messageTemplatePO.getIsDeleted());
        messageTemplateDTO.setAddTime(messageTemplatePO.getAddTime());
        messageTemplateDTO.setModTime(messageTemplatePO.getModTime());
        return messageTemplateDTO;
    }
}
