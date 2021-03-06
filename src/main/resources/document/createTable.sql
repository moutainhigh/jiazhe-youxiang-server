drop table if exists sys_log;
CREATE TABLE `sys_log` (
    `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `module_name` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '模块名称',
    `operate` VARCHAR(100) NOT NULL COMMENT '操作动作',
    `level` INT(10) NOT NULL DEFAULT '0' COMMENT '日志级别',
    `operator_id` INT(10) UNSIGNED NOT NULL COMMENT '操作人id',
    `operator_name` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '操作人名称',
    `ip` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '操作的ip地址',
    `detail` VARCHAR(5000) NOT NULL DEFAULT '' COMMENT '操作的详细信息',
    `ext_info` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '预留的其它字段',
    `is_deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否已删除,0:未删除,1:已删除',
    `add_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `mod_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`),
    KEY `idx_operator_id` (`operator_id`)
)  ENGINE=INNODB COMMENT='日志信息表';

drop table if exists sys_user;
CREATE TABLE `sys_user` (
    `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `mobile` VARCHAR(100) NOT NULL COMMENT '登录手机号',
    `loginName` VARCHAR(100) NOT NULL COMMENT '登录名',
    `displayName` VARCHAR(100) NOT NULL COMMENT '显示名',
    `salt` VARCHAR(100) NOT NULL COMMENT '运算的salt',
    `password` VARCHAR(100) NOT NULL DEFAULT '0' COMMENT '对前端传过来的密码经过salt再次加密后的登录密码',
    `last_login_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '上一次登录时间',
    `last_login_ip` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '上一次登录IP',
    `remark` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '备注信息',
    `ext_info` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '预留的其它字段',
    `is_deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否已删除,0:未删除,1:已删除',
    `add_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `mod_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`),
    KEY `idx_loginName` (`loginName`)
)  ENGINE=INNODB COMMENT='后台用户表';

drop table if exists sys_role;
CREATE TABLE `sys_role` (
    `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `name` VARCHAR(100) NOT NULL COMMENT '角色名称',
    `is_super` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否是超级管理员 0:不是,1:是',
    `priority` INT(10) NOT NULL DEFAULT '0' COMMENT '排序序号',
    `ext_info` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '预留的其它字段',
    `is_deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否已删除,0:未删除,1:已删除',
    `add_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `mod_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`),
    KEY `idx_name` (`name`)
)  ENGINE=INNODB COMMENT='角色表';

drop table if exists sys_role_permission;
CREATE TABLE `sys_role_permission` (
    `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `role_id` INT(10) UNSIGNED NOT NULL COMMENT '角色ID',
    `perm_url` VARCHAR(255) NOT NULL COMMENT '权限URL',
    `ext_info` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '预留的其它字段',
    `is_deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否已删除,0:未删除,1:已删除',
    `add_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `mod_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`),
    KEY `idx_role_id` (`role_id`)
)  ENGINE=INNODB COMMENT='角色权限关联表';

drop table if exists sys_user_role;
CREATE TABLE `sys_user_role` (
    `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `user_id` INT(10) UNSIGNED NOT NULL COMMENT '用户ID',
    `role_id` INT(10) UNSIGNED NOT NULL COMMENT '角色ID',
    `ext_info` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '预留的其它字段',
    `is_deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否已删除,0:未删除,1:已删除',
    `add_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `mod_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`),
    KEY `idx_user_id` (`user_id`),
    KEY `idx_role_id` (`role_id`)
)  ENGINE=INNODB COMMENT='用户角色关联表';

drop table if exists sys_city;
CREATE TABLE `sys_city` (
    `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `city_code` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '城市编码',
    `city_name` VARCHAR(255) NOT NULL DEFAULT ''  COMMENT '城市名称',
    `city_pinyin` VARCHAR(255) NOT NULL DEFAULT '0'  COMMENT '城市拼音',
    `city_level` INT(10) NOT NULL COMMENT '城市等级',
    `priority` INT(10) NOT NULL DEFAULT '0' COMMENT '排序序号',
    `parent_code` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '上级城市编码，没有值表示为根节点',
    `status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '状态：0:未启用,1:启用',
    `ext_info` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '预留的其它字段',
    `is_deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否已删除,0:未删除,1:已删除',
    `add_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `mod_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`),
    KEY `idx_city_code` (`city_code`)
)  ENGINE=INNODB COMMENT='城市信息表';


drop table if exists recharge_card;
CREATE TABLE `recharge_card` (
    `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `name` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '充值卡名称',
    `description` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '充值卡详细信息',
    `customer_id` INT(10) UNSIGNED NOT NULL COMMENT '绑定客户的id',
    `project_id` INT(10) UNSIGNED COMMENT '对应项目id，为空表示无对应项目',
    `city_codes` VARCHAR(5000) NOT NULL DEFAULT '' COMMENT '适用城市Code集合，粒度到2级城市，以逗号隔开',
    `product_ids` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '适用商品ID集合，以逗号隔开',
    `face_value` DECIMAL(20,4) NOT NULL DEFAULT '0.00' COMMENT '充值卡面值（最初的价值）',
    `balance` DECIMAL(20,4) NOT NULL DEFAULT '0.00' COMMENT '充值卡余额',
    `exchange_record_id` INT(10) UNSIGNED NOT NULL COMMENT '兑换记录ID',
    `effective_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '充值卡生效时间',
    `expiry_time` DATETIME COMMENT '过期时间，为空永不过期',
    `status` TINYINT(4) NOT NULL DEFAULT '1' COMMENT '状态：0:停用,1:启用',
    `ext_info` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '预留的其它字段',
    `is_deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否已删除,0:未删除,1:已删除',
    `add_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `mod_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`),
    KEY `idx_customer_id` (`customer_id`),
    KEY `idx_project_id` (`project_id`),
    KEY `idx_exchange_record_id` (`exchange_record_id`)
)  ENGINE=INNODB COMMENT='充值卡信息表';


