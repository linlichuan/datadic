package com.llc.springcloud.util;

public class StringUtil {

	public static boolean isNotBlank(String text) {
		return !isBlank(text);
	}

	public static boolean isBlank(String text) {
		return text == null || "".equals(text.trim());
	}

}
