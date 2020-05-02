package com.springboot.dbtool;

import com.springboot.dbtool.config.DynamicDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@ComponentScan(basePackages = {"com.springboot.dbtool.*"})
@Import({DynamicDataSource.class})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
