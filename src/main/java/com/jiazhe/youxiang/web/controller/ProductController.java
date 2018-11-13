package com.jiazhe.youxiang.web.controller;

import com.jiazhe.youxiang.base.controller.BaseController;
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

    @RequestMapping(value = "/productcategory/index")
    public String productCategoryIndex() {
        return "product/productcategory/index";
    }

    @RequestMapping(value = "/product/index")
    public String productIndex(String productCategoryId, String productCategoryName, Model model) {
        model.addAttribute("productCategoryId", productCategoryId);
        model.addAttribute("productCategoryName", productCategoryName);
        return "product/product/index";
    }
}
