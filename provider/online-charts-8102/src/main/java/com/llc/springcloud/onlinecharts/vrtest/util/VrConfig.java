package com.llc.springcloud.onlinecharts.vrtest.util;


public class VrConfig {
	
	/** 请求主路径*/
	private static final String URL;
	
	/** 应用ID*/
	public static final String APP_ID;
	
	/** 应用秘钥*/
	public static final String SECRET;
	
	/** drive_tel (驾校后台登陆账号，即手机号) */
	public static final String DRIV_TEL;
	
	
	static{
		URL = "https://dev.chaofan-vr.com/";
		APP_ID = "cfvrkp8rt9523dhdbn";
		SECRET = "cf168cadhgkb208ccdca3ajmz2bac7e9";
		DRIV_TEL = "15013209069";
	}

	
	public static String getUrl(VrApiConfig api) {
		return api == null ? URL : URL + api.getApi();
	}
	
	
}
