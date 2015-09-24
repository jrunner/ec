package com.ilucky.aplay.util.android;

import com.ilucky.aplay.core.application.AplayApplication;

import android.widget.Toast;

/**
 * @author IluckySi
 * @since 20150812
 */
public class ToastUtil {

	public static void toast(String message) {
		Toast.makeText(AplayApplication.getContext(), message, Toast.LENGTH_SHORT).show();
	}
	
	public static void toast(int message) {
		Toast.makeText(AplayApplication.getContext(), message, Toast.LENGTH_SHORT).show();
	}
	
	 public static void toast(String message, int duration) {
		 Toast.makeText(AplayApplication.getContext(), message, duration).show();
	 }
	 
	 public static void toast(int message, int duration) {
		 Toast.makeText(AplayApplication.getContext(), message, duration).show();
	 }
}
