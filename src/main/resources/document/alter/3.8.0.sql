DROP TABLE IF EXISTS `charge_off`;
CREATE TABLE `charge_off` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `city_code` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '城市编码',
  `city_name` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '城市名称',
  `bank_outlets_name` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '银行信息',
  `charge_off_type` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '核销类型 0-换商品，1-充值积分',
  `charge_off_point` varchar(2000) NOT NULL DEFAULT '' COMMENT '核销密码集合，逗号隔开，为了查询',
  `total_point` DECIMAL(20,4) NOT NULL DEFAULT '0.00' COMMENT '核销总积分',
  `customer_name` varchar(255) NOT NULL DEFAULT '' COMMENT '客户姓名',
  `customer_mobile` varchar(255) NOT NULL DEFAULT '' COMMENT '客户手机号',
  `product_value` DECIMAL(20,4) NOT NULL DEFAULT '0.00' COMMENT '记录所给实物的价值',
  `status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '状态 0-未提交，1-已提交',
  `submitter_id` INT(10) UNSIGNED NOT NULL COMMENT '提交人id',
  `submitter_name` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '提交人姓名',
  `submitter_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '提交时间',
  `remark` VARCHAR(2000) NOT NULL DEFAULT '' COMMENT '备注信息',
  `ext_info` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '预留的其它字段',
  `is_deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否已删除,0:未删除,1:已删除',
  `add_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `mod_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_customer_name` (`customer_name`),
  KEY `idx_customer_mobile` (`customer_mobile`)
) ENGINE=InnoDB COMMENT='核销记录表';


DROP TABLE IF EXISTS `charge_off_point`;
CREATE TABLE `charge_off_point` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `charge_off_id` int(10) NOT NULL COMMENT '核销记录id',
  `point_exchange_code_id` INT(10) UNSIGNED NOT NULL COMMENT '积分兑换码id',
  `point_name` VARCHAR(255) NOT NULL COMMENT '兑换成积分卡的卡名',
  `point_value` DECIMAL(20,4) NOT NULL DEFAULT '0.00' COMMENT '积分分值',
  `point_exchange_code_code` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '兑换码(卡号)',
  `point_exchange_code_keyt` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '密钥（密码）',
  `ext_info` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '预留的其它字段',
  `is_deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否已删除,0:未删除,1:已删除',
  `add_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `mod_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_charge_off_id` (`charge_off_id`),
  KEY `idx_point_exchange_code_keyt` (`point_exchange_code_keyt`)
) ENGINE=InnoDB COMMENT='核销积分详情表';

DROP TABLE IF EXISTS `city_exchange_ratio`;
CREATE TABLE `city_exchange_ratio` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `city_code` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '兑换城市code',
  `city_name` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '兑换城市名称',
  `exchange_ratio` DECIMAL(20,4) NOT NULL DEFAULT '0.50' COMMENT '兑换比例，默认50%',
  `ext_info` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '预留的其它字段',
  `is_deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否已删除,0:未删除,1:已删除',
  `add_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `mod_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_city_code` (`city_code`)
) ENGINE=InnoDB COMMENT='城市积分兑换比例';
/**
 商品下单时间约束
 */
alter table product add column `book_days` INT(10) UNSIGNED NOT NULL DEFAULT 20 COMMENT '可连续预约的天数';

/**
增加索引
 */
