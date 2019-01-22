package com.jiazhe.youxiang.server.common.enums;

import com.jiazhe.youxiang.server.common.constant.PermissionConstant;

/**
 * @author TU
 * @description 权限树枚举类
 * @date 2018/12/6.
 */
public enum PermissionTreeEnum {

    /***************************客户相关**************************************/
    //客户管理
    CUSTOMER_MANAGEMENT(1, 0, "客户管理", PermissionConstant.CUSTOMER_MANAGEMENT),
    //客户查询
    CUSTOMER_SEARCH(101, 1, "客户查询", PermissionConstant.CUSTOMER_SEARCH),
    //客户添加
    CUSTOMER_ADD(102, 1, "客户添加", PermissionConstant.CUSTOMER_ADD),
    //客户修改
    CUSTOMER_EDIT(103, 1, "修改客户", PermissionConstant.CUSTOMER_EDIT),
    //查看积分卡
    CUSTOMER_POINT_DETAIL(104, 1, "查看积分卡", PermissionConstant.CUSTOMER_POINT_DETAIL),
    //绑定积分卡
    CUSTOMER_POINT_BINDING(105, 1, "绑定积分卡", PermissionConstant.CUSTOMER_POINT_BINDING),
    //直接充值积分卡
    CUSTOMER_POINT_RECHARGE(106, 1, "直接充值积分卡", PermissionConstant.CUSTOMER_POINT_CHARGE),
    //查看充值卡
    CUSTOMER_RECHARGE_CARD_DETAIL(107, 1, "查看充值卡", PermissionConstant.CUSTOMER_RECHARGE_CARD_DETAIL),
    //绑定充值卡
    CUSTOMER_RECHARGE_CARD_BINDING(108, 1, "绑定充值卡", PermissionConstant.CUSTOMER_RECHARGE_CARD_BINDING),
    //直接充值充值卡
    CUSTOMER_RECHARGE_CARD_RECHARGE(109, 1, "直接充值充值卡", PermissionConstant.CUSTOMER_RECHARGE_CARD_CHARGE),
    //查看代金券
    CUSTOMER_VOUCHER_DETAIL(110, 1, "查看代金券", PermissionConstant.CUSTOMER_VOUCHER_DETAIL),
    //绑定代金券
    CUSTOMER_VOUCHER_BINDING(111, 1, "绑定代金券", PermissionConstant.CUSTOMER_VOUCHER_BINDING),
    //查看客户订单
    CUSTOMER_ORDER_DETAIL(112, 1, "查看客户订单", PermissionConstant.CUSTOMER_ORDER_DETAIL),
    //后台下单
    CUSTOMER_USER_PLACE_ORDER(112, 1, "后台下单", PermissionConstant.CUSTOMER_USER_PLACE_ORDER),

    /***************************商品相关**************************************/
    //商品大类管理
    PRODUCT_CATEGORY_MANAGEMENT(2, 0, "商品大类管理", PermissionConstant.PRODUCT_CATEGORY_MANAGEMENT),
    //商品大类添加
    PRODUCT_CATEGORY_ADD(201, 2, "商品大类添加", PermissionConstant.PRODUCT_CATEGORY_ADD),
    //商品大类修改
    PRODUCT_CATEGORY_EDIT(202, 2, "商品大类修改", PermissionConstant.PRODUCT_CATEGORY_EDIT),
    //商品大类删除
    PRODUCT_CATEGORY_DELETE(203, 2, "商品大类删除", PermissionConstant.PRODUCT_CATEGORY_DELETE),
    //商品上下架
    PRODUCT_CATEGORY_ONOFFLINE(204, 2, "商品上下架", PermissionConstant.PRODUCT_CATEGORY_ONOFFLINE),
    //商品管理
    PRODUCT_MANAGEMENT(205, 2, "商品管理", PermissionConstant.PRODUCT_MANAGEMENT),
    //商品添加
    PRODUCT_ADD(206, 2, "商品添加", PermissionConstant.PRODUCT_ADD),
    //商品修改
    PRODUCT_EDIT(207, 2, "商品修改", PermissionConstant.PRODUCT_EDIT),
    //商品删除
    PRODUCT_DELETE(208, 2, "商品删除", PermissionConstant.PRODUCT_DELETE),
    //商品上下架
    PRODUCT_ONOFFLINE(209, 2, "商品上下架", PermissionConstant.PRODUCT_ONOFFLINE),
    //商品价格管理
    PRODUCT_PRICE_MANAGEMENT(210, 2, "商品价格管理", PermissionConstant.PRODUCT_PRICE_MANAGEMENT),
    //商品价格编辑
    PRODUCT_PRICE_EDIT(211, 2, "商品价格编辑", PermissionConstant.PRODUCT_PRICE_EDIT),
    //商品价格删除
    PRODUCT_PRICE_DELETE(212, 2, "商品价格删除", PermissionConstant.PRODUCT_PRICE_DELETE),
    //商品价格生效失效
    PRODUCT_PRICE_EFFECT(2132, 2, "商品价格生效失效", PermissionConstant.PRODUCT_PRICE_EFFECT),

