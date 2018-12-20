package cn.org.meteor.comp.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * *************************************************************************
 * <p/>
 *
 * @文件名称: SessionConfiguration.java
 * @包 路 径： cn.org.meteor.comp.interceptor
 * @版权所有：北京数字认证股份有限公司 (C) 2018
 * @类描述: 登录拦截器
 * @版本: V1.0 @创建人：wangyangyang
 * @创建时间：2018/12/3 14:36
 */
public class LoginInterceptor implements HandlerInterceptor {
    private String login = "/login";
    private String error = "/error";
    private String limit = "/limit";
    private String push = "/push";
    private String getData = "/getData";
    private String swagger = "swagger";
    private String docs = "api-docs";
    private String undefined = "undefined";
    private String passwordRegister = "passwordRegister";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        String requestUrl = request.getRequestURL().toString();
        if (requestUrl.contains(login) || requestUrl.contains(limit) || requestUrl.contains(push) ||
                requestUrl.contains(getData) || requestUrl.contains(swagger) ||
                requestUrl.contains(docs) || requestUrl.contains(docs) || requestUrl.contains("passwordRegister")) {
            return true;
        }
        //检查session中是否有用户
         Object sessionUser = request.getSession().getAttribute("session_user");
        if (sessionUser == null) {
            response.sendRedirect("login/loginView");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
