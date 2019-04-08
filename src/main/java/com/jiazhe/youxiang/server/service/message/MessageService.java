package com.jiazhe.youxiang.server.service.message;

import com.jiazhe.youxiang.server.dto.message.MessageDTO;
import com.jiazhe.youxiang.server.vo.Paging;

import java.util.Date;
import java.util.List;


/**
 * @author tu
 * @description：
 * @date 2019-04-07
 */
public interface MessageService {
    /**
     * 查询短信列表
     * @param status 短信发送状态
     * @param type 短信类型
     * @param mobile 手机号码
     * @param topic 短信主题
     * @param sendStartTime 发送时间起
     * @param sendEndTime 发送时间止
     * @param paging 分页信息
     * @return
     */
    List<MessageDTO> getList(Byte status, Byte type, String mobile, String topic, Date sendStartTime, Date sendEndTime,Paging paging);

    /**
     * 插入手机验证码短信记录
     * @param serviceProvider
     * @param phone
     * @param code
     * @param type
     */
    void insertVerCodeMsg(Byte serviceProvider,String phone, String code, Byte type);

    /**
     * 重新发送短信
     * @param id
     */
    void resend(Integer id);

    /**
     * 单条发送短信
     * @param mobile
     * @param type
     * @param topic
     * @param messageTemplateId
     * @param content
     */
    void sendSingle(String mobile, Byte type, String topic, int messageTemplateId, String content);
}
