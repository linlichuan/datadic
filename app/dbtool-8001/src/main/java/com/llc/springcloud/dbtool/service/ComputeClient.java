package com.llc.springcloud.dbtool.service;

import com.llc.springcloud.dbtool.service.api.ComputeService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "compute-service")
public interface ComputeClient extends ComputeService {
}
