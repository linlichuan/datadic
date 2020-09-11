package com.llc.springcloud.apiservice.config;

import com.llc.springcloud.apiservice.interceptors.DataSourceInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyWebMvcConfigure implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new DataSourceInterceptor()).addPathPatterns("/**");
	}
}
