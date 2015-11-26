package com.t2t.top.dubbo.service;

import com.t2t.top.dubbo.model.UserDto;

public interface DemoService {
    public String sayHello(String name);

    public int getLength(String name);

    //传递对象
    public String add(UserDto dto);

    //返回对象
    public UserDto get(int id);
}  