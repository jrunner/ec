package com.ilucky.aplay.util.zip;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;

import com.ilucky.aplay.util.android.LogUtil;

/**
 * 解压缩文件工具
 * windows下文件目录路径格式:E:\\src\\test.txt,Linux下路文件目录径格式;/home/src/test.txt.
 * 解压缩文件支持如下操作:
 * 将某压缩文件解压缩到某目录.
 * @author IluckySi
 * @since 20150721
 */
public class UnZipUtil {

	private String TAG = "UnZipUtil";
	private String code = "GB2312";
	private int buffer = 1024;
	private String srcPath;
	private String dstPath;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getBuffer() {
		return buffer;
	}
	public void setBuffer(int buffer) {
		this.buffer = buffer;
	}
	public String getSrcPath() {
		return srcPath;
	}
	public void setSrcPath(String srcPath) {
		this.srcPath = srcPath;
	}
	public String getDstPath() {
		return dstPath;
	}
	public void setDstPath(String dstPath) {
		this.dstPath = dstPath;
	}
	
	@SuppressWarnings("rawtypes")
	public void startUnZip () {
		if(!new File(srcPath).exists()) {
			LogUtil.e(TAG, "源路径不存在"+srcPath);
			return;
		}
		if(!new File(dstPath).exists()) {
			LogUtil.e(TAG, "目标路径不存在"+dstPath);
			return;
		}
		ZipFile zipFile = null;
		try {
			zipFile = new ZipFile(srcPath, code); 
			Enumeration emu = zipFile.getEntries();
			while(emu.hasMoreElements()) {
				InputStream is = null;
				BufferedInputStream bis = null;
				FileOutputStream fos = null;
				BufferedOutputStream bos = null;
				try {
					ZipEntry entry = (ZipEntry) emu.nextElement();
					
					//注意:遍历压缩文件中所有非目录节点.
					if(!entry.isDirectory()) {
						
						//关键:创建父文件,mkdirs是创建多层目录.
						File file = new File(dstPath + File.separator + entry.getName());
						File parent = file.getParentFile();
						if (parent != null && (!parent.exists())) {
							parent.mkdirs();
							LogUtil.d(TAG, "创建目录"+parent.getPath());
						}
						is = zipFile.getInputStream(entry);
						bis = new BufferedInputStream(is);
						fos = new FileOutputStream(file);
						bos = new BufferedOutputStream(fos, buffer);
						byte[] byteArray = new byte[buffer];
						int length = 0;
						while ((length = bis.read(byteArray, 0, buffer)) != -1) {
							bos.write(byteArray, 0, length);
							bos.flush();
						}
					}
				} catch (Exception e) {
					LogUtil.e(TAG, e.toString());
				} finally {
					try {
						if(bos != null) {
							bos.close();
							bos = null;
						}
						if(fos != null) {
							fos.close();
							fos = null;
						}
						if(bis != null) {
							bis.close();
							bis = null;
						}
						if(is != null) {
							is.close();
							is = null;
						}
					} catch (IOException e) {
						LogUtil.e(TAG, e.toString());
					}
				}
			}
		} catch (Exception e) {
			LogUtil.e(TAG, e.toString());
		}
	}
}