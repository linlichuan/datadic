package com.llc.springcloud.dbtool.service;

import com.llc.springcloud.dbtool.service.api.IIndexService;
import com.llc.springcloud.dbtool.config.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "api-service",configuration = FeignClientConfig.class,
        fallbackFactory = FeignClientConfig.HystrixClientFactory.class)
public interface IIndexClient extends IIndexService {
}
