/**
中行储蓄卡需求修改数据库SQL
 */
alter table point_exchange_code_batch add column gift_no VARCHAR(100) NOT NULL DEFAULT '' COMMENT '对应外部商品、礼品编号';
alter table point_exchange_code add column out_order_code VARCHAR(100) NOT NULL DEFAULT '' COMMENT '对应外部订单号';
alter table point_exchange_code modify column used TINYINT(4) NOT NULL DEFAULT '0' COMMENT '0-未使用，1-已使用，2-已退货';

/**
增加city_codes的长度，原来为1023，现改为5000
 */
alter table point_exchange_code_batch modify column city_codes VARCHAR(5000) NOT NULL DEFAULT '' COMMENT '适用城市code集合，粒度到2级城市，以逗号隔开';
alter table point_exchange_code modify column city_codes VARCHAR(5000) NOT NULL DEFAULT '' COMMENT '适用城市code集合，粒度到2级城市，以逗号隔开';
alter table point modify column city_codes VARCHAR(5000) NOT NULL DEFAULT '' COMMENT '适用城市code集合，粒度到2级城市，以逗号隔开';
alter table voucher_exchange_code_batch modify column city_codes VARCHAR(5000) NOT NULL DEFAULT '' COMMENT '适用城市code集合，粒度到2级城市，以逗号隔开';
alter table voucher_exchange_code modify column city_codes VARCHAR(5000) NOT NULL DEFAULT '' COMMENT '适用城市code集合，粒度到2级城市，以逗号隔开';
alter table voucher modify column city_codes VARCHAR(5000) NOT NULL DEFAULT '' COMMENT '适用城市code集合，粒度到2级城市，以逗号隔开';
alter table recharge_card_exchange_code_batch modify column city_codes VARCHAR(5000) NOT NULL DEFAULT '' COMMENT '适用城市code集合，粒度到2级城市，以逗号隔开';
alter table recharge_card_exchange_code modify column city_codes VARCHAR(5000) NOT NULL DEFAULT '' COMMENT '适用城市code集合，粒度到2级城市，以逗号隔开';
alter table recharge_card modify column city_codes VARCHAR(5000) NOT NULL DEFAULT '' COMMENT '适用城市code集合，粒度到2级城市，以逗号隔开';

/**
日志中detail长度太短了
 */
alter table sys_log modify column detail VARCHAR(5000) NOT NULL DEFAULT '' COMMENT '操作的详细信息';

