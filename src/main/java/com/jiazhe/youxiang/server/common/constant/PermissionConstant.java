package com.jiazhe.youxiang.server.common.constant;

/**
 * @author TU
 * @description 权限字符串
 * @date 2018/12/6.
 */
public class PermissionConstant {

    //客户管理
    public static final String CUSTOMER_MANAGEMENT = "customerManagement";
    //客户查询
    public static final String CUSTOMER_SEARCH = "customerSearch";
    //客户添加
    public static final String CUSTOMER_ADD = "customerAdd";
    //客户修改
    public static final String CUSTOMER_EDIT = "customerEdit";
    //查看积分卡
    public static final String CUSTOMER_POINT_DETAIL = "customerPointDetail";
    //绑定积分卡
    public static final String CUSTOMER_POINT_BINDING = "customerPointBindig";
    //直接充值积分卡
    public static final String CUSTOMER_POINT_CHARGE = "customerPointCharge";
    //查看充值卡
    public static final String CUSTOMER_RECHARGE_CARD_DETAIL = "customerRechargeCardDetail";
    //绑定充值卡
    public static final String CUSTOMER_RECHARGE_CARD_BINDING = "customerRechargeCardBindig";
    //直接充值充值卡
    public static final String CUSTOMER_RECHARGE_CARD_CHARGE = "customerRechargeCardCharge";
    //查看代金券
    public static final String CUSTOMER_VOUCHER_DETAIL = "customerVoucherDetail";
    //绑定代金券
    public static final String CUSTOMER_VOUCHER_BINDING = "customerVoucherBinding";
    //查看客户订单
    public static final String CUSTOMER_ORDER_DETAIL = "customerOrderDetail";
    //后台下单
    public static final String CUSTOMER_USER_PLACE_ORDER = "customerUserPlaceOrder";

    //商品大类管理
    public static final String PRODUCT_CATEGORY_MANAGEMENT = "productCategoryManagement";
    //商品大类添加
    public static final String PRODUCT_CATEGORY_ADD = "productCategoryAdd";
    //商品大类修改
    public static final String PRODUCT_CATEGORY_EDIT = "productCategoryEdit";
    //商品大类删除
    public static final String PRODUCT_CATEGORY_DELETE = "productCategoryDelete";
    //商品上下架
    public static final String PRODUCT_CATEGORY_ONOFFLINE = "productCategoryOnOffLine";
    //商品管理
    public static final String PRODUCT_MANAGEMENT = "productManagement";
    //商品添加
    public static final String PRODUCT_ADD = "productAdd";
    //商品修改
    public static final String PRODUCT_EDIT = "productEdit";
    //商品删除
    public static final String PRODUCT_DELETE = "productDelete";
    //商品上下架
    public static final String PRODUCT_ONOFFLINE = "productOnOffLine";
    //商品价格管理
    public static final String PRODUCT_PRICE_MANAGEMENT = "productPriceManagement";
    //商品价格编辑
    public static final String PRODUCT_PRICE_EDIT = "productPriceEdit";
    //商品价格删除
    public static final String PRODUCT_PRICE_DELETE = "productPriceDelete";
    //商品价格生效失效
    public static final String PRODUCT_PRICE_EFFECT = "productPriceEffect";

    //商家订单管理
    public static final String PARTNER_ORDER_MANAGEMENT = "partnerOrderManagement";
    //商家订单查询
    public static final String PARTNER_ORDER_SEARCH = "partnerOrderSearch";
    //商家订单添加
    public static final String PARTNER_ORDER_ADD = "partnerOrderAdd";
    //商家订单修改
    public static final String PARTNER_ORDER_EDIT = "partnerOrderEdit";
    //商家订单高级修改
    public static final String PARTNER_ORDER_HIGHER_EDIT = "partnerOrderHigherEdit";
    //查看预支付记录
    public static final String ADVANCE_PAY_MANAGEMENT = "advancePayManagement";
    //添加预支付记录
    public static final String ADVANCE_PAY_ADD = "advancePayAdd";
    //商家订单导出
    public static final String PARTNER_ORDER_EXPORT = "partnerOrderExport";

