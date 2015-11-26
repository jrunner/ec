package com.t2t.top.dubbo.client;


import com.t2t.top.dubbo.model.Account;
import com.t2t.top.dubbo.model.UserDto;
import com.t2t.top.dubbo.service.DemoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 服务提供者
 *
 * @author yangpengfei
 */
public class ClientConsumer {

    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"dubbo-consumer.xml"});
        context.start();

        // 获取远程服务代理
        DemoService demoService = (DemoService) context.getBean("demoService");
        // 普通方法测试，返回字符串
        System.out.println(demoService.sayHello("yangpengfei"));
        // 普通方法测试，返回整型
        System.out.println(demoService.getLength("taobao"));

        List addresses = new ArrayList();
        addresses.add("北京市海淀区");
        addresses.add("北京市朝阳区");
        Account account = new Account();
        account.setAccount("yangpengfei");
        account.setPassword("123456789");
        account.setCreateTime(new Date());
        List accounts = new ArrayList();
        accounts.add(account);

        UserDto dto = new UserDto();
        dto.setName("杨鹏飞");
        dto.setAge(30);
        dto.setId(1);
        dto.setAddresses(addresses);
        dto.setAccounts(accounts);

        //测试传递参数为对象
        System.out.println(demoService.add(dto));
        //测试返回指为对象
        System.out.println(demoService.get(1).getName());
    }

}