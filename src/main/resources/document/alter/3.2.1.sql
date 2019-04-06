/**
短信记录表
 */
drop table if exists message;
CREATE TABLE `message` (
    `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `mobile` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '手机号',
    `content` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '短信内容',
    `type` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '短信类型 1其他 2验证码类 3业务类 4营销类 ',
    `topic` VARCHAR(100) COMMENT '发送主题',
    `status` TINYINT(4) NOT NULL DEFAULT '1' COMMENT '发送状态 1成功 0失败',
    `count` TINYINT(4) NOT NULL DEFAULT '1' COMMENT '此条短信记录，对应发送的条数',
    `service_provider` TINYINT(4) NOT NULL DEFAULT '1' COMMENT '短信服务商 1腾讯 2阿里',
    `send_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发送时间',
    `operator_id` INT(10) UNSIGNED NOT NULL DEFAULT '0' COMMENT '操作人id',
    `operator_name` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '操作人名称',
    `ext_info` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '预留的其它字段',
    `is_deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否已删除,0:未删除,1:已删除',
    `add_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `mod_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`)
)  ENGINE=INNODB COMMENT='短信记录表';