package com.llc.springcloud.onlinecharts.struct;

import com.llc.springcloud.onlinecharts.struct.pojo.ChartsData;

import java.io.Serializable;
import java.util.List;

/**
 * 柱状图
 * */
@SuppressWarnings("serial")
public class HistogramStruct implements MyStruct, Serializable {
	
	private String title;
	private List<ChartsData> data;
	
	public HistogramStruct() {
		super();
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public void setData(List<ChartsData> data) {
		this.data = data;
	}
	
	public List<ChartsData> getData() {
		return this.data;
	}
}
