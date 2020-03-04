/**
 商品下单时间约束
 */
alter table product add column service_product_id INT(10) UNSIGNED NOT NULL COMMENT '服务商品id';
