package com.llc.springcloud.apiservice.service;

import com.llc.springcloud.apiservice.annotation.DatabaseParam;
import com.llc.springcloud.apiservice.dto.TableConnectMsgDto;
import com.llc.springcloud.apiservice.enums.DatabaseParamEnum;

import javax.servlet.http.HttpServletResponse;

public interface IIndexService {
    void exportTableInfo(String dataSourceKey, String schema, HttpServletResponse response) throws Exception;

    void getTableInformation(String url,
                             String username,
                             String password,
                             String database,
                             HttpServletResponse response) throws Exception;
    
}
