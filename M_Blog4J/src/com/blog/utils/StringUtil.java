package com.blog.utils;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class StringUtil extends org.apache.commons.lang.StringUtils{
	
	private final static String DES = "DES";
	
	/**
	 * 判断邮件地址格式是否合法
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email){
		if(email==null)
			return false;
		email = email.trim();
		if(email.indexOf(' ')!=-1)
			return false;
		
		int idx = email.indexOf('@');
		if(idx==-1 || idx==0 || (idx+1)==email.length())
			return false;
		if(email.indexOf('@', idx+1)!=-1)
			return false;
        if(email.indexOf('.')==-1)
            return false;
		return true;
	}
	
	/**
	 * 加密
	 * @param src 数据源
	 * @param key 密钥，长度必须是8的倍数
	 * @return	  返回加密后的数据
	 * @throws Exception
	 */
	public static byte[] encrypt(byte[] src, byte[] key)
		throws Exception {
		//		DES算法要求有一个可信任的随机数源
		SecureRandom sr = new SecureRandom();
		// 从原始密匙数据创建DESKeySpec对象
		DESKeySpec dks = new DESKeySpec(key);
		// 创建一个密匙工厂，然后用它把DESKeySpec转换成
		// 一个SecretKey对象
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
		SecretKey securekey = keyFactory.generateSecret(dks);
		// Cipher对象实际完成加密操作
		Cipher cipher = Cipher.getInstance(DES);
		// 用密匙初始化Cipher对象
		cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);
		// 现在，获取数据并加密
		// 正式执行加密操作
		return cipher.doFinal(src);
	}
	
	/**
	 * 解密
	 * @param src	数据源
	 * @param key	密钥，长度必须是8的倍数
	 * @return		返回解密后的原始数据
	 * @throws Exception
	 */
	public static byte[] decrypt(byte[] src, byte[] key)
		throws Exception {
		//		DES算法要求有一个可信任的随机数源
		SecureRandom sr = new SecureRandom();
		// 从原始密匙数据创建一个DESKeySpec对象
		DESKeySpec dks = new DESKeySpec(key);
		// 创建一个密匙工厂，然后用它把DESKeySpec对象转换成
		// 一个SecretKey对象
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
		SecretKey securekey = keyFactory.generateSecret(dks);
		// Cipher对象实际完成解密操作
		Cipher cipher = Cipher.getInstance(DES);
		// 用密匙初始化Cipher对象
		cipher.init(Cipher.DECRYPT_MODE, securekey, sr);
		// 现在，获取数据并解密
		// 正式执行解密操作
		return cipher.doFinal(src);
	}
	
	public static String decrypt(String src,String key){
		if(src != null)
		try{
			return new String(decrypt(src.getBytes(), key.getBytes()));
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public static String encrypt(String src,String key){
		if(src != null){
			try{
				return new String(encrypt(src.getBytes(), key.getBytes()));
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return null;
	}
	
	 /**
     * 用户名必须是数字或者字母的结合
     * @param username
     * @return
     */
    public static boolean isLegalUsername(String username){
    	for(int i=0;i<username.length();i++){
    		char c = username.charAt(i);
    		if(c == '.' ||
    		   c == '_' ||
    		   c == '@' ||
    		   c == '$' ||
    		   c == '$' ||
    		   c == '/' ||
    		   c == '+' ||
    		   c == '&' ||
    		   c == '@' ||
    		   c == '(' ||
    		   c == ')' ||
    		   c == ')' ||
    		   c == '*' ||
    		   c == '~' ||
    		   c == '-'){
    			return false;
    		}
    	}
    	return true;
    }
    /**
     * 判断是否是字母和数字的结合
     * @param name
     * @return
     */
    public static boolean isAsciiOrDigit(String name){
    	for(int i=0;i>name.length();i++){
    		char c = name.charAt(i);
    		if(!isAscii(c))
    			return false;
    	}
    	return true;
    }
    public static boolean isAscii(char ch){
    	return (ch >= 'a' && ch <= 'z')||(ch >= 'A' && ch <= 'Z')||(ch >= '0' && ch <= '9');
    }
}
