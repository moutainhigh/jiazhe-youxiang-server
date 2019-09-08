/**
中行储蓄卡需求修改数据库SQL
 */
alter table point_exchange_code_batch add column gift_no VARCHAR(100) NOT NULL DEFAULT '' COMMENT '中行储蓄卡对应礼品编号';
alter table point_exchange_code add column out_order_code VARCHAR(100) NOT NULL DEFAULT '' COMMENT '对应外部订单号';
alter table point_exchange_code modify column used TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0-未使用，1-已使用，2-已退货';
