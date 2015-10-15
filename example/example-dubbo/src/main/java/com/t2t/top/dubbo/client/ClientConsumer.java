package com.t2t.top.dubbo.client;

/**
 * 服务提供者
 *
 * @author yangpengfei
 */

import com.t2t.top.dubbo.service.DemoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ClientConsumer {

    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"dubbo-consumer.xml"});
        context.start();

        // 获取远程服务代理
        DemoService demoService = (DemoService) context.getBean("demoService");
        // 执行远程方法,并打印
        System.out.println(demoService.sayHello("yangpengfei"));
        // 执行远程方法,并打印
        System.out.println(demoService.getLength("taobao"));
    }

}