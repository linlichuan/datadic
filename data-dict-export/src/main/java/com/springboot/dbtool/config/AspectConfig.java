package com.springboot.dbtool.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AspectConfig {


    @Pointcut("@annotation(com.springboot.dbtool.annotation.DataSourceSwitch)")
    public void pointcut(){

    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable{
        Object[] objects = point.getArgs();
        String dataSourceKey = (String) objects[0];

        if (dataSourceKey == null){
            DynamicDataSourceRouter.setContextKey("oa");
        }else {
            DynamicDataSourceRouter.setContextKey(dataSourceKey);
        }

        try {
            return point.proceed();
        }finally {

        }
    }
}
