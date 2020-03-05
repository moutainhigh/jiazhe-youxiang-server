DROP TABLE IF EXISTS `charge_off`;
CREATE TABLE `charge_off` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `city_code` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '城市编码',
  `city_name` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '城市名称',
  `bank_outlets_name` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '银行信息',
  `charge_off_type` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '核销类型 0-换商品，1-充值积分',
  `total_point` int(10) NOT NULL COMMENT '核销总积分',
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

