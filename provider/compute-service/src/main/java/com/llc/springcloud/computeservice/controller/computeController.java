package com.llc.springcloud.computeservice.controller;

import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/compute")
@Api(tags = {"数学计算接口"})
public class computeController {

    @ApiOperation(value = "加运算",httpMethod = "POST")
    @ApiOperationSupport(params = @DynamicParameters(name = "json", properties = {
            @DynamicParameter(name = "a",value = "被加数",dataTypeClass = Integer.class),
            @DynamicParameter(name = "b",value = "加数",dataTypeClass = Integer.class)
    }))
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Integer add(@RequestParam("a") Integer a,@RequestParam("b") Integer b){
        return a + b;
    }

}
