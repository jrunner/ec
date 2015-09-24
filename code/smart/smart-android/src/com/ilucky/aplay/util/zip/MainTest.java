package com.ilucky.aplay.util.zip;

/**
 * 测试压缩文件工具和解压缩文件工具
 * @author IluckySi
 * @date 20150721
 */
public class MainTest {

	public static void main(String[] args) {
		//压缩.
		ZipUtil zipUtil = new ZipUtil();
		zipUtil.setCode("GB2312");
		zipUtil.setBuffer(10240);
		zipUtil.setSrcPath("E:\\src");
		zipUtil.setDstPath("E:\\dst\\src.zip");
		long startZip = System.currentTimeMillis();
		zipUtil.startZip();
		long endZip = System.currentTimeMillis();
		System.out.println("压缩文件耗时: " + (endZip - startZip) + "毫秒");
		
		//解压缩.
		UnZipUtil unZipUtil = new UnZipUtil();
		unZipUtil.setCode("GB2312");
		unZipUtil.setBuffer(10240);
		unZipUtil.setSrcPath("E:\\dst\\src.zip");
		unZipUtil.setDstPath("E:\\dst");
		long startUnZip = System.currentTimeMillis();
		//unZipUtil.startUnZip();
		long endUnZip = System.currentTimeMillis();
		System.out.println("解压缩文件耗时: " + (endUnZip - startUnZip) + "毫秒");
	}
}
