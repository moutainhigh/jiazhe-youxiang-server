/**
 商家订单模块
 */
alter table partner_order_info modify column status TINYINT COMMENT '订单状态 1待派单,2待服务,3已完成,4已取消';
update partner_order_info set status=status+1;

/**
充值审核模块
 */
alter table audit_record drop column submitter_remark;

alter table audit_record add column charge_receipt_status TINYINT(4) NOT NULL DEFAULT '0' COMMENT '小票录入完成状态 0录入未完成 1录入完成';
alter table audit_record add column point_codes VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '当兑换类型为2时，记录积分兑换码的卡号';
alter table audit_record add column product_value DECIMAL(8 , 2 ) NOT NULL DEFAULT '0.00' COMMENT '当兑换类型为3时，记录所给实物的价值';

alter table audit_record modify column point_ids VARCHAR(1023) COMMENT '当兑换类型为1时，记录积分卡id集合';
alter table audit_record modify column audit_reason VARCHAR(100) COMMENT '驳回理由';
alter table audit_record modify column remark VARCHAR(1023) COMMENT '提交人填写备注信息';
alter table audit_record modify column exchange_point DECIMAL(8 , 2 ) COMMENT '总兑换积分';

alter table audit_record modify column status  TINYINT COMMENT '审核状态 1-已保存，2-已提交，3-已驳回，4-已通过';
update audit_record set status=status+2;

alter table audit_record add column temp_col VARCHAR(100);
update audit_record set temp_col = exchange_type;
alter table audit_record drop column exchange_type;
alter table audit_record add column exchange_type TINYINT(4) NOT NULL DEFAULT '1' COMMENT '兑换类型 1是直接充值，2是兑换积分卡，3是兑换购物卡';
update audit_record set exchange_type = 1 where temp_col = '服务';
update audit_record set exchange_type = 3 where temp_col = '电子卡';
alter table audit_record drop column temp_col;

CREATE TABLE `charge_receipt` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `audit_record_id` INT(10) UNSIGNED NOT NULL COMMENT '兑换审核信息表主键',
  `exchange_point` DECIMAL(8 , 2 ) NOT NULL DEFAULT '0.00' COMMENT '拆分的兑换积分',
  `pos_code` VARCHAR(100) NOT NULL DEFAULT '' COMMENT 'POS机编号',
  `trade_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '交易日期',
  `card_no` VARCHAR(20) NOT NULL DEFAULT '' COMMENT '银行卡号【后四位】',
  `customer_name` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '客户姓名',
  `inputer_id` INT(10) UNSIGNED NOT NULL COMMENT '录入人ID',
  `inputer_name` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '录入人姓名',
  `ext_info` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '预留的其它字段',
  `is_deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否已删除,0:未删除,1:已删除',
  `add_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `mod_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
)  ENGINE=INNODB COMMENT='充值凭证表';
