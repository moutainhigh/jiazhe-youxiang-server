package com.jiazhe.youxiang.server.service.impl.message;

import com.jiazhe.youxiang.server.adapter.message.MessageTemplateAdapter;
import com.jiazhe.youxiang.server.common.constant.CommonConstant;
import com.jiazhe.youxiang.server.dao.mapper.MessageTemplatePOMapper;
import com.jiazhe.youxiang.server.domain.po.MessageTemplatePO;
import com.jiazhe.youxiang.server.domain.po.MessageTemplatePOExample;
import com.jiazhe.youxiang.server.dto.message.MessageTemplateDTO;
import com.jiazhe.youxiang.server.service.message.MessageTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author TU
 * @description
 * @date 2019-04-08.
 */
@Service("messageTemplateService")
public class MessageTemplateServiceImpl implements MessageTemplateService {
    @Autowired
    private MessageTemplatePOMapper msgTemplatePOMapper;

    @Override
    public List<MessageTemplateDTO> getAll(Byte status) {
        MessageTemplatePOExample example = new MessageTemplatePOExample();
        MessageTemplatePOExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo(status);
        criteria.andIsDeletedEqualTo(CommonConstant.CODE_NOT_DELETED);
        List<MessageTemplatePO> poList = msgTemplatePOMapper.selectByExample(example);
        return poList.stream().map(MessageTemplateAdapter::po2Dto).collect(Collectors.toList());
    }

    @Override
    public MessageTemplateDTO getById(int id) {
        MessageTemplatePO po = msgTemplatePOMapper.selectByPrimaryKey(id);
        return MessageTemplateAdapter.po2Dto(po);
    }
}
