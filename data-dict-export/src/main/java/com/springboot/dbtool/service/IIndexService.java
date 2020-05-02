package com.springboot.dbtool.service;


import javax.servlet.http.HttpServletResponse;

public interface IIndexService {
    public void exportTableInfo(String dataSourceKey, String schema, HttpServletResponse response) throws Exception;
}
