package com.llc.springcloud.util;

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
}
