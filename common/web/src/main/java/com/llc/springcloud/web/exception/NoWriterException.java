package com.llc.springcloud.web.exception;

/**
 * 输出freemarker模板时没有writer
 * */
public class NoWriterException extends RuntimeException {
	
	public NoWriterException(String message) {
		super(message);
	}
	
	@Override
	public String getMessage() {
		return super.getMessage();
	}
}
