package com.ilucky.mybatis2.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ilucky.mybatis2.model.UserSay;

/**
 * @author IluckySi
 * @since 20151014
 */
public interface UserSayMapper {

	/**
	 * 创建UserSay集合
	 * @param userSay
	 */
	public void createUserSayList(List<UserSay> userSayList);
	
	/**
	 * 查询UserSay集合
	 * @param user
	 * @return  List<UserSay>
	 */
	public List<UserSay> getUserSayListByUser1(@Param(value="user")  String user);
	
	
	/**
	 * 查询UserSay集合
	 * @param user
	 * @return  List<UserSay>
	 */
	public List<UserSay> getUserSayListByUser2(Map<String, Object> user);
	
	/**
	 * 查询UserSay集合
	 * @param user
	 * @return  List<Map<String, Object>>
	 */
	public List<Map<String, Object>> getUserSayListByUser3(String user);
}