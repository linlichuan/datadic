package com.llc.springcloud.eureka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableEurekaServer
@SpringBootApplication
@RestController
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Autowired
    public TestObject testObject1;

    @Autowired
    public TestObject testObject2;

    @RequestMapping("/index/test")
    public String test(){
        return testObject1.getName() + " ---- " + testObject2.getName();
    }

}