    /***************************订单相关**************************************/
    //订单管理
    ORDER_MANAGEMENT(3, 0, "订单管理", PermissionConstant.ORDER_MANAGEMENT),
    //订单查询
    ORDER_SEARCH(301, 3, "订单查询", PermissionConstant.ORDER_SEARCH),
    //订单预约
    ORDER_RESERVATION(301, 3, "订单预约", PermissionConstant.ORDER_RESERVATION),
    //订单修改
    ORDER_EDIT(302, 3, "订单修改", PermissionConstant.ORDER_EDIT),
    //订单完成
    ORDER_COMPLETE(303, 3, "订单完成", PermissionConstant.ORDER_COMPLETE),
    //订单追加
    ORDER_APPEND(304, 3, "订单追加", PermissionConstant.ORDER_APPEND),
    //订单审核
    ORDER_CHECK(305, 3, "订单审核", PermissionConstant.ORDER_CHECK),
    //订单取消
    ORDER_CANCEL(306, 3, "订单取消", PermissionConstant.ORDER_CANCEL),

    /***************************项目相关**************************************/
    //项目管理
    PROJECT_MANAGEMENT(4, 0, "项目管理", PermissionConstant.PROJECT_MANAGEMENT),
    //项目查询
    PROJECT_SEARCH(401, 4, "项目查询", PermissionConstant.PROJECT_SEARCH),
    //项目添加
    PROJECT_ADD(402, 4, "项目添加", PermissionConstant.PROJECT_ADD),
    //项目修改
    PROJECT_EDIT(403, 4, "项目修改", PermissionConstant.PROJECT_EDIT),
    //项目删除
    PROJECT_DELETE(404, 4, "项目删除", PermissionConstant.PROJECT_DELETE),
    //项目开始
    PROJECT_STATUS_CHANGE(405, 4, "项目开始、结束", PermissionConstant.PROJECT_STATUS_CHANGE),

    /***************************消费记录相关相关**************************************/
    //消费记录管理
    AUDIT_RECORD_MANAGEMENT(5, 0, "消费记录管理", PermissionConstant.AUDIT_RECORD_MANAGEMENT),
    //消费记录审核
    AUDIT_RECORD_CHECK(501, 5, "消费记录审核", PermissionConstant.AUDIT_RECORD_CHECK),