drop table if exists recharge_card_exchange_code_batch;
CREATE TABLE `recharge_card_exchange_code_batch` (
    `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `name` VARCHAR(255) NOT NULL COMMENT '批次名称',
    `recharge_card_name` VARCHAR(255) NOT NULL COMMENT '兑换成充值卡的卡名',
    `is_virtual` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否是虚拟批次,0:否,1:是',
    `is_made` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否已经生成,0:否,1:是',
    `description` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '充值卡详细信息',
    `amount` INT(10) NOT NULL DEFAULT '0' COMMENT '充值卡数量',
    `project_id` INT(10) COMMENT '对应项目id，为空表示无对应项目',
    `city_codes` VARCHAR(5000) NOT NULL DEFAULT '' COMMENT '适用城市code集合，粒度到2级城市，以逗号隔开',
    `product_ids` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '适用商品ID集合，以逗号隔开',
    `face_value` DECIMAL(20,4) NOT NULL DEFAULT '0.00' COMMENT '对应的金额',
    `expiry_time` DATETIME NOT NULL COMMENT '兑换码本身过期时间',
    `recharge_card_effective_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '充值卡生效时间',
    `recharge_card_expiry_time` DATETIME NOT NULL COMMENT '充值卡过期时间',
    `validity_period` INT(10) NOT NULL DEFAULT '0' COMMENT '自兑换、激活之日起多少天有效',
    `expiry_type` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '过期时间类型 0-充值卡过期时间，1-兑换之日起多少天有效，2-激活之日起多少天有效',
    `status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '状态：0:停用,1:启用',
    `ext_info` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '预留的其它字段',
    `is_deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否已删除,0:未删除,1:已删除',
    `add_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `mod_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`),
    KEY `idx_project_id` (`project_id`)
)  ENGINE=INNODB COMMENT='充值卡兑换码批次信息表';

drop table if exists recharge_card_exchange_code;
CREATE TABLE `recharge_card_exchange_code` (
    `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `batch_id` INT(10) UNSIGNED NOT NULL COMMENT '批次id',
    `batch_name` VARCHAR(255) NOT NULL COMMENT '批次名称',
    `recharge_card_name` VARCHAR(255) NOT NULL COMMENT '兑换成充值卡的卡名',
    `batch_description` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '充值卡详细信息',
    `project_id` INT(10) COMMENT '对应项目id，为空表示无对应项目',
    `city_codes` VARCHAR(5000) NOT NULL DEFAULT '' COMMENT '适用城市code集合，粒度到2级城市，以逗号隔开',
    `product_ids` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '适用商品ID集合，以逗号隔开',
    `code` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '兑换码',
    `keyt` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '密钥',
    `face_value` DECIMAL(20,4) NOT NULL DEFAULT '0.00' COMMENT '对应的金额',
    `expiry_time` DATETIME COMMENT '过期时间，为空永不过期',
    `recharge_card_effective_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '充值卡生效时间',
    `recharge_card_expiry_time` DATETIME NOT NULL COMMENT '充值卡过期时间',
    `validity_period` INT(10) NOT NULL DEFAULT '0' COMMENT '自兑换、激活之日起多少天有效',
    `expiry_type` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '过期时间类型 0-充值卡过期时间，1-兑换之日起多少天有效，2-激活之日起多少天有效',
    `status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '状态：0:停用,1:启用',
    `used` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0-未使用，1-已使用',
    `customer_id` INT(10) UNSIGNED COMMENT '客户id',
    `ext_info` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '预留的其它字段',
    `is_deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否已删除,0:未删除,1:已删除',
    `add_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `mod_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`),
    KEY `idx_batch_id` (`batch_id`),
    KEY `idx_project_id` (`project_id`),
    KEY `idx_code` (`code`),
    KEY `idx_keyt` (`keyt`)
)  ENGINE=INNODB COMMENT='充值卡兑换码信息表';


