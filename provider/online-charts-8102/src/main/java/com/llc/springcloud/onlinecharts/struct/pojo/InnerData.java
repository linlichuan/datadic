package com.llc.springcloud.onlinecharts.struct.pojo;

import java.io.Serializable;
import java.util.LinkedList;

@SuppressWarnings("serial")
public class InnerData implements Serializable {
	
	private String name;
	private String type;
	private LinkedList<Integer> data;
	
	public InnerData() {
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
	
	public void setData(LinkedList<Integer> data) {
		this.data = data;
	}
	
	public LinkedList<Integer> getData() {
		return this.data;
	}
}
