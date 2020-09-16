package com.llc.springcloud.apiservice.controller;

import com.llc.springcloud.apiservice.dto.TableConnectMsgDto;
import com.llc.springcloud.apiservice.service.IIndexService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;


@RestController
@RequestMapping("/dbTool")
@Api(tags = {"导出表结构接口"})
public class DbToolController {

    static Logger log = LoggerFactory.getLogger(DbToolController.class);

    @Autowired
    IIndexService indexService;

    @ApiOperation(value = "导出表结构",httpMethod = "GET")
    @ApiOperationSupport(params = @DynamicParameters(name = "json", properties = {
            @DynamicParameter(name = "dataSourceKey",value = "jdbc.properties中数据源的前缀",dataTypeClass = String.class),
            @DynamicParameter(name = "schema",value = "数据库名",dataTypeClass = String.class)
    }))
    @RequestMapping(value = "/export/{dataSourceKey}/{schema}",method = RequestMethod.GET)
    public String exportTableInfo(@PathVariable String dataSourceKey, @PathVariable String schema, HttpServletResponse response){
        try {
            log.info("??????");
            response.setContentType("application/msword");
            response.setHeader("Content-disposition", "attachment;filename="+ URLEncoder.encode(dataSourceKey + System.currentTimeMillis() + ".docx", "utf-8"));
            indexService.exportTableInfo(dataSourceKey,schema,response);
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return "success";
    }

    @ApiOperation(value = "导出表结构，可以传数据库链接")
    @RequestMapping(value = "/export", method = RequestMethod.POST)
    public String customExportTableInfo(TableConnectMsgDto tableConnectMsgDto) {
        return "";
    }

    @ApiOperation(value = "获取端口",httpMethod = "GET")
    @RequestMapping(value = "/host",method = RequestMethod.GET)
    public String getMyPort(){
        return indexService.getMyHost();
    }
}