drop table if exists recharge_card_exchange_record;
CREATE TABLE `recharge_card_exchange_record` (
    `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `recharge_card_id` INT(10) UNSIGNED NOT NULL COMMENT '充值卡id',
    `exchange_code_id` INT(10) UNSIGNED COMMENT '兑换码id',
    `exchange_type` INT(10) NOT NULL DEFAULT '0' COMMENT '0-后台兑换码兑换，1-客户自行兑换码兑换，2-直接充值，3-消费记录审核而来',
    `operator_id` INT(10) UNSIGNED NOT NULL DEFAULT '0' COMMENT '操作人id',
    `operator_name` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '操作人名称',
    `ext_info` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '预留的其它字段',
    `is_deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否已删除,0:未删除,1:已删除',
    `add_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `mod_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`),
    KEY `idx_recharge_card_id` (`recharge_card_id`),
    KEY `idx_exchange_code_id` (`exchange_code_id`),
    KEY `idx_operator_id` (`operator_id`)
)  ENGINE=INNODB COMMENT='充值卡兑换记录表';


drop table if exists voucher;
CREATE TABLE `voucher` (
    `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `name` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '代金券名称',
    `description` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '代金券详细信息',
    `customer_id` INT(10) UNSIGNED NOT NULL COMMENT '绑定客户的id',
    `project_id` INT(10) UNSIGNED COMMENT '对应项目id，为空表示无对应项目',
    `city_codes` VARCHAR(5000) NOT NULL DEFAULT '' COMMENT '适用城市code集合，粒度到2级城市，以逗号隔开',
    `product_ids` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '适用商品ID集合，以逗号隔开',
    `count` INT(10) UNSIGNED NOT NULL COMMENT '可兑换商品的数量',
    `exchange_record_id` INT(10) UNSIGNED NOT NULL COMMENT '兑换记录ID',
    `effective_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '代金券生效时间',
    `expiry_time` DATETIME COMMENT '过期时间，为空永不过期',
    `used` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0-未使用，1-已使用',
    `status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '状态：0:停用,1:启用',
    `ext_info` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '预留的其它字段',
    `is_deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否已删除,0:未删除,1:已删除',
    `add_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `mod_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`),
    KEY `idx_customer_id` (`customer_id`),
    KEY `idx_project_id` (`project_id`),
    KEY `idx_exchange_record_id` (`exchange_record_id`)
)  ENGINE=INNODB COMMENT='代金券信息表';

drop table if exists voucher_exchange_code_batch;
CREATE TABLE `voucher_exchange_code_batch` (
    `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `name` VARCHAR(255) NOT NULL COMMENT '批次名称',
    `voucher_name` VARCHAR(255) NOT NULL COMMENT '兑换成代金券后的名称',
    `project_id` INT(10) UNSIGNED COMMENT '对应项目id，为空表示无对应项目',
    `is_made` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否已经制作,0:否,1:是',
    `description` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '代金券详细信息',
    `amount` INT(10) NOT NULL DEFAULT '0' COMMENT '代金券数量',
    `city_codes` VARCHAR(5000) NOT NULL DEFAULT '' COMMENT '适用城市code集合，粒度到2级城市，以逗号隔开',
    `product_ids` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '适用商品ID集合，以逗号隔开',
    `count` INT(10) UNSIGNED NOT NULL COMMENT '可兑换商品的数量',
    `expiry_time` DATETIME COMMENT '过期时间，为空永不过期',
    `voucher_effective_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '代金券生效时间',
    `voucher_expiry_time` DATETIME NOT NULL COMMENT '代金券过期时间',
    `validity_period` INT(10) NOT NULL DEFAULT '0' COMMENT '自兑换、激活之日起多少天有效',
    `expiry_type` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '过期时间类型 0-代金券过期时间，1-兑换之日起多少天有效，2-激活之日起多少天有效',
    `status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '状态：0:停用,1:启用',
    `ext_info` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '预留的其它字段',
    `is_deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否已删除,0:未删除,1:已删除',
    `add_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `mod_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`),
    KEY `idx_project_id` (`project_id`)
)  ENGINE=INNODB COMMENT='代金券兑换码批次信息表';

drop table if exists voucher_exchange_code;
CREATE TABLE `voucher_exchange_code` (
    `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `batch_id` INT(10) UNSIGNED NOT NULL COMMENT '批次id',
    `batch_name` VARCHAR(255) NOT NULL COMMENT '批次名称',
    `voucher_name` VARCHAR(255) NOT NULL COMMENT '兑换成代金券后的名称',
    `batch_description` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '代金券详细信息',
    `project_id` INT(10) COMMENT '对应项目id，为空表示无对应项目',
    `city_codes` VARCHAR(5000) NOT NULL DEFAULT '' COMMENT '适用城市code集合，粒度到2级城市，以逗号隔开',
    `product_ids` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '适用商品ID集合，以逗号隔开',
    `count` INT(10) UNSIGNED NOT NULL COMMENT '可兑换商品的数量',
    `code` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '兑换码',
    `keyt` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '密钥',
    `expiry_time` DATETIME COMMENT '过期时间，为空永不过期',
    `voucher_effective_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '代金券生效时间',
    `voucher_expiry_time` DATETIME NOT NULL COMMENT '代金券过期时间',
    `validity_period` INT(10) NOT NULL DEFAULT '0' COMMENT '自兑换、激活之日起多少天有效',
    `expiry_type` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '过期时间类型 0-代金券过期时间，1-兑换之日起多少天有效，2-激活之日起多少天有效',
    `status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '状态：0:停用,1:启用',
    `used` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0-未兑换，1-已兑换',
    `customer_id` INT(10) UNSIGNED COMMENT '客户id',
    `ext_info` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '预留的其它字段',
    `is_deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否已删除,0:未删除,1:已删除',
    `add_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `mod_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`),
    KEY `idx_batch_id` (`batch_id`),
    KEY `idx_project_id` (`project_id`),
    KEY `idx_code` (`code`),
    KEY `idx_keyt` (`keyt`)
)  ENGINE=INNODB COMMENT='代金券兑换码信息表';

