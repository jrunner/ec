package com.t2t.top.base.controller;


import com.t2t.top.base.constant.ErrorCodeConstants;
import com.t2t.top.base.exception.BizException;
import com.t2t.top.base.exception.ServiceException;
import com.t2t.top.base.model.dto.ResponseDto;
import com.t2t.top.base.utils.json.GsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class BaseController {
    protected static Logger logger = LoggerFactory.getLogger(BaseController.class);

    @ExceptionHandler
    @ResponseBody
    public Object handler(HttpServletRequest request, Exception e, HttpServletResponse response) {
        ResponseDto errorDto = null;
        String url = request.getRequestURL().toString();
        if (e instanceof ServiceException) {
            ServiceException ex = (ServiceException) e;
            errorDto = ResponseDto.bulidFailResult(ex.getMsg(), ex.getCode());
            logger.error("url:{} 业务模块异常{}{}{}", url, ex.getCode(), ex.getMsg(), ex);
            return errorDto;
        }

        if (e instanceof BizException) {
            BizException ex = (BizException) e;
            errorDto = ResponseDto.bulidFailResult(ex.getMsg(), ex.getCode());
            logger.error("url:{} 业务模块异常{}{}{}", url, ex.getCode(), ex.getMsg(), ex);
            return errorDto;
        }

        logger.error("url:{}  未知异常:{}", url, e);
        errorDto = ResponseDto.bulidFailResult().setData(ErrorCodeConstants.UNKNOW_ERROR);
        return GsonUtils.toJson(errorDto);
    }

    /**
     * 获得cookie对应的值
     */
    public String getCookieValue(String key) {
        Cookie cookie = this.getCookie(key);
        if (cookie != null) {
            try {
                return URLDecoder.decode(cookie.getValue(), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                logger.error("从cookie中去登录信息异常", e);
            }
        }
        return null;
    }

    /**
     * 根据键获取对应的cookie对象
     */
    public Cookie getCookie(String key) {
        Cookie[] cookies = this.getRequest().getCookies();
        if (cookies == null || cookies.length < 1)
            return null;
        for (Cookie temp : cookies) {
            if (temp.getName().equals(key)) {
                return temp;
            }
        }
        return null;
    }

    /**
     * 得到HttpRequest
     */
    public HttpServletRequest getRequest() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return attr.getRequest();
    }

    /**
     * 得到HttpResponse
     */
    public HttpServletResponse getResponse() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return attr.getResponse();
    }


    @RequestMapping(value = "/ping")
    public String ping() {
        return "ping";
    }
}
