package com.ilucky.mybatis2.model;

import java.util.Date;

/**
 * @author IluckySi
 * @since 20151013
 */
public class User {
	private String id;
	private String name;
	private String password;
	private boolean sex;
	private Date birthday;
	private Date createTime;
	private UserType userType;

	public User() {
	}
	
	public User(String id, String name, String password, boolean sex, Date birthday, Date createTime, UserType userType) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.sex = sex;
		this.birthday = birthday;
		this.createTime = createTime;
		this.userType = userType;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isSex() {
		return sex;
	}
	public void setSex(boolean sex) {
		this.sex = sex;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public UserType getUserType() {
		return userType;
	}
	public void setUserType(UserType userType) {
		this.userType = userType;
	}
	
	public String toString() {
		return "id="+id+",name="+name+",password="+password+",sex="+sex+",birthday="+birthday+",createTime="+createTime+",userType="+userType;
	}
}
