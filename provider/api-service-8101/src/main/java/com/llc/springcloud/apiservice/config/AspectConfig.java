package com.llc.springcloud.apiservice.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.llc.springcloud.apiservice.annotation.DatabaseParam;
import com.llc.springcloud.apiservice.dto.TableConnectMsgDto;
import com.llc.springcloud.apiservice.enums.DatabaseParamEnum;
import com.llc.springcloud.util.EncryptUtil;
import com.llc.springcloud.util.StringUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

@Component
@Aspect
public class AspectConfig {

    @Resource
    DataSource dataSource;
    
    final static String INFORMATION_SCHEMA = "information_schema";
    
    Map<String, Method> methodCache = new HashMap<>();
    Map<String, Method> dataSourceCache = new HashMap<>();

    @Pointcut("@annotation(com.llc.springcloud.apiservice.annotation.DataSourceSwitch)")
    public void pointcut(){

    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable{
        Object[] objects = point.getArgs();
        if (objects.length > 0) {
            String dataSourceKey = "";
            TableConnectMsgDto dto = new TableConnectMsgDto();
            Object target = point.getTarget();
            Method method = getMethodAndCache(target.getClass(), point.getSignature().getName());
            if (method != null) {
                Parameter[] parameters = method.getParameters();
                for (int i = 0; i < objects.length; i++) {
                    if (StringUtil.isNotBlank(dataSourceKey)) {
                        break;
                    }
                    Parameter parameter = parameters[i];
                    Class<?> clz = parameter.getType();
                    if (clz != String.class) {
                        continue;
                    }
                    String param = (String) objects[i];
                    DatabaseParam databaseParam = parameter.getDeclaredAnnotation(DatabaseParam.class);
                    if (databaseParam != null) {
                        DatabaseParamEnum paramEnum = databaseParam.type();
                        switch (paramEnum) {
                            case DATA_SOURCE:
                                dataSourceKey = param;
                                break;
                            case URL:
                                dto.setUrl(param);
                                break;
                            case USER_NAME:
                                dto.setUserName(param);
                                break;
                            case PASSWORD:
                                dto.setPassword(param);
                                break;
                            default:
                        }
                    }
                }
                if (StringUtil.isNotBlank(dataSourceKey)) {
                    DynamicDataSourceRouter.setContextKey(dataSourceKey);
                } else {
                    if (StringUtil.isNotBlank(dto.getUrl()) && StringUtil.isNotBlank(dto.getUserName()) && StringUtil.isNotBlank(dto.getPassword())) {
                        String key = buildKey(dto);
                        if (!dataSourceCache.containsKey(key)) {
                            DruidDataSource druidDataSource = DruidDataSourceBuilder.create().build();
                            druidDataSource.setUrl(getUrl(dto.getUrl()));
                            druidDataSource.setUsername(dto.getUserName());
                            druidDataSource.setPassword(dto.getPassword());
                            DynamicDataSourceRouter dynamicDataSource = (DynamicDataSourceRouter) dataSource;
                            dynamicDataSource.putNewDataSource(key, druidDataSource);
                            DynamicDataSourceRouter.setContextKey(key);
                        }
                    }
                }
            }
        }
        return point.proceed();
    }
    
    public String buildKey(TableConnectMsgDto dto) {
        return EncryptUtil.sha(dto.getUrl());
    }
    
    public Method getMethodAndCache(Class<?> target, String methodStr) {
        if (methodCache.containsKey(methodStr)) {
            return methodCache.get(methodStr);
        }
        Method[] methods = target.getDeclaredMethods();
        Method method = Stream.of(methods).filter(m -> m.getName().equals(methodStr)).findFirst().orElse(null);
        methodCache.put(methodStr, method);
        return method;
    }
    
    public String getUrl(String url) {
        StringBuilder sb = new StringBuilder(url);
        int slashIdx = sb.lastIndexOf("/");
        if (slashIdx == sb.length() - 1) {
            sb.append(INFORMATION_SCHEMA);
        } else {
            sb.append("/").append(INFORMATION_SCHEMA);
        }
        return sb.toString();
    }
}