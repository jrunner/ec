package com.ilucky.mybatis2;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ilucky.mybatis2.model.User;
import com.ilucky.mybatis2.service.UserService;
import com.ilucky.mybatis2.model.UserType;
import com.ilucky.mybatis2.util.IdUtil;

/**
 * @author IluckySi
 * @since 20151013
 */
public class MainTest {

	private static Logger logger = Logger.getLogger(MainTest.class);
	public static ClassPathXmlApplicationContext context;
	public static UserService userService;
	
	public static void main(String[] args) {
		//简单测试.
		test();
		
		//初始化环境.
		//if(!initEnv()) {
			//return;
		//}
		
		//获取service bean.
		//userService = (UserService) context.getBean("userService");
		
		//保存单个对象.
		//注意:如果我们传入到Mapper层的参数是一个对象,mybatis会自动将其转换成Map集合,key=对象字段名称,value=对象字段值.
		//createUser();
		
	   //保存多个对象.
		//注意:如果我们传入到Mapper层的参数只有一个,并且是List集合,mybatis会自动将其封装成Map集合,且key="list",value=List.
		//同时注意:如果List集合中是对象,则遍历集合时mybatis会自动将对象转换为Map集合,key=对象字段名称,value=对象字段值.
		//createUserList();
		
		//修改对象.
		//注意:如果我们传入到Mapper层的参数是一个对象,mybatis会自动将其转换成Map集合,key=对象字段名称,value=对象字段值.
		//同时注意:拼写sql时哪些是条件字段,哪些是需要修改的字段.
		//modifyUser();
		
		//删除单个对象.
		//注意:如果我们传入到Mapper层的参数是一个元素,mybatis会自动将其转换为字符串作为key,value=元素值.
		//deleteUser();
		
		//删除多个对象.
		//注意:如果我们传入到Mapper层的参数只有一个,并且是List集合,mybatis会自动将其封装成Map集合,且key="list",value=List.
		//deleteUserList();
		
		//测试事务.
		//testTransaction();
		
		//测试事务.
		//注意:一般在service层处理业务的时候,我们的service方法都会被try catch包裹,
		//在这种情况下,如果service层抛出了异常,事务是不能回滚的,因为异常被捕获了,
		//解决方法是捕获Exception后手动抛出异常,throw new RuntimeException("事务回滚");
		//testTransaction2();
		
		//查询单个对象.
		//报错:A query was run and no Result Maps were found for the Mapped Statement 'com.ilucky.mybatis.mapper.UserMapper.getUser'.  
		//It's likely that neither a Result Type nor a Result Map was specified.
		//mybatis针对查询语句,必须指定返回类型,通过resultType或resultMap指定,resultType和resultMap的区别是什么呢?稍后会做说明.
		//解决方法:添加resultType="User", 即<select id="getUser"  resultType="User">
		//报错:Error instantiating class com.ilucky.mybatis.model.User with invalid types () or values (). Cause: java.lang.NoSuchMethodException: 
		//针对select id from mybatis_user where id=#{user},mybatis会将mybatis_user数据表中的数据按照字段名称通过反射机制自动映射到User类中.
		//具体过程是:首先通过反射机制获取User,然后通过反射机制和数据库字段名称完成数据映射.所以User必须提供一个空的构造方法,否则mybatis无法通过反射获取User.
		//为User添加完空的构造方法,继续执行,输出结果如下
		//id=49455d9faf79443c801e40ae736f0b85,name=name-8,password=password-8,sex=true,birthday=Tue Nov 03 10:07:51 CST 2015,createTime=null,userType=null
		//了解了mybatis自动映射的原理后,导致上面结果的原因就很清楚了,mybatis通过映射获取了User后,然后通过反射机制调用各个字段的set方法完成数据映射,
		//而set方法是按照数据库字段拼接的,所以setCreate_time和setUser_type会出现问题,从而导致数据丢失.
		//注意:如果User类中的字段名称和mybaits_user数据表中的名称严格一致,则不会导致上面的问题.		
		//解决方法:声明一个resultMap,将User类中的字段名称和mybatis_usr数据表中的名称进行手动映射.
		//即:resultType和resultMap的区别是:如果返回的数据可以支持自动映射到某个model类上,则可以使用resultType,否则需要使用resultMap进行手动映射.
		//完成手动映射,输出结果如下:
		//id=49455d9faf79443c801e40ae736f0b85,name=name-8,password=password-8,sex=true,birthday=Tue Nov 03 10:07:51 CST 2015,createTime=Thu Oct 01 12:38:31 CST 2015,userType=COMMON
		//getUser();
		
		//查询所有对象.
		//getUserList();
		
		//根据name查询对象.
		//注意:查询单个对象和查询所有对象,写法基本上是一样的(<select id="getUser"  resultMap="userMap">),mybatis会是如何查询结果并进行封装的?
		//是根据mapper层写的返回结果吗?通过一个测试进行分析.
		//如果数据表中name是唯一的,则查询是没有问题的,但是如果不是唯一的,有两条数据name是一样的,
		//则User getUserByName1(String name);会报错:expected one result (or null) to be returned by selectOne(), but found: 2
		//而List<User> getUserByName2(String name);不会报错.
		//结论:当查询对象时,mybatis会分析mapper层方法的返回结果,如果是一个对象,则数据库查询必须返回一个,否则会报错,
		//如果mybatis分析mapper层方法的返回结果是一个对象集合,则没有任何限制.
		//最后,如果mapper层返回的是一个对象,则封装成resultMap,如果mapper层返回的是一个对象集合,则封装成一个元素为resultMap的List集合.
		//这里只做一个简单分析,后面会分析mybatis底层源码.
		//getUserByName();
		
		//查询所有对象个数.
		//getUserCount();
		
		//分页查询对象.
		//getUserListByPage();
	}
	
