package com.ilucky.mybatis2.util;

import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * @author IluckySi
 * @since 20151014
 */
public class RestClient {

	private static Logger logger = Logger.getLogger(RestClient.class);
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Object post(String url, Map<String, Object> message) {
		Object result = null;
		try {
			RestTemplate rest = new RestTemplate();
			MultiValueMap<String, Object> param = new LinkedMultiValueMap();
			for(Entry<String, Object> entry : message.entrySet()) {
				param.add(entry.getKey(), entry.getValue());
			}
			result = rest.postForObject(url, param, String.class);
		} catch (Exception e) {
			logger.error("发送消息发生异常"+e);
		}
		return result;
	}
}
