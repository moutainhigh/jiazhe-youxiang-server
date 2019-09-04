/**
中行信用卡对接
 */
alter table voucher_exchange_code_batch add column boccc_product_id VARCHAR(100)  NOT NULL DEFAULT '' COMMENT '中行对接产品ID：WXXXXNNNNNN';
alter table voucher_exchange_code add column boccc_product_id VARCHAR(100)  NOT NULL DEFAULT '' COMMENT '中行对接产品ID：WXXXXNNNNNN';
alter table voucher_exchange_code add column used_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '使用时间';
update voucher_exchange_code vec set vec.used_time = (select ver.add_time from voucher_exchange_record ver where ver.exchange_code_id = vec.id) where vec.used = '1'
