package com.springboot.dbtool.interceptors;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.util.Properties;

@Intercepts({
        @Signature(type = ParameterHandler.class, method = "getParameterObject",args = {}),
        @Signature(type = ParameterHandler.class, method = "setParameters",args = {PreparedStatement.class})
})
@Component
public class ParameterInterceptor implements Interceptor {

    @Override
    public void setProperties(Properties properties) {

    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        return invocation.proceed();
    }
}
