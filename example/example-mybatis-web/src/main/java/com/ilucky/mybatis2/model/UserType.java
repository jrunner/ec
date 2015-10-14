package com.ilucky.mybatis2.model;

/**
 * @author IluckySi
 * @since 20151013
 */
public enum UserType {
	
	COMMON("common", "普通用户"), SUPER("super", "超级用户"); 

	public String name;
	public String displayName;
	
	UserType(String name, String displayName) {
		this.name = name;
		this.displayName = displayName;
	}
}
