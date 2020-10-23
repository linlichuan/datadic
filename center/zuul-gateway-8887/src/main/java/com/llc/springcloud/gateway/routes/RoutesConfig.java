package com.llc.springcloud.gateway.routes;

import com.llc.springcloud.gateway.constants.Constants;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RoutesConfig {

	@Bean
	public RouteLocator getPathRouter(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("zh-api-path", r -> r.path("/zh-api").uri(Constants.SERVICE_ZH_API))
				.route("api-path", r -> r.path("/api").uri(Constants.SERVICE_API))
				.build();
	}

}
