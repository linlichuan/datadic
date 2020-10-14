package com.llc.springcloud.apiservice.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.lang.reflect.Field;
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

    public static void setContextKey(String key) {
        context.set(key);
    }

    public static String getContextKey() {
        return context.get();
    }

    public static void clearContextKey() {
        context.remove();
    }
    
    @Override
    protected Object resolveSpecifiedLookupKey(Object lookupKey) {
        return super.resolveSpecifiedLookupKey(lookupKey);
    }
    
    @Override
    protected DataSource resolveSpecifiedDataSource(Object dataSource) throws IllegalArgumentException {
        return super.resolveSpecifiedDataSource(dataSource);
    }
    
    public void putNewDataSource(Object lookupKey, Object object) {
        Object key = resolveSpecifiedLookupKey(lookupKey);
        DataSource dataSource = resolveSpecifiedDataSource(object);
        try {
            Field field = AbstractRoutingDataSource.class.getDeclaredField("resolvedDataSources");
            field.setAccessible(true);
            Map<Object, DataSource> resolvedDataSources = (Map<Object, DataSource>)field.get(this);
            if (!resolvedDataSources.containsKey(key)) {
                resolvedDataSources.put(key, dataSource);
                field.set(this, resolvedDataSources);
            }
        } catch (IllegalAccessException | NoSuchFieldException iae) {
            iae.printStackTrace();
        }
    }
}
