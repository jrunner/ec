package com.ilucky.aplay.core.http;

import java.util.HashMap;

/**
 * @author IluckySi
 * @since 20150724
 */
public class HttpCondition extends HashMap<Object, Object> {

	private static final long serialVersionUID = 1L;

	/**
	 * 获取http查询条件
	 * @param args
	 * @return HttpCondition
	 */
	public HttpCondition getHttpCondition(Object ...args) {
		for(int i = 0; args != null && i < args.length; i++){
			Object arg1 = args[i];
			Object arg2 = args[++i];
			this.put(arg1, arg2);
		}
		return this;
	}
}
