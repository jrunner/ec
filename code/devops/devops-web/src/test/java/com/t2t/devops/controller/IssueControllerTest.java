package com.t2t.devops.controller;

import com.t2t.devops.BaseTest;
import com.t2t.top.devops.model.po.Issue;
import com.t2t.top.devops.util.BeanUtils;
import com.t2t.top.devops.util.RestUtils;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yangpengfei on 2015/11/6.
 */
public class IssueControllerTest extends BaseTest {
    String url = "http://localhost:8080/devops-web";

    @Test
    public void test_query() {
        Map param = new HashMap();
        param.put("name", "杨鹏飞");



        Object json = RestUtils.post(url + "/issue/query", param);
        System.out.println(json);
    }

    @Test
    public void test_update() {
        try {
            Issue issue = new Issue();
            issue.setBugLevel(1);
            issue.setDealOwner("yangpengfei");
            //issue.setDealTime(new Date());
            issue.setDescription("测试");
            issue.setSolution("测试");
            issue.setTitle("测试");

            Map param = BeanUtils.objectToMap(issue);
            param.put("dealTime",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            Object json = RestUtils.post(url + "/issue/update", param);
            System.out.println(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