drop table if exists voucher_exchange_record;
CREATE TABLE `voucher_exchange_record` (
    `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `voucher_id` INT(10) UNSIGNED NOT NULL COMMENT '代金券id',
    `exchange_code_id` INT(10) UNSIGNED COMMENT '兑换码id',
    `exchange_type` INT(10) NOT NULL DEFAULT '0' COMMENT '0-后台兑换码兑换，1-客户自行兑换码兑换，2-直接充值',
    `operator_id` INT(10) UNSIGNED NOT NULL DEFAULT '0' COMMENT '操作人id',
    `operator_name` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '操作人名称',
    `ext_info` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '预留的其它字段',
    `is_deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否已删除,0:未删除,1:已删除',
    `add_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `mod_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`),
    KEY `idx_voucher_id` (`voucher_id`),
    KEY `idx_exchange_code_id` (`exchange_code_id`),
    KEY `idx_operator_id` (`operator_id`)
)  ENGINE=INNODB COMMENT='代金券兑换记录表';

drop table if exists project;
CREATE TABLE `project` (
    `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `name` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '项目名称',
    `description` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '项目描述信息',
    `priority` INT(10) NOT NULL DEFAULT '0' COMMENT '排序序号',
    `point_conversion_rate` DECIMAL(8 , 4) NOT NULL DEFAULT '0.00' COMMENT '积分兑换比例',
    `status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '状态：0:未启动，1：进行中，2：已结束',
    `ext_info` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '预留的其它字段',
    `is_deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否已删除,0:未删除,1:已删除',
    `add_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `mod_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`)
)  ENGINE=INNODB COMMENT='项目信息表';

drop table if exists product;
CREATE TABLE `product` (
    `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `product_category_id` INT(10) UNSIGNED NOT NULL COMMENT '商品分类Id',
    `name` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '商品名称',
    `description` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '商品描述信息',
    `delay_days` INT(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '需提前预定的天数',
    `book_days` INT(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '可连续预约的天数',
    `thumbnail_url` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '缩略图url',
    `header_img_url` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '头部图url',
    `detail_img_url` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '详情图url',
    `product_type` INT(10) NOT NULL DEFAULT '0' COMMENT '0-服务，1-电子卡',
    `unit_name` VARCHAR(32) NOT NULL DEFAULT '' COMMENT '计量单位名称',
    `last_num` INT(10) NOT NULL DEFAULT '1' COMMENT '最少购买数量',
    `status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '状态：0:下架,1:上架',
    `priority` INT(10) NOT NULL DEFAULT '0' COMMENT '排序序号',
    `sms_template` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '短信发送模板',
    `effective_days` INT(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '有效期天数',
    `ext_info` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '预留的其它字段',
    `is_deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否已删除,0:未删除,1:已删除',
    `add_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `mod_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`),
    KEY `idx_product_category_id` (`product_category_id`)
)  ENGINE=INNODB COMMENT='商品信息';

drop table if exists electronic_product_exchange_code;
CREATE TABLE `electronic_product_exchange_code` (
    `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `product_id` INT(10) UNSIGNED NOT NULL COMMENT '商品id',
    `batch_name` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '批次名称',
    `order_id` INT(10) UNSIGNED NOT NULL COMMENT '订单ID，未使用时为空字符串',
    `order_code` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '订单编号，未使用时为空字符串',
    `code` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '兑换码（卡号）',
    `keyt` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '密钥',
    `status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '状态：0-未兑换，1-已兑换',
    `expiry_time` DATETIME NOT NULL COMMENT '过期时间',
    `ext_info` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '预留的其它字段',
    `is_deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否已删除,0:未删除,1:已删除',
    `add_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `mod_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`),
    KEY `idx_product_id` (`product_id`),
    KEY `idx_order_id` (`order_id`),
    KEY `idx_order_code` (`order_code`),
    KEY `idx_code` (`code`),
    KEY `idx_keyt` (`keyt`)
)  ENGINE=INNODB COMMENT='电子商品兑换码信息';

drop table if exists product_price;
CREATE TABLE `product_price` (
    `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `city_code` VARCHAR(255) NOT NULL DEFAULT ''  COMMENT '城市编码',
    `city_name` VARCHAR(255) NOT NULL DEFAULT ''  COMMENT '城市名称',
    `product_id` INT(10) UNSIGNED NOT NULL COMMENT '商品id',
    `price` DECIMAL(20,4) NOT NULL DEFAULT '0.00' COMMENT '商品价格',
    `status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '状态：0:未生效,1:已生效',
    `ext_info` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '预留的其它字段',
    `is_deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否已删除,0:未删除,1:已删除',
    `add_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `mod_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`),
    KEY `idx_city_code` (`city_code`),
    KEY `idx_product_id` (`product_id`)
)  ENGINE=INNODB COMMENT='商品价格信息表';

drop table if exists product_category;
CREATE TABLE `product_category` (
    `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `name` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '商品分类名称',
    `description` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '商品分类描述信息',
    `thumbnail_url` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '缩略图url',
    `detail_img_url` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '详情图url',
    `priority` INT(10) NOT NULL DEFAULT '0' COMMENT '排序序号',
    `status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '状态：0:下架,1:上架',
    `ext_info` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '预留的其它字段',
    `is_deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否已删除,0:未删除,1:已删除',
    `add_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `mod_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`)
)  ENGINE=INNODB COMMENT='商品分类信息';

drop table if exists `customer`;
CREATE TABLE `customer` (
    `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `mobile` VARCHAR(32) NOT NULL DEFAULT '' COMMENT '客户手机号',
    `name` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '客户姓名',
    `password` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '密码',
    `remark` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '备注信息',
    `default_address_id` INT(10) UNSIGNED DEFAULT '0' COMMENT '默认地址id,0表示没有地址',
    `ext_info` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '预留的其它字段',
    `is_deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否已删除,0:未删除,1:已删除',
    `add_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `mod_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`),
    KEY `idx_mobile` (`mobile`)
)  ENGINE=INNODB COMMENT='客户信息表';

drop table if exists `customer_address`;
CREATE TABLE `customer_address` (
    `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `customer_id` INT(10) UNSIGNED NOT NULL COMMENT '客户id',
    `gender` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '性别 0-女，1-男',
    `city_code` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '城市编码',
    `city_name` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '城市名称',
    `address` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '联系地址',
    `mobile` VARCHAR(32) NOT NULL DEFAULT '' COMMENT '联系手机号',
    `name` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '联系人姓名',
    `remark` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '备注信息',
    `ext_info` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '预留的其它字段',
    `is_deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否已删除,0:未删除,1:已删除',
    `add_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `mod_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`),
    KEY `idx_customer_id` (`customer_id`)
)  ENGINE=INNODB COMMENT='客户地址信息表';


drop table if exists `order_info`;
CREATE TABLE `order_info` (
    `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `order_code` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '订单编号',
    `customer_id` INT(10) UNSIGNED NOT NULL COMMENT '客户id',
    `product_id` INT(10) UNSIGNED NOT NULL COMMENT '扣分商品id',
    `service_product_id` INT(10) UNSIGNED NOT NULL COMMENT '服务商品id',
    `customer_city_code` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '客户下单的城市code',
    `customer_city_name` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '客户下单的城市名称',
    `product_price` DECIMAL(20,4) NOT NULL DEFAULT '0.00' COMMENT '商品单价',
    `count` INT(10) UNSIGNED NOT NULL DEFAULT '0' COMMENT '购买数量',
    `customer_address` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '联系地址',
    `customer_mobile` VARCHAR(32) NOT NULL DEFAULT '' COMMENT '联系手机号',
    `customer_name` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '联系人姓名',
    `customer_remark` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '客户留言',
    `worker_name` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '服务人员姓名',
    `worker_mobile` VARCHAR(32) NOT NULL DEFAULT '' COMMENT '服务人员联系方式',
    `order_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '下单时间',
    `service_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '预约服务时间',
    `real_service_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '真实服务时间',
    `pay_recharge_card` DECIMAL(20,4) NOT NULL DEFAULT '0.00' COMMENT '充值卡支付对应的金额',
    `pay_point` DECIMAL(20,4) NOT NULL DEFAULT '0.00' COMMENT '积分支付对应的金额',
    `pay_voucher` DECIMAL(20,4) NOT NULL DEFAULT '0.00' COMMENT '代金券支付对应的金额',
    `pay_cash` DECIMAL(20,4) NOT NULL DEFAULT '0.00' COMMENT '现金支付',
    `total_amount` DECIMAL(20,4) NOT NULL DEFAULT '0.00' COMMENT '订单总金额',
    `cost` DECIMAL(20,4) NOT NULL DEFAULT '0.00' COMMENT '订单成本金额',
    `comments` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '订单备注信息',
    `type` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '订单类型[0员工下单、1客户下单]',
    `status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '订单状态：待确认',
    `audit_reason` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '订单取消时，审核理由',
    `ext_info` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '预留的其它字段',
    `is_deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否已删除,0:未删除,1:已删除',
    `add_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `mod_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`),
    KEY `idx_order_code` (`order_code`),
    KEY `idx_customer_id` (`customer_id`),
    KEY `idx_product_id` (`product_id`),
    KEY `idx_service_product_id` (`service_product_id`)
)  ENGINE=INNODB COMMENT='订单信息表';