    //订单管理
    public static final String ORDER_MANAGEMENT = "orderManagement";
    //订单查询
    public static final String ORDER_SEARCH = "orderSearch";
    //订单预约
    public static final String ORDER_RESERVATION = "orderReservation";
    //订单修改
    public static final String ORDER_EDIT = "orderEdit";
    //订单完成
    public static final String ORDER_COMPLETE = "orderComplete";
    //订单追加
    public static final String ORDER_APPEND = "orderAppend";
    //订单审核
    public static final String ORDER_CHECK = "orderCheck";
    //订单取消
    public static final String ORDER_CANCEL = "orderCancel";


    //项目管理
    public static final String PROJECT_MANAGEMENT = "projectManagement";
    //项目查询
    public static final String PROJECT_SEARCH = "projectSearch";
    //项目添加
    public static final String PROJECT_ADD = "projectAdd";
    //项目修改
    public static final String PROJECT_EDIT = "projectEdit";
    //项目删除
    public static final String PROJECT_DELETE = "projectDelete";
    //项目开始、结束
    public static final String PROJECT_STATUS_CHANGE = "projectStatusChange";

    //消费记录管理
    public static final String AUDIT_RECORD_MANAGEMENT = "auditRecordManagement";
    //消费记录查询
    public static final String AUDIT_RECORD_SEARCH = "auditRecordSearch";
    //消费记录审核
    public static final String AUDIT_RECORD_CHECK = "auditRecordCheck";
    //凭证管理
    public static final String CHARGE_RECEIPT_MANAGEMENT = "chargeReceiptManagement";
    //凭证导出
    public static final String CHARGE_RECEIPT_EXPORT = "chargeReceiptExport";
    //凭证添加
    public static final String CHARGE_RECEIPT_ADD = "chargeReceiptAdd";
    //凭证删除
    public static final String CHARGE_RECEIPT_DELETE = "chargeReceiptDelete";
    //凭证修改
    public static final String CHARGE_RECEIPT_EDIT = "chargeReceiptEdit";
    //完成凭证录入
    public static final String COMPLETE_CHARGE_RECEIPT = "completeChargeReceipt";
    //重置凭证录入未完成
    public static final String UNCOMPLETE_CHARGE_RECEIPT = "uncompleteChargeReceipt";

    //信息查询
    public static final String INFO_SEARCH = "infoSearch";
    //积分卡查询
    public static final String POINT_SEARCH = "pointSearch";
    //积分卡修改
    public static final String POINT_SEARCH_EDIT = "pointSearchEdit";
    //积分卡启、停用
    public static final String POINT_SEARCH_STATUS_CHANGE = "pointSearchStatusChange";
    //积分卡兑换码查询
    public static final String POINT_CODE_SEARCH = "pointCodeSearch";
    //积分卡兑换码修改
    public static final String POINT_CODE_SEARCH_EDIT = "pointCodeSearchEdit";
    //积分卡兑换码启、停用
    public static final String POINT_CODE_SEARCH_STATUS_CHANGE = "pointCodeSearchStatusChange";
    //充值卡查询
    public static final String RECHARGE_CARD_SEARCH = "rechargeCardSearch";
    //充值卡修改
    public static final String RECHARGE_CARD_SEARCH_EDIT = "rechargeCardSearchEdit";
    //充值卡启、停用
    public static final String RECHARGE_CARD_SEARCH_STATUS_CHANGE = "rechargeCardSearchStatusChange";
    //充值卡兑换码查询
    public static final String RECHARGE_CARD_CODE_SEARCH = "rechargeCardCodeSearch";
    //充值卡兑换码修改
    public static final String RECHARGE_CARD_CODE_SEARCH_EDIT = "rechargeCardCodeSearchEdit";
    //充值卡兑换码启、停用
    public static final String RECHARGE_CARD_CODE_SEARCH_STATUS_CHANGE = "rechargeCardCodeSearchStatusChange";
    //代金券查询
    public static final String VOUCHER_SEARCH = "voucherSearch";
    //代金券修改
    public static final String VOUCHER_SEARCH_EDIT = "voucherSearchEdit";
    //代金券启、停用
    public static final String VOUCHER_SEARCH_STATUS_CHANGE = "voucherSearchStatusChange";
    //代金券兑换码查询
    public static final String VOUCHER_CODE_SEARCH = "voucherCodeSearch";
    //代金券兑换码修改
    public static final String VOUCHER_CODE_SEARCH_EDIT = "voucherCodeSearchEdit";
    //代金券兑换码启、停用
    public static final String VOUCHER_CODE_SEARCH_STATUS_CHANGE = "voucherCodeSearchStatusChange";

