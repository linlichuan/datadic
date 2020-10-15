package com.llc.springcloud.apiservice.enums;


public enum DatabaseParamEnum {
	DATA_SOURCE(0),
	URL(1),
	USER_NAME(2),
	PASSWORD(3);
	
	int code;
	DatabaseParamEnum(int code) {
		this.code = code;
	}
}
