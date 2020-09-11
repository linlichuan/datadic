package com.llc.springcloud.apiservice.interceptors;

import com.llc.springcloud.apiservice.config.DynamicDataSource;
import com.llc.springcloud.apiservice.config.DynamicDataSourceRouter;
import com.llc.springcloud.util.StringUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DataSourceInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

//		String key = DynamicDataSourceRouter.getContextKey();
//		if (StringUtil.isBlank(key) || !DynamicDataSource._MAIN_DATASOURCE.equals(key)) {
//			DynamicDataSourceRouter.setContextKey(DynamicDataSource._MAIN_DATASOURCE);
//		}

		return super.preHandle(request, response, handler);
	}
}
