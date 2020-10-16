package com.llc.springcloud.util;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class TimeUtil {
	
	public static Calendar getCalender() {
		return Calendar.getInstance();
	}
	
	public static long getTimestamp() {
		return getCalender().getTimeInMillis();
	}

	public static Date getCurrentDate() {
		Calendar calendar = getCalender();
		calendar.setTime(new Date());
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}
	
	public static Date getCurrentDateTime() {
		return getCalender().getTime();
	}
}
