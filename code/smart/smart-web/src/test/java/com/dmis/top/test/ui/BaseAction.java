package com.dmis.top.test.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dmis.top.service.BaseService;
import com.dmis.top.test.ServiceUtil;

/**
 * 基础控制器.
 * 
 * 定制控制器都具有的功能.
 * 
 * 
 * @title BaseAction
 * @package com.dmis.top.test.ui
 * @author ypf
 * @version 1.0.0
 * @date 2014-6-17
 * 
 */
@SuppressWarnings("unchecked")
public abstract class BaseAction {
	public static Logger log = LoggerFactory.getLogger(TemplateActionTest.class);
	protected BaseService service = (BaseService) ServiceUtil.getBean("baseServiceImpl");

	public HttpSession getHttpSession() {

		return request.getSession();
	}

	public HttpServletRequest getRequest() {

		return request;
	}

	public HttpServletResponse getResponse() {

		return response;
	}

	public HashMap<String, String> getParameterMap() {

		HttpServletRequest request = this.getRequest();
		Enumeration<String> names = request.getParameterNames();
		HashMap<String, String> formInputMap = new HashMap<String, String>();
		while (names.hasMoreElements()) {
			String key = names.nextElement().toString();
			formInputMap.put(key, request.getParameter(key));
		}

		return formInputMap;
	}

	public HttpServletRequest request = new HttpServletRequest() {

		public Object getAttribute(String arg0) {

			return null;
		}

		public Enumeration getAttributeNames() {

			return new Enumeration() {

				public boolean hasMoreElements() {

					return false;
				}

				public Object nextElement() {

					return null;
				}
			};
		}

		public String getCharacterEncoding() {

			return null;
		}

		public int getContentLength() {

			return 0;
		}

		public String getContentType() {

			return null;
		}

		public ServletInputStream getInputStream() throws IOException {

			return null;
		}

		public String getLocalAddr() {

			return null;
		}

		public String getLocalName() {

			// TODO Auto-generated method stub
			return null;
		}

		public int getLocalPort() {

			// TODO Auto-generated method stub
			return 0;
		}

		public Locale getLocale() {

			// TODO Auto-generated method stub
			return null;
		}

		public Enumeration getLocales() {

			return new Enumeration() {

				public boolean hasMoreElements() {

					return false;
				}

				public Object nextElement() {

					return null;
				}
			};
		}

		public String getParameter(String arg0) {

			// TODO Auto-generated method stub
			return null;
		}

		public Map getParameterMap() {

			return new HashMap();
		}

		public Enumeration getParameterNames() {

			return new Enumeration() {

				public boolean hasMoreElements() {

					return false;
				}

				public Object nextElement() {

					return null;
				}
			};
		}

		public String[] getParameterValues(String arg0) {

			// TODO Auto-generated method stub
			return new String[0];
		}

		public String getProtocol() {

			// TODO Auto-generated method stub
			return null;
		}

		public BufferedReader getReader() throws IOException {

			// TODO Auto-generated method stub
			return null;
		}

		public String getRealPath(String arg0) {

			// TODO Auto-generated method stub
			return null;
		}

		public String getRemoteAddr() {

			// TODO Auto-generated method stub
			return null;
		}

		public String getRemoteHost() {

			// TODO Auto-generated method stub
			return null;
		}

		public int getRemotePort() {

			// TODO Auto-generated method stub
			return 0;
		}

		public RequestDispatcher getRequestDispatcher(String arg0) {

			// TODO Auto-generated method stub
			return null;
		}

		public String getScheme() {

			// TODO Auto-generated method stub
			return null;
		}

		public String getServerName() {

			// TODO Auto-generated method stub
			return null;
		}

		public int getServerPort() {

			// TODO Auto-generated method stub
			return 0;
		}

		public boolean isSecure() {

			// TODO Auto-generated method stub
			return false;
		}

		public void removeAttribute(String arg0) {

			// TODO Auto-generated method stub

		}

		public void setAttribute(String arg0, Object arg1) {

			// TODO Auto-generated method stub

		}

		public void setCharacterEncoding(String arg0) throws UnsupportedEncodingException {

			// TODO Auto-generated method stub

		}

		public String getAuthType() {

			// TODO Auto-generated method stub
			return null;
		}

		public String getContextPath() {

			// TODO Auto-generated method stub
			return null;
		}

		public Cookie[] getCookies() {

			// TODO Auto-generated method stub
			return null;
		}

		public long getDateHeader(String arg0) {

			// TODO Auto-generated method stub
			return 0;
		}

		public String getHeader(String arg0) {

			// TODO Auto-generated method stub
			return null;
		}

		public Enumeration getHeaderNames() {

			return new Enumeration() {

				public boolean hasMoreElements() {

					return false;
				}

				public Object nextElement() {

					return null;
				}
			};
		}

		public Enumeration getHeaders(String arg0) {

			return new Enumeration() {

				public boolean hasMoreElements() {

					return false;
				}

				public Object nextElement() {

					return null;
				}
			};
		}

		public int getIntHeader(String arg0) {

			// TODO Auto-generated method stub
			return 0;
		}

		public String getMethod() {

			// TODO Auto-generated method stub
			return null;
		}

		public String getPathInfo() {

			// TODO Auto-generated method stub
			return null;
		}

		public String getPathTranslated() {

			// TODO Auto-generated method stub
			return null;
		}

		public String getQueryString() {

			// TODO Auto-generated method stub
			return null;
		}

		public String getRemoteUser() {

			// TODO Auto-generated method stub
			return null;
		}

		public String getRequestURI() {

			// TODO Auto-generated method stub
			return null;
		}

		public StringBuffer getRequestURL() {

			// TODO Auto-generated method stub
			return null;
		}

		public String getRequestedSessionId() {

			// TODO Auto-generated method stub
			return null;
		}

		public String getServletPath() {

			// TODO Auto-generated method stub
			return null;
		}

		public HttpSession getSession() {

			// TODO Auto-generated method stub
			return null;
		}

		public HttpSession getSession(boolean arg0) {

			// TODO Auto-generated method stub
			return null;
		}

		public Principal getUserPrincipal() {

			// TODO Auto-generated method stub
			return null;
		}

		public boolean isRequestedSessionIdFromCookie() {

			// TODO Auto-generated method stub
			return false;
		}

		public boolean isRequestedSessionIdFromURL() {

			return false;
		}

		public boolean isRequestedSessionIdFromUrl() {

			// TODO Auto-generated method stub
			return false;
		}

		public boolean isRequestedSessionIdValid() {

			// TODO Auto-generated method stub
			return false;
		}

		public boolean isUserInRole(String arg0) {

			// TODO Auto-generated method stub
			return false;
		}

	};

