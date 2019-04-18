/**
充值审核表
 */
alter table audit_record add column submit_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '提交时间';
update audit_record set submit_time = mod_time where 1 = 1;

/**
凭证记录表
 */
alter table charge_receipt add column img_url VARCHAR(100) NOT NULL DEFAULT '' COMMENT '小票图片';
update charge_receipt set img_url = ext_info where 1 = 1;
update charge_receipt set ext_info = '' where 1 = 1;