    /***************************查询模块相关**************************************/
    //信息查询
    INFO_SEARCH(6, 0, "信息查询", PermissionConstant.INFO_SEARCH),
    //积分卡兑换码查询
    POINT_CODE_SEARCH_PAGE(601, 6, "积分卡兑换码查询", PermissionConstant.POINT_CODE_SEARCH),
    //兑换码查询
    POINT_CODE_SEARCH_SEARCH(60101, 601, "兑换码查询", PermissionConstant.POINT_CODE_SEARCH),
    //兑换码修改
    POINT_CODE_SEARCH_EDIT(60102, 601, "兑换码修改", PermissionConstant.POINT_CODE_SEARCH_EDIT),
    //兑换码启、停用
    POINT_CODE_SEARCH_STATUS_CHANGE(60103, 601, "兑换码启、停用", PermissionConstant.POINT_CODE_SEARCH_STATUS_CHANGE),
    //积分卡查询
    POINT_SEARCH_PAGE(602, 6, "积分卡查询", PermissionConstant.POINT_SEARCH),
    //积分卡查询
    POINT_SEARCH(60201, 602, "积分卡查询", PermissionConstant.POINT_SEARCH),
    //积分卡修改
    POINT_SEARCH_EDIT(60202, 602, "积分卡修改", PermissionConstant.POINT_SEARCH_EDIT),
    //积分卡启、停用
    POINT_SEARCH_STATUS_CHANGE(60203, 602, "积分卡启、停用", PermissionConstant.POINT_SEARCH_STATUS_CHANGE),
    //充值卡兑换码查询
    RECHARGE_CARD_CODE_SEARCH_PAGE(603, 6, "充值卡兑换码查询", PermissionConstant.RECHARGE_CARD_CODE_SEARCH),
    //兑换码查询
    RECHARGE_CARD_CODE_SEARCH(60301, 603, "兑换码查询", PermissionConstant.RECHARGE_CARD_CODE_SEARCH),
    //兑换码修改
    RECHARGE_CARD_CODE_SEARCH_EDIT(60302, 603, "兑换码修改", PermissionConstant.RECHARGE_CARD_CODE_SEARCH_EDIT),
    //兑换码启、停用
    RECHARGE_CARD_CODE_SEARCH_STATUS_CHANGE(60303, 603, "兑换码启、停用", PermissionConstant.RECHARGE_CARD_CODE_SEARCH_STATUS_CHANGE),
    //充值卡查询
    RECHARGE_CARD_SEARCH_PAGE(604, 6, "充值卡查询", PermissionConstant.RECHARGE_CARD_SEARCH),
    //充值卡查询
    RECHARGE_CARD_SEARCH(60401, 604, "充值卡查询", PermissionConstant.RECHARGE_CARD_SEARCH),
    //充值卡修改
    RECHARGE_CARD_SEARCH_EDIT(60402, 604, "充值卡修改", PermissionConstant.RECHARGE_CARD_SEARCH_EDIT),
    //充值卡启、停用
    RECHARGE_CARD_SEARCH_STATUS_CHANGE(60403, 604, "充值卡启、停用", PermissionConstant.RECHARGE_CARD_SEARCH_STATUS_CHANGE),
    //代金券兑换码查询
    VOUCHER_CODE_SEARCH_PAGE(605, 6, "代金券兑换码查询", PermissionConstant.VOUCHER_CODE_SEARCH),
    //兑换码查询
    VOUCHER_CODE_SEARCH(60501, 605, "兑换码查询", PermissionConstant.VOUCHER_CODE_SEARCH),
    //代金券兑换码修改
    VOUCHER_CODE_SEARCH_EDIT(60502, 605, "兑换码修改", PermissionConstant.VOUCHER_CODE_SEARCH_EDIT),
    //代金券兑换码启、停用
    VOUCHER_CODE_SEARCH_STATUS_CHANGE(60503, 605, "兑换码启、停用", PermissionConstant.VOUCHER_CODE_SEARCH_STATUS_CHANGE),
    //代金券查询
    VOUCHER_SEARCH_PAGE(606, 6, "代金券查询", PermissionConstant.VOUCHER_SEARCH),
    //代金券查询
    VOUCHER_SEARCH(60601, 606, "代金券查询", PermissionConstant.VOUCHER_SEARCH),
    //代金券修改
    VOUCHER_SEARCH_EDIT(60602, 606, "代金券修改", PermissionConstant.VOUCHER_SEARCH_EDIT),
    //代金券启、停用
    VOUCHER_SEARCH_STATUS_CHANGE(60603, 606, "代金券启、停用", PermissionConstant.VOUCHER_SEARCH_STATUS_CHANGE),


