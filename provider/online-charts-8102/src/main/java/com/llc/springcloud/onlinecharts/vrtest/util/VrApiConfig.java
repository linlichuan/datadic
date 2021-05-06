package com.llc.springcloud.onlinecharts.vrtest.util;

public enum VrApiConfig {
	
	
	/** 获取用户API地址*/
	USER("api/user/user.php"),
	
	
	/** 增加用户API地址（派发VIP卡接口）*/
	USER_ADD("api/user/user_add.php");
	
	
	private String api;
	

	private VrApiConfig(String api) {
		this.api = api;
	}

	public String getApi() {
		return api;
	}
	
	
}
