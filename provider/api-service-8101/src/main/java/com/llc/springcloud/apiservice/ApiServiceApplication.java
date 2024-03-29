package com.llc.springcloud.apiservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;
import com.llc.springcloud.apiservice.config.DynamicDataSource;


@SpringBootApplication(scanBasePackages = {"com.llc.springcloud.web.*", "com.llc.springcloud.apiservice.*"})
@EnableDiscoveryClient
@EntityScan(basePackages = {"com.llc.springcloud.common.hibernate"})
@Import({DynamicDataSource.class})
public class ApiServiceApplication {

    private static final Logger log = LoggerFactory.getLogger(ApiServiceApplication.class);
    
    public static void main(String[] args) {
        MDC.put("first", "lin");
        MDC.put("last", "lichuan");
        SpringApplication.run(ApiServiceApplication.class,args);
    }

}
