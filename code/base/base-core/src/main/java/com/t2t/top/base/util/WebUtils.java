package com.t2t.top.base.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

/**
 * WebUtil 负责Cookie/Session等的管理。通常，所有对Cookie/Session的操作都通过此类来完成。 <br>
 *
 * @author yangpengfei
 */
public class WebUtils {
    protected static Logger logger = LoggerFactory.getLogger(WebUtils.class);
    /**
     * 根据名字从Session中获取一个对象
     *
     * @param request HttpServletRequest对象
     * @param name    Session中对象的名字
     * @return Object
     */
    @SuppressWarnings("unchecked")
    public static <T> T getObject(HttpServletRequest request, String name) {
        return (T) request.getSession().getAttribute(name);
    }

    /**
     * 根据给定的name将一个对象保存到Session中
     *
     * @param request HttpServletRequest对象
     * @param name    Session中对象的名字
     * @param object  需要保存到Session的对象
     */
    public static <T> void putObject(HttpServletRequest request, String name, T object) {
        request.getSession().setAttribute(name, object);
    }

    /**
     * 将Session置为无效状态，通常在注销时调用
     *
     * @param request HttpServletRequest
     */
    public static void invalidateSession(HttpServletRequest request) {
        request.getSession().invalidate();
    }

    /**
     * 获取URI的路径,如路径为http://www.example.com/example/user.do?method=add, 得到的值为"/example/user.do"
     *
     * @param request
     * @return String
     */
    public static String getRequestURI(HttpServletRequest request) {
        return request.getRequestURI();
    }

    /**
     * 获取不包含应用名字的URI的路径, 并去掉最前面的"/", <br>
     * 如路径为http://localhost:8080/appName/user/list.do, 得到的值为"user/list.do",其中appNames为应用的名字
     *
     * @param request
     * @return String
     */
    public static String getNoAppNamedRequestURI(HttpServletRequest request) {
        String contextPath = request.getContextPath();
        String uri = request.getRequestURI();
        if (uri.indexOf(contextPath) >= 0) {
            uri = uri.substring(contextPath.length());
        }
        while (uri.startsWith("/")) {
            uri = uri.substring(1);
        }
        uri = uri.replaceAll("/+", "/");
        return uri;
    }

    public static String getHttpHeaderInfo(HttpServletRequest request) {
        Enumeration<String> names = request.getHeaderNames();
        StringBuilder buf = new StringBuilder(0);


        while (names.hasMoreElements()) {
            String name = (String) names.nextElement();
            buf.append(name + ": " + request.getHeader(name) + " \n");
        }

        return buf.toString();
    }


    /**
     * 获取应用的根目录
     *
     * @param request
     */
    public static String getContextPath(HttpServletRequest request) {
        String contextPath = request.getContextPath();
        if (contextPath.equals("/")) {
            return "";
        }
        return contextPath;
    }

    /**
     * 获取完整请求路径(含内容路径及请求参数)
     *
     * @param request
     * @return
     */
    public static String getRequestURIWithParam(HttpServletRequest request) {
        return getRequestURI(request) + (request.getQueryString() == null ? "" : "?" + request.getQueryString());
    }

    /**
     * 添加cookie
     *
     * @param response
     * @param name     cookie的名称
     * @param value    cookie的值
     * @param maxAge   cookie存放的时间(以秒为单位,假如存放三天,即3*24*60*60; 如果值为0,cookie将随浏览器关闭而清除)
     */
    public static void addCookie(HttpServletResponse response, String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        if (maxAge > 0) {
            cookie.setMaxAge(maxAge);
        }
        response.addCookie(cookie);
    }

    public static void addCookie(HttpServletResponse response, String name, String value, int maxAge, String domain) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        if (maxAge > 0) {
            cookie.setMaxAge(maxAge);
        }
        cookie.setDomain(domain);
        response.addCookie(cookie);
    }

    /**
     * 获取cookie的值
     *
     * @param request
     * @param name    cookie的名称
     * @return
     */
    public static String getCookieByName(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals(name)) {
                    return cookies[i].getValue();
                }
            }
        }
        return null;
    }

    public static String getOSName() {
        String os = "Windows";
        if (System.getProperty("os.name").indexOf("Linux") != -1) {
            os = "Linux";
        } else if (System.getProperty("os.name").indexOf("Windows") != -1) {
            os = "Windows";
        }
        return os;
    }

    public static void main(String[] s) {
        String os = WebUtils.getOSName();
        System.out.println(os);
    }

    public static String getIpAddr(HttpServletRequest request) {
        String xForwardedFor = "";
        try {
            String ip = request.getHeader("x-forwarded-for");
            xForwardedFor = ip;
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0  || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
            }
            if (ip == null || ip.length() == 0  || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                String s = request.getRemoteHost();
                ip = request.getRemoteAddr();
            }
            if (StringUtils.isNotBlank(ip)) {
                ip = ip.trim();
                String[] tmp = ip.split(",");
                ip = tmp[tmp.length - 1];
                if (ip.length() > 20) {
                    ip = ip.substring(0, 20);
                }
            }
            return ip;
        } catch (Exception e) {
            logger.error("HttpServletRequest中的ip获取失败，xForwardedFor:",xForwardedFor,e);
            return "";
        }
    }
}
