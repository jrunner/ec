package com.ilucky.aplay.util.id;

import java.util.UUID;

/**
 * @author IluckySi
 * @since 20150721
 */
public class IdUtil {

	/**
	 * 生成一个随机的UUID
	 * @return String
	 */
	public static String getId() {
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(getId());
	}
}
/**
2077b4d4d45a4f2d97748af14ba2cd32
*/