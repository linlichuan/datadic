package com.llc.springcloud.onlinecharts.rule;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.llc.springcloud.util.StringUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Json2JsonRule extends AbstractRule<String, JSONObject> {
	
	public Json2JsonRule(String key) {
		super(key);
	}
	
	@Override
	public List<String> getList(JSONObject jsonObject) {
		Object json = getFinalDataByKey(jsonObject);
		String key = getKey();
		if (json == null) {
			return new ArrayList<>();
		}
		if (json instanceof JSONObject) {
			if (!StringUtil.isNumber(key)) {
				return castToStringList(((JSONObject) json).get(key));
			}
		}
		if (json instanceof JSONArray) {
			if (StringUtil.isNumber(key)) {
				return castToStringList(((JSONArray) json).get(Integer.parseInt(key)));
			}
		}
		return new ArrayList<>();
	}
	
	@Override
	public String getData(JSONObject jsonObject) {
		Object json = getFinalDataByKey(jsonObject);
		String key = getKey();
		if (json == null) {
			return null;
		}
		if (json instanceof JSONObject) {
			if (!StringUtil.isNumber(key)) {
				return ((JSONObject) json).getString(key);
			}
		}
		if (json instanceof JSONArray) {
			if (StringUtil.isNumber(key)) {
				return ((JSONArray) json).getString(Integer.parseInt(key));
			}
		}
		return null;
	}
	
	@Override
	public Map<String, String> getMap(JSONObject jsonObject) {
		Object json = getFinalDataByKey(jsonObject);
		String key = getKey();
		if (json == null) {
			return null;
		}
		if (json instanceof JSONObject) {
			if (!StringUtil.isNumber(key)) {
				return castToStringMap(((JSONObject) json).get(key));
			}
		}
		if (json instanceof JSONArray) {
			if (StringUtil.isNumber(key)) {
				return castToStringMap(((JSONArray) json).get(Integer.parseInt(key)));
			}
		}
		return new HashMap<>();
	}
	
	private Object getFinalDataByKey(Object json) {
		String cur, next;
		String[] keyChain = getKeyChain();
		int len = keyChain.length;
		if (len <= 1) {
			return json;
		}
		Object target = json;
		for (int i = 0; i < len; i++) {
			cur = keyChain[i];
			next = i + 1 < len ? keyChain[i + 1] : null;
			if (i == 0 && StringUtil.strToInt(cur) != null) {
				break;
			}
			if (next == null) {
				break;
			} else {
				target = getDataByKey(cur, next, target);
				if (target instanceof JSONObject) {
					if (!StringUtil.isNumber(cur)) {
						if (StringUtil.isNumber(next)) {
							target = ((JSONObject) target).getJSONArray(cur);
						} else {
							target = ((JSONObject) target).getJSONObject(cur);
						}
					}
				} else if (target instanceof JSONArray) {
					if (StringUtil.isNumber(cur)) {
						if (StringUtil.isNumber(next)) {
							target = ((JSONArray) target).getJSONArray(Integer.parseInt(cur));
						} else {
							target = ((JSONArray) target).getJSONObject(Integer.parseInt(cur));
						}
					}
				} else {
					target = null;
					break;
				}
			}
		}
		return target;
	}
	
	private Object getDataByKey(String key, String nextKey, Object json) {
		if (json instanceof JSONObject) {
			if (!StringUtil.isNumber(key)) {
				if (StringUtil.isNumber(nextKey)) {
					return ((JSONObject) json).getJSONArray(key);
				} else {
					return ((JSONObject) json).getJSONObject(key);
				}
			}
		}
		if (json instanceof JSONArray) {
			if (StringUtil.isNumber(key)) {
				if (StringUtil.isNumber(nextKey)) {
					return ((JSONArray) json).getJSONArray(Integer.parseInt(key));
				} else {
					return ((JSONArray) json).getJSONObject(Integer.parseInt(key));
				}
			}
		}
		return null;
	}
	
	private List<String> castToStringList(Object obj) {
		List<String> list = new ArrayList<>();
		if (obj instanceof JSONArray) {
			JSONArray arr = (JSONArray) obj;
			for (int i = 0; i < arr.size(); i++) {
				list.add(arr.getString(i));
			}
		}
		return list;
	}
	
	private Map<String, String> castToStringMap(Object obj) {
		Map<String, String> map = new HashMap<>();
		if (obj instanceof JSONObject) {
			JSONObject json = (JSONObject) obj;
			Set<Map.Entry<String, Object>> set = json.entrySet();
			for (Map.Entry<String, Object> entry : set) {
				map.put(entry.getKey(), entry.getValue().toString());
			}
		}
		return map;
	}
	
	
}
