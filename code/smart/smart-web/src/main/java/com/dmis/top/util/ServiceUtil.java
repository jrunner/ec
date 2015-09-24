package com.dmis.top.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class ServiceUtil {
	private static WebApplicationContext getWebApplicationContext(HttpServletRequest request) {
		return WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
	}

	public static Object getBean(HttpServletRequest request, String beanName) {
		return getWebApplicationContext(request).getBean(beanName);
	}

	/**
	 * 获取Spring service
	 * 
	 * @@param request HttpServletRequest
	 * @@param object 配置文件中的Beand的Id
	 * @@return Object
	 */
	public static Object getBean(HttpServletRequest request, Class<?> z) {
		return getBean(request, z.getName());
	}
}