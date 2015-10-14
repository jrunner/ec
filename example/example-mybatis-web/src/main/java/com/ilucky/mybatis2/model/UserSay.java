package com.ilucky.mybatis2.model;

import java.util.Date;

/**
 * @author IluckySi
 * @since 20151014
 */
public class UserSay {

	private String id;
	private String user;
	private String say;
	private Date createTime;
	
	public UserSay() {
	}

	public UserSay(String id, String user, String say, Date createTime) {
		super();
		this.id = id;
		this.user = user;
		this.say = say;
		this.createTime = createTime;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getSay() {
		return say;
	}
	public void setSay(String say) {
		this.say = say;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public String toString() {
		return "id="+id+",user="+user+",say="+say+",createTime="+createTime;
	}
}
