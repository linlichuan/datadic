package com.llc.springcloud.apiservice.annotation;

import com.llc.springcloud.apiservice.enums.DatabaseParamEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 设置数据源注解，DatabaseParamEnum 设置DATA_SOURCE为使用已缓存的数据源，否则用新增的数据源
 * */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface DatabaseParam {
	
	DatabaseParamEnum type();
	
}
