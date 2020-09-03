package com.llc.springcloud.dbtool.config;

import com.llc.springcloud.dbtool.service.IIndexClient;
import feign.Feign;
import feign.Logger;
import feign.Response;
import feign.hystrix.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

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

    @Component
    static public class HystrixClientFactory implements FallbackFactory<IIndexClient>{
        @Override
        public IIndexClient create(Throwable cause) {
            return new IIndexClient() {
                @Override
                public Response exportTableInfo(String dataSourceKey, String schema) throws Exception {
                    return null;
                }

                @Override
                public String getHost() {
                    return "null";
                }
            };
        }
    }
}
