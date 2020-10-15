package com.llc.springcloud.util;

import java.util.Calendar;
import java.util.TimeZone;

public class TimeUtil {

	public static TimeZone timeZone = TimeZone.getTimeZone("GTM+8");
	
	public static Calendar getCalender() {
		return Calendar.getInstance(timeZone);
	}
	
	public static long getTimestamp() {
		return getCalender().getTimeInMillis();
	}

}
