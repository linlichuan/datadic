package com.llc.springcloud.apiservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;
import com.llc.springcloud.apiservice.config.DynamicDataSource;


@SpringBootApplication
@EnableDiscoveryClient
@Import({DynamicDataSource.class})
public class application {

    public static void main(String[] args) {
        SpringApplication.run(application.class,args);
    }

}
