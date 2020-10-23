package com.llc.springcloud.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = {"com.llc.springcloud.gateway"})
@EnableDiscoveryClient
public class GateApplication {
    public static void main(String[] args) {
        SpringApplication.run(GateApplication.class,args);
    }
}
