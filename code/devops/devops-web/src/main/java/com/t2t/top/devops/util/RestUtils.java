package com.t2t.top.devops.util;

import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * @author IluckySi
 * @since 20151014
 */
public class RestUtils {

    protected static Logger logger = LoggerFactory.getLogger(RestUtils.class);

    public static Object post(String url, Map<String, Object> message) {
        Object result = null;
        try {
            logger.info(url);
            logger.info(message + "");
            RestTemplate rest = new RestTemplate();
            MultiValueMap<String, Object> param = new LinkedMultiValueMap();
            for (Entry<String, Object> entry : message.entrySet()) {
                param.add(entry.getKey(), entry.getValue());
            }
            result = rest.postForObject(url, param, String.class);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("发送消息发生异常" + e);
        }
        return result;
    }

}
