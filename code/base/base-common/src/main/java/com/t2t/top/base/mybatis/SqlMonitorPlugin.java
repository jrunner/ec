package com.t2t.top.base.mybatis;


import com.t2t.top.base.utils.MonitorUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * Created by chengmeng
 */
@Intercepts({@Signature(type = Executor.class, method ="update" ,args = {MappedStatement.class,Object.class}) ,
             @Signature(type = Executor.class, method ="query" ,args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})
})
public class SqlMonitorPlugin implements Interceptor {
    private static final Logger logger = LoggerFactory.getLogger(SqlMonitorPlugin.class);
    private static final String SQL_ERROR="SQL_ERROR";
    private static final String SQL_EXECUTE="SQL_EXECUTE";
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        long start=System.currentTimeMillis();
        try{
            return invocation.proceed();
        }catch (Throwable e){
            MonitorUtils.recordOne(SQL_ERROR);
            logger.error("sql 执行出错 ！",e);
            throw  e;
        }finally {
            MonitorUtils.recordOne(SQL_EXECUTE, System.currentTimeMillis() - start);
        }
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof Executor) {
            return Plugin.wrap(target, this);
        } else {
            return target;
        }
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
