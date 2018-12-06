package com.jiazhe.youxiang.server.common.enums;

import com.jiazhe.youxiang.server.common.constant.PermissionStr;
/**
 * @author TU
 * @description 权限树枚举类 【
 * @date 2018/12/6.
 */
public enum PermissionTreeEnum {
    //客户管理
    CUSTOMER_MANAGEMENT(1, 0, "客户管理", PermissionStr.CUSTOMER_MANAGEMENT),
    //添加客户
    CUSTOMER_ADD(101, 1, "添加客户", PermissionStr.CUSTOMER_ADD),
    //修改客户
    CUSTOMER_EDIT(102, 1, "修改客户", PermissionStr.CUSTOMER_EDIT),
    //查看客户充值卡
    CUSTOMER_RECHARGE_CARD_DETAIL(103, 1, "查看客户充值卡", PermissionStr.CUSTOMER_RECHARGE_CARD_DETAIL),
    //绑定充值卡
    CUSTOMER_RECHARGE_CARD_BINDING(104, 1, "绑定充值卡", PermissionStr.CUSTOMER_RECHARGE_CARD_BINDING),
    //直接充值
    CUSTOMER_RECHARGE_CARD_RECHARGE(105, 1, "直接充值", PermissionStr.CUSTOMER_RECHARGE_CARD_RECHARGE),
    //查看客户代金券
    CUSTOMER_VOUCHER_DETAIL(106, 1, "查看客户代金券", PermissionStr.CUSTOMER_VOUCHER_DETAIL),
    //绑定代金券
    CUSTOMER_VOUCHER_BINDING(107, 1, "绑定代金券", PermissionStr.CUSTOMER_VOUCHER_BINDING),
    //查看客户订单
    CUSTOMER_ORDER_DETAIL(108, 1, "查看客户订单", PermissionStr.CUSTOMER_ORDER_DETAIL),
    //后台下单
    CUSTOMER_USER_PLACE_ORDER(109, 1, "后台下单", PermissionStr.CUSTOMER_USER_PLACE_ORDER),
    //商品大类管理
    PRODUCT_CATEGORY_MANAGEMENT(2, 0, "商品大类管理", PermissionStr.PRODUCT_CATEGORY_MANAGEMENT),
    //商品大类添加
    PRODUCT_CATEGORY_ADD(201, 2, "商品大类添加", PermissionStr.PRODUCT_CATEGORY_ADD),
    //商品大类修改
    PRODUCT_CATEGORY_EDIT(202, 2, "商品大类修改", PermissionStr.PRODUCT_CATEGORY_EDIT),
    //商品大类删除
    PRODUCT_CATEGORY_DELETE(203, 2, "商品大类删除", PermissionStr.PRODUCT_CATEGORY_DELETE),
    //商品上下架
    PRODUCT_CATEGORY_ONOFFLINE(204, 2, "商品上下架", PermissionStr.PRODUCT_CATEGORY_ONOFFLINE),
    //商品管理
    PRODUCT_MANAGEMENT(205, 2, "商品管理", PermissionStr.PRODUCT_MANAGEMENT),
    //商品添加
    PRODUCT_ADD(206, 2, "商品添加", PermissionStr.PRODUCT_ADD),
    //商品修改
    PRODUCT_EDIT(207, 2, "商品修改", PermissionStr.PRODUCT_EDIT),
    //商品删除
    PRODUCT_DELETE(208, 2, "商品删除", PermissionStr.PRODUCT_DELETE),
    //商品上下架
    PRODUCT_ONOFFLINE(209, 2, "商品上下架", PermissionStr.PRODUCT_ONOFFLINE),
    //商品价格管理
    PRODUCT_PRICE_MANAGEMENT(210, 2, "商品价格管理", PermissionStr.PRODUCT_PRICE_MANAGEMENT),
    //商品价格编辑
    PRODUCT_PRICE_EDIT(211, 2, "商品价格编辑", PermissionStr.PRODUCT_PRICE_EDIT),
    //商品价格生效失效
    PRODUCT_PRICE_EFFECT(212, 2, "商品价格生效失效", PermissionStr.PRODUCT_PRICE_EFFECT),
    //订单管理
    ORDER_MANAGEMENT(3, 0, "订单管理", PermissionStr.ORDER_MANAGEMENT),
    //订单预约
    ORDER_RESERVATION(301, 3, "订单预约", PermissionStr.ORDER_RESERVATION),
    //订单修改
    ORDER_EDIT(302, 3, "订单修改", PermissionStr.ORDER_EDIT),
    //订单完成
    ORDER_COMPLETE(303, 3, "订单完成", PermissionStr.ORDER_COMPLETE),
    //订单追加
    ORDER_APPEND(304, 3, "订单追加", PermissionStr.ORDER_APPEND),
    //订单审核
    ORDER_CHECK(305, 3, "订单审核", PermissionStr.ORDER_CHECK),
    //订单取消
    ORDER_CANCEL(306, 3, "订单取消", PermissionStr.ORDER_CANCEL),
    //项目管理
    PROJECT_MANAGEMENT(4, 0, "项目管理", PermissionStr.PROJECT_MANAGEMENT),
    //项目添加
    PROJECT_ADD(401, 4, "项目添加", PermissionStr.PROJECT_ADD),
    //项目修改
    PROJECT_EDIT(402, 4, "项目修改", PermissionStr.PROJECT_EDIT),
    //项目删除
    PROJECT_DELETE(403, 4, "项目删除", PermissionStr.PROJECT_DELETE),
    //项目开始
    PROJECT_START(404, 4, "项目开始", PermissionStr.PROJECT_START),
    //项目结束
    PROJECT_STOP(405, 4, "项目结束", PermissionStr.PROJECT_STOP),
    //消费记录管理
    AUDIT_RECORD_MANAGEMENT(5, 0, "消费记录管理", PermissionStr.AUDIT_RECORD_MANAGEMENT),
    //消费记录审核
    AUDIT_RECORD_CHECK(501, 5, "消费记录审核", PermissionStr.AUDIT_RECORD_CHECK),
    //信息查询
    INFO_SEARCH(6, 0, "信息查询", PermissionStr.INFO_SEARCH),
    //充值卡查询
    RECHARGE_CARD_SEARCH(601, 6, "充值卡查询", PermissionStr.RECHARGE_CARD_SEARCH),
    //充值卡修改
    RECHARGE_CARD_SEARCH_EDIT(602, 6, "充值卡修改", PermissionStr.RECHARGE_CARD_SEARCH_EDIT),
    //充值卡兑换码查询
    RECHARGE_CARD_CODE_SEARCH(603, 6, "充值卡兑换码查询", PermissionStr.RECHARGE_CARD_CODE_SEARCH),
    //充值卡兑换码修改
    RECHARGE_CARD_CODE_SEARCH_EDIT(604, 6, "充值卡兑换码修改", PermissionStr.RECHARGE_CARD_CODE_SEARCH_EDIT),
    //代金券查询
    VOUCHER_SEARCH (605, 6, "代金券查询", PermissionStr.VOUCHER_SEARCH),
    //代金券修改
    VOUCHER_SEARCH_EDIT(606, 6, "代金券修改", PermissionStr.VOUCHER_SEARCH_EDIT),
    //代金券兑换码查询
    VOUCHER_CODE_SEARCH(607, 6, "代金券兑换码查询", PermissionStr.VOUCHER_CODE_SEARCH),
    //代金券兑换码修改
    VOUCHER_CODE_SEARCH_EIDT(608, 6, "代金券兑换码修改", PermissionStr.VOUCHER_CODE_SEARCH_EIDT),
    //系统管理
    SYSTEM_MANAGEMENT(7, 0, "系统管理", PermissionStr.SYSTEM_MANAGEMENT),
    //充值卡兑换码批次管理
    RC_BATCH_MANAGEMENT(701, 7, "充值卡兑换码批次管理", PermissionStr.RC_BATCH_MANAGEMENT),
    //充值卡兑换码批次添加
    RC_BATCH_ADD(702, 7, "充值卡兑换码批次添加", PermissionStr.RC_BATCH_ADD),
    //充值卡兑换码批次修改
    RC_BATCH_EDIT(703, 7, "充值卡兑换码批次修改", PermissionStr.RC_BATCH_EDIT),
    //充值卡兑换码管理
    RC_CODE_MANAGEMENT(704, 7, "充值卡兑换码管理", PermissionStr.RC_CODE_MANAGEMENT),
    //充值卡兑换码修改
    RC_CODE_EDIT(705, 7, "充值卡兑换码修改", PermissionStr.RC_CODE_EDIT),
    //代金券兑换码批次管理
    VOUCHER_BATCH_MANAGEMENT(706, 7, "代金券兑换码批次管理", PermissionStr.VOUCHER_BATCH_MANAGEMENT),
    //代金券兑换码批次添加
    VOUCHER_BATCH_ADD(707, 7, "代金券兑换码批次添加", PermissionStr.VOUCHER_BATCH_ADD),
    //代金券兑换码批次修改
    VOUCHER_BATCH_EDIT(708, 7, "代金券兑换码批次修改", PermissionStr.VOUCHER_BATCH_EDIT),
    //代金券兑换码管理
    VOUCHER_CODE_MANAGEMENT(709, 7, "代金券兑换码管理", PermissionStr.VOUCHER_CODE_MANAGEMENT),
    //代金券兑换码修改
    VOUCHER_CODE_EDIT(710, 7, "代金券兑换码修改", PermissionStr.VOUCHER_CODE_EDIT),
    //电子商品管理
    ELE_CODE_MANAGEMENT(711, 7, "电子商品管理", PermissionStr.ELE_CODE_MANAGEMENT),
    //电子商品添加
    ELE_CODE_ADD(712, 7, "电子商品添加", PermissionStr.ELE_CODE_ADD),
    //电子商品修改
    ELE_CODE_EDIT(713, 7, "电子商品修改", PermissionStr.ELE_CODE_EDIT),
    //员工管理
    USER_MANAGEMENT(714, 7, "员工管理", PermissionStr.USER_MANAGEMENT),
    //角色管理
    ROLE_MANAGEMENT(715, 7, "角色管理", PermissionStr.ROLE_MANAGEMENT),
    //城市管理
    CITY_MANAGEMENT(716, 7, "城市管理", PermissionStr.CITY_MANAGEMENT),
    //日志管理
    LOG_MANAGEMENT(717, 7, "日志管理", PermissionStr.LOG_MANAGEMENT),
    ;

    PermissionTreeEnum(Integer id, Integer pId, String name, String perm) {
        this.id = id;
        this.pId = pId;
        this.name = name;
        this.perm = perm;
    }

    private Integer id;
    private Integer pId;
    private String name;
    private String perm;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPerm() {
        return perm;
    }

    public void setPerm(String perm) {
        this.perm = perm;
    }
}