drop table if exists `order_payment`;
CREATE TABLE `order_payment` (
    `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `order_code` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '订单编号',
    `order_id` INT(10) UNSIGNED NOT NULL COMMENT '订单ID',
    `pay_type` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '支付类型',
    `point_id` INT(10) UNSIGNED COMMENT '积分卡id',
    `recharge_card_id` INT(10) UNSIGNED COMMENT '充值卡id',
    `voucher_id` INT(10) UNSIGNED COMMENT '代金券id',
    `pay_money` DECIMAL(20,4) NOT NULL DEFAULT '0.00' COMMENT '支付金额',
    `serial_number` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '第三方支付流水号',
    `ext_info` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '预留的其它字段',
    `is_deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否已删除,0:未删除,1:已删除',
    `add_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `mod_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`),
    KEY `idx_order_code` (`order_code`),
    KEY `idx_order_id` (`order_id`),
    KEY `idx_point_id` (`point_id`),
    KEY `idx_recharge_card_id` (`recharge_card_id`),
    KEY `idx_voucher_id` (`voucher_id`)
)  ENGINE=INNODB COMMENT='支付信息表';

drop table if exists `order_refund`;
CREATE TABLE `order_refund` (
    `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `order_code` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '订单编号',
    `order_id` INT(10) UNSIGNED NOT NULL COMMENT '订单ID',
    `refund_type` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '退款类型',
    `point_id` INT(10) UNSIGNED COMMENT '积分卡id',
    `recharge_card_id` INT(10) UNSIGNED COMMENT '充值卡id',
    `voucher_id` INT(10) UNSIGNED COMMENT '代金券id',
    `refund_money` DECIMAL(20,4) NOT NULL DEFAULT '0.00' COMMENT '退款金额',
    `serial_number` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '第三方退款流水号',
    `ext_info` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '预留的其它字段',
    `is_deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否已删除,0:未删除,1:已删除',
    `add_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `mod_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`),
    KEY `idx_order_code` (`order_code`),
    KEY `idx_order_id` (`order_id`),
    KEY `idx_point_id` (`point_id`),
    KEY `idx_recharge_card_id` (`recharge_card_id`),
    KEY `idx_voucher_id` (`voucher_id`)
)  ENGINE=INNODB COMMENT='退款信息表';

