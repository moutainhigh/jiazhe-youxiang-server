drop table if exists sys_log;
CREATE TABLE `sys_log` (
    `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `module_name` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '模块名称',
    `operate` VARCHAR(100) NOT NULL COMMENT '操作动作',
    `level` INT(10) NOT NULL DEFAULT '0' COMMENT '日志级别',
    `operator_id` INT(10) UNSIGNED NOT NULL COMMENT '操作人id',
    `operator_name` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '操作人名称',
    `ip` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '操作的ip地址',
    `detail` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '操作的详细信息',
    `ext_info` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '预留的其它字段',
    `is_deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否已删除,0:未删除,1:已删除',
    `add_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `mod_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`)
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
    PRIMARY KEY (`id`)
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
    PRIMARY KEY (`id`)
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
    PRIMARY KEY (`id`)
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
    PRIMARY KEY (`id`)
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
    PRIMARY KEY (`id`)
)  ENGINE=INNODB COMMENT='城市信息表';


drop table if exists recharge_card;
CREATE TABLE `recharge_card` (
    `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `name` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '充值卡名称',
    `description` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '充值卡详细信息',
    `customer_id` INT(10) UNSIGNED NOT NULL COMMENT '绑定客户的id',
    `project_id` INT(10) UNSIGNED COMMENT '对应项目id，为空表示无对应项目',
    `city_codes` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '适用城市Code集合，粒度到2级城市，以逗号隔开',
    `product_ids` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '适用商品ID集合，以逗号隔开',
    `face_value` DECIMAL(8 , 2 ) NOT NULL DEFAULT '0.00' COMMENT '充值卡面值（最初的价值）',
    `balance` DECIMAL(8 , 2 ) NOT NULL DEFAULT '0.00' COMMENT '充值卡余额',
    `exchange_record_id` INT(10) UNSIGNED NOT NULL COMMENT '兑换记录ID',
    `expiry_time` DATETIME COMMENT '过期时间，为空永不过期',
    `status` TINYINT(4) NOT NULL DEFAULT '1' COMMENT '状态：0:停用,1:启用',
    `ext_info` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '预留的其它字段',
    `is_deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否已删除,0:未删除,1:已删除',
    `add_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `mod_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`)
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
    `city_codes` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '适用城市code集合，粒度到2级城市，以逗号隔开',
    `product_ids` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '适用商品ID集合，以逗号隔开',
    `face_value` DECIMAL(8 , 2 ) NOT NULL DEFAULT '0.00' COMMENT '对应的金额',
    `expiry_time` DATETIME NOT NULL COMMENT '兑换码本身过期时间',
    `recharge_card_expiry_time` DATETIME NOT NULL COMMENT '充值卡过期时间',
    `validity_period` INT(10) NOT NULL DEFAULT '0' COMMENT '兑换之日起多少天有效',
    `expiry_type` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '过期时间类型 0-充值卡过期时间，1-兑换之日起多少天有效',
    `status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '状态：0:停用,1:启用',
    `ext_info` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '预留的其它字段',
    `is_deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否已删除,0:未删除,1:已删除',
    `add_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `mod_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`)
)  ENGINE=INNODB COMMENT='充值卡兑换码批次信息表';

drop table if exists recharge_card_exchange_code;
CREATE TABLE `recharge_card_exchange_code` (
    `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `batch_id` INT(10) UNSIGNED NOT NULL COMMENT '批次id',
    `batch_name` VARCHAR(255) NOT NULL COMMENT '批次名称',
    `recharge_card_name` VARCHAR(255) NOT NULL COMMENT '兑换成充值卡的卡名',
    `batch_description` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '充值卡详细信息',
    `project_id` INT(10) COMMENT '对应项目id，为空表示无对应项目',
    `city_codes` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '适用城市code集合，粒度到2级城市，以逗号隔开',
    `product_ids` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '适用商品ID集合，以逗号隔开',
    `code` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '兑换码',
    `keyt` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '密钥',
    `face_value` DECIMAL(8 , 2 ) NOT NULL DEFAULT '0.00' COMMENT '对应的金额',
    `expiry_time` DATETIME COMMENT '过期时间，为空永不过期',
    `recharge_card_expiry_time` DATETIME NOT NULL COMMENT '充值卡过期时间',
    `validity_period` INT(10) NOT NULL DEFAULT '0' COMMENT '兑换之日起多少天有效',
    `expiry_type` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '过期时间类型 0-充值卡过期时间，1-兑换之日起多少天有效',
    `status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '状态：0:停用,1:启用',
    `used` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0-未使用，1-已使用',
    `customer_id` INT(10) UNSIGNED COMMENT '客户id',
    `ext_info` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '预留的其它字段',
    `is_deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否已删除,0:未删除,1:已删除',
    `add_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `mod_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`)
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
    PRIMARY KEY (`id`)
)  ENGINE=INNODB COMMENT='充值卡兑换记录表';


drop table if exists voucher;
CREATE TABLE `voucher` (
    `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `name` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '代金券名称',
    `description` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '代金券详细信息',
    `customer_id` INT(10) UNSIGNED NOT NULL COMMENT '绑定客户的id',
    `project_id` INT(10) UNSIGNED COMMENT '对应项目id，为空表示无对应项目',
    `city_codes` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '适用城市code集合，粒度到2级城市，以逗号隔开',
    `product_ids` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '适用商品ID集合，以逗号隔开',
    `count` INT(10) UNSIGNED NOT NULL COMMENT '可兑换商品的数量',
    `exchange_record_id` INT(10) UNSIGNED NOT NULL COMMENT '兑换记录ID',
    `expiry_time` DATETIME COMMENT '过期时间，为空永不过期',
    `used` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0-未使用，1-已使用',
    `status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '状态：0:停用,1:启用',
    `ext_info` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '预留的其它字段',
    `is_deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否已删除,0:未删除,1:已删除',
    `add_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `mod_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`)
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
    `city_codes` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '适用城市code集合，粒度到2级城市，以逗号隔开',
    `product_ids` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '适用商品ID集合，以逗号隔开',
    `count` INT(10) UNSIGNED NOT NULL COMMENT '可兑换商品的数量',
    `expiry_time` DATETIME COMMENT '过期时间，为空永不过期',
    `voucher_expiry_time` DATETIME NOT NULL COMMENT '代金券过期时间',
    `validity_period` INT(10) NOT NULL DEFAULT '0' COMMENT '兑换之日起多少天有效',
    `expiry_type` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '过期时间类型 0-代金券过期时间，1-兑换之日起多少天有效',
    `status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '状态：0:停用,1:启用',
    `ext_info` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '预留的其它字段',
    `is_deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否已删除,0:未删除,1:已删除',
    `add_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `mod_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`)
)  ENGINE=INNODB COMMENT='代金券兑换码批次信息表';

