package com.t2t.top.base.utils;



import com.t2t.top.base.monitor.Monitor;

public class MonitorUtils {

	public static void recordOne(String name, long time) {
		String key = getKey(name);
		Monitor.recordOne(key, time);
	}

	private static String getKey(String name) {

		return name;/*
		if (StringUtils.isBlank(AppContext.getSite())) {
			return name;
		}
        String mobile = "";
        if(StringUtils.isNotBlank(AppContext.getMapi())){
            mobile = AppContext.getMapi();
        }
		return AppContext.getSite() + mobile + "_" + name;*/
	}

	public static void recordOne(String name) {
		String key = getKey(name);
		Monitor.recordOne(key);
	}

	public static void decrRecord(String name) {
		String key = getKey(name);
		Monitor.decrRecord(key);
	}

	public static void recordMany(String name, long count, long time) {
		String key = getKey(name);
		Monitor.recordMany(key, count, time);
	}

	public static void recordSize(String name, long size) {
		String key = getKey(name);
		Monitor.recordSize(key, size);
	}

	public static void recordValue(String name, long count) {
		String key = getKey(name);
		Monitor.recordValue(key, count);
	}

    public static void recordName(String name) {
        Monitor.recordOne(name);
    }
}