drop table if exists `audit_record`;
CREATE TABLE `audit_record` (
    `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `city_code` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '兑换城市code',
    `city_name` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '兑换城市名称',
    `bank_outlets_name` VARCHAR(100) COMMENT '银行网点名称',
    `exchange_type` INT(10) NOT NULL DEFAULT '1' COMMENT '兑换类型 1是直接充值，2是兑换积分卡，3是兑换商品，4是预采购',
    `exchange_point` DECIMAL(20,4) NOT NULL DEFAULT '0.00' COMMENT '总兑换积分',
    `giving_point` DECIMAL(20,4) NOT NULL DEFAULT '0.00' COMMENT '后台充值积分',
    `giving_type` VARCHAR(100) COMMENT '赠送类型 弃用',
    `pos_code` VARCHAR(100) NOT NULL DEFAULT '' COMMENT 'POS机编号 弃用',
    `trade_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '交易日期 弃用',
    `card_no` VARCHAR(20) NOT NULL DEFAULT '' COMMENT '银行卡号【后四位】 弃用',
    `img_urls` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '图片地址，以逗号隔开',
    `submit_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '提交时间',
    `submitter_id` INT(10) UNSIGNED NOT NULL COMMENT '提交人id',
    `submitter_name` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '提交人姓名',
    `customer_name` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '客户姓名',
    `customer_mobile` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '客户手机号',
    `remark` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '提交人填写备注信息',
    `point_ids` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '当兑换类型为1时，记录积分卡id集合',
    `point_codes` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '当兑换类型为2时，记录积分兑换码的卡号',
    `product_value` DECIMAL(20,4) NOT NULL DEFAULT '0.00' COMMENT '当兑换类型为3时，记录所给实物的价值',
    `status` TINYINT(4) NOT NULL DEFAULT '1' COMMENT '审核状态 1-已保存，2-已提交，3-已驳回，4-已通过',
    `charge_receipt_status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '小票录入完成状态 0录入未完成 1录入完成',
    `audit_reason` VARCHAR(100) COMMENT '驳回理由',
    `audit_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '审核时间',
    `auditor_id` INT(10) UNSIGNED NOT NULL COMMENT '审核人ID',
    `auditor_name` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '审核人姓名',
    `version` INT(10) UNSIGNED NOT NULL DEFAULT '0' COMMENT '版本号',
    `ext_info` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '预留的其它字段',
    `is_deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否已删除,0:未删除,1:已删除',
    `add_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `mod_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`),
    KEY `idx_submitter_id` (`submitter_id`),
    KEY `idx_auditor_id` (`auditor_id`)
)  ENGINE=INNODB COMMENT='兑换审核信息表';

drop table if exists `charge_receipt`;
CREATE TABLE `charge_receipt` (
    `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `audit_record_id` INT(10) UNSIGNED NOT NULL COMMENT '兑换审核信息表主键',
    `exchange_point` DECIMAL(20,4) NOT NULL DEFAULT '0.00' COMMENT '拆分的兑换积分',
    `pos_code` VARCHAR(100) NOT NULL DEFAULT '' COMMENT 'POS机编号',
    `trade_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '交易日期',
    `card_no` VARCHAR(20) NOT NULL DEFAULT '' COMMENT '银行卡号【后四位】',
    `customer_name` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '客户姓名',
    `inputer_id` INT(10) UNSIGNED NOT NULL COMMENT '录入人ID',
    `inputer_name` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '录入人姓名',
    `img_url` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '小票图片',
    `ext_info` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '预留的其它字段',
    `is_deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否已删除,0:未删除,1:已删除',
    `add_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `mod_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`),
    KEY `idx_audit_record_id` (`audit_record_id`),
    KEY `idx_inputer_id` (`inputer_id`)
)  ENGINE=INNODB COMMENT='充值凭证表';

drop table if exists `simple_session`;
CREATE TABLE `simple_session` (
    `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `cookie` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'cookie',
    `session` VARCHAR(5000) NOT NULL DEFAULT '' COMMENT 'session',
    `status` TINYINT(4) NOT NULL DEFAULT '1' COMMENT '状态 0-不可用，1-可用',
    `ext_info` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '预留的其它字段',
    `add_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `mod_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`),
    KEY `idx_cookie` (`cookie`)
)  ENGINE=INNODB COMMENT='登录记录信息表';

drop table if exists `advance_pay`;
CREATE TABLE `advance_pay` (
    `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `advance_pay` DECIMAL(20,4) NOT NULL DEFAULT '0.00' COMMENT '预支金额',
    `advance_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '预支时间',
    `remark` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '备注',
    `ext_info` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '预留的其它字段',
    `is_deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否已删除,0:未删除,1:已删除',
    `add_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `mod_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`)
)  ENGINE=INNODB COMMENT='预支记录';

drop table if exists `partner`;
CREATE TABLE `partner` (
    `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `name` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '合作商名称',
    `status` TINYINT(4) NOT NULL DEFAULT '1' COMMENT '状态 0-不可用，1-可用',
    `ext_info` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '预留的其它字段',
    `is_deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否已删除,0:未删除,1:已删除',
    `add_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `mod_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`),
    KEY `idx_name` (`name`)
)  ENGINE=INNODB COMMENT='合作商';

