package com.jiazhe.youxiang.base.realm;

/**
 * Created by TU on 2018/9/1.
 * 处理未登录状态下的ajax请求，或页面跳转请求的异常处理【跳转到登陆页面】
 */
import com.jiazhe.youxiang.base.util.ProjectUtil;
import com.jiazhe.youxiang.base.util.ResponseUtil;
import com.jiazhe.youxiang.base.util.ResultPackage;
import net.sf.json.JSONObject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ShiroLoginFilter extends FormAuthenticationFilter {
    /**
     * 在访问controller前判断是否登录，返回json，不进行重定向。
     * @param request
     * @param response
     * @return true-继续往下执行，false-该filter过滤器已经处理，不继续执行其他过滤器
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        if (ProjectUtil.isAjax(request)) {
            System.out.println("ShiroLoginFilter：ajax请求，未登录");
            ResponseUtil.responseUtils(httpServletResponse, ResultPackage.resultPackage("000001",new JSONObject(),"未登录"));
        } else {
            /**
             * @Mark 非ajax请求重定向为登录页面
             * 由于是iframe里的请求，所以需要通过js来跳转到父层页面，用_parent来指定
             */
            System.out.println("ShiroLoginFilter：页面跳转，未登录");
            PrintWriter out = response.getWriter();
            out.println("<html>");
            out.println("<script>");
            out.println("window.openCity ('/"+ProjectUtil.projectName+"/system/index','_parent')");
            out.println("</script>");
            out.println("</html>");
        }
        return false;
    }
}
