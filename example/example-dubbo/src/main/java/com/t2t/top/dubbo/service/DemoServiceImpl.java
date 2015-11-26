package com.t2t.top.dubbo.service;

import com.alibaba.dubbo.common.json.JSONArray;
import com.alibaba.dubbo.common.json.JSONObject;
import com.google.gson.Gson;
import com.t2t.top.dubbo.model.UserDto;

public class DemoServiceImpl implements DemoService {

    public int getLength(String name) {
        if (name == null) {
            return -1;
        }
        return name.length();
    }


    public String sayHello(String name) {
        return "Hello " + name;
    }

    public String add(UserDto dto) {
        System.out.println(new Gson().toJson(dto));
        return "ok";
    }


    public UserDto get(int id) {
        String json = "{\"id\":1,\"name\":\"杨鹏飞\",\"age\":30,\"addresses\":[\"北京市海淀区\",\"北京市朝阳区\"],\"accounts\":[{\"account\":\"yangpengfei\",\"password\":\"123456789\",\"createTime\":\"Oct 17, 2015 12:58:25 PM\"}]}";
        UserDto dto = new Gson().fromJson(json, UserDto.class);
        return dto;
    }
}  