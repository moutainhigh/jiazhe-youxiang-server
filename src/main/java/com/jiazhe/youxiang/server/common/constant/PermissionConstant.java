package com.jiazhe.youxiang.server.common.constant;

import com.google.common.collect.Lists;
import com.jiazhe.youxiang.server.common.enums.PermissionTreeEnum;
import com.jiazhe.youxiang.server.vo.resp.sysrole.PermissionTreeResp;

import java.util.List;

/**
 * @author TU
 * @description 权限字符串
 * @date 2018/12/6.
 */
public class PermissionConstant {

    public static List<PermissionTreeResp> treeRespList = Lists.newArrayList();

    static{
        for (PermissionTreeEnum temp : PermissionTreeEnum.values()) {
            PermissionTreeResp tree = new PermissionTreeResp();
            tree.setId(temp.getId());
            tree.setpId(temp.getpId());
            tree.setName(temp.getName());
            tree.setPerm(temp.getPerm());
            treeRespList.add(tree);
        }
    }

    //客户管理
    public static String CUSTOMER_MANAGEMENT = "customerManagement";
    //添加客户
    public static String CUSTOMER_ADD = "customerAdd";
    //修改客户
    public static String CUSTOMER_EDIT = "customerEdit";
    //查看客户充值卡
    public static String CUSTOMER_RECHARGE_CARD_DETAIL = "customerRechargeCardDetail";
    //绑定充值卡
    public static String CUSTOMER_RECHARGE_CARD_BINDING = "customerRechargeCardBindig";
    //直接充值
    public static String CUSTOMER_RECHARGE_CARD_RECHARGE = "customerRechargeCardRecharge";
    //查看客户代金券
    public static String CUSTOMER_VOUCHER_DETAIL = "customerVoucherDetail";
    //绑定代金券
    public static String CUSTOMER_VOUCHER_BINDING = "customerVoucherBinding";
    //查看客户订单
    public static String CUSTOMER_ORDER_DETAIL = "customerOrderDetail";
    //后台下单
    public static String CUSTOMER_USER_PLACE_ORDER = "customerUserPlaceOrder";
    //商品大类管理
    public static String PRODUCT_CATEGORY_MANAGEMENT = "productCategoryManagement";
    //商品大类添加
    public static String PRODUCT_CATEGORY_ADD= "productCategoryAdd";
    //商品大类修改
    public static String PRODUCT_CATEGORY_EDIT = "productCategoryEdit";
    //商品大类删除
    public static String PRODUCT_CATEGORY_DELETE= "productCategoryDelete";
    //商品上下架
    public static String PRODUCT_CATEGORY_ONOFFLINE = "productCategoryOnOffLine";
    //商品管理
    public static String PRODUCT_MANAGEMENT = "productManagement";
    //商品添加
    public static String PRODUCT_ADD= "productAdd";
    //商品修改
    public static String PRODUCT_EDIT = "productEdit";
    //商品删除
    public static String PRODUCT_DELETE= "productDelete";
    //商品上下架
    public static String PRODUCT_ONOFFLINE = "productOnOffLine";
    //商品价格管理
    public static String PRODUCT_PRICE_MANAGEMENT = "productPriceManagement";
    //商品价格编辑
    public static String PRODUCT_PRICE_EDIT = "productPriceEdit";
    //商品价格生效失效
    public static String PRODUCT_PRICE_EFFECT = "productPriceEffect";
    //订单管理
    public static String ORDER_MANAGEMENT = "orderManagement";
    //订单预约
    public static String ORDER_RESERVATION = "orderReservation";
    //订单修改
    public static String ORDER_EDIT = "orderEdit";
    //订单完成
    public static String ORDER_COMPLETE = "orderComplete";
    //订单追加
    public static String ORDER_APPEND = "orderAppend";
    //订单审核
    public static String ORDER_CHECK = "orderCheck";
    //订单取消
    public static String ORDER_CANCEL = "orderCancel";
    //项目管理
    public static String PROJECT_MANAGEMENT = "projectManagement";
    //项目添加
    public static String PROJECT_ADD = "projectAdd";
    //项目修改
    public static String PROJECT_EDIT = "projectEdit";
    //项目删除
    public static String PROJECT_DELETE= "projectDelete";
    //项目开始
    public static String PROJECT_START = "projectStart";
    //项目结束
    public static String PROJECT_STOP = "projectStop";
    //消费记录管理
    public static String AUDIT_RECORD_MANAGEMENT = "auditRecordManagement";
    //消费记录审核
    public static String AUDIT_RECORD_CHECK = "auditRecordCheck";
    //信息查询
    public static String INFO_SEARCH = "infoSearch";
    //充值卡查询
    public static String RECHARGE_CARD_SEARCH = "rechargeCardSearch";
    //充值卡修改
    public static String RECHARGE_CARD_SEARCH_EDIT = "rechargeCardSearchEdit";
    //充值卡兑换码查询
    public static String RECHARGE_CARD_CODE_SEARCH = "rechargeCardCodeSearch";
    //充值卡兑换码修改
    public static String RECHARGE_CARD_CODE_SEARCH_EDIT = "rechargeCardCodeSearchEdit";
    //代金券查询
    public static String VOUCHER_SEARCH = "voucherSearch";
    //代金券修改
    public static String VOUCHER_SEARCH_EDIT = "voucherSearchEdit";
    //代金券兑换码查询
    public static String VOUCHER_CODE_SEARCH = "voucherCodeSearch";
    //代金券兑换码修改
    public static String VOUCHER_CODE_SEARCH_EIDT = "voucherCodeSearchEdit";
    //系统管理
    public static String SYSTEM_MANAGEMENT = "systemManagement";
    //充值卡兑换码批次管理
    public static String RC_BATCH_MANAGEMENT = "rcBatchManagement";
    //充值卡兑换码批次添加
    public static String RC_BATCH_ADD = "rcBatchAdd";
    //充值卡兑换码批次修改
    public static String RC_BATCH_EDIT = "rcBatchEdit";
    //充值卡兑换码管理
    public static String RC_CODE_MANAGEMENT = "rcCodeManagement";
    //充值卡兑换码修改
    public static String RC_CODE_EDIT = "rcCodeEdit";
    //代金券兑换码批次管理
    public static String VOUCHER_BATCH_MANAGEMENT = "voucherBatchManagement";
    //代金券兑换码批次添加
    public static String VOUCHER_BATCH_ADD = "voucherBatchAdd";
    //代金券兑换码批次修改
    public static String VOUCHER_BATCH_EDIT = "voucherBatchEdit";
    //代金券兑换码管理
    public static String VOUCHER_CODE_MANAGEMENT = "voucherCodeManagement";
    //代金券兑换码修改
    public static String VOUCHER_CODE_EDIT = "voucherCodeEdit";
    //电子商品管理
    public static String ELE_CODE_MANAGEMENT = "eleCodeManagement";
    //电子商品添加
    public static String ELE_CODE_ADD = "eleCodeAdd";
    //电子商品修改
    public static String ELE_CODE_EDIT = "eleCodeEdit";
    //员工管理
    public static String USER_MANAGEMENT = "userManagement";
    //角色管理
    public static String ROLE_MANAGEMENT = "roleManagement";
    //城市管理
    public static String CITY_MANAGEMENT = "cityManagement";
    //日志管理
    public static String LOG_MANAGEMENT = "logManagement";

}