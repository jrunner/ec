package com.t2t.top.base.utils;

import java.security.SecureRandom;
import java.util.Random;

/******************************************************************************
 * 
 * <p>Description: 产生随机数、字符工具</p> 
 * Project: job
 * Package: cn.com.gome.hotel.utils
 *    File: RandomUtils.java
 * 
 * @author chenxushao@hotmail.com
 * @date 2015年5月12日 上午10:59:22
 * 
 *****************************************************************************/
public class RandomUtils {
	
	public final static String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	private RandomUtils(){}

	/** 
	* @Title: generateRandomBytes 
	* @Description: 随机生成字节数组
	* @param  num_to_generate number of bytes to generate
	* @return byte[]
	*/
	public static byte[] generateRandomBytes(int num_to_generate) {
		Random random = new Random(System.currentTimeMillis());
		byte[] id = new byte[num_to_generate];
		random.nextBytes(id);
		return id;
	}

	/**
	 * @Title: generateRandomAlphanumerics
	 * @Description: 随机生成指定长度的字符串
	 * @param num_to_generate
	 * @return String
	 * @throws
	 */
	public static String generateRandomAlphanumerics(int length) {
		if (length < 1) {
			return "";
		}
		Random random = new Random(System.currentTimeMillis());
		StringBuffer buff = new StringBuffer(length);
		for (int i = 0; i < length; i++) {
			int pos = (int) (random.nextDouble() * alphabet.length());
			buff.append(alphabet.charAt(pos));
		}
		return buff.toString();
	}

	/** 
	* @Title: generateRandomPlusMinus
	* @Description: 随时生成1或-1
	* @return int  +1 or -1
	*/
	public static int generateRandomPlusMinus() {
		Random random = new Random(System.currentTimeMillis());
		return random.nextBoolean() ? -1 : 1;
	}

	/** 
	* @Title: nextFloat 
	* @Description: 随机生成float数值
	* @return float
	*/
	public static float nextFloat() {
		Random random = new Random(System.currentTimeMillis());
		return random.nextFloat();
	}
	
	/** 
	* @Title: nextBoolean 
	* @Description: 随机生成布尔值
	* @return boolean
	*/
	public static boolean nextBoolean() {
		Random random = new Random(System.currentTimeMillis());
		return random.nextBoolean();
	}

	public static byte[] nextSecureHash() {
	    SecureRandom secureRandom = new SecureRandom();
		byte[] hash = new byte[20];
		secureRandom.nextBytes(hash);
		return (hash);
	}

	public static byte[] nextHash() {
		Random random = new Random(System.currentTimeMillis());
		byte[] hash = new byte[20];
		random.nextBytes(hash);
		return (hash);
	}

	/** 
	* @Title: nextInt 
	* @Description: 生成一个随机的int值，该值介于[0,n)的区间
	* @param n
	* @return int
	*/
	public static int nextInt(int n) {
		Random random = new Random(System.currentTimeMillis());
		return random.nextInt(n);
	}

	/** 
	* @Title: nextByte 
	* @Description: 随机生成字节数值
	* @return byte
	*/
	public static byte nextByte() {
		Random random = new Random(System.currentTimeMillis());
		return (byte) random.nextInt();
	}

	/** 
	* @Title: nextInt 
	* @Description: 随机生成int值
	* @return int
	*/
	public static int nextInt() {
		Random random = new Random(System.currentTimeMillis());
		return random.nextInt();
	}

    /** 
    * @Title: nextDouble 
    * @Description: 随机生成double值
    * @return double
    */
    public double nextDouble() {
    	Random random = new Random(System.currentTimeMillis());
        switch (random.nextInt(10)) {
        case 0:
            return Double.MIN_VALUE;
        case 1:
            return Double.MAX_VALUE;
        case 2:
            return Float.MIN_VALUE;
        case 3:
            return Float.MAX_VALUE;
        case 4:
            return random.nextDouble();
        case 5:
        case 6:
            return 0;
        case 7:
            return random.nextGaussian() * 20000. - 2000.;
        default:
            return random.nextGaussian() * 200. - 50.;
        }
    }
	
	
	/** 
	* @Title: nextLong 
	* @Description: 随机生成long值
	* @return long
	*/
	public static long nextLong() {
		Random random = new Random(System.currentTimeMillis());
		return random.nextLong();
	}
	/** 
	* @Title: generateRandomIntUpto 
	* @Description:  随机生成 0 and max-1范围的整型数值
	* @param max
	* @return int   random int between 0 and max-1. e.g. param of 10 returns 0->9
	* @throws 
	*/
	public static int generateRandomIntUpto(int max) {
		return nextInt(max);
	}

	/** 
	* @Title: generateRandomIntBetween 
	* @Description: 返回指定范围的整型数值
	* @param min
	* @param max
	* @return int  random int between min and max, e.g params of [5,7] returns 5,6 or 7
	* @throws 
	*/
	public static int generateRandomIntBetween(int min, int max) {
		return min + generateRandomIntUpto(max + 1 - min);
	}
}