drop table if exists `service_item`;
CREATE TABLE `service_item` (
    `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `name` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '服务项目名称',
    `status` TINYINT(4) NOT NULL DEFAULT '1' COMMENT '状态 0-不可用，1-可用',
    `ext_info` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '预留的其它字段',
    `is_deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否已删除,0:未删除,1:已删除',
    `add_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `mod_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`),
    KEY `idx_name` (`name`)
)  ENGINE=INNODB COMMENT='服务项目';

drop table if exists `partner_order_info`;
CREATE TABLE `partner_order_info` (
    `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `customer_name` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '客户姓名',
    `customer_mobile` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '客户电话',
    `customer_address` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '联系地址',
    `customer_city_code` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '客户下单的城市code',
    `customer_city_name` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '客户下单的城市名称',
    `keyt` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '兑换密钥',
    `order_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '兑换时间',
    `service_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '预约时间',
    `order_source` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '订单来源',
    `worker_name` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '服务人员姓名',
    `worker_mobile` VARCHAR(32) NOT NULL DEFAULT '' COMMENT '服务人员联系方式',
    `service_item_id` INT(10) UNSIGNED NOT NULL COMMENT '服务项目id',
    `pre_pay` DECIMAL(20,4) NOT NULL DEFAULT '0.00' COMMENT '预付',
    `append_pay` DECIMAL(20,4) NOT NULL DEFAULT '0.00' COMMENT '二次支付',
    `remark` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '备注',
    `partner_id` INT(10) UNSIGNED NOT NULL COMMENT '合作商id',
    `status` TINYINT(4) NOT NULL DEFAULT '1' COMMENT '订单状态 1待派单,2待服务,3已完成,4已取消',
    `ext_info` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '预留的其它字段',
    `is_deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否已删除,0:未删除,1:已删除',
    `add_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `mod_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`),
    KEY `idx_service_item_id` (`service_item_id`),
    KEY `idx_partner_id` (`partner_id`)
)  ENGINE=INNODB COMMENT='合作商订单信息';

drop table if exists point;
CREATE TABLE `point` (
    `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `name` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '积分卡名称',
    `description` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '积分详细信息',
    `customer_id` INT(10) UNSIGNED NOT NULL COMMENT '绑定客户的id',
    `project_id` INT(10) UNSIGNED NOT NULL COMMENT '对应项目id，积分必须有对应的项目',
    `city_codes` VARCHAR(5000) NOT NULL DEFAULT '' COMMENT '适用城市Code集合，粒度到2级城市，以逗号隔开',
    `product_ids` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '适用商品ID集合，以逗号隔开',
    `face_value` DECIMAL(20,4) NOT NULL DEFAULT '0.00' COMMENT '原始积分值（类比充值卡面值）',
    `balance` DECIMAL(20,4) NOT NULL DEFAULT '0.00' COMMENT '积分余额',
    `exchange_record_id` INT(10) UNSIGNED NOT NULL COMMENT '兑换记录ID',
    `effective_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '积分生效时间',
    `expiry_time` DATETIME COMMENT '过期时间，为空永不过期',
    `status` TINYINT(4) NOT NULL DEFAULT '1' COMMENT '状态：0:停用,1:启用',
    `ext_info` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '预留的其它字段',
    `is_deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否已删除,0:未删除,1:已删除',
    `add_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `mod_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`),
    KEY `idx_customer_id` (`customer_id`),
    KEY `idx_project_id` (`project_id`),
    KEY `idx_exchange_record_id` (`exchange_record_id`)
)  ENGINE=INNODB COMMENT='积分信息表';

