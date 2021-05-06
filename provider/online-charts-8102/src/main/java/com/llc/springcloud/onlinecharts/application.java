package com.llc.springcloud.onlinecharts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = {"com.llc.springcloud.onlinecharts"})
@EnableDiscoveryClient
public class application {
    public static void main(String[] args) {
        SpringApplication.run(application.class,args);
    }
}
