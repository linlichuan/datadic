package com.llc.springcloud.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class TimeUtil {
	
	public static Calendar getCalender() {
		return Calendar.getInstance();
	}
	

	public static Date getCurrentDate() {
		Calendar calendar = getCalender();
		calendar.setTime(new Date());
		calendar.set(Calendar.HOUR_OF_DAY, calendar.getMinimum(Calendar.HOUR_OF_DAY));
		calendar.set(Calendar.MINUTE, calendar.getMinimum(Calendar.MINUTE));
		calendar.set(Calendar.SECOND, calendar.getMinimum(Calendar.SECOND));
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
	
	private static final String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
	
	public static final TimeZone zone = TimeZone.getTimeZone("GMT+8");
	
	public static final String DATE_FORMAT = "yyyy-MM-dd";
	
	public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	
	
	public static String format(long timeMillis, String format) {
		return format(getDate(timeMillis), format);
	}
	
	
	public static String format(Date date, String format) {
		try {
			return getSimpleDateFormat(format).format(date);
		} catch (Exception e) {
			return null;
		}
	}
	
	
	/**
	 * 获取星期几名称
	 *
	 * @param dt
	 * @return
	 */
	public static String getWeekOfDateStr(int weekOfDate) {
		weekOfDate = (weekOfDate < 0 ? weekOfDate * -1 : weekOfDate) % 7;
		return weekDays[weekOfDate];
	}
	
	
	/**
	 * 获取星期几名称
	 *
	 * @param dt
	 * @return
	 */
	public static String getWeekOfDateStr(Date dt) {
		return weekDays[getWeekOfDate(dt)];
	}
	
	
	/**
	 * 获取星期几，星期天是0，星期一是1，星期六是6
	 *
	 * @param dt
	 * @return
	 */
	public static int getWeekOfDate(Date dt) {
		Calendar cal = getCalendar();
		cal.setTime(dt);
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0) {
			w = 0;
		}
		return w;
	}
	
	
	/**
	 * 获取星期几，星期天是0，星期一是1，星期六是6
	 *
	 * @param dt
	 * @return
	 */
	public static int getWeekOfDate() {
		return getWeekOfDate(getDate());
	}
	
	
	public static Calendar getCalendar(Date date) {
		Calendar c = Calendar.getInstance(zone);
		if(date != null) {
			c.setTime(date);
		}
		return c;
	}
	
	
	public static Calendar getCalendar() {
		return Calendar.getInstance(zone);
	}
	
	
	
	
	public static Date parse(String pattern, String source) {
		try {
			return getSimpleDateFormat(pattern).parse(source);
		} catch (Exception e) {
			return null;
		}
	}
	
	
	public static Integer getSecondsOfDay(String pattern, String source) {
		return getSecondsOfDay(parse(pattern, source));
	}
	
	
	public static Integer getSecondsOfDay(Date date) {
		if(date == null) {
			return null;
		}
		Calendar calendar = getCalendar();
		calendar.setTime(date);
		return calendar.get(Calendar.HOUR_OF_DAY) * 3600 + calendar.get(Calendar.MINUTE) * 60 + calendar.get(Calendar.SECOND);
	}
	
	
	public static int getCurrentSecondsOfDay() {
		return getSecondsOfDay(getDate());
	}
	
	
	public static Date getDate(long time) {
		Calendar c = getCalendar();
		c.setTimeInMillis(time);
		return c.getTime();
	}
	
	
	public static Date getDate() {
		Calendar c = getCalendar();
		return c.getTime();
	}
	
	
	/**
	 * 秒转换为日期
	 *
	 * @param seconds
	 * @return
	 */
	public static Date parse(Integer seconds) {
		if (seconds == null) {
			return null;
		}
		Calendar c = getCalendar();
		c.setTimeInMillis((long) (Long.valueOf(seconds) * 1000L));
		return c.getTime();
	}
	
	
	/**
	 * 日期转换为秒
	 *
	 * @return
	 */
	public static Integer parse(Date date) {
		if (date == null) {
			return null;
		}
		Calendar c = getCalendar();
		c.setTime(date);
		return (int) (c.getTimeInMillis() / 1000);
	}
	
	
	/**
	 * 获得该年月的秒数
	 *
	 * @param year
	 * @param month
	 * @return
	 */
	public static int getSecondsByYearAndMonth(int year, int month) {
		Calendar date = getCalendar();
		date.set(year, month - 1, 1, 0, 0, 0);
		return (int) (date.getTimeInMillis() / 1000);
	}
	
	
	/** 保存的时间戳 */
	public static long getTimestamp() {
		return getCalendar().getTimeInMillis();
	}
	
	
	public static int getSeconds() {
		return (int) (getTimestamp() / 1000L);
	}
	
	
	public static String getSecondsStr() {
		return getSeconds() + "";
	}
	
	
	/** 获取当前日期00:00:00的时间戳（秒） */
	public static int getCurrentDateStartSeconds() {
		return (int) (getCurrentDateStartTime() / 1000);
	}
	
	
	public static int getCurrentDateEndSeconds() {
		return (int) (getCurrentDateEndTime() / 1000);
	}
	
	
	/** 获取当前日期00:00:00的时间戳（秒） */
	public static long getCurrentDateStartTime() {
		Calendar calendar = getCalendar();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTimeInMillis();
	}
	
	
	public static long getCurrentDateEndTime() {
		Calendar calendar = getCalendar();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.add(Calendar.DATE, 1);
		return calendar.getTimeInMillis();
	}
	
	
	/** 日期格式化(2010年10月3号) */
	public static String getCurrentDate(Date date) {
		return getSimpleDateFormat("yyyy年MM月dd日").format(date);
	}
	
	
	/** 日期格式化(20131129175034) */
	public static String getCurrentTime() {
		return getSimpleDateFormat("yyyyMMddHHmmss").format(getCalendar().getTime());
	}
	
	
	/***
	 * 根据传进来的字符串转为秒
	 *
	 * @param dateStr
	 *            "yyyy-MM-dd" 格式的字符串如：String timeStr="2013-12-10";
	 * @return 格式不对返回null ， 正确的返回秒数。Integer
	 */
	public static Integer dateStrToInteger(String dateStr) {
		return dateStrToInteger(dateStr, "yyyy-MM-dd");
	}
	
	
	/***
	 * 根据传进来的字符串转为秒
	 *
	 * @param timeStr
	 *            日期字符串，如： "2014-01-01 13:05:32"
	 * @param format
	 *            日期字符串格式，如 "yyyy-MM-dd HH:mm:ss"
	 * @return 格式不对返回null ， 正确的返回秒数。Integer
	 */
	public static Integer dateStrToInteger(String dateStr, String format) {
		if (dateStr == null || "".equals(dateStr.trim())) {
			return null;
		}
		try {
			Calendar c = getCalendar();
			c.setTime(getSimpleDateFormat(format).parse(dateStr));
			c.getTimeInMillis();
			return (int) (c.getTimeInMillis() / 1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/***
	 * 根据传进来的字符串转为毫秒
	 *
	 * @param timeStr
	 *            日期字符串，如： "2014-01-01 13:05:32"
	 * @param strFormat
	 *            日期字符串格式，如 "yyyy-MM-dd HH:mm:ss"
	 * @return 格式不对返回null ， 正确的返回秒数。Long
	 */
	public static Long dateStrToLong(String dateStr, String strFormat) {
		Long result = null;
		Integer time = dateStrToInteger(dateStr, strFormat);
		if (time != null) {
			result = (long) (time * 1000L);
		}
		return result;
	}
	
	
	/** 秒转为时间戳格式的字符串 yyyy-MM-dd */
	public static String secondsToDateStr(int seconds) {
		return secondsToDateStr(seconds, "yyyy-MM-dd");
	}
	
	
	/**
	 * 秒转为日期格式，
	 *
	 * @param seconds
	 *            秒
	 * @param format
	 *            指定日期格式
	 * @return
	 */
	public static String secondsToDateStr(int seconds, String format) {
		try {
			Calendar c = getCalendar();
			c.setTimeInMillis((long) (Long.parseLong(seconds + "") * 1000L));
			return getSimpleDateFormat(format).format(c.getTime());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	/**
	 * 获取当前日期，年月日 如 ： 20140101
	 *
	 * @return 如： 20140101
	 */
	public static String getYearMonthDay() {
		return getSimpleDateFormat("yyyyMMdd").format(getCalendar().getTime());
	}
	
	
	/**
	 * 获取当前时间 yyyyMMdd HH:mm:ss.SSS
	 *
	 * @return
	 */
	public static String getTimeStr() {
		return getTimeStr(getTimestamp());
	}
	
	
	/**
	 * 获取当前时间 yyyyMMdd HH:mm:ss.SSS
	 *
	 * @return
	 */
	public static String getTimeStr(long time) {
		return getSimpleDateFormat("yyyyMMdd HH:mm:ss.SSS").format(getDate(time));
	}
	
	
	/**
	 * 返回当月的开始时间(秒)
	 *
	 * @return
	 */
	public static int getCurrentMonth() {
		return getApartCurrentMonth(0);
	}
	
	
	/**
	 * 返回下个月的开始时间(毫秒)
	 *
	 * @return
	 */
	public static long getApartCurrentMonthTime(int apart) {
		Calendar calendar = getCalendar();
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.add(Calendar.MONTH, apart);
		return calendar.getTimeInMillis();
	}
	
	/**
	 * 返回下个月的开始时间(秒)
	 *
	 * @return
	 */
	public static int getApartCurrentMonth(int apart) {
		return (int) (getApartCurrentMonthTime(apart) / 1000);
	}
	
	
	public static int getTheDayFristTime(int time) {
		Calendar calendar = getCalendar();
		calendar.setTimeInMillis((long) (time * 1000L));
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return (int) (calendar.getTimeInMillis() / 1000);
	}
	
	
	/**
	 * 获取当前年份
	 *
	 * @return
	 */
	public static int getThisYear() {
		return getCalendar().getWeekYear();
	}
	
	
	/**
	 * 获取当前月份
	 *
	 * @return
	 */
	public static int getThisMonth() {
		return getCalendar().get(Calendar.MONTH) + 1;
	}
	
	
	/**
	 * 获取当前是一年中的第几周
	 *
	 * @return
	 */
	public static int getThisWeekOfYear() {
		return getCalendar().getWeeksInWeekYear();
	}
	
	
	public static int getYear(int time) {
		Calendar c = getCalendar();
		c.setTimeInMillis((long) (time * 1000L));
		return c.getWeekYear();
	}
	
	
	public static int getWeekOfYear(int time) {
		Calendar c = getCalendar();
		c.setTimeInMillis((long) (time * 1000L));
		return c.getWeeksInWeekYear();
	}
	
	
	public static long getTime(int year, int month, int day, int hour, int minute, int second) {
		Calendar c = getCalendar();
		c.clear();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month - 1);
		c.set(Calendar.DATE, day);
		c.set(Calendar.HOUR_OF_DAY, hour);
		c.set(Calendar.MINUTE, minute);
		c.set(Calendar.SECOND, second);
		return c.getTimeInMillis();
	}
	
	
	public static long getTime(int year, int month, int day) {
		return getTime(year, month, day, 0, 0, 0);
	}
	
	
	public static long getTime(int year, int month) {
		return getTime(year, month, 1, 0, 0, 0);
	}
	
	
	public static Date getYear(Date year) {
		if(year == null) {
			return null;
		}
		return getDate(getTime(getCalendar(year).get(Calendar.YEAR)));
	}
	
	
	public static Date getMonth(Date month) {
		if(month == null) {
			return null;
		}
		Calendar c = getCalendar(month);
		return getDate(getTime(c.get(Calendar.YEAR), c.get(Calendar.MONTH) + 1));
	}
	
	
	public static long getTime(int year) {
		return getTime(year, 1, 1, 0, 0, 0);
	}
	
	
	public static long addByField(long time, int amount, int field) {
		Calendar c = getCalendar();
		c.clear();
		c.setTimeInMillis(time);
		c.add(field, amount);
		return c.getTimeInMillis();
	}
	
	
	public static Date addYear(Date time, int addYear) {
		return time == null ? null : getDate(addYear(time.getTime(), addYear));
	}
	
	
	public static long addYear(long time, int addYear) {
		return addByField(time, addYear, Calendar.YEAR);
	}
	
	
	public static Date addMonth(Date time, int addMonth) {
		return time == null ? null : getDate(addMonth(time.getTime(), addMonth));
	}
	
	
	public static long addMonth(long time, int addMonth) {
		return addByField(time, addMonth, Calendar.MONTH);
	}
	
	
	public static Date addWeek(Date time, int addWeek) {
		return time == null ? null : getDate(addWeek(time.getTime(), addWeek));
	}
	
	
	public static long addWeek(long time, int addWeek) {
		return addByField(time, addWeek, Calendar.WEEK_OF_YEAR);
	}
	
	
	/**
	 * 获取输入时间的月份最大天数
	 *
	 * @param time
	 * @return
	 */
	public static int getMaxDayOfMonth(long time) {
		Calendar c = getCalendar();
		c.clear();
		c.setTimeInMillis(time);
		return c.getActualMaximum(Calendar.DATE);
	}
	
	public static long addDay(long time, int addDay) {
		Calendar c = getCalendar();
		c.setTimeInMillis(time);
		c.add(Calendar.DATE, addDay);
		return c.getTimeInMillis();
	}
	
	
	public static Date addDay(Date time, int addDay) {
		Calendar c = getCalendar();
		c.setTime(time);
		c.add(Calendar.DATE, addDay);
		return c.getTime();
	}
	
	
	public static long addHour(long time, int addHour) {
		Calendar c = getCalendar();
		c.setTimeInMillis(time);
		c.add(Calendar.HOUR_OF_DAY, addHour);
		return c.getTimeInMillis();
	}
	
	
	public static Date addHour(Date time, int addHour) {
		Calendar c = getCalendar();
		c.setTime(time);
		c.add(Calendar.HOUR_OF_DAY, addHour);
		return c.getTime();
	}
	
	
	public static long addMinute(long time, int addMinute) {
		Calendar c = getCalendar();
		c.setTimeInMillis(time);
		c.add(Calendar.MINUTE, addMinute);
		return c.getTimeInMillis();
	}
	
	
	public static Date addMinute(Date time, int addMinute) {
		Calendar c = getCalendar();
		c.setTime(time);
		c.add(Calendar.MINUTE, addMinute);
		return c.getTime();
	}
	
	
	public static long addSecond(long time, int addSecond) {
		Calendar c = getCalendar();
		c.setTimeInMillis(time);
		c.add(Calendar.SECOND, addSecond);
		return c.getTimeInMillis();
	}
	
	
	public static Date addSecond(Date time, int addSecond) {
		Calendar c = getCalendar();
		c.setTime(time);
		c.add(Calendar.SECOND, addSecond);
		return c.getTime();
	}
	
	
	/**
	 * 本周一开始时间
	 *
	 * @return
	 */
	public static long getCurrentWeekSatrtTime() {
		int week = getWeekOfDate();
		if (week == 0) {
			week = 7;
		}
		return addDay(getCurrentDateStartTime(), week * -1 + 1);
	}
	
	
	/**
	 * 本周日结束时间
	 *
	 * @return
	 */
	public static long getCurrentWeekEndTime() {
		return addDay(getCurrentDateEndTime(), 7 - getWeekOfDate());
	}
	
	
	public static long getCurrentMonthStartTime() {
		return getApartCurrentMonthTime(0);
	}
	
	
}