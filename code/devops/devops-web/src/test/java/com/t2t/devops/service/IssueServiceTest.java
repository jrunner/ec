package com.t2t.devops.service;

import com.t2t.devops.BaseTest;
import com.t2t.top.devops.model.po.Issue;
import com.t2t.top.devops.service.IssueService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

/**
 * Created by yangpengfei on 2015/11/7.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-core.xml"})
public class IssueServiceTest extends BaseTest {

    @Autowired
    public IssueService issueService;

    @Test
    public void test_add() {
        try {
            Issue issue = new Issue();
            issue.setBugLevel(1);
            issue.setDealOwner("yangpengfei");
            issue.setDealTime(new Date());
            issue.setDescription("页面显示错误，连接失效");
            issue.setSolution("原因js缺少个逗号");
            issue.setTitle("页面显示错误");
            issueService.add(issue);
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
    }

    @Test
    public void test_query() {
        try {
            Issue issue = new Issue();
            List list = issueService.query(issue);
            println(list);
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
    }

}