	/**
	 * 简单测试.
	 * 如下一般放在service层,在service层可以进行多数据源配置,稍后会学习到.
	 */
	public static void test() {
		String resource = "mybatis-test.xml";
		InputStream is = null;
		SqlSessionFactory ssf = null;
		SqlSession ss = null;
		try {
			is = Resources.getResourceAsStream(resource);
			ssf = new SqlSessionFactoryBuilder().build(is, "development");
			ss = ssf.openSession();
			User user = new User(IdUtil.getId(), "test", "123456", true, new Date(), new Date(), UserType.COMMON);
			ss.insert("createUser", user);
			ss.commit();
		} catch (IOException e) {
			logger.error("异常:"+e);
		} finally {
			try {
				if(ss != null) {
					ss.close();
				}
				if(is != null) {
					is.close();
					is = null;
				}
			} catch (IOException e) {
				logger.error("异常:"+e);
			}
		}
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
	
	/**
	 * 保存单个对象
	 */
	public static void createUser() {
		User user = new User(IdUtil.getId(), "name-0", "123456", true, new Date(), new Date(), UserType.COMMON);
		userService.createUser(user);
	}
	
	/**
	 * 保存多个对象
	 */
	public static void createUserList() {
		List<User> userList = new ArrayList<User>();
		for(int i = 0; i < 10; i++) {
			int number = new Random().nextInt(24 * 3);//生日跨度是72个小时~=3天.
			int number2 = new Random().nextInt(24 * 3); //创建时间跨度是72个小时~=3天.
			Date birthday = new Date(new Date().getTime() - 1000 * 60 * 60 * number);
			Date createTime = new Date(new Date().getTime() - 1000 * 60 * 60 * number2);
			User user = new User(IdUtil.getId(), "name-"+i, "password-"+i, i%2 == 0 ? true : false, birthday, createTime, i%2 == 0 ? UserType.COMMON : UserType.SUPER);
			userList.add(user);
		}
		userService.createUserList(userList);
	}
	
	/**
	 * 修改对象
	 */
	public static void modifyUser() {
		User user = new User("06f45bbc198643a18e6b7a14ba26090f", "name-1", "123456", false, new Date(), new Date(), UserType.SUPER);
		userService.modifyUser(user);
	}
	
	/**
	 * 删除单个对象
	 */
	public static void deleteUser() {
		userService.deleteUser("06f45bbc198643a18e6b7a14ba26090f");
	}
	
	/**
	 * 删除多个对象
	 */
	public static void deleteUserList() {
		List<String> userList = new ArrayList<String>();
		userList.add("410b7cd8b9e54c2f9fcf53a69aa67558");
		userList.add("ee1df808e62d4a2f895b77d4fdf51800");
		userService.deleteUserList(userList);
	}
	
	/**
	 * 测试事务
	 */
	private static void testTransaction() {
		userService.testTransaction();
	}
	
	/**
	 * 测试事务
	 */
	private static void testTransaction2() {
		userService.testTransaction2();
	}
	
	/**
	 * 查询单个对象
	 */
	private static void getUser() {
		userService.getUser("00b5d06624cd4ba694339824c8a65179");
	}
	
	/**
	 * 查询所有对象
	 */
	private static void getUserList() {
		userService.getUserList();
	}
	
	/**
	 * 查询所有对象个数
	 */
	private static void getUserCount() {
		userService.getUserCount();
	}
	
	/**
	 * 根据用户名查询对象
	 */
	private static void getUserByName() {
		userService.getUserByName("name-0");
	}
	
	/**
	 * 分页查询对象
	 */
	private static void getUserListByPage() {
		userService.getUserListByPage();
	}
}
