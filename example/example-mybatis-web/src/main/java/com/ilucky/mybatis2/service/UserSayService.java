package com.ilucky.mybatis2.service;

import java.util.List;

import com.ilucky.mybatis2.model.UserSay;

/**
 * @author IluckySi
 * @since 20151014
 */
public interface UserSayService {

	/**
	 * 创建UserSay集合
	 * @param userSayList
	 */
	public void createUserSayList(List<UserSay> userSayList);
	
	/**
	 * 查询UserSay集合
	 * @param user
	 */
	public void getUserSayListByUser1(String user);
	
	/**
	 * 查询UserSay集合
	 * @param user
	 */
	public void getUserSayListByUser2(String user);
	
	/**
	 * 查询UserSay集合
	 * @param user
	 */
	public void getUserSayListByUser3(String user);
}
