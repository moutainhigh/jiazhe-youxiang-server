package com.jiazhe.youxiang.server.biz.message;

import com.jiazhe.youxiang.server.dto.message.MessageDTO;
import com.jiazhe.youxiang.server.service.message.MessageService;
import com.jiazhe.youxiang.server.vo.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author tu
 * @description：
 * @date 2019-04-07
 */
@Service("messageBiz")
public class MessageBiz {

    @Autowired
    private MessageService messageService;

    public List<MessageDTO> getList(Byte status, Byte type, String mobile, String topic, Date sendStartTime, Date sendEndTime, Paging paging) {
        return messageService.getList(status, type, mobile, topic, sendStartTime, sendEndTime, paging);
    }

    public void insertVerCodeMsg(Byte serviceProvider, String phone, String code, Byte type) {
        messageService.insertVerCodeMsg(serviceProvider, phone, code, type);
    }

    public void resend(Integer id) {
        messageService.resend(id);
    }
}
