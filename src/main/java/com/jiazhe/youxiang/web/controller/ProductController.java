package com.jiazhe.youxiang.web.controller;

import com.jiazhe.youxiang.base.controller.BaseController;
import com.jiazhe.youxiang.server.common.constant.PermissionConstant;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用于商品管理相关页面的转发
 * Created by tujia on 2018/10/13.
 */
@Controller
@RequestMapping("/product")
public class ProductController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    /**
     * 商品大类管理页面跳转
     * @return
     */
    @RequiresPermissions(PermissionConstant.PRODUCT_CATEGORY_MANAGEMENT)
    @RequestMapping(value = "/productcategory/index")
    public String productCategoryIndex() {
        return "product/category/index";
    }

    /**
     * 商品管理页面跳转
     * @return
     */
    @RequiresPermissions(PermissionConstant.PRODUCT_MANAGEMENT)
    @RequestMapping(value = "/product/index")
    public String productIndex(String productCategoryId, String productCategoryName, Model model) {
        model.addAttribute("productCategoryId", productCategoryId);
        model.addAttribute("productCategoryName", productCategoryName);
        return "product/product/index";
    }

    /**
     * 商品价格管理页面跳转
     * @return
     */
    @RequiresPermissions(PermissionConstant.PRODUCT_PRICE_MANAGEMENT)
    @RequestMapping(value = "/productprice/index")
    public String productPriceIndex(String productId, String productName, String productCategoryId, String productCategoryName, Model model) {
        model.addAttribute("productId", productId);
        model.addAttribute("productName", productName);
        model.addAttribute("productCategoryId", productCategoryId);
        model.addAttribute("productCategoryName", productCategoryName);
        return "product/price/index";
    }
}
