package com.springboot.dbtool.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.springboot.dbtool.config.LogAspectConfig;
import com.springboot.dbtool.dao.redis.RedisAccesstor;
import com.springboot.dbtool.service.IIndexService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;


@RestController
@RequestMapping("/index")
@Api(tags = {"导出接口"})
public class IndexController {
    static Logger log = LoggerFactory.getLogger(IndexController.class);
    @Autowired
    IIndexService indexService;

    @Autowired
    RedisAccesstor redisAccesstor;

    @ApiOperation(value = "导出表结构",httpMethod = "GET")
    @ApiOperationSupport(params = @DynamicParameters(name = "json", properties = {
            @DynamicParameter(name = "dataSourceKey",value = "jdbc.properties中数据源的前缀",dataTypeClass = String.class),
            @DynamicParameter(name = "scheme",value = "数据库名",dataTypeClass = String.class)
    }))
    @RequestMapping(value = "/export/{dataSourceKey}/{scheme}",method = RequestMethod.GET)
    public String exportTableInfo(@PathVariable String dataSourceKey, @PathVariable String scheme, HttpServletResponse response){
        try {
            log.info("??????");
            response.setContentType("application/msword");
            response.setHeader("Content-disposition", "attachment;filename="+ URLEncoder.encode(dataSourceKey + System.currentTimeMillis() + ".docx", "utf-8"));
            indexService.exportTableInfo(dataSourceKey,scheme,response);
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return "success";
    }

    @RequestMapping(value = "/setRedis",method = RequestMethod.POST)
    public String setRedis(@RequestBody JSONObject json){
        String key = json.getString("key");
        String value = json.getString("value");

        redisAccesstor.set(key,value);

        return "success";
    }

    @RequestMapping(value = "/test1",method = RequestMethod.GET)
    public String test1(Integer i){
        System.out.println(i);
        return "111";
    }
    @RequestMapping(value = "/test2",method = RequestMethod.GET)
    public String test2(@Nullable Integer i){
        System.out.println(i);
        return "111";
    }
}
