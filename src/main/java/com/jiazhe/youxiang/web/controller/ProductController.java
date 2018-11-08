package com.jiazhe.youxiang.web.controller;

import com.jiazhe.youxiang.base.controller.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用于商品管理相关页面的转发
 * Created by tujia on 2018/10/13.
 */
@Controller
@RequestMapping("/product")
public class ProductController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    @RequestMapping(value = "/index")
    public String index() {
        return "product/productcategory/index";
    }
}
