/**
 商品下单时间约束
 */
alter table product add column `book_days` INT(10) UNSIGNED NOT NULL DEFAULT 20 COMMENT '可连续预约的天数';

