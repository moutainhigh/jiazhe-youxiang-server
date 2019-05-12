package com.jiazhe.youxiang.web.controller;

import com.jiazhe.youxiang.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author tu
 * @description：物料管理页面跳转
 * @date 2019-05-12
 */
@Controller
@RequestMapping("/material")
public class MaterialController extends BaseController {

    @RequestMapping(value = "/index")
    public String index() {
        return "material/index";
    }
}
