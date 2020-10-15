package com.llc.springcloud.codegen.config;


import freemarker.template.TemplateExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AliasFor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;

@Configuration
public class SpringConfig {
	
	private final static Logger log = LoggerFactory.getLogger(SpringConfig.class);
	
	@Bean
	@AliasFor("freemarkerCfg")
	public freemarker.template.Configuration freemarkerCfg() {
		try {
			File file = null;
			InputStream in;
			freemarker.template.Configuration cfg = new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_28);
			URL url = SpringConfig.class.getResource("/template");
			if (url != null) {
				file = new File(url.toURI());
			}
			cfg.setDirectoryForTemplateLoading(file);
			cfg.setDefaultEncoding("UTF-8");
			cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
			return cfg;
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
