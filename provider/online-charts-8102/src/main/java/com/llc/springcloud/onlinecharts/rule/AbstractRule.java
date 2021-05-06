package com.llc.springcloud.onlinecharts.rule;

import com.llc.springcloud.util.StringUtil;

import java.util.List;
import java.util.Map;

public class AbstractRule<Output, Input> implements MyRule<Output, Input> {
	
	private static final String KEY_SEPARATOR = "#";
	private String key;
	
	public AbstractRule(String key) {
		this.key = key;
	}
	
	@Override
	public String getRawKey() {
		return key;
	}
	
	@Override
	public String getKey() {
		return parseKey(key);
	}
	
	@Override
	public List<Output> getList(Input input) {
		return null;
	}
	
	@Override
	public Map<String, Output> getMap(Input input) {
		return null;
	}
	
	@Override
	public Output getData(Input input) {
		return null;
	}
	
	public String parseKey(String key) {
		if (StringUtil.isBlank(key)) {
			return "";
		}
		int idx = key.lastIndexOf(KEY_SEPARATOR);
		if (idx < 0) {
			return key;
		}
		return key.substring(idx + 1);
	}
	
	public String[] getKeyChain() {
		return key.split(KEY_SEPARATOR);
	}
	
}
