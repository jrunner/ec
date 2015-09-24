package com.ilucky.aplay.util.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.ilucky.aplay.util.android.LogUtil;

/**
 * @author IluckySi
 * @since 20150824
 */
public class FileReadUtil {

	private static final String TAG = "FileUtil";
	
	public static String readFile(File file) {
		String result = "";
		InputStream is = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		try {
			is = new FileInputStream(file);
			isr = new InputStreamReader(is);
			br = new BufferedReader(isr);
			String line = "";
			while ((line = br.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			LogUtil.e(TAG, "读取文件发生异常"+e);
		} finally {
			try {
				if(br != null) {
					br.close();
					br = null;
				}
				if(isr != null) {
					isr.close();
					isr = null;
				}
				if(is != null) {
					is.close();
					is = null;
				}
			} catch (Exception e){
				LogUtil.e(TAG, "关闭文件流发生异常"+e);
			}
		}
		return result;
	}
}
