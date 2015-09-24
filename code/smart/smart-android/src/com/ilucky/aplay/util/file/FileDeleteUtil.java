package com.ilucky.aplay.util.file;

import java.io.File;

import com.ilucky.aplay.util.android.LogUtil;

/**
 * 删除某个文件或目录
 * 如果删除目录,可以通过设置isDeleteRoot参数决定是否删除根目录.
 * @author IluckySi
 * @date 20150827
 */
public class FileDeleteUtil {

	private static final String TAG = "FileDeleteUtil";
	
	public static void delete(String src, boolean isDeleteRoot) {
		File srcFile = new File(src);
		if(!srcFile.exists()) {
			LogUtil.e(TAG, "源文件不存在"+src);
			return;
		}
		beginDelete(srcFile);
		if(isDeleteRoot) {
			srcFile.delete();
			LogUtil.d(TAG, "删除文件src="+srcFile.getPath()+"成功");
		}
	}
	
	private static void beginDelete(File srcFile) {
		try {
			File[] srcFileChildren = null;
			if(srcFile.isDirectory()) {
				srcFileChildren = srcFile.listFiles();
			} else {
				srcFileChildren = new File[1];
				srcFileChildren[0] = srcFile;
			}
			for(File file : srcFileChildren) {
				if(file.isDirectory()) {
					beginDelete(file);
				}
				file.delete();
				LogUtil.d(TAG, "删除文件src="+file.getPath()+"成功");
			}
		} catch (Exception e) {
			LogUtil.e(TAG, "删除文件src="+srcFile.getPath()+"发生异常:"+e);
		}
	}
}
