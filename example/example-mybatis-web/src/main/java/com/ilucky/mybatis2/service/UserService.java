package com.ilucky.mybatis2.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.ilucky.mybatis2.model.User;

/**
 * @author IluckySi
 * @since 20151013
 */
public interface UserService {

	/**
	 * 创建User
	 * @param user
	 */
	public void createUser(User user);
	
	/**
	 * 创建User
	 * @param response
	 * @param name
	 * @param password
	 * @param sex
	 * @param birthday
	 * @param userType
	 */
	public void createUser(HttpServletResponse response, String name, String password, boolean sex, String birthday, String userType);
	
	/**
	 * 创建User集合
	 * @param userList
	 */
	public void createUserList(List<User> userList);
	
	/**
	 * 修改User
	 * @param user
	 */
	public void modifyUser(User user);

	/**
	 * 修改User
	 * @param response
	 * @param id
	 * @param name
	 * @param password
	 * @param sex
	 * @param birthday
	 * @param userType
	 */
	public void modifyUser(HttpServletResponse response, String id, String name, String password, boolean sex, String birthday, String userType);
	
	/**
	 * 删除User
	 * @param user
	 */
	public void deleteUser(String user);
	
	/**
	 * 删除User
	 * @param response
	 * @param user
	 */
	public void deleteUser(HttpServletResponse response, String user);
	
	/**
	 * 删除User集合
	 * @param userList
	 */
	public void deleteUserList(List<String> userList);
	
	/**
	 * 测试事务
	 */
	public void testTransaction();
	
	/**
	 * 测试事务
	 */
	public void testTransaction2();
	
	/**
	 * 查询User
	 *@param user
	 */
	public void getUser(String user);
	
	/**
	 * 查询User集合
	 */
	public void getUserList();
	
	/**
	 * 根据name查询User
	 *@param user
	 */
	public void getUserByName(String name);
	
	/**
	 * 查询User数量
	 */
	public void getUserCount();
	
	/**
	 * 分页查询User集合
	 */
	public void getUserListByPage();
	
	/**
	 * 分页查询User集合
	 * @param response
	 */
	public void getUserListByPage(HttpServletResponse response);
}