    /***************************系统管理相关**************************************/
    //系统管理
    SYSTEM_MANAGEMENT(7, 0, "系统管理", PermissionConstant.SYSTEM_MANAGEMENT),
    //积分卡兑换码批次管理
    POINT_BATCH_MANAGEMENT(701, 7, "积分卡兑换码批次管理", PermissionConstant.POINT_BATCH_MANAGEMENT),
    //积分卡兑换码批次查询
    POINT_BATCH_SEARCH(70101, 701, "积分卡兑换码批次查询", PermissionConstant.POINT_BATCH_SEARCH),
    //积分卡兑换码批次添加
    POINT_BATCH_ADD(70102, 701, "积分卡兑换码批次添加", PermissionConstant.POINT_BATCH_ADD),
    //积分卡兑换码批次修改
    POINT_BATCH_EDIT(70103, 701, "积分卡兑换码批次修改", PermissionConstant.POINT_BATCH_EDIT),
    //积分卡兑换码批次启、停用
    POINT_BATCH_STATUS_CHANGE(70104, 701, "积分卡兑换码批次启、停用", PermissionConstant.POINT_BATCH_STATUS_CHANGE),
    //积分卡兑换码生成
    POINT_CODE_GENERATE(70105, 701, "积分卡兑换码生成", PermissionConstant.POINT_CODE_GENERATE),
    //积分卡兑换码导出
    POINT_CODE_EXPORT(70106, 701, "积分卡兑换码导出", PermissionConstant.POINT_CODE_EXPORT),
    //积分卡兑换码管理
    POINT_CODE_MANAGEMENT(70107, 701, "积分卡兑换码管理", PermissionConstant.POINT_CODE_MANAGEMENT),
    //积分卡兑换码修改
    POINT_CODE_EDIT(70108, 701, "积分卡兑换码修改", PermissionConstant.POINT_CODE_EDIT),
    //积分卡兑换码启、停用
    POINT_CODE_STATUS_CHANGE(70109, 701, "积分卡兑换码启、停用", PermissionConstant.POINT_CODE_STATUS_CHANGE),
    //充值卡兑换码批次管理
    RC_BATCH_MANAGEMENT(702, 7, "充值卡兑换码批次管理", PermissionConstant.RC_BATCH_MANAGEMENT),
    //充值卡兑换码批次查询
    RC_BATCH_SEARCH(70201, 702, "充值卡兑换码批次查询", PermissionConstant.RC_BATCH_SEARCH),
    //充值卡兑换码批次添加
    RC_BATCH_ADD(70202, 702, "充值卡兑换码批次添加", PermissionConstant.RC_BATCH_ADD),
    //充值卡兑换码批次修改
    RC_BATCH_EDIT(70203, 702, "充值卡兑换码批次修改", PermissionConstant.RC_BATCH_EDIT),
    //充值卡兑换码批次启、停用
    RC_BATCH_STATUS_CHANGE(70204, 702, "充值卡兑换码批次启、停用", PermissionConstant.RC_BATCH_STATUS_CHANGE),
    //充值卡兑换码生成
    RC_CODE_GENERATE(70205, 702, "充值卡兑换码生成", PermissionConstant.RC_CODE_GENERATE),
    //充值卡兑换码导出
    RC_CODE_EXPORT(70206, 702, "充值卡兑换码导出", PermissionConstant.RC_CODE_EXPORT),
    //充值卡兑换码管理
    RC_CODE_MANAGEMENT(70207, 702, "充值卡兑换码管理", PermissionConstant.RC_CODE_MANAGEMENT),
    //充值卡兑换码修改
    RC_CODE_EDIT(70208, 702, "充值卡兑换码修改", PermissionConstant.RC_CODE_EDIT),
    //积分卡兑换码启、停用
    RC_CODE_STATUS_CHANGE(70209, 702, "充值卡兑换码启、停用", PermissionConstant.RC_CODE_STATUS_CHANGE),
    //代金券兑换码批次管理
    VOUCHER_BATCH_MANAGEMENT(703, 7, "代金券兑换码批次管理", PermissionConstant.VOUCHER_BATCH_MANAGEMENT),
    //代金券兑换码批次查询
    VOUCHER_BATCH_SEARCH(70301, 703, "代金券兑换码批次查询", PermissionConstant.VOUCHER_BATCH_SEARCH),
    //代金券兑换码批次添加
    VOUCHER_BATCH_ADD(70302, 703, "代金券兑换码批次添加", PermissionConstant.VOUCHER_BATCH_ADD),
    //代金券兑换码批次修改
    VOUCHER_BATCH_EDIT(70303, 703, "代金券兑换码批次修改", PermissionConstant.VOUCHER_BATCH_EDIT),
    //代金券兑换码批次启、停用
    VOUCHER_BATCH_STATUS_CHANGE(70304, 703, "代金券兑换码批次启、停用", PermissionConstant.VOUCHER_BATCH_STATUS_CHANGE),
    //代金券兑换码生成
    VOUCHER_CODE_GENERATE(70305, 703, "代金券兑换码生成", PermissionConstant.VOUCHER_CODE_GENERATE),
    //代金券兑换码导出
    VOUCHER_CODE_EXPORT(70306, 703, "代金券兑换码导出", PermissionConstant.VOUCHER_CODE_EXPORT),
    //代金券兑换码管理
    VOUCHER_CODE_MANAGEMENT(70307, 703, "代金券兑换码管理", PermissionConstant.VOUCHER_CODE_MANAGEMENT),
    //代金券兑换码修改
    VOUCHER_CODE_EDIT(70308, 703, "代金券兑换码修改", PermissionConstant.VOUCHER_CODE_EDIT),
    //代金券兑换码启、停用
    VOUCHER_CODE_STATUS_CHANGE(70309, 703, "代金券兑换码启、停用", PermissionConstant.VOUCHER_CODE_STATUS_CHANGE),
    //电子商品码管理
    ELE_CODE_MANAGEMENT(704, 7, "电子码商品管理", PermissionConstant.ELE_CODE_MANAGEMENT),
    //电子商品码查询
    ELE_CODE_SEARCH(70401, 704, "电子码商品查询", PermissionConstant.ELE_CODE_SEARCH),
    //电子商品码添加
    ELE_CODE_ADD(70402, 704, "电子商品码添加", PermissionConstant.ELE_CODE_ADD),
    //电子商品码修改
    ELE_CODE_EDIT(704032, 704, "电子商品码修改", PermissionConstant.ELE_CODE_EDIT),
    //员工管理
    USER_MANAGEMENT(705, 7, "员工管理", PermissionConstant.USER_MANAGEMENT),
    //员工查询
    USER_SEARCH(70501, 7, "员工查询", PermissionConstant.USER_SEARCH),
    //员工添加
    USER_ADD(70502, 705, "员工添加", PermissionConstant.USER_ADD),
    //员工修改
    USER_EDIT(70503, 705, "员工修改", PermissionConstant.USER_EDIT),
    //员工删除
    USER_DELETE(70504, 705, "员工删除", PermissionConstant.USER_DELETE),
    //员工密码重置
    USER_PASSWORD_RESET(70505, 705, "员工密码重置", PermissionConstant.USER_PASSWORD_RESET),
    //角色管理
    ROLE_MANAGEMENT(706, 7, "角色管理", PermissionConstant.ROLE_MANAGEMENT),
    //角色查询
    ROLE_SEARCH(70601, 706, "角色查询", PermissionConstant.ROLE_SEARCH),
    //角色添加
    ROLE_ADD(70602, 706, "角色添加", PermissionConstant.ROLE_ADD),
    //角色修改
    ROLE_EDIT(70603, 706, "角色修改", PermissionConstant.ROLE_EDIT),
    //角色删除
    ROLE_DELETE(70604, 706, "角色删除", PermissionConstant.ROLE_DELETE),
    //城市管理
    CITY_MANAGEMENT(707, 7, "城市管理", PermissionConstant.CITY_MANAGEMENT),
    //日志管理
    LOG_MANAGEMENT(708, 7, "日志管理", PermissionConstant.LOG_MANAGEMENT),
    //商家订单管理
    PARTNER_ORDER_MANAGEMENT(8, 0, "商家订单管理", PermissionConstant.PARTNER_ORDER_MANAGEMENT),
    //商家订单查询
    PARTNER_ORDER_SEARCH(801, 8, "商家订单查询", PermissionConstant.PARTNER_ORDER_SEARCH),
    //商家订单导出
    PARTNER_ORDER_EXPORT(802,8,"商家订单导出",PermissionConstant.PARTNER_ORDER_EXPORT),
    //商家订单添加
    PARTNER_ORDER_ADD(803, 8, "商家订单添加", PermissionConstant.PARTNER_ORDER_ADD),
    //商家订单修改
    PARTNER_ORDER_EDIT(804, 8, "商家订单修改", PermissionConstant.PARTNER_ORDER_EDIT),
    //商家订单高级修改
    PARTNER_ORDER_HIGHER_EDIT(805, 8, "商家订单高级修改", PermissionConstant.PARTNER_ORDER_HIGHER_EDIT),
    //预支付记录查看
    ADVANCE_PAY_MANAGEMENT(806, 8, "预支付记录查看", PermissionConstant.ADVANCE_PAY_MANAGEMENT),
    //预支付记录添加
    ADVANCE_PAY_ADD(807, 8, "预支付记录添加", PermissionConstant.ADVANCE_PAY_ADD),

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
