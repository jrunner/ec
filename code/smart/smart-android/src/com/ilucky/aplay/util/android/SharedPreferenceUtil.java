package com.ilucky.aplay.util.android;

import com.ilucky.aplay.core.application.AplayApplication;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author IluckySi
 * @since 20150728
 */
public class SharedPreferenceUtil {

	public static void write(String file, String key, String value) {
		Context context = AplayApplication.getContext();
		SharedPreferences.Editor editor = context.getSharedPreferences(file, 0).edit();
		editor.putString(key, value);
		editor.commit();
	}

	public static String read(String file, String key) {
		Context context = AplayApplication.getContext();
		SharedPreferences preference = context.getSharedPreferences(file, 0);
		String value = preference.getString(key, null);
		return value;
	}
}
