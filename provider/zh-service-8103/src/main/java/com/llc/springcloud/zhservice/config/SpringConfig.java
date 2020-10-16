package com.llc.springcloud.zhservice.config;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
	
	@Bean
	public MapperScannerConfigurer mapperScannerConfigurer() {
		MapperScannerConfigurer bean = new MapperScannerConfigurer();
		bean.setBasePackage("com.llc.springcloud.zhservice.dao");
		return bean;
	}
}
