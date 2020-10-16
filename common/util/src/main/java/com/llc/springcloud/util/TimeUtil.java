package com.llc.springcloud.util;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class TimeUtil {

	public static TimeZone timeZone = TimeZone.getTimeZone("GTM+8");
	
	public static Calendar getCalender() {
		return Calendar.getInstance(timeZone);
	}
	
	public static long getTimestamp() {
		return getCalender().getTimeInMillis();
	}

	public static Date getCurrentDate() {
		Calendar calendar = getCalender();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}
}