ALTER TABLE `sys_log` ADD INDEX `idx_operator_id` (`operator_id`);
ALTER TABLE `sys_user` ADD INDEX `idx_loginName` (`loginName`);
ALTER TABLE `sys_role` ADD INDEX `idx_name` (`name`);
ALTER TABLE `sys_role_permission` ADD INDEX `idx_role_id` (`role_id`);
ALTER TABLE `sys_user_role` ADD INDEX `idx_user_id` (`user_id`);
ALTER TABLE `sys_user_role` ADD INDEX `idx_role_id` (`role_id`);
ALTER TABLE `sys_city` ADD INDEX `idx_city_code` (`city_code`);
ALTER TABLE `recharge_card` ADD INDEX `idx_customer_id` (`customer_id`);
ALTER TABLE `recharge_card` ADD INDEX `idx_project_id` (`project_id`);
ALTER TABLE `recharge_card` ADD INDEX `idx_exchange_record_id` (`exchange_record_id`);
ALTER TABLE `recharge_card_exchange_code_batch` ADD INDEX `idx_project_id` (`project_id`);
ALTER TABLE `recharge_card_exchange_code` ADD INDEX `idx_batch_id` (`batch_id`);
ALTER TABLE `recharge_card_exchange_code` ADD INDEX `idx_project_id` (`project_id`);
ALTER TABLE `recharge_card_exchange_code` ADD INDEX `idx_code` (`code`);
ALTER TABLE `recharge_card_exchange_code` ADD INDEX `idx_keyt` (`keyt`);
ALTER TABLE `recharge_card_exchange_record` ADD INDEX `idx_recharge_card_id` (`recharge_card_id`);
ALTER TABLE `recharge_card_exchange_record` ADD INDEX `idx_exchange_code_id` (`exchange_code_id`);
ALTER TABLE `recharge_card_exchange_record` ADD INDEX `idx_operator_id` (`operator_id`);
ALTER TABLE `voucher` ADD INDEX `idx_customer_id` (`customer_id`);
ALTER TABLE `voucher` ADD INDEX `idx_project_id` (`project_id`);
ALTER TABLE `voucher` ADD INDEX `idx_exchange_record_id` (`exchange_record_id`);
ALTER TABLE `voucher_exchange_code_batch` ADD INDEX `idx_project_id` (`project_id`);
ALTER TABLE `voucher_exchange_code` ADD INDEX `idx_batch_id` (`batch_id`);
ALTER TABLE `voucher_exchange_code` ADD INDEX `idx_project_id` (`project_id`);
ALTER TABLE `voucher_exchange_code` ADD INDEX `idx_code` (`code`);
ALTER TABLE `voucher_exchange_code` ADD INDEX `idx_keyt` (`keyt`);
ALTER TABLE `voucher_exchange_record` ADD INDEX `idx_voucher_id` (`voucher_id`);
ALTER TABLE `voucher_exchange_record` ADD INDEX `idx_exchange_code_id` (`exchange_code_id`);
ALTER TABLE `voucher_exchange_record` ADD INDEX `idx_operator_id` (`operator_id`);
ALTER TABLE `product` ADD INDEX `idx_product_category_id` (`product_category_id`);
ALTER TABLE `electronic_product_exchange_code` ADD INDEX `idx_product_id` (`product_id`);
ALTER TABLE `electronic_product_exchange_code` ADD INDEX `idx_order_id` (`order_id`);
ALTER TABLE `electronic_product_exchange_code` ADD INDEX `idx_order_code` (`order_code`);
ALTER TABLE `electronic_product_exchange_code` ADD INDEX `idx_code` (`code`);
ALTER TABLE `electronic_product_exchange_code` ADD INDEX `idx_keyt` (`keyt`);
ALTER TABLE `product_price` ADD INDEX `idx_city_code` (`city_code`);
ALTER TABLE `product_price` ADD INDEX `idx_product_id` (`product_id`);
ALTER TABLE `customer` ADD INDEX `idx_mobile` (`mobile`);
ALTER TABLE `customer_address` ADD INDEX `idx_customer_id` (`customer_id`);
ALTER TABLE `order_info` ADD INDEX `idx_order_code` (`order_code`);
ALTER TABLE `order_info` ADD INDEX `idx_customer_id` (`customer_id`);
ALTER TABLE `order_info` ADD INDEX `idx_product_id` (`product_id`);
ALTER TABLE `order_info` ADD INDEX `idx_service_product_id` (`service_product_id`);
ALTER TABLE `order_payment` ADD INDEX `idx_order_code` (`order_code`);
ALTER TABLE `order_payment` ADD INDEX `idx_order_id` (`order_id`);
ALTER TABLE `order_payment` ADD INDEX `idx_point_id` (`point_id`);
ALTER TABLE `order_payment` ADD INDEX `idx_recharge_card_id` (`recharge_card_id`);
ALTER TABLE `order_payment` ADD INDEX `idx_voucher_id` (`voucher_id`);
ALTER TABLE `order_refund` ADD INDEX `idx_order_code` (`order_code`);
ALTER TABLE `order_refund` ADD INDEX `idx_order_id` (`order_id`);
ALTER TABLE `order_refund` ADD INDEX `idx_point_id` (`point_id`);
ALTER TABLE `order_refund` ADD INDEX `idx_recharge_card_id` (`recharge_card_id`);
ALTER TABLE `order_refund` ADD INDEX `idx_voucher_id` (`voucher_id`);
ALTER TABLE `audit_record` ADD INDEX `idx_submitter_id` (`submitter_id`);
ALTER TABLE `audit_record` ADD INDEX `idx_auditor_id` (`auditor_id`);
ALTER TABLE `charge_receipt` ADD INDEX `idx_audit_record_id` (`audit_record_id`);
ALTER TABLE `charge_receipt` ADD INDEX `idx_inputer_id` (`inputer_id`);
ALTER TABLE `simple_session` ADD INDEX `idx_cookie` (`cookie`);
ALTER TABLE `partner` ADD INDEX `idx_name` (`name`);
ALTER TABLE `service_item` ADD INDEX `idx_name` (`name`);
ALTER TABLE `partner_order_info` ADD INDEX `idx_service_item_id` (`service_item_id`);
ALTER TABLE `partner_order_info` ADD INDEX `idx_partner_id` (`partner_id`);
ALTER TABLE `point` ADD INDEX `idx_customer_id` (`customer_id`);
ALTER TABLE `point` ADD INDEX `idx_project_id` (`project_id`);
ALTER TABLE `point` ADD INDEX `idx_exchange_record_id` (`exchange_record_id`);
ALTER TABLE `point_exchange_code_batch` ADD INDEX `idx_project_id` (`project_id`);
ALTER TABLE `point_exchange_code` ADD INDEX `idx_batch_id` (`batch_id`);
ALTER TABLE `point_exchange_code` ADD INDEX `idx_project_id` (`project_id`);
ALTER TABLE `point_exchange_code` ADD INDEX `idx_code` (`code`);
ALTER TABLE `point_exchange_code` ADD INDEX `idx_keyt` (`keyt`);
ALTER TABLE `point_exchange_record` ADD INDEX `idx_point_id` (`point_id`);
ALTER TABLE `point_exchange_record` ADD INDEX `idx_exchange_code_id` (`exchange_code_id`);
ALTER TABLE `point_exchange_record` ADD INDEX `idx_operator_id` (`operator_id`);
ALTER TABLE `message` ADD INDEX `idx_message_template_id` (`message_template_id`);
ALTER TABLE `message` ADD INDEX `idx_operator_id` (`operator_id`);
ALTER TABLE `material_info` ADD INDEX `idx_payer_id` (`payer_id`);
ALTER TABLE `material_info` ADD INDEX `idx_payee_id` (`payee_id`);
ALTER TABLE `order_track` ADD INDEX `idx_orderId` (`orderId`);
ALTER TABLE `partner_order_track` ADD INDEX `idx_orderId` (`orderId`);