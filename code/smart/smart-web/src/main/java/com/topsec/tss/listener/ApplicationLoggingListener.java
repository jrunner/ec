package com.topsec.tss.listener;

import com.topsec.tss.platform.log.PlatformLogger;
import com.topsec.tss.platform.log.PlatformLoggerConfiguration;
import com.topsec.tss.util.ApplicationReferent;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.ArrayList;


/**
 * 
 * 系统日志监听.
 * 
 * 系统日志启动监听.
 * 
 * @title ApplicationLoggingListener
 * @package com.topsec.tss.core.web.listener
 * @author baiyanwei
 * @version 1.0
 * @date 2014-5-15
 * 
 */
public class ApplicationLoggingListener implements ServletContextListener {

    /**
     * logger.
     */
    final private static PlatformLogger theLogger = PlatformLogger.getLogger(ApplicationLoggingListener.class);

    public void contextDestroyed(ServletContextEvent arg0) {

    }


    public void contextInitialized(ServletContextEvent arg0) {

        theLogger.info("startRegisterLogger");
        //填写配置文件
        fillParameterInSystem(arg0);
      
        //取得LOGGING配置文件名称
        String loggingCfgPath = arg0.getServletContext().getInitParameter(ApplicationReferent.appLogCfgPath.paremeterName());
        //
        try {
            ArrayList<Class<?>> frameworkClassList = new ArrayList<Class<?>>();
            //初始化LOGGING
            PlatformLoggerConfiguration.initConfigurationForLogging(loggingCfgPath, frameworkClassList);
        } catch (Exception e) {
            theLogger.error(loggingCfgPath, e);
        }
        theLogger.info("startRegisterLoggerFinshed");
    }

    /**
     * 将参数写入系统中, 根据WEB.XML中的context-param参数填充到System中.
     * 
     * @param sce
     */
    private void fillParameterInSystem(ServletContextEvent sce) {
        //设置启动时间
        System.setProperty(ApplicationReferent.appStartupTime.paremeterName(), String.valueOf(System.currentTimeMillis()));
        //LOGGING配置文件
        System.setProperty(ApplicationReferent.appLogCfgPath.paremeterName(), sce.getServletContext().getInitParameter(ApplicationReferent.appLogCfgPath.paremeterName()));
        //Services配置文件
        System.setProperty(ApplicationReferent.appServiceCfgPath.paremeterName(), sce.getServletContext().getInitParameter(ApplicationReferent.appServiceCfgPath.paremeterName()));

    }
}
