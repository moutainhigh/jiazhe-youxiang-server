package com.jiazhe.youxiang.server.service.impl.message;

import com.jiazhe.youxiang.base.util.MsgUtils;
import com.jiazhe.youxiang.server.adapter.message.MessageAdapter;
import com.jiazhe.youxiang.server.common.constant.CommonConstant;
import com.jiazhe.youxiang.server.dao.mapper.MessagePOMapper;
import com.jiazhe.youxiang.server.dao.mapper.manual.message.MessagePOManualMapper;
import com.jiazhe.youxiang.server.domain.po.MessagePO;
import com.jiazhe.youxiang.server.dto.message.MessageDTO;
import com.jiazhe.youxiang.server.service.message.MessageService;
import com.jiazhe.youxiang.server.vo.Paging;
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
        if (po.getStatus().equals(Byte.valueOf("1"))) {
            if (po.getType().equals(CommonConstant.MSG_TYPE_VER_CODE)) {
                MsgUtils.sendVerificationCodeMsg(po.getMobile(), po.getContent());
            } else {
                boolean success = MsgUtils.sendBusinessMsg(po.getMobile(), po.getContent());
                if(success){
                    messagePOMapper.insertSelective(po);
                }
            }
        } else {
            if (po.getType().equals(CommonConstant.MSG_TYPE_VER_CODE)) {
                MsgUtils.sendVerificationCodeMsg(po.getMobile(), po.getContent());
            } else {
                boolean success = MsgUtils.sendBusinessMsg(po.getMobile(), po.getContent());
                if(success){
                    po.setStatus(Byte.valueOf("1"));
                    messagePOMapper.updateByPrimaryKey(po);
                }
            }
        }
    }
}
