package com.t2t.top.base.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertiesUtils {
	public static final Logger logger = LoggerFactory.getLogger(PropertiesUtils.class);
	
	private static Properties properties;
	
	private static HashMap<String, String> propertiesKeys = new HashMap<String, String>();

	/**
	 * 获得字符串类型的数值
	 * 
	 * @param key
	 * @return
	 */
	public static String getStringValue(String key) {
		if (propertiesKeys.containsKey(key)) {
			return propertiesKeys.get(key);
		} else {
			try {
				logger.debug(key + " not fond,now load ./config/property/conf.properties ");
				properties = loadProperties("./config/property/conf.properties");
				String value = properties.getProperty(key);
				propertiesKeys.put(key, value);
				return value == null ? value : value.trim();
			} catch (Exception e) {
				logger.warn("read setting,properties error", e);
			}
		}
		return null;
	}
	
	public static Properties loadProperties(String path) {
		Properties p = new Properties();
		InputStream inStream = null;
		try {
			inStream = PropertiesUtils.class.getClassLoader().getResourceAsStream(path);
			p.load(inStream );
		}catch (FileNotFoundException e1) {
			logger.error("in class path the file "+path+" not found",e1);
		} catch (IOException e) {
			logger.error("PropertiesUtils load error",e);
		}finally{
			if(null!=inStream){
				try {
					inStream.close();
				} catch (IOException e) {
					logger.error("PropertiesUtils close InputStream error",e);
				}
			}
		}
		return p;
	}
}
