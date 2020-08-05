package com.llc.springcloud.apiclient;

import com.llc.springcloud.apiservice.TableStructs;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface IIndexService {
    void exportTableInfo(String dataSourceKey, String schema, HttpServletResponse response) throws Exception;
    List<TableStructs> getTableInfo(String scheme);
}
