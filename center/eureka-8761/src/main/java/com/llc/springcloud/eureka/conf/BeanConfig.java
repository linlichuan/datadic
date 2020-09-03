package com.llc.springcloud.eureka.conf;

import com.llc.springcloud.eureka.TestObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean("testObject1")
    public TestObject getTestObject1(){
        return new TestObject("testObject1");
    }

    @Bean("testObject2")
    public TestObject getTestObject2(){
        return new TestObject("testObject2");
    }

}