drop table if exists voucher_exchange_code;
CREATE TABLE `voucher_exchange_code` (
    `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `batch_id` INT(10) UNSIGNED NOT NULL COMMENT '批次id',
    `batch_name` VARCHAR(255) NOT NULL COMMENT '批次名称',
    `voucher_name` VARCHAR(255) NOT NULL COMMENT '兑换成代金券后的名称',
    `batch_description` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '代金券详细信息',
    `project_id` INT(10) COMMENT '对应项目id，为空表示无对应项目',
    `city_codes` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '适用城市code集合，粒度到2级城市，以逗号隔开',
    `product_ids` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '适用商品ID集合，以逗号隔开',
    `count` INT(10) UNSIGNED NOT NULL COMMENT '可兑换商品的数量',
    `code` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '兑换码',
    `keyt` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '密钥',
    `expiry_time` DATETIME COMMENT '过期时间，为空永不过期',
    `voucher_expiry_time` DATETIME NOT NULL COMMENT '代金券过期时间',
    `validity_period` INT(10) NOT NULL DEFAULT '0' COMMENT '兑换之日起多少天有效',
    `expiry_type` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '过期时间类型 0-代金券过期时间，1-兑换之日起多少天有效',
    `status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '状态：0:停用,1:启用',
    `used` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0-未兑换，1-已兑换',
    `customer_id` INT(10) UNSIGNED COMMENT '客户id',
    `ext_info` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '预留的其它字段',
    `is_deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否已删除,0:未删除,1:已删除',
    `add_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `mod_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`)
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
    PRIMARY KEY (`id`)
)  ENGINE=INNODB COMMENT='代金券兑换记录表';

