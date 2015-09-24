package com.topsec.tss.util;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;


/**
 * 
 * 系统ServletContext持有类.
 * 
 * 系统ServletContext持有类.
 * 
 * @title ApplicationServletContextUtils
 * @package com.topsec.tss.core.web.util
 * @author baiyanwei
 * @version 1.0
 * @date 2014-5-15
 * 
 */
public class ApplicationServletContextUtils {

    private static ServletContext APP_SERVLET_CONTEXT = null;

    public static ServletContext getServletContext() {

        return APP_SERVLET_CONTEXT;
    }

    public static void setServletContext(ServletContext servletContext) {

        APP_SERVLET_CONTEXT = servletContext;
    }

    /**
     * 取得spring的bean Context.
     * 
     * @return
     */
    public static WebApplicationContext getSpringApplicationContext() {

        if (APP_SERVLET_CONTEXT == null) {
            return null;
        }

        return WebApplicationContextUtils.getWebApplicationContext(APP_SERVLET_CONTEXT);

    }


}
