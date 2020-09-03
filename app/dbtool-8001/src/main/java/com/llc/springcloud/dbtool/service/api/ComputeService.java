package com.llc.springcloud.dbtool.service.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

public interface ComputeService {
    @RequestMapping(value = "/compute/add",method = RequestMethod.POST)
    Integer add(@RequestParam("a") Integer a, @RequestParam("b") Integer b);
}
