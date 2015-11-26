package com.t2t.top.base.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

public class ServletUtils {

	/**
	 * Get a parameter from a ServletRequest. Return null if the parameter contains only white spaces.
	 */
	public static String getParameter(ServletRequest request, String name) {
		String s = request.getParameter(name);
		if (s == null) {
			return null;
		}
		s = s.trim();
		return s.length() == 0 ? null : s;
	}

	/**
	 * extract a Map<string,Object> from HttpServletRequest
	 */
	public static Map<String, Object> getParmMap(HttpServletRequest request) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		@SuppressWarnings("unchecked")
		Map<String, String[]> orimap = request.getParameterMap();
		Set<String> keys = orimap.keySet();
		for (String key1 : keys) {
			String key = key1;
			String[] value = orimap.get(key);
			if (value.length > 1) {
				map.put(key, value);
			} else {
				map.put(key, value[0]);
			}
		}
		return map;
	}
}
