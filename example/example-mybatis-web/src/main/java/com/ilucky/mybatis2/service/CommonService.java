package com.ilucky.mybatis2.service;

import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author IluckySi
 * @since 20150612
 */
public class CommonService {

	private static Logger logger = Logger.getLogger(CommonService.class);
	public static final String CONTENT_TYPE_JSON = "application/json;charset=utf-8";
	
	public void commonService(HttpServletResponse response, Object result) {
		String serviceResult = JSON.toJSONString(result);
		logger.info("业务层处理结果:"+serviceResult);
		if(serviceResult != null) {
			response.setContentType(CONTENT_TYPE_JSON);
			PrintWriter pw = null;
			try {
				pw = response.getWriter();
				pw.write(serviceResult);
				pw.flush();
			} catch (IOException e) {
				logger.error("业务层处理发生异常:"+e);
			} finally {
				if(pw != null) {
					pw.close();
					pw = null;
				}
			}
		}
	}
}
