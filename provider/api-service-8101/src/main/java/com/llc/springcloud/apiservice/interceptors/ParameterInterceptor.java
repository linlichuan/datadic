package com.llc.springcloud.apiservice.interceptors;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
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
        DefaultParameterHandler statementHandler = (DefaultParameterHandler) invocation.getTarget();

        // 反射获取 BoundSql 对象，此对象包含生成的sql和sql的参数map映射
        Field boundSqlField = statementHandler.getClass().getDeclaredField("boundSql");
        boundSqlField.setAccessible(true);
        BoundSql boundSql = (BoundSql) boundSqlField.get(statementHandler);
        String sql = boundSql.getSql();
        System.out.println(sql);
        return invocation.proceed();
    }
}
