package com.cykj.base.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.Map;

/**
 * 日期操作类
 * 
 * @author LeiYong
 *
 */
public class DateUtil {

    /** 存放不同的日期模板格式的sdf的Map */
    private static Map<String, DateFormat> sdfMap = new Hashtable<>();

    static {
    	sdfMap.put("yyyy-MM-dd HH:mm", new SimpleDateFormat("yyyy-MM-dd HH:mm"));
    	sdfMap.put("yyyy-MM-dd HH:mm:ss", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    	sdfMap.put("yyyy-MM-dd", new SimpleDateFormat("yyyy-MM-dd"));
    }
    /**
     * 获取DateFormat对象
     * @param format
     * @return
     */
    public static DateFormat getDateFormat(String format){
    	if (sdfMap.get(format)==null) {
			DateFormat sf = new SimpleDateFormat(format);
			sdfMap.put(format, sf);
		}
    	return sdfMap.get(format);
    }
	/**
	 * 用给定的样式格式化给定的日期（字符串）
	 * 
	 * @param format
	 *            日期样式
	 * @param dateStr
	 *            目标日期字符串
	 * @return
	 * @throws java.text.ParseException
	 */
	public static Date parse(String format, String dateStr) throws ParseException {
		return getDateFormat(format).parse(dateStr);
	}

	/**
	 * 将给定的日期格式化为默认的样式:yyyy-MM-dd HH:mm:ss
	 * 
	 * @param dateStr
	 * @return
	 * @throws java.text.ParseException
	 */
	public static Date parse(String dateStr) throws ParseException {
		return parse("yyyy-MM-dd HH:mm:ss",dateStr);
	}


	/**
	 * 用给定的样式格式化给定的日期（日期格式）
	 * 
	 * @param format
	 *            樣式
	 * @param date
	 *            日期
	 * @return
	 */
	public static String format(String format,Date date) {
		return getDateFormat(format).format(date);
	}

	/**
	 * 用给定的样式格式化给定的日期
	 * 
	 * @param c
	 * @param format
	 * @return
	 */
	public static String format(String format,Calendar c) {
		return getDateFormat(format).format(c.getTime());
	}


	/**
	 * 格式化给定日期 为"yyyy-MM-dd HH:mm"
	 * 
	 * @return
	 */
	public static String format(Date date) {
		return format("yyyy-MM-dd HH:mm",date);
	}

	/**
	 * 某個月份的天數可能拥有的最大值
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static int getDayOfMonth(int year, int month) {
		Calendar a = Calendar.getInstance();
		int maxDate = 0;
		if (year != 0 && month != 0) {
			a.clear();
			a.set(1, year);
			a.set(2, month - 1);
			maxDate = a.getActualMaximum(5);
		}
		return maxDate;
	}

	/**
	 * 查询两时间间隔
	 * 
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static String getInterval(String startTime, String endTime) { // 传入的时间格式必须类似于2012-8-21
																			// 17:53:20这样的格式
		String interval = null;
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ParsePosition pos1 = new ParsePosition(0);
		ParsePosition pos2 = new ParsePosition(0);
		Date d1 = (Date) sd.parse(startTime, pos1);
		Date d2 = (Date) sd.parse(endTime, pos2);

		long time = d2.getTime() - d1.getTime();// 得出的时间间隔是毫秒

		if (time / 1000 < 20 && time / 1000 >= 0) {
			// 如果时间间隔小于20秒则显示“刚刚”
			interval = "刚刚";

		} else if (time / 3600000 < 24 && time / 3600000 > 0) {
			// 如果时间间隔小于24小时则显示多少小时前
			int h = (int) (time / 3600000);// 得出的时间间隔的单位是小时
			interval = h + "小时前";

		} else if (time / 60000 < 60 && time / 60000 > 0) {
			// 如果时间间隔小于60分钟则显示多少分钟前
			int m = (int) ((time % 3600000) / 60000);// 得出的时间间隔的单位是分钟
			interval = m + "分钟前";

		} else if (time / 1000 < 60 && time / 1000 > 0) {
			// 如果时间间隔小于60秒则显示多少秒前
			int se = (int) ((time % 60000) / 1000);
			interval = se + "秒前";

		} else {
			// 大于24小时，则显示天
			int d = (int) (time / (3600000 * 24));// 得出的时间间隔的单位是天
			interval = d + "天前";
		}
		return interval;
	}

}
