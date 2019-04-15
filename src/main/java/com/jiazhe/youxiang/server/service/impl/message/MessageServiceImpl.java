package com.jiazhe.youxiang.server.service.impl.message;

import com.jiazhe.youxiang.base.util.ExcelUtils;
import com.jiazhe.youxiang.base.util.MsgUtils;
import com.jiazhe.youxiang.server.adapter.message.MessageAdapter;
import com.jiazhe.youxiang.server.common.constant.CommonConstant;
import com.jiazhe.youxiang.server.common.enums.LoginCodeEnum;
import com.jiazhe.youxiang.server.common.enums.MessageCodeEnum;
import com.jiazhe.youxiang.server.common.exceptions.LoginException;
import com.jiazhe.youxiang.server.common.exceptions.MessageException;
import com.jiazhe.youxiang.server.dao.mapper.MessagePOMapper;
import com.jiazhe.youxiang.server.dao.mapper.manual.message.MessagePOManualMapper;
import com.jiazhe.youxiang.server.domain.po.MessagePO;
import com.jiazhe.youxiang.server.dto.message.MessageDTO;
import com.jiazhe.youxiang.server.dto.message.MessageTemplateDTO;
import com.jiazhe.youxiang.server.dto.sysuser.SysUserDTO;
import com.jiazhe.youxiang.server.service.message.MessageService;
import com.jiazhe.youxiang.server.service.message.MessageTemplateService;
import com.jiazhe.youxiang.server.vo.Paging;
import com.jiazhe.youxiang.server.vo.resp.message.SendSingleMsgResp;
import net.sf.json.JSONObject;
import org.apache.logging.log4j.util.Strings;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        SysUserDTO sysUserDTO = (SysUserDTO) SecurityUtils.getSubject().getPrincipal();
        if (null == sysUserDTO) {
            throw new LoginException(LoginCodeEnum.LOGIN_NOT_SIGNIN_IN);
        }
        MessagePO po = messagePOMapper.selectByPrimaryKey(id);
        po.setOperatorId(sysUserDTO.getId());
        po.setOperatorName(sysUserDTO.getDisplayName());
        MessageTemplateDTO msgTemplateDTO = msgTemplateService.getById(po.getMessageTemplateId());
        //status为1，短信已经成功，重新发送增加记录。status为0，短信发送未成功，重发短信仅仅修改历史发送失败的记录
        if (po.getStatus().equals(Byte.valueOf("1"))) {
            if (po.getType().equals(CommonConstant.MSG_TYPE_VER_CODE)) {
                MsgUtils.sendVerificationCodeMsg(po.getMobile(), po.getContent());
            } else {
                SendSingleMsgResp resp = MsgUtils.sendBusinessMsg(po.getMobile(), msgTemplateDTO.getTencentTemplateId(), msgTemplateDTO.getTencentTemplateContent(), msgTemplateDTO.getAliTemplateCode(), msgTemplateDTO.getAliTemplateContent(), po.getContent().split(";"));
                if (resp.isSuccess()) {
                    po.setServiceProvider(resp.getServiceProvider());
                    po.setId(null);
                    messagePOMapper.insertSelective(po);
                } else {
                    throw new MessageException(MessageCodeEnum.MSG_SEND_ERROR.getCode(), MessageCodeEnum.MSG_SEND_ERROR.getType(), resp.getErrorMsg());
                }
            }
        } else {
            if (po.getType().equals(CommonConstant.MSG_TYPE_VER_CODE)) {
                MsgUtils.sendVerificationCodeMsg(po.getMobile(), po.getContent());
            } else {
                SendSingleMsgResp resp = MsgUtils.sendBusinessMsg(po.getMobile(), msgTemplateDTO.getTencentTemplateId(), msgTemplateDTO.getTencentTemplateContent(), msgTemplateDTO.getAliTemplateCode(), msgTemplateDTO.getAliTemplateContent(), po.getContent().split(";"));
                if (resp.isSuccess()) {
                    po.setServiceProvider(resp.getServiceProvider());
                    po.setStatus(Byte.valueOf("1"));
                    messagePOMapper.updateByPrimaryKey(po);
                } else {
                    throw new MessageException(MessageCodeEnum.MSG_SEND_ERROR.getCode(), MessageCodeEnum.MSG_SEND_ERROR.getType(), resp.getErrorMsg());
                }
            }
        }
    }

    @Override
    public void sendSingle(String mobile, Byte type, String topic, int messageTemplateId, String content) {
        SysUserDTO sysUserDTO = (SysUserDTO) SecurityUtils.getSubject().getPrincipal();
        if (null == sysUserDTO) {
            throw new LoginException(LoginCodeEnum.LOGIN_NOT_SIGNIN_IN);
        }
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
        messagePO.setStatus(resp.isSuccess() ? Byte.valueOf("1") : Byte.valueOf("0"));
        messagePO.setOperatorId(sysUserDTO.getId());
        messagePO.setOperatorName(sysUserDTO.getDisplayName());
        messagePOMapper.insertSelective(messagePO);
        if (!resp.isSuccess()) {
            throw new MessageException(MessageCodeEnum.MSG_SEND_ERROR.getCode(), MessageCodeEnum.MSG_SEND_ERROR.getType(), resp.getErrorMsg());
        }
    }

    @Override
    public void sendBatch(Byte type, String topic, int messageTemplateId, String excelUrl) {
        SysUserDTO sysUserDTO = (SysUserDTO) SecurityUtils.getSubject().getPrincipal();
        if (null == sysUserDTO) {
            throw new LoginException(LoginCodeEnum.LOGIN_NOT_SIGNIN_IN);
        }
        MessageTemplateDTO msgTemplateDTO = msgTemplateService.getById(messageTemplateId);
        if (null == msgTemplateDTO || msgTemplateDTO.getIsDeleted().equals(CommonConstant.CODE_DELETED)) {
            throw new MessageException(MessageCodeEnum.TEMPLATE_IS_NOT_EXIST);
        }
        if (msgTemplateDTO.getStatus().equals(CommonConstant.CODE_STOP_USING)) {
            throw new MessageException(MessageCodeEnum.TEMPLATE_IS_STOP_USING);
        }
        //验证模板和系统数据库存储的模板是否一致，并且是否可用
        MsgUtils.validateTemplate(msgTemplateDTO.getTencentTemplateId(), msgTemplateDTO.getTencentTemplateContent(), msgTemplateDTO.getAliTemplateCode(), msgTemplateDTO.getAliTemplateContent());
        List<MessagePO> poList = new ArrayList<>();
        Sheet sheet = ExcelUtils.excel2Sheet(excelUrl);
        boolean isFirstRow = true;
        for (Row row : sheet) {
            if (isFirstRow) {
                isFirstRow = false;
                continue;
            }
            JSONObject jsonObject = MsgUtils.row2Params(row, Strings.isEmpty(msgTemplateDTO.getTencentTemplateContent()) ? msgTemplateDTO.getAliTemplateContent() : msgTemplateDTO.getTencentTemplateContent());
            SendSingleMsgResp resp = MsgUtils.sendBusinessMsg(jsonObject.getString("mobile"), msgTemplateDTO.getTencentTemplateId(), msgTemplateDTO.getTencentTemplateContent(), msgTemplateDTO.getAliTemplateCode(), msgTemplateDTO.getAliTemplateContent(), jsonObject.getString("content").split(";"));
            MessagePO messagePO = new MessagePO();
            messagePO.setMobile(jsonObject.getString("mobile"));
            messagePO.setMessageTemplateId(messageTemplateId);
            messagePO.setTopic(topic);
            messagePO.setType(type);
            messagePO.setContent(jsonObject.getString("content"));
            messagePO.setServiceProvider(resp.getServiceProvider());
            messagePO.setStatus(resp.isSuccess() ? Byte.valueOf("1") : Byte.valueOf("0"));
            messagePO.setOperatorId(sysUserDTO.getId());
            messagePO.setOperatorName(sysUserDTO.getDisplayName());
            poList.add(messagePO);
        }
        messagePOManualMapper.batchInsert(poList);
    }
}
