/**
3.6.1 第一批次上线
 */
alter table audit_record modify column exchange_type INT(10) NOT NULL DEFAULT '1' COMMENT '兑换类型 1是直接充值，2是兑换积分卡，3是兑换商品，4是预购';

