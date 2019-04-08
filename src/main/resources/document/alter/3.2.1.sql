/**
短信记录表
 */

drop table if exists message;
CREATE TABLE `message` (
    `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `mobile` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '手机号',
    `content` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '短信内容',
    `type` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '短信类型 1其他 2业务类 3营销类 4验证码类 ',
    `topic` VARCHAR(100) COMMENT '发送主题',
    `status` TINYINT(4) NOT NULL DEFAULT '1' COMMENT '发送状态 1成功 0失败',
    `count` TINYINT(4) NOT NULL DEFAULT '1' COMMENT '此条短信记录，对应发送的条数',
    `service_provider` TINYINT(4) NOT NULL DEFAULT '1' COMMENT '短信服务商 1腾讯 2阿里',
    `send_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发送时间',
    `message_template_id` INT(10) UNSIGNED NOT NULL DEFAULT '0' COMMENT '短信模板id',
    `operator_id` INT(10) UNSIGNED NOT NULL DEFAULT '0' COMMENT '操作人id',
    `operator_name` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '操作人名称',
    `ext_info` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '预留的其它字段',
    `is_deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否已删除,0:未删除,1:已删除',
    `add_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `mod_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`)
)  ENGINE=INNODB COMMENT='短信记录表';

/**
短信模板表
 */

drop table if exists message_template;
CREATE TABLE `message_template` (
    `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `name` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '模板名称',
    `param_count` INT(10) NOT NULL COMMENT '参数个数',
    `ali_template_code` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '阿里云的短信模板code',
    `ali_template_content` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '阿里云的短信模板内容',
    `tencent_template_id` INT(10) NOT NULL COMMENT '腾讯云的短信模板id',
    `tencent_template_content` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '腾讯云的短信模板内容',
    `status` TINYINT(4) NOT NULL DEFAULT '1' COMMENT '发送状态 1可用 0不可用',
    `excel_template` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '批量发送时，对应excel模板',
    `remark` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '备注',
    `ext_info` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '预留的其它字段',
    `is_deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否已删除,0:未删除,1:已删除',
    `add_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `mod_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`)
)  ENGINE=INNODB COMMENT='短信模板';
