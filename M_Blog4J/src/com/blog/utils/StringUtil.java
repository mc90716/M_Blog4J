package com.blog.utils;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class StringUtil extends org.apache.commons.lang.StringUtils{
	
	private final static String DES = "DES";
	
	/**
	 * �ж��ʼ���ַ��ʽ�Ƿ�Ϸ�
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
	 * ����
	 * @param src ����Դ
	 * @param key ��Կ�����ȱ�����8�ı���
	 * @return	  ���ؼ��ܺ������
	 * @throws Exception
	 */
	public static byte[] encrypt(byte[] src, byte[] key)
		throws Exception {
		//		DES�㷨Ҫ����һ�������ε������Դ
		SecureRandom sr = new SecureRandom();
		// ��ԭʼ�ܳ����ݴ���DESKeySpec����
		DESKeySpec dks = new DESKeySpec(key);
		// ����һ���ܳ׹�����Ȼ��������DESKeySpecת����
		// һ��SecretKey����
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
		SecretKey securekey = keyFactory.generateSecret(dks);
		// Cipher����ʵ����ɼ��ܲ���
		Cipher cipher = Cipher.getInstance(DES);
		// ���ܳ׳�ʼ��Cipher����
		cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);
		// ���ڣ���ȡ���ݲ�����
		// ��ʽִ�м��ܲ���
		return cipher.doFinal(src);
	}
	
	/**
	 * ����
	 * @param src	����Դ
	 * @param key	��Կ�����ȱ�����8�ı���
	 * @return		���ؽ��ܺ��ԭʼ����
	 * @throws Exception
	 */
	public static byte[] decrypt(byte[] src, byte[] key)
		throws Exception {
		//		DES�㷨Ҫ����һ�������ε������Դ
		SecureRandom sr = new SecureRandom();
		// ��ԭʼ�ܳ����ݴ���һ��DESKeySpec����
		DESKeySpec dks = new DESKeySpec(key);
		// ����һ���ܳ׹�����Ȼ��������DESKeySpec����ת����
		// һ��SecretKey����
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
		SecretKey securekey = keyFactory.generateSecret(dks);
		// Cipher����ʵ����ɽ��ܲ���
		Cipher cipher = Cipher.getInstance(DES);
		// ���ܳ׳�ʼ��Cipher����
		cipher.init(Cipher.DECRYPT_MODE, securekey, sr);
		// ���ڣ���ȡ���ݲ�����
		// ��ʽִ�н��ܲ���
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
     * �û������������ֻ�����ĸ�Ľ��
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
     * �ж��Ƿ�����ĸ�����ֵĽ��
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
