package com.llc.springcloud.zhservice.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AspectLogConfig {
	
	@Pointcut("execution(* com.llc.springcloud.zhservice.controller..*.*(..))")
	public void pointCutBeforeController() {
	
	}
	
	@Before("pointCutBeforeController()")
	public void before(ProceedingJoinPoint point) {
		point.getArgs();
	}
	
}
