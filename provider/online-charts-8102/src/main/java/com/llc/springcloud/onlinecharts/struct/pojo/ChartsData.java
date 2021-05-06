package com.llc.springcloud.onlinecharts.struct.pojo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ChartsData implements Serializable {
	
	private String name;
	private String type;
	private InnerData data;
	
	public ChartsData() {
		super();
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return this.type;
	}
	
	public void setData(InnerData data) {
		this.data = data;
	}
	
	public InnerData getData() {
		return this.data;
	}
}
