package com.t2t.top.devops.interceptor.auth;

/******************************************************************************
 * 
 * <p>Description: 登录检验响应类型 </p> 
 *
 *****************************************************************************/
public enum AuthResponeType {
	RedirectUrl(1,"重定向URL"),
	RenderJson(2,"渲染JSON");
	
	public final int type;
	public final String desc;

	private AuthResponeType(int type, String desc) {
		this.type = type;
		this.desc = desc;
	}
}