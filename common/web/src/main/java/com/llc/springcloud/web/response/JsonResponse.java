package com.llc.springcloud.web.response;

import com.llc.springcloud.util.StringUtil;

import java.io.Serializable;

@SuppressWarnings("serial")
public class JsonResponse<T> implements Serializable {
	
	public static final String OK_MSG = "ok";
	public static final String ERROR_MSG = "error";
	
	public static final int OK_CODE = 200;
	public static final int ERROR_CODE = 400;
	
	private int code;
	private String msg;
	private T data;
	
	public JsonResponse() {
		super();
	}
	
	public static <T> JsonResponse<T> ok() {
		return new JsonResponse<T>().success(null, null);
	}
	
	public static <T> JsonResponse<T> ok(T data) {
		return new JsonResponse<T>().success(data, null);
	}
	
	public static <T> JsonResponse<T> ok(T data, String msg) {
		return new JsonResponse<T>().success(data, msg);
	}
	
	public static <T> JsonResponse<T> failure() {
		return new JsonResponse<T>().error(ERROR_MSG);
	}
	
	public static <T> JsonResponse<T> failure(String msg) {
		return new JsonResponse<T>().error(msg);
	}
	
	
	public JsonResponse<T> success(T data, String msg) {
		this.data = data;
		this.msg = StringUtil.isBlank(msg) ? OK_MSG : msg;
		this.code = OK_CODE;
		return this;
	}
	
	public JsonResponse<T> error(String msg) {
		this.msg = StringUtil.isBlank(msg) ? ERROR_MSG : msg;
		this.code = ERROR_CODE;
		this.data = null;
		return this;
	}
	
	public void setCode(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return this.code;
	}
	
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public String getMsg() {
		return this.msg;
	}
	
	public void setData(T data) {
		this.data = data;
	}
	
	public T getData() {
		return this.data;
	}
}
