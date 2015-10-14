package com.ilucky.mybatis2.util;

import java.util.UUID;

/**
 * @author IluckySi
 * @since 20151013
 */
public class IdUtil {

	public static String getId() {
		return UUID.randomUUID().toString().replace("-", "");
	}
}
