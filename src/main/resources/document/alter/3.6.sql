/**
3.6.1 第一批次上线  develop、test、master已执行
 */
alter table audit_record modify column exchange_type INT(10) NOT NULL DEFAULT '1' COMMENT '兑换类型 1是直接充值，2是兑换积分卡，3是兑换商品，4是预购';

/**

3.6.2 第二批次上线 develop已经执行，test、master未执行
 */
alter table order_info modify column product_id INT(10) UNSIGNED NOT NULL COMMENT '扣分商品id';
alter table order_info add column service_product_id INT(10) UNSIGNED NOT NULL COMMENT '服务商品id';
update order_info set service_product_id = product_id;

/**
积分体系转换 兑换比例：develop已经执行，test、master未执行
 */
alter table project modify column point_conversion_rate DECIMAL(8 , 4) NOT NULL DEFAULT '0.00' COMMENT '积分兑换比例';

/**
积分转换体系 数据范围扩大：兑换比例：develop、test未执行，master未执行
 */
alter TABLE recharge_card modify column face_value DECIMAL(20,4) NOT NULL DEFAULT '0.00' COMMENT '充值卡面值（最初的价值）';
alter TABLE recharge_card modify column balance DECIMAL(20,4) NOT NULL DEFAULT '0.00' COMMENT '充值卡余额';
alter TABLE recharge_card_exchange_code_batch modify column face_value DECIMAL(20,4) NOT NULL DEFAULT '0.00' COMMENT '对应的金额';
alter TABLE recharge_card_exchange_code modify column face_value DECIMAL(20,4) NOT NULL DEFAULT '0.00' COMMENT '对应的金额';
alter TABLE product_price modify column price DECIMAL(20,4) NOT NULL DEFAULT '0.00' COMMENT '商品价格';
alter TABLE order_info modify column product_price DECIMAL(20,4) NOT NULL DEFAULT '0.00' COMMENT '商品单价';
alter TABLE order_info modify column pay_recharge_card DECIMAL(20,4) NOT NULL DEFAULT '0.00' COMMENT '充值卡支付对应的金额';
alter TABLE order_info modify column pay_point DECIMAL(20,4) NOT NULL DEFAULT '0.00' COMMENT '积分支付对应的金额';
alter TABLE order_info modify column pay_voucher DECIMAL(20,4) NOT NULL DEFAULT '0.00' COMMENT '代金券支付对应的金额';
alter TABLE order_info modify column pay_cash DECIMAL(20,4) NOT NULL DEFAULT '0.00' COMMENT '现金支付';
alter TABLE order_info modify column total_amount DECIMAL(20,4) NOT NULL DEFAULT '0.00' COMMENT '订单总金额';
alter TABLE order_info modify column cost DECIMAL(20,4) NOT NULL DEFAULT '0.00' COMMENT '订单成本金额';
alter TABLE order_payment modify column pay_money DECIMAL(20,4) NOT NULL DEFAULT '0.00' COMMENT '支付金额';
alter TABLE order_refund modify column refund_money DECIMAL(20,4) NOT NULL DEFAULT '0.00' COMMENT '退款金额';
alter TABLE audit_record modify column exchange_point DECIMAL(20,4) NOT NULL DEFAULT '0.00' COMMENT '总兑换积分';
alter TABLE audit_record modify column giving_point DECIMAL(20,4) NOT NULL DEFAULT '0.00' COMMENT '后台充值积分';
alter TABLE audit_record modify column product_value DECIMAL(20,4) NOT NULL DEFAULT '0.00' COMMENT '当兑换类型为3时，记录所给实物的价值';
alter TABLE charge_receipt modify column exchange_point DECIMAL(20,4) NOT NULL DEFAULT '0.00' COMMENT '拆分的兑换积分';
alter TABLE advance_pay modify column advance_pay DECIMAL(20,4) NOT NULL DEFAULT '0.00' COMMENT '预支金额';
alter TABLE partner_order_info modify column pre_pay DECIMAL(20,4) NOT NULL DEFAULT '0.00' COMMENT '预付';
alter TABLE partner_order_info modify column append_pay DECIMAL(20,4) NOT NULL DEFAULT '0.00' COMMENT '二次支付';
alter TABLE point modify column face_value DECIMAL(20,4) NOT NULL DEFAULT '0.00' COMMENT '原始积分值（类比充值卡面值）';
alter TABLE point modify column balance DECIMAL(20,4) NOT NULL DEFAULT '0.00' COMMENT '积分余额';
alter TABLE point_exchange_code_batch modify column face_value DECIMAL(20,4) NOT NULL DEFAULT '0.00' COMMENT '对应的金额';
alter TABLE point_exchange_code modify column face_value DECIMAL(20,4) NOT NULL DEFAULT '0.00' COMMENT '对应的金额';
alter TABLE material_info modify column transfer_amount DECIMAL(20,4) NOT NULL DEFAULT '0.00' COMMENT '转账金额';
alter TABLE material_info modify column material_value DECIMAL(20,4) NOT NULL DEFAULT '0.00' COMMENT '物料价值';

/**
develop已经执行，test、master未执行
 */
DROP TABLE IF EXISTS `order_track`;
CREATE TABLE `order_track` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `orderId` int(10) NOT NULL COMMENT '订单ID',
  `opreation` int(10) NOT NULL DEFAULT '0' COMMENT '操作类型(0其他 1 创建 2 更新 3 取消 )',
  `userName` varchar(255) NOT NULL DEFAULT '' COMMENT '用户姓名',
  `msg` longtext NOT NULL COMMENT '描述信息',
  `ext_info` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '预留的其它字段',
  `is_deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否已删除,0:未删除,1:已删除',
  `add_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `mod_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB COMMENT='客户订单操作留痕表';

DROP TABLE IF EXISTS `partner_order_track`;
CREATE TABLE `partner_order_track` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `orderId` int(10) NOT NULL COMMENT '订单ID',
  `opreation` int(10) NOT NULL DEFAULT '0' COMMENT '操作类型()',
  `userName` varchar(255) NOT NULL DEFAULT '' COMMENT '用户姓名',
  `msg` longtext NOT NULL COMMENT '描述信息',
  `ext_info` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '预留的其它字段',
  `is_deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否已删除,0:未删除,1:已删除',
  `add_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `mod_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB COMMENT='商家订单操作留痕表';