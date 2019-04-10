package com.jiazhe.youxiang.server.service.impl.message;

import com.jiazhe.youxiang.base.util.MsgUtils;
import com.jiazhe.youxiang.server.adapter.message.MessageAdapter;
import com.jiazhe.youxiang.server.common.constant.CommonConstant;
import com.jiazhe.youxiang.server.common.enums.MessageCodeEnum;
import com.jiazhe.youxiang.server.common.exceptions.MessageException;
import com.jiazhe.youxiang.server.dao.mapper.MessagePOMapper;
import com.jiazhe.youxiang.server.dao.mapper.manual.message.MessagePOManualMapper;
import com.jiazhe.youxiang.server.domain.po.MessagePO;
import com.jiazhe.youxiang.server.dto.message.MessageDTO;
import com.jiazhe.youxiang.server.dto.message.MessageTemplateDTO;
import com.jiazhe.youxiang.server.service.message.MessageService;
import com.jiazhe.youxiang.server.service.message.MessageTemplateService;
import com.jiazhe.youxiang.server.vo.Paging;
import com.jiazhe.youxiang.server.vo.resp.message.SendSingleMsgResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tu
 * @description：
 * @date 2019-04-07
 */
@Service("messageService")
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessagePOMapper messagePOMapper;

    @Autowired
    private MessagePOManualMapper messagePOManualMapper;

    @Autowired
    private MessageTemplateService msgTemplateService;

    @Override
    public List<MessageDTO> getList(Byte status, Byte type, String mobile, String topic, Date sendStartTime, Date sendEndTime, Paging paging) {
        Integer count = messagePOManualMapper.count(status, type, mobile, topic, sendStartTime, sendEndTime);
        List<MessagePO> auditRecordPOList = messagePOManualMapper.query(status, type, mobile, topic, sendStartTime, sendEndTime, paging.getOffset(), paging.getLimit());
        paging.setTotal(count);
        return auditRecordPOList.stream().map(MessageAdapter::Po2Dto).collect(Collectors.toList());
    }

    @Override
    public void insertVerCodeMsg(Byte serviceProvider, String phone, String code, Byte type) {
        MessagePO po = new MessagePO();
        po.setCount(Byte.valueOf("1"));
        po.setMobile(phone);
        po.setType(type);
        po.setOperatorId(0);
        po.setOperatorName(phone);
        po.setStatus(Byte.valueOf("1"));
        po.setServiceProvider(serviceProvider);
        po.setContent(code);
        po.setTopic("验证码");
        messagePOMapper.insertSelective(po);
    }

    @Override
    public void resend(Integer id) {
        MessagePO po = messagePOMapper.selectByPrimaryKey(id);
        MessageTemplateDTO msgTemplateDTO = msgTemplateService.getById(po.getMessageTemplateId());
        if (po.getStatus().equals(Byte.valueOf("1"))) {//重新发送
            if (po.getType().equals(CommonConstant.MSG_TYPE_VER_CODE)) {
                MsgUtils.sendVerificationCodeMsg(po.getMobile(), po.getContent());
            } else {
                SendSingleMsgResp resp = MsgUtils.sendBusinessMsg(po.getMobile(), msgTemplateDTO.getTencentTemplateId(), msgTemplateDTO.getTencentTemplateContent(), msgTemplateDTO.getAliTemplateCode(), msgTemplateDTO.getAliTemplateContent(), po.getContent().split(";"));
                if (resp.isSuccess()) {
                    po.setServiceProvider(resp.getServiceProvider());
                    messagePOMapper.updateByPrimaryKey(po);
                }
            }
        } else {//重试
            if (po.getType().equals(CommonConstant.MSG_TYPE_VER_CODE)) {
                MsgUtils.sendVerificationCodeMsg(po.getMobile(), po.getContent());
            } else {
                SendSingleMsgResp resp = MsgUtils.sendBusinessMsg(po.getMobile(), msgTemplateDTO.getTencentTemplateId(), msgTemplateDTO.getTencentTemplateContent(), msgTemplateDTO.getAliTemplateCode(), msgTemplateDTO.getAliTemplateContent(), po.getContent().split(";"));
                if (resp.isSuccess()) {
                    po.setServiceProvider(resp.getServiceProvider());
                    po.setStatus(Byte.valueOf("1"));
                    messagePOMapper.updateByPrimaryKey(po);
                }
            }
        }
    }

    @Override
    public void sendSingle(String mobile, Byte type, String topic, int messageTemplateId, String content) {
        MessageTemplateDTO msgTemplateDTO = msgTemplateService.getById(messageTemplateId);
        if (null == msgTemplateDTO || msgTemplateDTO.getIsDeleted().equals(CommonConstant.CODE_DELETED)) {
            throw new MessageException(MessageCodeEnum.TEMPLATE_IS_NOT_EXIST);
        }
        if (msgTemplateDTO.getStatus().equals(CommonConstant.CODE_STOP_USING)) {
            throw new MessageException(MessageCodeEnum.TEMPLATE_IS_STOP_USING);
        }
        //验证模板和系统数据库存储的模板是否一致，并且是否可用
        MsgUtils.validateTemplate(msgTemplateDTO.getTencentTemplateId(), msgTemplateDTO.getTencentTemplateContent(), msgTemplateDTO.getAliTemplateCode(), msgTemplateDTO.getAliTemplateContent());
        String[] params = content.split(";");
        if (params.length != msgTemplateDTO.getParamCount()) {
            throw new MessageException(MessageCodeEnum.CONTENT_TEMPLATE_MISMATCH);
        }
        SendSingleMsgResp resp = MsgUtils.sendBusinessMsg(mobile, msgTemplateDTO.getTencentTemplateId(), msgTemplateDTO.getTencentTemplateContent(), msgTemplateDTO.getAliTemplateCode(), msgTemplateDTO.getAliTemplateContent(), params);
        MessagePO messagePO = new MessagePO();
        messagePO.setMobile(mobile);
        messagePO.setMessageTemplateId(messageTemplateId);
        messagePO.setTopic(topic);
        messagePO.setType(type);
        messagePO.setContent(content);
        messagePO.setServiceProvider(resp.getServiceProvider());
        messagePOMapper.insertSelective(messagePO);
    }
}
