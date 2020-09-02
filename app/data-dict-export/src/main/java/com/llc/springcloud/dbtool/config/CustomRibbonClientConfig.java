package com.llc.springcloud.dbtool.config;

import com.netflix.loadbalancer.*;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//针对所有的ribbon客户端生效
//@RibbonClients(defaultConfiguration = CustomRibbonClientConfig.mRibbonClientConfig.class)
public class CustomRibbonClientConfig {

    @Configuration
    @RibbonClient(name = "api-service",configuration = apiRibbonClientConfig.class)
    static class apiRibbonClientConfig{

        @Bean
        public IRule ribbonRule(){
            return new RoundRobinRule();
        }

    }

    @Configuration
    @RibbonClient(name = "api-service",configuration = computeRibbonClientConfig.class)
    static class computeRibbonClientConfig{
        @Bean
        public IRule ribbonRule(){
            return new IRule() {
                @Override
                public Server choose(Object key) {
                    return null;
                }

                @Override
                public void setLoadBalancer(ILoadBalancer lb) {

                }

                @Override
                public ILoadBalancer getLoadBalancer() {
                    return null;
                }
            };
        }
    }
}
