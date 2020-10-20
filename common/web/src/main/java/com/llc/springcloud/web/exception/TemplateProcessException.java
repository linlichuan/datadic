package com.llc.springcloud.web.exception;

/**
 * process freemarker 模板异常
 * */
public class TemplateProcessException extends RuntimeException {
	
	public TemplateProcessException(String message) {
		super(message);
	}
	
	@Override
	public String getMessage() {
		return super.getMessage();
	}
}
