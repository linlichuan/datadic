package com.llc.springcloud.apiservice.config;


import java.io.IOException;
import java.util.Properties;

public class PropertyConfig extends Properties {
	
	private static final PropertyConfig instance = new PropertyConfig();
	
	{
		try {
			this.load(PropertyConfig.class.getResourceAsStream("/jdbc.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String get(String key) {
		return instance.getProperty(key);
	}
	
}
