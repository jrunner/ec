package com.ilucky.aplay.util.code;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author IluckySi
 * @since 20150721
 */
public class Md5Util {

	/**
	 * 用md5加密字符串
	 * @param before
	 * @return String
	 */
	public static String md5(String before) {
		return DigestUtils.md5Hex(before).toString();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(md5("123456"));
	}
}

/**
e10adc3949ba59abbe56e057f20f883e
*/