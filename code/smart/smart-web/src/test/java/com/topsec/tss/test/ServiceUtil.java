package com.topsec.tss.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * 
 * 
 * 获取spring中bean的工具类
 * 
 * @title ServiceUtil
 * @package com.dmis.tss.test
 * @author ypf
 * @version
 * @date 2014-6-17
 * 
 */
public class ServiceUtil {

	private static String[] strs = { "src/main/resources/spring-servlet.xml", "src/main/resources/spring-core.xml" };

	private static ApplicationContext context = null;

	static {
		context = new FileSystemXmlApplicationContext(strs);
	}

	/**
	 * 根据spring配置文件中的bean的id或name属性返回指定的对象
	 * 
	 * @param beanName
	 */
	public static Object getBean(String beanName) {

		Object ret = null;

		try {
			ret = context.getBean(beanName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	public static Object getBean(Class<?> c) {

		Object ret = null;
		try {
			ret = context.getBean(c.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	public static Object getBeanBySimpleName(Class<?> c) {

		Object ret = null;
		try {
			ret = context.getBean(c.getSimpleName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	public static void main(String[] args) {
		new ServiceUtil();
		System.out.println("ok");
	}

}
