package com.jiazhe.youxiang.base.realm;

/**
 * Created by TU on 2018/9/1.
 * 处理未登录状态下的ajax请求，或页面跳转请求的异常处理【跳转到登陆页面】
 */

import com.jiazhe.youxiang.base.util.ProjectUtil;
import com.jiazhe.youxiang.server.common.enums.LoginCodeEnum;
import net.sf.json.JSONObject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ShiroLoginFilter extends FormAuthenticationFilter {

    private static final Logger logger = LoggerFactory.getLogger(ShiroLoginFilter.class);

    /**
     * 在访问controller前判断是否登录，返回json，不进行重定向。
     *
     * @param request
     * @param response
     * @return true-继续往下执行，false-该filter过滤器已经处理，不继续执行其他过滤器
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
        if (ProjectUtil.isPageJump(request)) {
            /**
             * @Mark 非ajax请求重定向为登录页面
             * 由于是iframe里的请求，所以需要通过js来跳转到父层页面，用_parent来指定
             */
            logger.info("ShiroLoginFilter：页面跳转，未登录");
            PrintWriter out = response.getWriter();
            out.println("<html>");
            out.println("<script>");
            out.println("window.open('" + "/system/index','_parent')");
            out.println("</script>");
            out.println("</html>");
        } else {
            logger.info("ShiroLoginFilter：ajax请求，未登录");
            JSONObject obj = new JSONObject();
            obj.put("code", LoginCodeEnum.LOGIN_NOT_SIGNIN_IN.getCode());
            obj.put("type", LoginCodeEnum.LOGIN_NOT_SIGNIN_IN.getType());
            obj.put("message", LoginCodeEnum.LOGIN_NOT_SIGNIN_IN.getMessage());
            JSONObject result = new JSONObject();
            result.put("error", obj);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().write(result.toString());
        }
        return false;
    }
}
