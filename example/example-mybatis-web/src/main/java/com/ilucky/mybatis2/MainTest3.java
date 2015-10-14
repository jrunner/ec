package com.ilucky.mybatis2;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ilucky.mybatis2.model.User;
import com.ilucky.mybatis2.model.UserSay;
import com.ilucky.mybatis2.model.UserType;
import com.ilucky.mybatis2.service.UserSayService;
import com.ilucky.mybatis2.service.UserService;
import com.ilucky.mybatis2.util.IdUtil;

/**
 * @author IluckySi
 * @since 20151014
 */
public class MainTest3 {

	private static Logger logger = Logger.getLogger(MainTest3.class);
	public static ClassPathXmlApplicationContext context;
	public static UserService userService;
	public static UserSayService userSayService;

	public static void main(String[] args) {
		//初始化环境.
		if(!initEnv()) {
			return;
		}
		
		//获取service bean.
		userService = (UserService) context.getBean("userService");
		userSayService = (UserSayService) context.getBean("userSayService");
		
		//创建测试数据.
		//createUserSayList();
		//不级联查询.
		getUserSayListByUser1();
		//测试级联查询.
		//getUserSayListByUser2();
	}

	/**
	 * 加载spring配置文件和log4j日志文件
	 * @return boolean
	 */
	public static boolean initEnv() {
		try {
			context = new ClassPathXmlApplicationContext("spring.xml");
			PropertyConfigurator.configure("src/main/resources/log4j.properties");
			logger.info("初始化环境成功");
			return true;
		} catch (Exception e) {
			logger.error("初始化环境异常:"+e);
			return false;
		}
	}
	
	private static void createUserSayList() {
		String userId = IdUtil.getId();
		User user = new User(userId, "asso", "123456", true, new Date(), new Date(), UserType.COMMON);
		userService.createUser(user);
		List<UserSay> userSayList = new ArrayList<UserSay>();
		for(int i = 0; i < 10; i++) {
			UserSay userSay = new UserSay(IdUtil.getId(), userId, "title="+i, new Date());
			userSayList.add(userSay);
		}
		userSayService.createUserSayList(userSayList);
		logger.info(userId);
	}
	
	private static void getUserSayListByUser1() {
		userSayService.getUserSayListByUser1("093696d2c4234cae9ee7dbf3ff012fe5");
	}
	
	private static void getUserSayListByUser2() {
		userSayService.getUserSayListByUser1("093696d2c4234cae9ee7dbf3ff012fe5");
	}
	
	private static void getUserSayListByUser3() {
		userSayService.getUserSayListByUser2("093696d2c4234cae9ee7dbf3ff012fe5");
	}
}
