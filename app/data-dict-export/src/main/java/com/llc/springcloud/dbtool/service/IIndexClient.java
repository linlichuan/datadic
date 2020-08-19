package com.llc.springcloud.dbtool.service;

import com.llc.springcloud.apiclient.index.IIndexService;
import com.llc.springcloud.dbtool.config.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "api-service",configuration = FeignClientConfig.class)
public interface IIndexClient extends IIndexService {
}
