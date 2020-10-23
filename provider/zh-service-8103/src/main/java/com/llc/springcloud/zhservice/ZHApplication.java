package com.llc.springcloud.zhservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = {"com.llc.springcloud.zhservice"})
@EnableDiscoveryClient
public class ZHApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZHApplication.class,args);
    }

}
