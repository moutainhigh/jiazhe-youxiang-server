package com.jiazhe.youxiang.server.service.message;

import com.jiazhe.youxiang.server.dto.message.MessageTemplateDTO;

import java.util.List;

/**
 * @author TU
 * @description
 * @date 2019-04-08.
 */
public interface MessageTemplateService {
    /**
     * 根据模板状态 获取所有模板
     * @param status
     * @return
     */
    List<MessageTemplateDTO> getAll(Byte status);

    /**
     * 查询短信模板信息
     * @param id
     * @return
     */
    MessageTemplateDTO getById(int id);
}
