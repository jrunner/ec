package com.t2t.top.devops.interceptor.auth;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.InitBinder;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 为拦截器提供跳转url
 */
@Component
public class PageRedirectMapping {
    private static ConcurrentMap<String, String> mappings;

    @InitBinder
    public void init() {
        mappings = new ConcurrentHashMap<String, String>();
        load();
    }

    /**
     * 根据key获取配置的url
     */
    public String getMapping(String key) {
        load();
        return mappings.get(key);
    }

    /**
     * 添加url的key
     */
    private void load() {
        //String url = ReloadableConfig.getProperty("loginUrl","https://g.gome.com.cn/ec/homeus/myaccount/login.jsp?orginURI=");
        //mappings.put(RedirectUrlConstants.REDIRECT_LOGINURL_KEY,url);// app_domain
    }

    /**
     * 获得当前配置的url拷贝
     */
    public Map<String, String> getMappingsCopy() {
        return null;
    }
}
