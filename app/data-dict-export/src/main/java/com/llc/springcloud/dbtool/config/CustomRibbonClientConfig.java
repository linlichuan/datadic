package com.llc.springcloud.dbtool.config;

import com.netflix.loadbalancer.BestAvailableRule;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RibbonClient(name = "api-service",configuration = CustomRibbonClientConfig.mRibbonClientConfig.class)
//针对所有的ribbon客户端生效
//@RibbonClients(defaultConfiguration = CustomRibbonClientConfig.mRibbonClientConfig.class)
public class CustomRibbonClientConfig {

    @Configuration
    static class mRibbonClientConfig{

        @Bean
        public IRule ribbonRule(){
            return new RoundRobinRule();
        }

    }
}
