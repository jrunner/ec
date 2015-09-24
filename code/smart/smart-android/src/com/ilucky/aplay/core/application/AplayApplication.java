package com.ilucky.aplay.core.application;

import android.app.Application;
import android.content.Context;

/**
 * @author IluckySi
 * @since 20150721
 */
public class AplayApplication extends Application {
		
	private static Context context;
		
	@Override
	public void onCreate() {
		context = getApplicationContext();
	}
	
	public static Context getContext() {
		return context;
	}
}
