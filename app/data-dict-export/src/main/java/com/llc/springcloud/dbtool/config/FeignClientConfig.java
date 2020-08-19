package com.llc.springcloud.dbtool.config;

import feign.Feign;
import feign.Logger;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;

@Configuration
@Import(FeignClientsConfiguration.class)
public class FeignClientConfig {

    /**
     * feign log
     * */
    @Bean
    public Logger.Level loggerLevel(){
        return Logger.Level.FULL;
    }

    /**
     * Hystrix支持
     * */
//    @Bean
//    @Scope("prototype")
//    public Feign.Builder hystrixSupport(){
//        return Feign.builder();
//    }

}
