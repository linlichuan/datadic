package com.llc.springcloud.dbtool.controller;

import com.llc.springcloud.dbtool.dao.redis.RedisAccesstor;
import com.llc.springcloud.dbtool.service.ComputeClient;
import com.llc.springcloud.dbtool.service.IIndexClient;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;


@RestController
@RequestMapping("/index")
@Api(tags = {"导出接口"})
public class IndexController {
    static Logger log = LoggerFactory.getLogger(IndexController.class);

    @Value("${testEnv}")
    String currentEnv;

    @Autowired
    IIndexClient iIndexClient;

    @Autowired
    ComputeClient computeClient;

    @Autowired
    RedisAccesstor redisAccesstor;

    @ApiOperation(value = "导出表结构",httpMethod = "GET")
    @ApiOperationSupport(params = @DynamicParameters(name = "json", properties = {
            @DynamicParameter(name = "dataSourceKey",value = "jdbc.properties中数据源的前缀",dataTypeClass = String.class),
            @DynamicParameter(name = "scheme",value = "数据库名",dataTypeClass = String.class)
    }))
    @RequestMapping(value = "/export/{dataSourceKey}/{scheme}",method = RequestMethod.GET)
    public String exportTableInfo(@PathVariable String dataSourceKey, @PathVariable String scheme, HttpServletResponse response){
        InputStream ips = null;
        OutputStream ops = null;
        try {
            response.setContentType("application/msword");
            response.setHeader("Content-disposition", "attachment;filename="+ URLEncoder.encode(dataSourceKey + System.currentTimeMillis() + ".docx", "utf-8"));

            feign.Response resp = iIndexClient.exportTableInfo(dataSourceKey,scheme);
            ips = resp.body().asInputStream();
            ops = response.getOutputStream();
            int len = 0;
            byte[] bt = new byte[1024];
            while ((len = ips.read(bt)) > 0){
                ops.write(bt);
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (ops != null){
                try {
                    ops.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (ips != null){
                try {
                    ips.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "success";
    }

    @ApiOperation(value = "获取端口",httpMethod = "GET")
    @RequestMapping(value = "/host",method = RequestMethod.GET)
    public String getHost(){
        return iIndexClient.getHost();
    }

    @ApiOperation(value = "获取当前环境",httpMethod = "GET")
    @RequestMapping(value = "/env",method = RequestMethod.GET)
    public String getEnv(){
        return this.currentEnv;
    }
}
