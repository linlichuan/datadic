package com.llc.springcloud.dbtool;

import com.llc.springcloud.dbtool.config.CustomRibbonClientConfig;
import com.llc.springcloud.dbtool.config.FeignClientConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@ComponentScan(basePackages = {"com.llc.springcloud.*"},
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
                classes = {
                        FeignClientConfig.class,
                        CustomRibbonClientConfig.class
                })}
        )
@EnableFeignClients(basePackages = "com.llc.springcloud.dbtool.service")
@EnableDiscoveryClient
@EnableHystrix
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
