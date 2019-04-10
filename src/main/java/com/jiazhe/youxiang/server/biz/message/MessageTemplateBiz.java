package com.jiazhe.youxiang.server.biz.message;

import com.jiazhe.youxiang.server.dto.message.MessageTemplateDTO;
import com.jiazhe.youxiang.server.service.message.MessageTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author TU
 * @description
 * @date 2019-04-08.
 */
@Service("messageTemplateBiz")
public class MessageTemplateBiz {

    @Autowired
    private MessageTemplateService msgTemplateService;

    public List<MessageTemplateDTO> getAll(Byte status) {
        return msgTemplateService.getAll(status);
    }

    public MessageTemplateDTO getById(Integer id) {
        return msgTemplateService.getById(id);
    }
}
