package com.llc.springcloud.onlinecharts.vrtest.pojo;

import java.io.Serializable;

/**
 * VR响应数据基础数据模型
 * @param <T> d（业务数据）的类型
 */
@SuppressWarnings("serial")
public abstract class VrAbstractPojo<T> implements Serializable {
	
	private String a; // 方法名
	private String m; // 类名
	private Integer code; // 状态码：200-为成功，300-为失败
	private VrBaseDataPojo<T> d; // 业务数据、提示信息、分页信息
	
	
	public VrAbstractPojo() {
		super();
	}
	
	
	public boolean isSuccess() {
		return code != null && code.intValue() == 200;
	}
	
	
	public String getA() {
		return a;
	}
	public void setA(String a) {
		this.a = a;
	}
	public String getM() {
		return m;
	}
	public void setM(String m) {
		this.m = m;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public VrBaseDataPojo<T> getD() {
		return d;
	}
	public void setD(VrBaseDataPojo<T> d) {
		this.d = d;
	}
	
	
}
