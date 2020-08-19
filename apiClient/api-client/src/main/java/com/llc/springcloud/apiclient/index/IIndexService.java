package com.llc.springcloud.apiclient.index;

import feign.Response;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface IIndexService {
    @RequestMapping(value = "/index/export/{dataSourceKey}/{schema}",method = RequestMethod.GET)
    feign.Response exportTableInfo(@PathVariable("dataSourceKey") String dataSourceKey, @PathVariable("schema") String schema) throws Exception;

    @RequestMapping(value = "/index/host",method = RequestMethod.GET)
    String getHost();
}
