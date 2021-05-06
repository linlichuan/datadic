package com.llc.springcloud.util;

import java.util.Collection;
import java.util.Iterator;

public class StringUtil {

	public static boolean isNotBlank(String text) {
		return !isBlank(text);
	}

	public static boolean isBlank(String text) {
		return text == null || "".equals(text.trim());
	}

	public static String longToString(Long num) {
		return num == null ? "" : String.valueOf(num);
	}

	public static String join(char separator, String...strs) {
		if (strs == null || strs.length == 0) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < strs.length; i++) {
			sb.append(strs[i]);
			if (i != strs.length - 1) {
				sb.append(separator);
			}
		}
		return sb.toString();
	}
	
	public static Integer strToInt(String str) {
		try {
			return Integer.parseInt(str);
		} catch (Exception e) {
			return null;
		}
	}
	
	public static boolean isNumber(String str) {
		return strToInt(str) != null;
	}
	
	public static <T> String join(char separator, Collection<T> collect) {
		if (collect == null || collect.isEmpty()) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		Iterator<T> it = collect.iterator();
		while (it.hasNext()) {
			sb.append(it.next()).append(separator);
		}
		String result = sb.toString();
		if (result.endsWith(String.valueOf(separator))) {
			result = result.substring(0, result.length() - 1);
		}
		return result;
	}
}
