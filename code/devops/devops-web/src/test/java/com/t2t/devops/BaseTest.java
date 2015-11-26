package com.t2t.devops;

import com.t2t.top.base.utils.json.GsonUtils;
import org.junit.After;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

/**
 * Created by yangpengfei on 2015/10/19.
 */
public class BaseTest {

    protected Logger logger = LoggerFactory.getLogger(BaseTest.class);

    Long st = 0L;
    Long et = 0L;

    @Before
    public void before() {
        System.out.println("方法开始");
        st = System.currentTimeMillis();
    }

    @After
    public void after() {
        et = System.currentTimeMillis();
        System.out.println("耗时：" + (et - st) + "毫秒\n");
    }


    public void println(Object... beans) {
        println(Arrays.asList(beans));
    }

    public void println(List list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(GsonUtils.toJson(list.get(i)));
        }
    }
}
