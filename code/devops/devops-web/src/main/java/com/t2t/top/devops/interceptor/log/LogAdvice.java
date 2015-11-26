package com.t2t.top.devops.interceptor.log;

import com.t2t.top.base.utils.json.GsonUtils;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yangpengfei on 2015/2/3
 */
@Component
@Aspect
public class LogAdvice {

    private static final Logger logger = LoggerFactory.getLogger(LogAdvice.class);


    @Around(value = "@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public Object addMonitorLog(ProceedingJoinPoint pjp) throws Throwable {
        String methodName = pjp.getSignature().getName();
        String className = parseShortClassName(pjp.getSignature().getDeclaringTypeName());
        String fullName = className + "_" + methodName;
        long start = System.currentTimeMillis();
        try {
            // 拦截的方法参数
            Object[] args = pjp.getArgs();
            Map<String, Object> parammaps = new HashMap<String, Object>();
            //循环获得所有参数对象
            for (int i = 0; i < args.length; i++) {
                if (null != args[i]) {

                    if (args[i] instanceof HttpServletRequest || args[i] instanceof HttpServletResponse) {
                        parammaps.put("args[" + i + "]", args[i].getClass().getName());
                        continue;
                    }


                    parammaps.put("args[" + i + "]", GsonUtils.toJson(args[i]));
                } else {
                    parammaps.put("args[" + i + "]", "空参数");
                }
            }
            logger.info("方法：{}请求参数：{}!", fullName, GsonUtils.toJson(parammaps));
            return pjp.proceed();
        } finally {
            long cost = System.currentTimeMillis() - start;
            logger.info("方法：{}执行耗时：{}!", fullName, cost);
        }
    }


    private String parseShortClassName(String className) {
        return StringUtils.substringAfterLast(className, ".");
    }
}
