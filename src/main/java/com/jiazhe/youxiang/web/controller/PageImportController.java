package com.jiazhe.youxiang.web.controller;

import com.jiazhe.youxiang.base.controller.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author TU
 * @description 用于请求公共modal
 * @date 2018/11/7.
 */
@Controller
@RequestMapping("/pageimport")
public class PageImportController extends BaseController{

    private static final Logger LOGGER = LoggerFactory.getLogger(PageImportController.class);
    /**
     *选择城市和商品页面
     */
    @RequestMapping(value = "/selectcityandproduct")
    public String selectCitys() {
        return "system/share/selectCityAndProduct";
    }


}
