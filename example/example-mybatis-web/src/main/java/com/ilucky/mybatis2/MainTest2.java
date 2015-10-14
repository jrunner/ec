package com.ilucky.mybatis2;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.ilucky.mybatis2.util.RestClient;

/**
 * @author IluckySi
 * @since 20151014
 */
public class MainTest2 {

	private static Logger logger = Logger.getLogger(MainTest2.class);
	
	public static void main(String[] args) {
		//testCreateUser();
		//testModifyUser();
		//testDeleteUser();
		//testGetUserListByPage();
	}

	private static void testCreateUser() {
		Map<String, Object> message = new HashMap<String, Object>();
		message.put("name", "IluckySi");
		message.put("password", "123456");
		message.put("sex", "true");
		message.put("birthday", "1980-08-08");
		message.put("userType", "COMMON");
		Object result = RestClient.post("http://localhost:8080/mybatis-util2/userController/createUser.mvc", message);
		logger.info(result);
	}
	
	private static void testModifyUser() {
		Map<String, Object> message = new HashMap<String, Object>();
		message.put("id", "ad6511a44f684c39a693dcfb97b8fabd");
		message.put("name", "IluckySi2");
		message.put("password", "1234567");
		message.put("sex", "false");
		message.put("birthday", "1980-08-09");
		message.put("userType", "SUPER");
		Object result = RestClient.post("http://localhost:8080/mybatis-util2/userController/modifyUser.mvc", message);
		logger.info(result);
	}
	
	private static void testDeleteUser() {
		Map<String, Object> message = new HashMap<String, Object>();
		message.put("id", "531bf8795b6644c08be67e3ef2b79177,d0fb727f41eb4d36b713b91ce324d3e7");
		Object result = RestClient.post("http://localhost:8080/mybatis-util2/userController/deleteUser.mvc", message);
		logger.info(result);
	}
	
	private static void testGetUserListByPage() {
		Map<String, Object> message = new HashMap<String, Object>();
		Object result = RestClient.post("http://localhost:8080/mybatis-util2/userController/getUserListByPage.mvc", message);
		logger.info(result);
	}
}
