package com.llc.springcloud.apiservice.interceptors;

import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.springframework.stereotype.Component;

import java.sql.CallableStatement;
import java.sql.Statement;
import java.util.Properties;

@Intercepts({
        @Signature(type = ResultSetHandler.class,method = "handleResultSets",args = {Statement.class}),
        @Signature(type = ResultSetHandler.class,method = "handleOutputParameters",args = {CallableStatement.class})
})
@Component
public class ResultSetInterceptor implements Interceptor {

    @Override
    public void setProperties(Properties properties) {

    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        ResultSetHandler resultSetHandler = (ResultSetHandler)invocation.getTarget();
        return invocation.proceed();
    }
}
