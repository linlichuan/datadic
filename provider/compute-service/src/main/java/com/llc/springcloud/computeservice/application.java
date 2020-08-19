package com.llc.springcloud.computeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class application {
    public static void main(String[] args) {
        SpringApplication.run(application.class,args);
    }
}