drop table if exists project;
CREATE TABLE `project` (
    `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `name` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '项目名称',
    `description` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '项目描述信息',
    `priority` INT(10) NOT NULL DEFAULT '0' COMMENT '排序序号',
    `point_conversion_rate` INT(10) UNSIGNED NOT NULL DEFAULT '5' COMMENT '积分兑换比例',
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
    PRIMARY KEY (`id`)
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
    PRIMARY KEY (`id`)
)  ENGINE=INNODB COMMENT='电子商品兑换码信息';

drop table if exists product_price;
CREATE TABLE `product_price` (
    `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `city_code` VARCHAR(255) NOT NULL DEFAULT ''  COMMENT '城市编码',
    `city_name` VARCHAR(255) NOT NULL DEFAULT ''  COMMENT '城市名称',
    `product_id` INT(10) UNSIGNED NOT NULL COMMENT '商品id',
    `price` DECIMAL(8 , 2 ) NOT NULL DEFAULT '0.00' COMMENT '商品价格',
    `status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '状态：0:未生效,1:已生效',
    `ext_info` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '预留的其它字段',
    `is_deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否已删除,0:未删除,1:已删除',
    `add_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `mod_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`)
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
    PRIMARY KEY (`id`)
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
    PRIMARY KEY (`id`)
)  ENGINE=INNODB COMMENT='客户地址信息表';


drop table if exists `order_info`;
CREATE TABLE `order_info` (
    `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `order_code` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '订单编号',
    `customer_id` INT(10) UNSIGNED NOT NULL COMMENT '客户id',
    `product_id` INT(10) UNSIGNED NOT NULL COMMENT '商品id',
    `customer_city_code` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '客户下单的城市code',
    `customer_city_name` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '客户下单的城市名称',
    `product_price` DECIMAL(8 , 2 ) NOT NULL DEFAULT '0.00' COMMENT '商品单价',
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
    `pay_recharge_card` DECIMAL(8 , 2 ) NOT NULL DEFAULT '0.00' COMMENT '充值卡支付对应的金额',
    `pay_point` DECIMAL(8 , 2 ) NOT NULL DEFAULT '0.00' COMMENT '积分支付对应的金额',
    `pay_voucher` DECIMAL(8 , 2 ) NOT NULL DEFAULT '0.00' COMMENT '代金券支付对应的金额',
    `pay_cash` DECIMAL(8 , 2 ) NOT NULL DEFAULT '0.00' COMMENT '现金支付',
    `total_amount` DECIMAL(8 , 2 ) NOT NULL DEFAULT '0.00' COMMENT '订单总金额',
    `cost` DECIMAL(8 , 2 ) NOT NULL DEFAULT '0.00' COMMENT '订单成本金额',
    `comments` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '订单备注信息',
    `type` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '订单类型[0员工下单、1客户下单]',
    `status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '订单状态：待确认',
    `audit_reason` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '订单取消时，审核理由',
    `ext_info` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '预留的其它字段',
    `is_deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否已删除,0:未删除,1:已删除',
    `add_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `mod_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`)
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
    `pay_money` DECIMAL(8 , 2 ) NOT NULL DEFAULT '0.00' COMMENT '支付金额',
    `serial_number` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '第三方支付流水号',
    `ext_info` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '预留的其它字段',
    `is_deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否已删除,0:未删除,1:已删除',
    `add_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `mod_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`)
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
    `refund_money` DECIMAL(8 , 2 ) NOT NULL DEFAULT '0.00' COMMENT '退款金额',
    `serial_number` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '第三方退款流水号',
    `ext_info` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '预留的其它字段',
    `is_deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否已删除,0:未删除,1:已删除',
    `add_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `mod_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`)
)  ENGINE=INNODB COMMENT='退款信息表';


drop table if exists `audit_record`;
CREATE TABLE `audit_record` (
    `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `point_id` INT(10) UNSIGNED NOT NULL COMMENT '积分卡id',
    `customer_name` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '客户姓名',
    `customer_mobile` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '客户手机号',
    `exchange_money` DECIMAL(8 , 2 ) NOT NULL DEFAULT '0.00' COMMENT '兑换金额',
    `remark` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '备注信息',
    `img_urls` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '图片地址，以逗号隔开',
    `submitter_id` INT(10) UNSIGNED NOT NULL COMMENT '提交人id',
    `submitter_name` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '提交人姓名',
    `submitter_remark` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '提交人备注信息',
    `status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '审核状态 0-待审核，1-未通过，2-已通过',
    `audit_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '审核时间',
    `auditor_id` INT(10) UNSIGNED NOT NULL COMMENT '审核人ID',
    `auditor_name` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '审核人姓名',
    `version` INT(10) UNSIGNED NOT NULL DEFAULT '0' COMMENT '版本号',
    `ext_info` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '预留的其它字段',
    `is_deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否已删除,0:未删除,1:已删除',
    `add_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `mod_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`)
)  ENGINE=INNODB COMMENT='兑换审核信息表';

