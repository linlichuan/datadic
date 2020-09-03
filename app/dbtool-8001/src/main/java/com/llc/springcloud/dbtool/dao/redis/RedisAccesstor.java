package com.llc.springcloud.dbtool.dao.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class RedisAccesstor {

    @Scope("singleton")
    @Bean
    public StrRedis getStringRedis(){
        return new StrRedis();
    }

    @Scope("singleton")
    @Bean
    public SRedis getSetRedis(){
        return new SRedis();
    }

    @Scope("singleton")
    @Bean
    public ZsRedis getSortedSetRedis(){
        return new ZsRedis();
    }

    @Scope("singleton")
    @Bean
    public HRedis getHashRedis(){
        return new HRedis();
    }

    @Scope("singleton")
    @Bean
    public LRedis getLishRedis(){
        return new LRedis();
    }


    class SRedis{

    }

    class StrRedis{

    }

    class ZsRedis{

    }

    class HRedis{

    }

    class LRedis{

    }

}