	public HttpServletResponse response = new HttpServletResponse() {

		public void flushBuffer() throws IOException {

		}

		public int getBufferSize() {

			return 0;
		}

		public String getCharacterEncoding() {

			return null;
		}

		public String getContentType() {

			return null;
		}

		public Locale getLocale() {

			return null;
		}

		public ServletOutputStream getOutputStream() throws IOException {

			// TODO Auto-generated method stub
			return null;
		}

		public PrintWriter getWriter() throws IOException {

			// TODO Auto-generated method stub
			return null;
		}

		public boolean isCommitted() {

			return false;
		}

		public void reset() {

			// TODO Auto-generated method stub

		}

		public void resetBuffer() {

			// TODO Auto-generated method stub

		}

		public void setBufferSize(int arg0) {

			// TODO Auto-generated method stub

		}

		public void setCharacterEncoding(String arg0) {

			// TODO Auto-generated method stub

		}

		public void setContentLength(int arg0) {

			// TODO Auto-generated method stub

		}

		public void setContentType(String arg0) {

			// TODO Auto-generated method stub

		}

		public void setLocale(Locale arg0) {

			// TODO Auto-generated method stub

		}

		public void addCookie(Cookie arg0) {

			// TODO Auto-generated method stub

		}

		public void addDateHeader(String arg0, long arg1) {

			// TODO Auto-generated method stub

		}

		public void addHeader(String arg0, String arg1) {

			// TODO Auto-generated method stub

		}

		public void addIntHeader(String arg0, int arg1) {

			// TODO Auto-generated method stub

		}

		public boolean containsHeader(String arg0) {

			// TODO Auto-generated method stub
			return false;
		}

		public String encodeRedirectURL(String arg0) {

			// TODO Auto-generated method stub
			return null;
		}

		public String encodeRedirectUrl(String arg0) {

			// TODO Auto-generated method stub
			return null;
		}

		public String encodeURL(String arg0) {

			// TODO Auto-generated method stub
			return null;
		}

		public String encodeUrl(String arg0) {

			// TODO Auto-generated method stub
			return null;
		}

		public void sendError(int arg0) throws IOException {

			// TODO Auto-generated method stub

		}

		public void sendError(int arg0, String arg1) throws IOException {

			// TODO Auto-generated method stub

		}

		public void sendRedirect(String arg0) throws IOException {

			// TODO Auto-generated method stub

		}

		public void setDateHeader(String arg0, long arg1) {

			// TODO Auto-generated method stub

		}

		public void setHeader(String arg0, String arg1) {

			// TODO Auto-generated method stub

		}

		public void setIntHeader(String arg0, int arg1) {

			// TODO Auto-generated method stub

		}

		public void setStatus(int arg0) {

			// TODO Auto-generated method stub

		}

		public void setStatus(int arg0, String arg1) {

			// TODO Auto-generated method stub

		}

	};
}
