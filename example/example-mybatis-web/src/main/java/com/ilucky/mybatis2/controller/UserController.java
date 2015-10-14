package com.ilucky.mybatis2.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ilucky.mybatis2.service.UserService;

/**
 * @author IluckySi
 * @since 20151014
 */
@Controller("userController")
@Scope("prototype")
@RequestMapping("/userController")
public class UserController {

	private static Logger logger = Logger.getLogger(UserController.class);
	@Autowired
	private UserService userService;
	
	@RequestMapping("/createUser")
	public void createUser(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "password", required = true) String password,
			@RequestParam(value = "sex", required = true) boolean sex,
			@RequestParam(value = "birthday", required = true) String birthday,
			@RequestParam(value = "userType", required = true) String userType) {
		logger.info("收到参数:name="+name+",password="+password+",sex="+sex+",birthday="+birthday+",userType="+userType);
		userService.createUser(response, name, password, sex, birthday, userType);
	}
	
	@RequestMapping("/modifyUser")
	public void modifyUser(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "id", required = true) String id,
			@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "password", required = true) String password,
			@RequestParam(value = "sex", required = true) boolean sex,
			@RequestParam(value = "birthday", required = true) String birthday,
			@RequestParam(value = "userType", required = true) String userType) {
		logger.info("收到参数:id="+id+",name="+name+",password="+password+",sex="+sex+",birthday="+birthday+",userType="+userType);
		userService.modifyUser(response, id, name, password, sex, birthday, userType);
	}
	

	@RequestMapping("/deleteUser")
	public void modifyUser(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "id", required = true) String id) {
		logger.info("收到参数:id="+id);
		userService.deleteUser(response, id);
	}
	
	@RequestMapping("/getUserListByPage")
	public void getUserListByPage(HttpServletRequest request, HttpServletResponse response) {
		userService.getUserListByPage(response);
	}
}
