package com.ilucky.aplay.util.android;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import com.ilucky.aplay.core.application.AplayApplication;

/**
 * @author IluckySi
 * @since 20150820
 */
public class FileUtil {

	private static final String TAG = "FileUtil";
	
	public static File getCacheDir() {
		return AplayApplication.getContext().getCacheDir();
	}
	
	public static File getFileDir() {
		return AplayApplication.getContext().getFilesDir();
	}

	public static File getOtherDir(String name) {
		File file = AplayApplication.getContext().getDir(name, 0);
		if(!file.exists()) {
			file.mkdirs();
		}
		return file;
	}
	
	public static InputStream getAssestInputStream(String file) {
		try {
			return AplayApplication.getContext().getAssets().open(file);
		} catch (IOException e) {
			LogUtil.e(TAG, e.toString());
			return null;
		}
	}
}
