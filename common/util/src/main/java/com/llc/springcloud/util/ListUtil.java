package com.llc.springcloud.util;

import java.util.Collection;

public class ListUtil {
	
	public static <T> boolean isEmpty(Collection<T> list) {
		return list == null || list.isEmpty();
	}
	
}
