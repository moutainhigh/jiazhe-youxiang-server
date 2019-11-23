/**
3.6.1 第一批次上线
 */
alter table audit_record modify column exchange_type INT(10) NOT NULL DEFAULT '1' COMMENT '兑换类型 1是直接充值，2是兑换积分卡，3是兑换商品，4是预购';

/**
3.6.2 第二批次上线
 */
alter table order_info modify column product_id INT(10) UNSIGNED NOT NULL COMMENT '扣分商品id';
alter table order_info add column service_product_id INT(10) UNSIGNED NOT NULL COMMENT '服务商品id';
update order_info set service_product_id = product_id;