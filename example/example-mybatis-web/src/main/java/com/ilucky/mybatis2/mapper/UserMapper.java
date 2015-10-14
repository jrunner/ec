package com.ilucky.mybatis2.mapper;

import java.util.List;
import java.util.Map;

import com.ilucky.mybatis2.model.User;

/**
 * @author IluckySi
 * @since 20151013
 */
public interface UserMapper {

	/**
	 * 创建User
	 * @param user
	 */
	public void createUser(User user);
	
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
	 * 删除User
	 * @param user
	 */
	public void deleteUser(String user);
	
	/**
	 * 删除User集合
	 * @param user
	 */
	public void deleteUserList(List<String> userList);
	
	/**
	 * 查询User
	 *@param user
	 *@reuturn User
	 */
	public User getUser(String user);
	
	/**
	 * 根据name查询User
	 *@param user
	 *@reuturn User
	 */
	public User getUserByName1(String name);
	
	/**
	 * 根据name查询User
	 *@param user
	 * @return List<User>
	 */
	public List<User> getUserByName2(String name);
	
	/**
	 * 查询User集合
	 * @return List<User>
	 */
	public List<User> getUserList(Map<String, Object> condition);
	
	/**
	 * 查询User数量
	 * @return int
	 */
	public int getUserCount();
}
