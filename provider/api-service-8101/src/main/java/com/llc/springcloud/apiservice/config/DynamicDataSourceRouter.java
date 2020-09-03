package com.llc.springcloud.apiservice.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.Map;


public class DynamicDataSourceRouter extends AbstractRoutingDataSource {

    private static final ThreadLocal<String> context = new ThreadLocal<>();

    public DynamicDataSourceRouter(DataSource defaultTargetDataSource, Map<Object,Object> targetDataSources){
        //主数据源
        super.setDefaultTargetDataSource(defaultTargetDataSource);
        //可选数据源
        super.setTargetDataSources(targetDataSources);
        super.afterPropertiesSet();
    }

    /**
     * 每个sql连接调用一次
    * @author llc
    * @Date 2020/1/2
    **/
    @Override
    protected Object determineCurrentLookupKey() {
        return context.get();
    }

    public static void setContextKey(String key){
        context.set(key);
    }

    public static void clearContextKey(){
        context.remove();
    }
}
