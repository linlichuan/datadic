package com.llc.springcloud.onlinecharts.adapter;

import com.llc.springcloud.onlinecharts.mapper.MyMapper;
import com.llc.springcloud.onlinecharts.rule.MyRule;

import java.util.List;

public class Json2RegularStructAdapter {
	
	private List<MyRule> rules;
	private MyMapper mapper;
	
	
	
	
	
	
	public void setRules(List<MyRule> rules) {
		this.rules = rules;
	}
	
	public void setMapper(MyMapper mapper) {
		this.mapper = mapper;
	}
}
