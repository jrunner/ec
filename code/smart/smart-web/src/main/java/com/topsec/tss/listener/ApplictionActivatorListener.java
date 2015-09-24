package com.topsec.tss.listener;

import com.topsec.tss.platform.core.services.IService;
import com.topsec.tss.platform.core.services.PropertyLoaderService;
import com.topsec.tss.platform.core.services.ServiceHelper;
import com.topsec.tss.platform.log.PlatformLogger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.ArrayList;

/**
 * 
 * 系统后台服务Activator.
 * 
 * 将后台服务需要的Service启动，注册与解析配置文件.
 * 
 * @title ApplictionActivatorListener
 * @package com.topsec.tss.core.web.listener
 * @author baiyanwei
 * @version 1.0
 * @date 2014-5-15
 * 
 */
public class ApplictionActivatorListener implements ServletContextListener {

	/**
	 * 平台日志.
	 */
	final private static PlatformLogger log = PlatformLogger.getLogger(ApplictionActivatorListener.class);
	//
	/**
	 * 启动的服务集合.
	 */
	ArrayList<IService> startUpServices = new ArrayList<IService>();

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

		// 停止服务
		for (int i = 0; i < startUpServices.size(); i++) {
			try {
				startUpServices.get(i).stop();
			} catch (Exception e) {
				log.exception(e);
			}
		}

	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {

		// 服务启动并注册，解析配置内容
		log.info("startActivator");
		long currentPoint = System.currentTimeMillis();
		System.setProperty(PropertyLoaderService.SERVICE_CONFIGURATION_PATH, sce.getServletContext().getInitParameter("application.serviceCfgPath"));
		try {
			PropertyLoaderService s = new PropertyLoaderService();
			s.start();
			ServiceHelper.registerService(s, true, false);
//			ServiceHelper.registerService(new SimpleHelper());
		} catch (Exception e) {
			e.printStackTrace();
		}

		log.info("finishActivator", String.valueOf(startUpServices.size()), String.valueOf(System.currentTimeMillis() - currentPoint));
	}
}
