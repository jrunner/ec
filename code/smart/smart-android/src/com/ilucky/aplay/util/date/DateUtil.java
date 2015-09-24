package com.ilucky.aplay.util.date;

import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang3.time.FastDateFormat;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * @author IluckySi
 * @since 20150914
 * 注意:SimpleDateFormat是线程不安全的,所以建议使用FastDateFormat.
 */
public class DateUtil {

	/**
	 * @param date
	 * @param pattern
	 * @return String
	 */
	public static String getDateString(Date date, String pattern) {
		FastDateFormat formatter = FastDateFormat.getInstance(pattern);
		return formatter.format(date);
	}
	
	/**
	 * @param dateTime
	 * @param pattern
	 * @return String
	 */
	public static String getDateTimeString(DateTime dateTime, String pattern) {
		FastDateFormat formatter = FastDateFormat.getInstance(pattern);
		return formatter.format(dateTime.getMillis());
	}
	
	/**
	 * @param dateString
	 * @param pattern
	 * @return Date
	 */
	public static Date getDate(String dateString, String pattern){
		DateTimeFormatter formatter =  DateTimeFormat.forPattern(pattern);
		DateTime dateTime = formatter.parseDateTime(dateString);
		return dateTime.toDate();
	}
	
	/**
	 * @param dateString
	 * @param pattern
	 * @return DateTime
	 */
	public static DateTime getDateTime(String dateString, String pattern){
		DateTimeFormatter formatter =  DateTimeFormat.forPattern(pattern);
		return formatter.parseDateTime(dateString);
	}
	
	/**
	 * @param args
	 * @throws ParseException
	 */
	public static void main(String[] args) throws ParseException {
		System.out.println(getDateString(new Date(), "yyyy/MM/dd HH:mm:ss"));
		System.out.println(getDateTimeString(new DateTime(), "yyyy/MM/dd HH:mm:ss"));
		System.out.println(new Date());
		System.out.println(getDate("2015/07/01 00:00:00", "yyyy/MM/dd HH:mm:ss"));
		System.out.println(new DateTime());
		System.out.println(getDateTime("2015/07/01 00:00:00", "yyyy/MM/dd HH:mm:ss"));
	}
}
/**
2015/07/21 22:18:11
2015/07/21 22:18:11
Tue Jul 21 22:18:11 CST 2015
Wed Jul 01 00:00:00 CST 2015
2015-07-21T22:18:11.586+08:00
2015-07-01T00:00:00.000+08:00
*/
