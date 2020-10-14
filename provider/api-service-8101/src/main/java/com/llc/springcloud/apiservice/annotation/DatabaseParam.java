package com.llc.springcloud.apiservice.annotation;

import com.llc.springcloud.apiservice.enums.DatabaseParamEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface DatabaseParam {
	
	DatabaseParamEnum type();
	
}
