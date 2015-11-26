package com.t2t.top.base.utils;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zr_wenshengkun on 2015/4/14.
 * AES加密类
 */
public class AESUtils {
	public static final int ENCRYPTION_LENGTH = 128;
	private final static Logger logger = LoggerFactory.getLogger(AESUtils.class);
    /**
     * 加密
     * @method encrypt
     * @param content   需要加密的内容
     * @param password  加密密码
     * @return
     * @throws
     * @since v1.0
     */
	public static byte[] encrypt(String content, String password){
		return encrypt(content,password,ENCRYPTION_LENGTH);
	}

    /**
     * 
     * @Title: encrypt 
     * @Description: 加密
     * @param content
     * @param password
     * @param encryptionLength  128
     * @return 返回byte[] 类型
     */
    public static byte[] encrypt(String content, String password,int encryptionLength){
    	if(null==content){
    		return null;
    	}
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(ENCRYPTION_LENGTH);            
            SecretKey secretKey = new SecretKeySpec(initPasswordKey(password,encryptionLength),"AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] result = cipher.doFinal(content.getBytes());
            return result; 
        } catch (Exception e) {
        	logger.error("加密失败",e);
        }
        return null;
    }

    /**
     * 解密
     * @method decrypt
     * @param content   待解密内容
     * @param password  解密密钥
     * @return
     * @throws
     * @since v1.0
     */
    public static byte[] decrypt(byte[] content, String password){
    	return decrypt(content, password , ENCRYPTION_LENGTH);
    }
    
    /**
     * 解密
     * @Title: decrypt 
     * @Description: 解密
     * @param content
     * @param password
     * @param encryptionLength
     * @return 返回byte[] 类型
     */
    public static byte[] decrypt(byte[] content, String password,int encryptionLength){
    	if(null==content||content.length<1){
    		return null;
    	}
        try {
            SecretKey secretKey = new SecretKeySpec(initPasswordKey(password,encryptionLength),"AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] result = cipher.doFinal(content);
            return result; 
        } catch (Exception e) {
        	logger.error("解密失败",e);
        }
        return null;
    }
    
    public static byte[] initPasswordKey(String password,int encryptionLength){
		int keyLength = encryptionLength/8;
		byte[] pwd = password.getBytes();
		if(pwd.length!=keyLength){
			if(pwd.length>keyLength){
				return Arrays.copyOfRange(pwd, 0, keyLength);
			}else{
				byte[] pwd2 = Arrays.copyOf(pwd, keyLength);
				for(int i =keyLength - pwd.length;i<0;i--){
					pwd2[i] = 'G';
				}
				return pwd2;
			}
		}
		return pwd;
	}
    /** 
     * 将二进制转换成16进制 
     * @method parseByte2HexStr 
     * @param buf 
     * @return 
     * @throws  
     * @since v1.0 
     */  
    public static String parseByte2HexStr(byte buf[]){  
    	if(null==buf||buf.length<1){
    		return null;
    	}
        return Base64.encodeBase64String(buf); 
    }  
      
    /** 
     * 将16进制转换为二进制 
     * @method parseHexStr2Byte 
     * @param hexStr 
     * @return 
     * @throws  
     * @since v1.0 
     */  
    public static byte[] parseHexStr2Byte(String hexStr){  
        if(hexStr==null||hexStr.length() < 1) {
            return null;  
        }
        return Base64.decodeBase64(hexStr);  
    }  
}
