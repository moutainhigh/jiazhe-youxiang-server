/**
 商家订单模块
 */
alter table partner_order_info modify column status TINYINT COMMENT '订单状态 1待派单,2待服务,3已完成,4已取消';
update partner_order_info set status=status+1;

/**
充值审核模块
 */
