package com.llc.springcloud.apiservice.service;

import com.llc.springcloud.apiservice.entity.TableStructs;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface IIndexService {
    void exportTableInfo(String dataSourceKey, String schema, HttpServletResponse response) throws Exception;
    
    List<TableStructs> getTableInformation(String url,
                                           String username,
                                           String password,
                                           String database,
                                           HttpServletResponse response) throws Exception;
    
}
