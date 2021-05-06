package com.llc.springcloud.onlinecharts.rule;

import java.util.List;
import java.util.Map;

public interface MyRule<Output, Input> {
	
	String getRawKey();
	
	String getKey();
	
	List<Output> getList(Input input);
	
	Map<String, Output> getMap(Input input);
	
	Output getData(Input input);
}
