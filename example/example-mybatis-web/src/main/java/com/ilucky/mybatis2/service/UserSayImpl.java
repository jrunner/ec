package com.ilucky.mybatis2.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ilucky.mybatis2.mapper.UserSayMapper;
import com.ilucky.mybatis2.model.UserSay;

/**
 * @author IluckySi
 * @since 20151014
 */
@Service("userSayService")
@Transactional(rollbackFor = Exception.class)
public class UserSayImpl implements UserSayService {

	private static Logger logger = Logger.getLogger(UserSayImpl.class);
	
	@Autowired
	private UserSayMapper userSayMapper;
	
	@Override
	public void createUserSayList(List<UserSay> userSayList) {
		userSayMapper.createUserSayList(userSayList);
	}

	@Override
	public void getUserSayListByUser1(String user) {
		List<UserSay> userSayList = userSayMapper.getUserSayListByUser1(user);
		if(userSayList != null) {
			logger.info("共"+userSayList.size()+"条数据");
			for(int i = 0; i < userSayList.size(); i++) {
				UserSay userSay = userSayList.get(i);
				logger.info(userSay);
			}
		}
	}
	
	@Override
	public void getUserSayListByUser2(String user) {
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("user", user);
		List<UserSay> userSayList = userSayMapper.getUserSayListByUser2(m);
		if(userSayList != null) {
			logger.info("共"+userSayList.size()+"条数据");
			for(int i = 0; i < userSayList.size(); i++) {
				UserSay userSay = userSayList.get(i);
				logger.info(userSay);
			}
		}
	}
	
	@Override
	public void getUserSayListByUser3(String user) {
		List<Map<String, Object>> mapList = userSayMapper.getUserSayListByUser3(user);
		if(mapList != null) {
			logger.info("共"+mapList.size()+"条数据");
			for(int i = 0; i < mapList.size(); i++) {
				Map<String, Object> map = mapList.get(i);
				for(Entry<String, Object> entry : map.entrySet()) {
					logger.info(entry.getKey()+"="+entry.getValue());
				}
			}
		}
	}
}
