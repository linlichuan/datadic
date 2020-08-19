package com.llc.springcloud.dbtool.service;

import com.llc.springcloud.apiclient.compute.ComputeService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "compute-service")
public interface ComputeClient extends ComputeService {
}
