package com.topsec.tss.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class EncodingFilter implements Filter {

	private String encoding = "UTF-8";

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		request.setCharacterEncoding(encoding);
		response.setCharacterEncoding(encoding);

		try {
			filterChain.doFilter(request, response);
		} catch (Exception e) {
			HttpServletResponse re = (HttpServletResponse) response;
			re.getWriter().print("请先登录本网站，然后才能访问本网站内的其它网页！");
		}
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		if (filterConfig.getInitParameter("encoding") != null) {
			encoding = filterConfig.getInitParameter("encoding");
		}
	}

}