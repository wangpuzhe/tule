package com.kiah.tule.Interceptor;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author kiah
 * @version Version 1.0
 * @date 2018/12/1
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        if(request.getRequestURI().endsWith("loginByName")||request.getRequestURI().endsWith("register")){
            return true;
        }else{
            HttpSession session = request.getSession();
            boolean isLogin = session.getAttribute("isLogin")==null?false:(Boolean)session.getAttribute("isLogin");
            if(isLogin){
                return true;
            }else{
                String contextPath = request.getContextPath();
                String loginUrl = "/tule/login";
                if(StringUtils.isNotEmpty(contextPath)){
                    loginUrl=contextPath+loginUrl;
                }
                response.sendRedirect(loginUrl);
                return false;
            }
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) throws Exception {

    }
}
