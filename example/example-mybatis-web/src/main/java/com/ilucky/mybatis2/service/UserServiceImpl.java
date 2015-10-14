package com.ilucky.mybatis2.service;

import com.ilucky.mybatis2.mapper.UserMapper;
import com.ilucky.mybatis2.model.User;
import com.ilucky.mybatis2.model.UserType;
import com.ilucky.mybatis2.util.IdUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author IluckySi
 * @since 20151013
 */
@Service("userService")
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl extends CommonService implements UserService {

    private static Logger logger = Logger.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Override
    public void createUser(User user) {
        userMapper.createUser(user);
    }

    @Override
    public void createUser(HttpServletResponse response, String name, String password, boolean sex, String birthday, String userType) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            Date birthdayDate = new SimpleDateFormat("yyyy-MM-dd").parse(birthday);
            User user = new User(IdUtil.getId(), name, password, sex, birthdayDate, new Date(), UserType.valueOf(userType));
            userMapper.createUser(user);
            result.put("result", "200");
            result.put("message", "创建成功");
        } catch (Exception e) {
            logger.error("异常" + e);
            result.put("result", "200");
            result.put("message", "创建失败");
            throw new RuntimeException("事务回滚");
        } finally {
            commonService(response, result);
        }
    }

    @Override
    public void createUserList(List<User> userList) {
        userMapper.createUserList(userList);
    }

    @Override
    public void modifyUser(User user) {
        userMapper.modifyUser(user);
    }

    @Override
    public void modifyUser(HttpServletResponse response, String id, String name, String password, boolean sex, String birthday, String userType) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            Date birthdayDate = new SimpleDateFormat("yyyy-MM-dd").parse(birthday);
            User user = new User(id, name, password, sex, birthdayDate, null, UserType.valueOf(userType));
            userMapper.modifyUser(user);
            result.put("result", "200");
            result.put("message", "修改成功");
        } catch (Exception e) {
            logger.error("异常" + e);
            result.put("result", "400");
            result.put("message", "修改失败");
            throw new RuntimeException("事务回滚");
        } finally {
            commonService(response, result);
        }
    }

    @Override
    public void deleteUser(String user) {
        userMapper.deleteUser(user);
    }

    @Override
    public void deleteUserList(List<String> userList) {
        userMapper.deleteUserList(userList);
    }

    @Override
    public void deleteUser(HttpServletResponse response, String user) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            userMapper.deleteUserList(Arrays.asList(user.split(",")));
            result.put("result", "200");
            result.put("message", "删除成功");
        } catch (Exception e) {
            logger.error("异常" + e);
            result.put("result", "400");
            result.put("message", "删除失败");
            throw new RuntimeException("事务回滚");
        } finally {
            commonService(response, result);
        }
    }

    @Override
    public void testTransaction() {
        User user1 = new User(IdUtil.getId(), "name-transaction1", "123456", true, new Date(), new Date(), UserType.COMMON);
        User user2 = new User(IdUtil.getId(), "name-transaction2", "123456", true, new Date(), new Date(), UserType.COMMON);
        userMapper.createUser(user1);
        int i = 1;
        logger.info("测试事务" + 100 / (i - 1));
        userMapper.createUser(user2);
    }

    @Override
    public void testTransaction2() {
        try {
            User user1 = new User(IdUtil.getId(), "name-transaction1", "123456", true, new Date(), new Date(), UserType.COMMON);
            User user2 = new User(IdUtil.getId(), "name-transaction2", "123456", true, new Date(), new Date(), UserType.COMMON);
            userMapper.createUser(user1);
            int i = 1;
            logger.info("测试事务" + 100 / (i - 1));
            userMapper.createUser(user2);
        } catch (Exception e) {
            logger.error("测试事务发生异常" + e);
            throw new RuntimeException("事务回滚");
        }
    }

    @Override
    public void getUser(String user) {
        User userResult = userMapper.getUser(user);
        logger.info(userResult);
    }

    @Override
    public void getUserList() {
        List<User> userList = userMapper.getUserList(null);
        if (userList != null) {
            logger.info("共" + userList.size() + "条数据");
            for (int i = 0; i < userList.size(); i++) {
                logger.info(userList.get(i));
            }
        }
    }

    @Override
    public void getUserByName(String name) {
        //User userResult = userMapper.getUserByName1(name);
        //logger.info(userResult);
        List<User> userList = userMapper.getUserByName2(name);
        if (userList != null) {
            logger.info("共" + userList.size() + "条数据");
            for (int i = 0; i < userList.size(); i++) {
                logger.info(userList.get(i));
            }
        }
    }

    @Override
    public void getUserCount() {
        int count = userMapper.getUserCount();
        logger.info(count);
    }

    @Override
    public void getUserListByPage() {
        Map<String, Object> condition = new HashMap<String, Object>();
        condition.put("first", 0);
        condition.put("count", 5);
        condition.put("userType", UserType.COMMON.name());
        int number = new Random().nextInt(24 * 3);//生日跨度是72个小时~=3天.
        Date smallBirthday = new Date(new Date().getTime() - 1000 * 60 * 60 * number);
        logger.info("smallBirthday=" + smallBirthday);
        condition.put("smallBirthday", smallBirthday);
        condition.put("bigBirthday", new Date());
        List<User> userList = userMapper.getUserList(condition);
        if (userList != null) {
            logger.info("共" + userList.size() + "条数据");
            for (int i = 0; i < userList.size(); i++) {
                logger.info(userList.get(i));
            }
        }
    }

    @Override
    public void getUserListByPage(HttpServletResponse response) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            Map<String, Object> condition = new HashMap<String, Object>();
            condition.put("first", 0);
            condition.put("count", 5);
            List<User> userList = userMapper.getUserList(condition);
            if (userList != null) {
                logger.info("共" + userList.size() + "条数据");
                for (int i = 0; i < userList.size(); i++) {
                    logger.info(userList.get(i));
                }
            }
            result.put("result", "200");
            result.put("message", userList);
        } catch (Exception e) {
            logger.error("异常" + e);
            result.put("result", "400");
            result.put("message", null);
            throw new RuntimeException("事务回滚");
        } finally {
            commonService(response, result);
        }
    }
}
