package com.ilucky.aplay.util.android;

import android.os.Environment;

/**
 * @author IluckySi
 * @since 20150729
 */
public class SdCardUtil {

	public static boolean hasSdcard(){
		String state = Environment.getExternalStorageState();
		if(state.equals(Environment.MEDIA_MOUNTED)){
			return true;
		}else{
			return false;
		}
	}
}
