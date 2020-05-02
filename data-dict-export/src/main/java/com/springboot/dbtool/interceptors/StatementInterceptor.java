package com.springboot.dbtool.interceptors;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

@Intercepts({
        @Signature(type = StatementHandler.class,method = "prepare",args = {Connection.class,Integer.class}),
        @Signature(type = StatementHandler.class,method = "parameterize",args = {Statement.class}),
        @Signature(type = StatementHandler.class,method = "batch",args = {Statement.class}),
        @Signature(type = StatementHandler.class,method = "update",args = {Statement.class}),
        @Signature(type = StatementHandler.class,method = "query",args = {Statement.class, ResultHandler.class})
})
@Component
public class StatementInterceptor implements Interceptor {

    @Override
    public void setProperties(Properties properties) {

    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        return invocation.proceed();
    }

    public static void main(String[] arg){
        List<String> list = Arrays.asList("三年级","五年级","初一","六年级","高三","初二","一年级");

        list.sort((o1,o2) -> {
            if (Objects.equals(o1,o2)){
                return 0;
            }
            String[] sortList = {"一年级","二年级","三年级","四年级","五年级","六年级","初一","初二","初三","高一","高二","高三"};
            int int1 = 0,int2 = 0;
            for (String s : sortList) {
                if (s.equals(o1)){
                    int1 = 1;break;
                }
                if (s.equals(o2)){
                    int2 = 1;break;
                }
            }
            return int2 - int1;
        });

        System.out.println(list);
    }
}