drop table if exists point_exchange_code_batch;
CREATE TABLE `point_exchange_code_batch` (
    `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `name` VARCHAR(255) NOT NULL COMMENT '批次名称',
    `point_name` VARCHAR(255) NOT NULL COMMENT '兑换成积分的名称',
    `is_virtual` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否是虚拟批次,0:否,1:是',
    `is_made` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否已经生成,0:否,1:是',
    `description` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '积分详细信息',
    `amount` INT(10) NOT NULL DEFAULT '0' COMMENT '积分卡数量',
    `project_id` INT(10) NOT NULL COMMENT '对应项目id，积分必须有对应项目',
    `city_codes` VARCHAR(5000) NOT NULL DEFAULT '' COMMENT '适用城市code集合，粒度到2级城市，以逗号隔开',
    `product_ids` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '适用商品ID集合，以逗号隔开',
    `face_value` DECIMAL(20,4) NOT NULL DEFAULT '0.00' COMMENT '对应的金额',
    `expiry_time` DATETIME NOT NULL COMMENT '兑换码本身过期时间',
    `point_effective_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '积分生效时间',
    `point_expiry_time` DATETIME NOT NULL COMMENT '积分卡过期时间',
    `validity_period` INT(10) NOT NULL DEFAULT '0' COMMENT '自兑换、激活之日起多少天有效',
    `expiry_type` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '过期时间类型 0-积分过期时间，1-兑换之日起多少天有效，2-激活之日起多少天有效',
    `status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '状态：0:停用,1:启用',
    `gift_no` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '中行储蓄卡对应礼品编号',
    `ext_info` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '预留的其它字段',
    `is_deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否已删除,0:未删除,1:已删除',
    `add_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `mod_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`),
    KEY `idx_project_id` (`project_id`)
)  ENGINE=INNODB COMMENT='积分兑换码批次信息表';

drop table if exists point_exchange_code;
CREATE TABLE `point_exchange_code` (
    `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `batch_id` INT(10) UNSIGNED NOT NULL COMMENT '批次id',
    `batch_name` VARCHAR(255) NOT NULL COMMENT '批次名称',
    `point_name` VARCHAR(255) NOT NULL COMMENT '兑换成积分卡的卡名',
    `batch_description` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '积分详细信息',
    `project_id` INT(10) NOT NULL COMMENT '对应项目id，积分必须有对应项目',
    `city_codes` VARCHAR(5000) NOT NULL DEFAULT '' COMMENT '适用城市code集合，粒度到2级城市，以逗号隔开',
    `product_ids` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '适用商品ID集合，以逗号隔开',
    `code` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '兑换码',
    `keyt` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '密钥',
    `face_value` DECIMAL(20,4) NOT NULL DEFAULT '0.00' COMMENT '对应的金额',
    `expiry_time` DATETIME COMMENT '过期时间，为空永不过期',
    `point_effective_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '积分生效时间',
    `point_expiry_time` DATETIME NOT NULL COMMENT '积分过期时间',
    `validity_period` INT(10) NOT NULL DEFAULT '0' COMMENT '自兑换、激活之日起多少天有效',
    `expiry_type` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '过期时间类型 0-积分过期时间，1-兑换之日起多少天有效，2-激活之日起多少天有效',
    `status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '状态：0:停用,1:启用',
    `used` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0-未使用，1-已使用，2-已退货',
    `customer_id` INT(10) UNSIGNED COMMENT '客户id',
    `out_order_code` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '对应外部订单号',
    `ext_info` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '预留的其它字段',
    `is_deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否已删除,0:未删除,1:已删除',
    `add_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `mod_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`),
    KEY `idx_batch_id` (`batch_id`),
    KEY `idx_project_id` (`project_id`),
    KEY `idx_code` (`code`),
    KEY `idx_keyt` (`keyt`)
)  ENGINE=INNODB COMMENT='积分兑换码信息表';

drop table if exists point_exchange_record;
CREATE TABLE `point_exchange_record` (
    `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `point_id` INT(10) UNSIGNED NOT NULL COMMENT '积分卡Id',
    `exchange_code_id` INT(10) UNSIGNED COMMENT '兑换码id',
    `exchange_type` INT(10) NOT NULL DEFAULT '0' COMMENT '0-后台兑换码兑换，1-客户自行兑换码兑换，2-直接充值，3-消费记录审核而来，4-二维码兑换，5-核销兑换',
    `operator_id` INT(10) UNSIGNED NOT NULL DEFAULT '0' COMMENT '操作人id',
    `operator_name` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '操作人名称',
    `ext_info` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '预留的其它字段',
    `is_deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否已删除,0:未删除,1:已删除',
    `add_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `mod_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`),
    KEY `idx_point_id` (`point_id`),
    KEY `idx_exchange_code_id` (`exchange_code_id`),
    KEY `idx_operator_id` (`operator_id`)
)  ENGINE=INNODB COMMENT='积分兑换记录表';

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
    PRIMARY KEY (`id`),
    KEY `idx_message_template_id` (`message_template_id`),
    KEY `idx_operator_id` (`operator_id`)
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

/**
物料信息表
 */
drop table if exists material_info;
CREATE TABLE `material_info` (
    `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `transfer_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '转账时间',
    `transfer_amount` DECIMAL(20,4) NOT NULL DEFAULT '0.00' COMMENT '转账金额',
    `material_value` DECIMAL(20,4) NOT NULL DEFAULT '0.00' COMMENT '物料价值',
    `payer_id` INT(10) UNSIGNED NOT NULL DEFAULT '0' COMMENT '付款人id',
    `payer_name` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '付款人名称',
    `payee_id` INT(10) UNSIGNED NOT NULL DEFAULT '0' COMMENT '收款人id',
    `payee_name` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '收款人名称',
    `remark` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '备注',
    `ext_info` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '预留的其它字段',
    `is_deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否已删除,0:未删除,1:已删除',
    `add_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `mod_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`),
    KEY `idx_payer_id` (`payer_id`),
    KEY `idx_payee_id` (`payee_id`)
)  ENGINE=INNODB COMMENT='物料信息';

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
  PRIMARY KEY (`id`),
  KEY `idx_orderId` (`orderId`)
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
  PRIMARY KEY (`id`),
  KEY `idx_orderId` (`orderId`)
) ENGINE=InnoDB COMMENT='商家订单操作留痕表';

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