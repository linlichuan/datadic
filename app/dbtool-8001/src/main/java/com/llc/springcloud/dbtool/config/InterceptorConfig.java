package com.llc.springcloud.dbtool.config;

import com.llc.springcloud.dbtool.interceptors.ApiInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InterceptorConfig {

    @Bean
    public ApiInterceptor getApiInterceptpr(){
        return new ApiInterceptor();
    }
}
