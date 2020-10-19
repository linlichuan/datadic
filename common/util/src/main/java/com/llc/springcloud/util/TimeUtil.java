package com.llc.springcloud.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class TimeUtil {
	
	public static Calendar getCalender() {
		return Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
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
	
	public static SimpleDateFormat getSimpleDateFormat(String pattern) {
		return new SimpleDateFormat(pattern);
	}
	
	public static Date strToDate(String str, String pattern) {
		Date date;
		try {
			date = getSimpleDateFormat(pattern).parse(str);
		} catch (Exception e) {
			date = null;
		}
		return date;
	}
	
	public static String dateToStr(Date date, String pattern) {
		return getSimpleDateFormat(pattern).format(date);
	}
	
	public static Date getLastDate() {
		Date date = getCurrentDate();
		Calendar calendar = getCalender();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, -1);
		return calendar.getTime();
	}
	
	public static Date getCurrentDateTime() {
		return getCalender().getTime();
	}
	
	public static void main(String[] args) {
		System.out.println(getCurrentDate());
		System.out.println(getLastDate());
	}
}
