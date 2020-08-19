package com.llc.springcloud.dbtool.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAspectConfig {

    static Logger log = LoggerFactory.getLogger(LogAspectConfig.class);

//    @Pointcut("execution(* com.springboot.dbtool.controller..*.*(..))")
    public void pointcut(){
        log.info("pointcut切入点");
    }

//    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point){
        Object[] objects = point.getArgs();
        log.info("logs参数数量" + objects.length);
        for (int i = 0; i < objects.length; i++) {
            log.info(objects[i].getClass().getCanonicalName());
        }
        try {
            return point.proceed();
        }catch (Throwable t){
            t.printStackTrace();
            return null;
        }
    }
}