drop table if exists `simple_session`;
CREATE TABLE `simple_session` (
    `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `cookie` VARCHAR(50) NOT NULL DEFAULT '' COMMENT 'cookie',
    `session` VARCHAR(5000) NOT NULL DEFAULT '' COMMENT 'session',
    `status` TINYINT(4) NOT NULL DEFAULT '1' COMMENT '状态 0-不可用，1-可用',
    `ext_info` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '预留的其它字段',
    `add_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `mod_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`)
)  ENGINE=INNODB COMMENT='登录记录信息表';

drop table if exists `advance_pay`;
CREATE TABLE `advance_pay` (
    `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `advance_pay` DECIMAL(8 , 2 ) NOT NULL DEFAULT '0.00' COMMENT '预支金额',
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
    PRIMARY KEY (`id`)
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
    PRIMARY KEY (`id`)
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
    `pre_pay` DECIMAL(8 , 2 ) NOT NULL DEFAULT '0.00' COMMENT '预付',
    `append_pay` DECIMAL(8 , 2 ) NOT NULL DEFAULT '0.00' COMMENT '二次支付',
    `remark` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '备注',
    `partner_id` INT(10) UNSIGNED NOT NULL COMMENT '合作商id',
    `status` TINYINT(4) NOT NULL DEFAULT '1' COMMENT '订单状态 1待服务，2已完成，3已取消',
    `ext_info` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '预留的其它字段',
    `is_deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否已删除,0:未删除,1:已删除',
    `add_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `mod_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`)
)  ENGINE=INNODB COMMENT='合作商订单信息';

drop table if exists point;
CREATE TABLE `point` (
    `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `name` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '积分卡名称',
    `description` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '积分详细信息',
    `customer_id` INT(10) UNSIGNED NOT NULL COMMENT '绑定客户的id',
    `project_id` INT(10) UNSIGNED NOT NULL COMMENT '对应项目id，积分必须有对应的项目',
    `city_codes` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '适用城市Code集合，粒度到2级城市，以逗号隔开',
    `product_ids` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '适用商品ID集合，以逗号隔开',
    `face_value` DECIMAL(8 , 2 ) NOT NULL DEFAULT '0.00' COMMENT '原始积分值（类比充值卡面值）',
    `balance` DECIMAL(8 , 2 ) NOT NULL DEFAULT '0.00' COMMENT '积分余额',
    `exchange_record_id` INT(10) UNSIGNED NOT NULL COMMENT '兑换记录ID',
    `expiry_time` DATETIME COMMENT '过期时间，为空永不过期',
    `status` TINYINT(4) NOT NULL DEFAULT '1' COMMENT '状态：0:停用,1:启用',
    `ext_info` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '预留的其它字段',
    `is_deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否已删除,0:未删除,1:已删除',
    `add_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `mod_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`)
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
    `city_codes` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '适用城市code集合，粒度到2级城市，以逗号隔开',
    `product_ids` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '适用商品ID集合，以逗号隔开',
    `face_value` DECIMAL(8 , 2 ) NOT NULL DEFAULT '0.00' COMMENT '对应的金额',
    `expiry_time` DATETIME NOT NULL COMMENT '兑换码本身过期时间',
    `point_expiry_time` DATETIME NOT NULL COMMENT '积分卡过期时间',
    `validity_period` INT(10) NOT NULL DEFAULT '0' COMMENT '兑换之日起多少天有效',
    `expiry_type` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '过期时间类型 0-积分过期时间，1-兑换之日起多少天有效',
    `status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '状态：0:停用,1:启用',
    `ext_info` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '预留的其它字段',
    `is_deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否已删除,0:未删除,1:已删除',
    `add_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `mod_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`)
)  ENGINE=INNODB COMMENT='积分兑换码批次信息表';

drop table if exists point_exchange_code;
CREATE TABLE `point_exchange_code` (
    `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `batch_id` INT(10) UNSIGNED NOT NULL COMMENT '批次id',
    `batch_name` VARCHAR(255) NOT NULL COMMENT '批次名称',
    `point_name` VARCHAR(255) NOT NULL COMMENT '兑换成积分卡的卡名',
    `batch_description` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '积分详细信息',
    `project_id` INT(10) NOT NULL COMMENT '对应项目id，积分必须有对应项目',
    `city_codes` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '适用城市code集合，粒度到2级城市，以逗号隔开',
    `product_ids` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '适用商品ID集合，以逗号隔开',
    `code` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '兑换码',
    `keyt` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '密钥',
    `face_value` DECIMAL(8 , 2 ) NOT NULL DEFAULT '0.00' COMMENT '对应的金额',
    `expiry_time` DATETIME COMMENT '过期时间，为空永不过期',
    `point_expiry_time` DATETIME NOT NULL COMMENT '积分过期时间',
    `validity_period` INT(10) NOT NULL DEFAULT '0' COMMENT '兑换之日起多少天有效',
    `expiry_type` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '过期时间类型 0-积分过期时间，1-兑换之日起多少天有效',
    `status` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '状态：0:停用,1:启用',
    `used` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0-未使用，1-已使用',
    `customer_id` INT(10) UNSIGNED COMMENT '客户id',
    `ext_info` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '预留的其它字段',
    `is_deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否已删除,0:未删除,1:已删除',
    `add_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `mod_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`)
)  ENGINE=INNODB COMMENT='积分兑换码信息表';

drop table if exists point_exchange_record;
CREATE TABLE `point_exchange_record` (
    `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `point_id` INT(10) UNSIGNED NOT NULL COMMENT '积分卡Id',
    `exchange_code_id` INT(10) UNSIGNED COMMENT '兑换码id',
    `exchange_type` INT(10) NOT NULL DEFAULT '0' COMMENT '0-后台兑换码兑换，1-客户自行兑换码兑换，2-直接充值，3-消费记录审核而来',
    `operator_id` INT(10) UNSIGNED NOT NULL DEFAULT '0' COMMENT '操作人id',
    `operator_name` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '操作人名称',
    `ext_info` VARCHAR(1023) NOT NULL DEFAULT '' COMMENT '预留的其它字段',
    `is_deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否已删除,0:未删除,1:已删除',
    `add_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `mod_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`)
)  ENGINE=INNODB COMMENT='积分兑换记录表';

