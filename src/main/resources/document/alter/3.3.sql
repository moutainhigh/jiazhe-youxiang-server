/**
物料信息建表语句
 */
drop table if exists material_info;
CREATE TABLE `material_info` (
    `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `transfer_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '转账时间',
    `transfer_amount` DECIMAL(8 , 2 ) NOT NULL DEFAULT '0.00' COMMENT '转账金额',
    `material_value` DECIMAL(8 , 2 ) NOT NULL DEFAULT '0.00' COMMENT '物料价值',
    `payer_id` INT(10) UNSIGNED NOT NULL DEFAULT '0' COMMENT '付款人id',
    `payer_name` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '付款人名称',
    `payee_id` INT(10) UNSIGNED NOT NULL DEFAULT '0' COMMENT '收款人id',
    `payee_name` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '收款人名称',
    `remark` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '备注',
    `ext_info` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '预留的其它字段',
    `is_deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否已删除,0:未删除,1:已删除',
    `add_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `mod_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`)
)  ENGINE=INNODB COMMENT='物料信息';