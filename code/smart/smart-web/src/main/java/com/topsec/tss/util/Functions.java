package com.topsec.tss.util;

import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * 自定义公用库
 *
 * @author jrunner
 */
public class Functions {

	public static Date convertObj2Date(Object obj, String pattern) throws ParseException {
		if (obj == null || obj.toString().equals(""))
			return null;
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.parse(obj + "");
	}

	public static String convertDate2String(Object obj, String pattern) throws ParseException {
		if (obj == null || obj.toString().equals(""))
			return null;
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format((Date) obj);
	}

	public static String getValue(Object obj, String length) {
		if (obj == null) {
			return "";
		}
		if (obj instanceof String) {
			String str = (String) obj;
			if (isSpace(str)) {
				return "";
			}
			if (!isSpace(length)) {
				if (str.length() > Integer.valueOf(length)) {
					str = str.substring(0, Integer.valueOf(length)) + "...";
				}
			}
			return str;
		}

		// oracle.sql.TIMESTAMP，转成字符串类型
		if (obj.toString().indexOf("oracle.sql.TIMESTAMP") == 0) {
			Class clz = obj.getClass();
			Method m = null;
			try {
				m = clz.getMethod("timestampValue", null);
				obj = m.invoke(obj, null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (obj instanceof Date) {
			if (!isSpace(length)) {
				String dateType = "yyyy-MM-dd HH:mm:ss";
				switch (Integer.valueOf(length)) {
					case 4:
						dateType = "yyyy";
						break;
					case 10:
						dateType = "yyyy-MM-dd";
						break;
					default:
						break;
				}
				return formatDateTime((Date) obj, dateType);
			}
			return formatDateTime((Date) obj, "yyyy-MM-dd HH:mm:ss");
		}
		return "";
	}

	/**
	 * 判读map是否存在obj的键
	 *
	 * @param map
	 * @param obj
	 * @return boolean
	 */
	public static boolean containsKey(Map map, Object obj) {
		return map.containsKey(obj);
	}

	/**
	 * 判读map是否存在obj的值
	 *
	 * @param map
	 * @param obj
	 * @return boolean
	 */
	public static boolean containsValue(Map map, Object obj) {
		return map.containsValue(obj);
	}

	/**
	 * 判读来个字符串是否相等
	 *
	 * @param str1
	 * @param str2
	 * @return boolean
	 */
	public static boolean equals(String str1, String str2) {
		return str1.equals(str2);
	}

	/**
	 * 截取字符串
	 *
	 * @param str
	 *            要转换的字符串
	 * @param length
	 *            长度
	 * @param prefix
	 *            要加的后缀
	 * @return String
	 */
	public static String subString(String str, int length, String prefix) {
		if (str.length() > length) {
			str = str.substring(0, length) + prefix;
		}
		return str;
	}

	/**
	 * 格式化日期 主要用于显示在文本框中
	 *
	 * @param date
	 * @return String
	 */
	public static String formatDateTime(Date date, String format) {
		if (date == null) {
			return null;
		}
		if (format == null) {
			format = "yyyy-MM-dd HH:mm:ss";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	/**
	 * 获取当前时间
	 *
	 * @param format
	 *            格式
	 * @return String
	 */
	public static String getCurrentTime(String... format) {
		if (format == null || format.length == 0) {
			format = new String[] { "yyyy-MM-dd HH:mm:ss" };
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format[0]);
		return sdf.format(new Date());
	}

	public static String getWeek(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
		String week = null;
		switch (dayOfWeek) {
			case 1:
				week = "星期日";
				break;
			case 2:
				week = "星期一";
				break;
			case 3:
				week = "星期二";
				break;
			case 4:
				week = "星期三";
				break;
			case 5:
				week = "星期四";
				break;
			case 6:
				week = "星期五";
				break;
			case 7:
				week = "星期六";
				break;
		}
		return week;
	}

	/**
	 * 是否是本月
	 *
	 * @param currentTime
	 *            当前时间
	 * @return boolean
	 */
	public static boolean isInCurrentMonth(String currentTime) {
		if (currentTime == null) {
			return false;
		}

		Date date = null;

		Date dateStart = null;// 本月第一天时间
		Date dateEnd = null;// 本月最后一天时间
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if (currentTime.indexOf(':') >= 0)
				sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			date = sdf.parse(currentTime);

			dateStart = new Date();
			dateStart.setDate(1);
			dateStart = sdf.parse(sdf.format(dateStart));
			dateEnd = new Date();
			dateEnd.setDate(1);
			dateEnd.setMonth(dateEnd.getMonth() + 1);

			dateEnd = sdf.parse(sdf.format(dateEnd));

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return (date.compareTo(dateStart) >= 0 && date.compareTo(dateEnd) <= 0);
	}

	public static boolean isInCurrentMonth(Date date) {
		if (date == null) {
			return false;
		}

		Date dateStart = null;// 本月第一天时间
		Date dateEnd = null;// 本月最后一天时间
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			dateStart = new Date();
			dateStart.setDate(1);
			dateStart = sdf.parse(sdf.format(dateStart));
			dateEnd = new Date();
			dateEnd.setDate(1);
			dateEnd.setMonth(dateEnd.getMonth() + 1);

			dateEnd = sdf.parse(sdf.format(dateEnd));

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return (date.compareTo(dateStart) >= 0 && date.compareTo(dateEnd) <= 0);
	}

	public static Date getMonthFirstTime(Date d) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		try {
			date = sdf.parse(sdf.format(new Date(d.getYear(), d.getMonth(), 1)));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 将null转换成字符串并去掉空格
	 *
	 * @param obj
	 * @return String
	 */
	public static String convertNULL2String(Object obj) {
		if (obj == null)
			return "";
		return obj.toString().trim();
	}

	/**
	 * 是否为空
	 *
	 * @param obj
	 *            (注：如果obj的只为null、""、-1、-1L、-1D、-1F返回值都为true)
	 * @return boolean
	 */
	public static boolean isSpace(Object obj) {
		if (obj == null)
			return true;

		if (obj instanceof String)
			return "".equals(obj.toString()) || "null".equalsIgnoreCase(obj.toString()) || "undefined".equalsIgnoreCase(obj.toString());
		if (obj instanceof Integer)
			return (Integer.parseInt(obj.toString()) == -1) || (Integer.parseInt(obj.toString()) == 0);
		if (obj instanceof Long)
			return (Long.parseLong(obj.toString()) == -1L);
		if (obj instanceof Double)
			return (Double.parseDouble(obj.toString()) == -1d);
		if (obj instanceof Float)
			return (Float.parseFloat(obj.toString()) == -1f);

		return false;
	}

	public static String filer2String(Object obj) {
		if (obj == null)
			return "";
		String str = obj.toString().trim().replace("'", "''");
		return str;
	}

	/**
	 * 将json数据转换为Map
	 *
	 * @param json
	 * @return
	 */
	public static Map convertJson2Map(String json) {
		Gson gson = new Gson();
		List rows = gson.fromJson(json, new TypeToken<List<Map<String, String>>>() {
		}.getType());
		Map map = new HashMap();
		for (int i = 0; i < rows.size(); i++) {
			HashMap row = (HashMap) rows.get(i);
			// map.put("_state", row.get("_state"));
			map = row;
		}
		return map;
	}

	/**
	 * 将null 与 空字符转换为0
	 *
	 * @param obj
	 * @return
	 */
	public static String convertStringToZero(Object obj) {
		if (obj == null || "".equals(obj + "") || "null".equalsIgnoreCase(obj + ""))
			return "0";
		return obj.toString();
	}

	/**
	 * 将null 与 空字符转换为0.0
	 *
	 * @param obj
	 * @return
	 */
	public static float convertStringToFloat(Object obj) {

		if (obj == null || "".equals(obj) || "null".equals(obj) || "null.0".equals(obj)) {
			return 0.0f;
		}else {
			if (!obj.toString().contains(".")) {
				obj = obj + ".0";
			}
			return Float.parseFloat(obj.toString());
		}
	}

	/**
	 * 将null 与 空字符转换为0
	 *
	 * @param obj
	 * @return
	 */
	public static int convertStringToInt(Object obj) {
		if (obj == null || "".equals(obj) || "null".equals(obj)) {
			return 0;
		}else {
			String str = obj.toString();
			if (str.contains(".")) {
				str = str.substring(0, str.indexOf("."));
			}
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");//去掉空格
			Matcher m = p.matcher(str);
			str = m.replaceAll("");
			return Integer.parseInt(str);
		}
	}

	/**
	 * 构建SQL需要的条件
	 * @param ids  ",1231,2345,2345,";
	 * @return ""、或 '1231','2345','2345'
	 */
	public static String buildSQLCmd(String ids) {
		if(isSpace(ids)){
			return "";
		}

		String[] _ids = ids.split(",");

		String id = "";
		for (int i = 0; i < _ids.length; i++) {
			if (_ids[i].length() > 0) {
				id += "'" + _ids[i].trim() + "',";
			}
		}

		if (id.length() > 0) {
			id = id.substring(0, id.length() - 1);
		}

		return id;
	}

	public static void main(String[] args) {
		String ids = ",1231,2345,2345,";
		String[] _ids = ids.split(",");
		if (ids.startsWith(",")) {
			ids = ids.substring(1, ids.length());
		}
		if (ids.endsWith(",")) {
			ids = ids.substring(0, ids.length() - 1);
		}
		ids = "'" + ids.replace(",", "','") + "'";
		System.out.println(ids);

		System.err.println(Functions.convertStringToFloat("3.65"));
		float a = 0.0f /0.0f;
		System.err.println(0/1);
	}

}
