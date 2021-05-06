package com.llc.springcloud.onlinecharts.vrtest.pojo;

import com.alibaba.fastjson.JSONArray;

import java.io.Serializable;

@SuppressWarnings("serial")
public class DriverRidersDetailPojo implements Serializable {

	private String name;//科目下驾考攻略类型
	
	private Integer standard;
	private Integer tol;
	private JSONArray list;
	
	
	public DriverRidersDetailPojo() {
		super();
	}
	
	public DriverRidersDetailPojo(String name, Integer standard, Integer tol, JSONArray list) {
		this.name = name;
		this.standard = standard;
		this.tol = tol;
		if (list == null) {
			this.list = new JSONArray();
		} else {
			this.list = list;
		}
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setStandard(Integer standard) {
		this.standard = standard;
	}
	
	public Integer getStandard() {
		return this.standard;
	}
	
	public void setTol(Integer tol) {
		this.tol = tol;
	}
	
	public Integer getTol() {
		return this.tol;
	}
	
	public void setList(JSONArray list) {
		this.list = list;
	}
	
	public JSONArray getList() {
		return this.list;
	}
}
