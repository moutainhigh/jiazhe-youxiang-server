package com.jiazhe.youxiang.server.adapter.message;

import com.jiazhe.youxiang.server.domain.po.MessagePO;
import com.jiazhe.youxiang.server.dto.message.MessageDTO;
import com.jiazhe.youxiang.server.vo.resp.message.MessageResp;

/**
 * @author tu
 * @description：
 * @date 2019-04-07
 */
public class MessageAdapter {
    public static MessageDTO Po2Dto(MessagePO messagePO) {
        if (messagePO == null) {
            return null;
        }
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setId(messagePO.getId());
        messageDTO.setMobile(messagePO.getMobile());
        messageDTO.setContent(messagePO.getContent());
        messageDTO.setType(messagePO.getType());
        messageDTO.setTopic(messagePO.getTopic());
        messageDTO.setStatus(messagePO.getStatus());
        messageDTO.setCount(messagePO.getCount());
        messageDTO.setServiceProvider(messagePO.getServiceProvider());
        messageDTO.setSendTime(messagePO.getSendTime());
        messageDTO.setOperatorId(messagePO.getOperatorId());
        messageDTO.setOperatorName(messagePO.getOperatorName());
        messageDTO.setExtInfo(messagePO.getExtInfo());
        messageDTO.setIsDeleted(messagePO.getIsDeleted());
        messageDTO.setAddTime(messagePO.getAddTime());
        messageDTO.setModTime(messagePO.getModTime());
        return messageDTO;
    }

    public static MessageResp dto2Resp(MessageDTO messageDTO) {
        if (messageDTO == null) {
            return null;
        }
        MessageResp messageResp = new MessageResp();
        messageResp.setId(messageDTO.getId());
        messageResp.setMobile(messageDTO.getMobile());
        messageResp.setContent(messageDTO.getContent());
        messageResp.setType(messageDTO.getType());
        messageResp.setTopic(messageDTO.getTopic());
        messageResp.setStatus(messageDTO.getStatus());
        messageResp.setCount(messageDTO.getCount());
        messageResp.setServiceProvider(messageDTO.getServiceProvider());
        messageResp.setSendTime(messageDTO.getSendTime().getTime());
        messageResp.setOperatorName(messageDTO.getOperatorName());
        return messageResp;
    }
}
