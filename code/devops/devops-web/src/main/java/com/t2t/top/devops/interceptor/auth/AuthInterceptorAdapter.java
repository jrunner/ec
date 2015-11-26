package com.t2t.top.devops.interceptor.auth;

import com.t2t.top.base.exception.ServiceException;
import com.t2t.top.devops.constant.ErrorCodeConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.MessageFormat;

public class AuthInterceptorAdapter extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(AuthInterceptorAdapter.class);

    @Autowired(required = false)
    PageRedirectMapping redirectMapping;

    private String loginUrl = "";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1、请求到登录页面 放行
        if (request.getServletPath().startsWith(loginUrl)) {
            return true;
        }

        //2、判断是否声明了注解
        if (!handler.getClass().isAssignableFrom(HandlerMethod.class)) {
            return true;
        }

        //3、判断明了AuthPassport的注解
        AuthPassport authPassport = ((HandlerMethod) handler).getMethodAnnotation(AuthPassport.class);
        if (authPassport == null) {
            return true;
        }
        logger.info(authPassport.msg(), authPassport.url(), authPassport.paraNames(), authPassport.mpKey());

        //4、比如退出、首页等页面无需登录，即此处要放行 允许游客的请求

        //5、如果用户已经登录放行,更好的实现方式的使用cookie
        if (request.getSession().getAttribute("username") != "admin") {
            return true;
        }

        //6.1、重定向
        AuthResponeType type = authPassport.type();
        if (AuthResponeType.RedirectUrl.equals(type)) {
            response.sendRedirect(authPassport.url());
            return false;
        }
        //6.2、返回json
        if (AuthResponeType.RenderJson.equals(type)) {
            throw new ServiceException(ErrorCodeConstants.NOT_LOGIN_ERROR.getCode(), ErrorCodeConstants.NOT_LOGIN_ERROR.getValue());
        }
        return false;
    }

    private String fillParameters(String[] paraNames, ServletRequest request, String url) {
        String[] paraValues = new String[paraNames.length];
        for (int i = 0; i < paraNames.length; i++) {
            paraValues[i] = request.getParameter(paraNames[i]);
        }
        try {
            return MessageFormat.format(url, paraValues);
        } catch (Exception e) {
            throw new RuntimeException("LoginAuthInterceptor url parameter format error", e);
        }
    }
}