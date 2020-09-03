package com.llc.springcloud.apiservice.service;

import javax.servlet.http.HttpServletResponse;

public interface IIndexService {
    void exportTableInfo(String dataSourceKey, String schema, HttpServletResponse response) throws Exception;

    String getMyHost();
}
