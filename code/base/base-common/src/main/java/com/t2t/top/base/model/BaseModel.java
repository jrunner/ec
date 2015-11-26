package com.t2t.top.base.model;

import java.io.Serializable;


import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class BaseModel implements Serializable, Cloneable {


    private static final long serialVersionUID = -5152170884916847629L;

	/**
	 * 系统请求版本号，用于手机端多版本兼容
	 */
	protected String ver;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	@SuppressWarnings("unchecked")
	public <T> T clone(Class<T> cls) throws CloneNotSupportedException {
		return (T) clone();
	}

	public String getVer() {
		return ver;
	}

	public void setVer(String ver) {
		this.ver = ver;
	}


}