    //短信管理
    public static final String MESSAGE_MANAGEMENT = "messageManagement";
    //单条发送
    public static final String MESSAGE_SINGLE_SEND = "messageSingleSend";
    //多条发送
    public static final String MESSAGE_BATCH_SEND = "messageBatchSend";
    //短信重发
    public static final String MESSAGE_RESEND = "messageResend";
    //短信查看
    public static final String MESSAGE_SHOW = "messageShow";

    //物料管理
    public static final String MATERIAL_MANAGEMENT = "materialManagement";
    //转账明细
    public static final String MATERIAL_TRANSFER_MONEY_DETAIL = "materialTransferMoneyDetail";
    //消耗明细
    public static final String MATERIAL_USED_DETAIL = "materialUsedDetail";
    //添加转账
    public static final String MATERIAL_TRANSFER_MONEY_ADD = "materialTransferMoneyAdd";
    //修改转账
    public static final String MATERIAL_TRANSFER_MONEY_EDIT = "materialTransferMoneyEdit";
    //删除转账
    public static final String MATERIAL_TRANSFER_MONEY_DELETE = "materialTransferMoneyDelete";

    //系统管理
    public static final String SYSTEM_MANAGEMENT = "systemManagement";
    //积分卡兑换码批次管理
    public static final String POINT_BATCH_MANAGEMENT = "pointBatchManagement";
    //积分卡兑换码批次查询
    public static final String POINT_BATCH_SEARCH = "pointBatchSearch";
    //积分卡兑换码批次添加
    public static final String POINT_BATCH_ADD = "pointBatchAdd";
    //积分卡兑换码批次修改
    public static final String POINT_BATCH_EDIT = "pointBatchEdit";
    //积分卡兑换码批次启、停用
    public static final String POINT_BATCH_STATUS_CHANGE = "pointBatchStatusChange";
    //积分卡兑换码生成
    public static final String POINT_CODE_GENERATE = "pointCodeGenerate";
    //积分卡兑换码导出
    public static final String POINT_CODE_EXPORT = "pointCodeExport";
    //积分卡兑换码管理
    public static final String POINT_CODE_MANAGEMENT = "pointCodeManagement";
    //积分卡兑换码修改
    public static final String POINT_CODE_EDIT = "pointCodeEdit";
    //积分卡兑换码启、停用
    public static final String POINT_CODE_STATUS_CHANGE = "pointCodeStatusChange";
    //启、停用所有积分卡兑换码（包括已经兑换为的积分卡）
    public static final String ALL_POINT_CODE_STATUS_CHANGE = "allPointCodeStatusChange";
    //充值卡兑换码批次管理
    public static final String RC_BATCH_MANAGEMENT = "rcBatchManagement";
    //充值卡兑换码批次查询
    public static final String RC_BATCH_SEARCH = "rcBatchSearch";
    //充值卡兑换码批次添加
    public static final String RC_BATCH_ADD = "rcBatchAdd";
    //充值卡兑换码批次修改
    public static final String RC_BATCH_EDIT = "rcBatchEdit";
    //充值卡兑换码批次启、停用
    public static final String RC_BATCH_STATUS_CHANGE = "rcBatchStatusChange";
    //充值卡兑换码生成
    public static final String RC_CODE_GENERATE = "rcCodeGenerate";
    //充值卡兑换码导出
    public static final String RC_CODE_EXPORT = "rcCodeExport";
    //充值卡兑换码管理
    public static final String RC_CODE_MANAGEMENT = "rcCodeManagement";
    //充值卡兑换码修改
    public static final String RC_CODE_EDIT = "rcCodeEdit";
    //充值卡兑换码启、停用
    public static final String RC_CODE_STATUS_CHANGE = "rcCodeStatusChange";
    //启、停用所有充值卡兑换码（包括已经兑换为的充值卡）
    public static final String ALL_RC_CODE_STATUS_CHANGE = "allRCCodeStatusChange";
    //代金券兑换码批次管理
    public static final String VOUCHER_BATCH_MANAGEMENT = "voucherBatchManagement";
    //代金券兑换码批次查询
    public static final String VOUCHER_BATCH_SEARCH = "voucherBatchSearch";
    //代金券兑换码批次添加
    public static final String VOUCHER_BATCH_ADD = "voucherBatchAdd";
    //代金券兑换码批次修改
    public static final String VOUCHER_BATCH_EDIT = "voucherBatchEdit";
    //代金券兑换码批次启、停用
    public static final String VOUCHER_BATCH_STATUS_CHANGE = "voucherBatchStatusChange";
    //代金券兑换码生成
    public static final String VOUCHER_CODE_GENERATE = "voucherCodeGenerate";
    //代金券兑换码导出
    public static final String VOUCHER_CODE_EXPORT = "voucherCodeExport";
    //代金券兑换码管理
    public static final String VOUCHER_CODE_MANAGEMENT = "voucherCodeManagement";
    //代金券兑换码修改
    public static final String VOUCHER_CODE_EDIT = "voucherCodeEdit";
    //代金券兑换码启、停用
    public static final String VOUCHER_CODE_STATUS_CHANGE = "voucherCodeStatusChange";
    //启、停用所有代金券兑换码（包括已经兑换为的代金券）
    public static final String ALL_VOUCHER_CODE_STATUS_CHANGE = "allVoucherCodeStatusChange";
    //电子商品码管理
    public static final String ELE_CODE_MANAGEMENT = "eleCodeManagement";
    //电子商品码查询
    public static final String ELE_CODE_SEARCH = "eleCodeSearch";
    //电子商品添加
    public static final String ELE_CODE_ADD = "eleCodeAdd";
    //电子商品修改
    public static final String ELE_CODE_EDIT = "eleCodeEdit";
    //员工管理
    public static final String USER_MANAGEMENT = "userManagement";
    //员工查询
    public static final String USER_SEARCH = "userSearch";
    //员工添加
    public static final String USER_ADD = "userAdd";
    //员工修改
    public static final String USER_EDIT = "userEdit";
    //员工删除
    public static final String USER_DELETE = "userDelete";
    //员工密码重置
    public static final String USER_PASSWORD_RESET = "userPasswordReset";
    //角色管理
    public static final String ROLE_MANAGEMENT = "roleManagement";
    //角色查询
    public static final String ROLE_SEARCH = "roleSearch";
    //角色添加
    public static final String ROLE_ADD = "roleAdd";
    //角色修改
    public static final String ROLE_EDIT = "roleEdit";
    //角色删除
    public static final String ROLE_DELETE = "roleDelete";
    //城市管理
    public static final String CITY_MANAGEMENT = "cityManagement";
    //日志管理
    public static final String LOG_MANAGEMENT = "logManagement";

    //客户端访问固定权限
    public static final String CUSTOMER_PERMISSION = "customerPermission";

}
