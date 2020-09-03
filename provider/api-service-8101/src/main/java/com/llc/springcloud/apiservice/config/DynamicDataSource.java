package com.llc.springcloud.apiservice.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.alibaba.fastjson.JSONObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.*;

@Configuration
@Component
public class DynamicDataSource {

    public static String _MAIN_DATASOURCE = "_main";

    public List<DataSource> change(){
        DruidDataSource druidDataSource = DruidDataSourceBuilder.create().build();
        return null;
    }

    /**
     * 默认的数据源
    * @author llc
    * @Date 2020/1/2
    **/
    @Bean
    public DataSource getMainDataSource() throws Exception{
        DruidDataSource druidDataSource;
        Map<Object,Object> dataSources = getMultiDataSource();
        if (!dataSources.containsKey(_MAIN_DATASOURCE)){
            throw new Exception();
        }
        druidDataSource = (DruidDataSource) dataSources.get(_MAIN_DATASOURCE);
        return druidDataSource;
    }

    /**
     * 读取jdbc.properties
    * @author llc
    * @Date 2020/1/2
    **/
    @Bean
    public Map<Object,Object> getMultiDataSource(){
        Map<Object,Object> map = new HashMap<>();
        JSONObject result = new JSONObject();
        Resource resource = new ClassPathResource("/jdbc.properties");
        Properties props;
        try {
            props = PropertiesLoaderUtils.loadProperties(resource);
            Set set = props.keySet();
            Iterator<String> it = set.iterator();
            while (it.hasNext()) {
                String dataSource = null;
                String prop = null;
                String key = it.next();
                dataSource = key.substring(0,key.indexOf("."));
                prop = key.substring(key.indexOf(".") + 1);

                if (result.containsKey(dataSource)){
                    JSONObject pair = result.getJSONObject(dataSource);
                    pair.put(prop,props.getProperty(key));
                }else {
                    JSONObject json = new JSONObject();
                    json.put(prop,props.getProperty(key));
                    result.put(dataSource,json);
                }
            }

            if (!result.isEmpty()){
                Iterator<String> dsIt = result.keySet().iterator();
                while (dsIt.hasNext()) {
                    String dsKey = dsIt.next();
                    JSONObject pair = result.getJSONObject(dsKey);
                    DruidDataSource druidDataSource = DruidDataSourceBuilder.create().build();
                    druidDataSource.setUrl(pair.getString("url"));
                    druidDataSource.setUsername(pair.getString("username"));
                    druidDataSource.setPassword(pair.getString("password"));
                    map.put(dsKey,druidDataSource);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return map;
    }

    @Bean
    @Primary
    public DynamicDataSourceRouter multiDataSource(){
        try {
            DruidDataSource mainDataSource = (DruidDataSource) getMainDataSource();
            System.out.println(mainDataSource.getUrl());
            System.out.println(mainDataSource.getUsername());
            System.out.println(mainDataSource.getPassword());
            Map<Object,Object> multiDataSource = getMultiDataSource();
            return new DynamicDataSourceRouter(mainDataSource,multiDataSource);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
