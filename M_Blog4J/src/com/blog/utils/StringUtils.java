package com.blog.utils;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.htmlparser.Parser;
import org.htmlparser.visitors.TextExtractingVisitor;





/**
 * ������ԿΪ��mc90716
 * @author ��
 *
 */
public class StringUtils extends org.apache.commons.lang.StringUtils{


	private static final String PASSWORD_CRYPT_KEY = "M_Blog_4J";
	private final static String ISO8859_1 = "8859_1";
	private final static String DES = "DES";


	/*private final static NodeFilter nodeFilter = new NodeFilter() {
		public boolean accept(Node node) {
			return true;
		}
	};*/


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
	/**
	 * ���ݽ���
	 * @param data
	 * @param key ��Կ
	 * @return
	 * @throws Exception
	 */
	public final static String decrypt(String data, String key){
		if(data!=null)
			try {
				return new String(decrypt(hex2byte(data.getBytes()),key.getBytes()));
			}catch(Exception e) {
				e.printStackTrace();
			}
		return null;
	}
	/**
	 * ���ݼ���
	 * @param data
	 * @param key ��Կ
	 * @return
	 * @throws Exception
	 */
	public final static String encrypt(String data, String key){
		if(data!=null)
			try {
				return byte2hex(encrypt(data.getBytes(),key.getBytes()));
			}catch(Exception e) {
				e.printStackTrace();
			}
		return null;
	}
	/**
	 * �������
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public final static String decryptPassword(String data){
		if(data!=null)
			try {
				return new String(decrypt(hex2byte(data.getBytes()),PASSWORD_CRYPT_KEY.getBytes()));
			}catch(Exception e) {
				e.printStackTrace();
			}
		return null;
	}

	/**
	 * �������
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public final static String encryptPassword(String password){
		if(password!=null)
			try {
				return byte2hex(encrypt(password.getBytes(),PASSWORD_CRYPT_KEY.getBytes()));
			}catch(Exception e) {
				e.printStackTrace();
			}
		return null;
	}
	/**
	 * ������ת�ַ���
	 * @param b
	 * @return
	 */
	public static String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; b!=null && n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
		}
		return hs.toUpperCase();
	}

	public static byte[] hex2byte(byte[] b) {
		if((b.length%2)!=0)
			throw new IllegalArgumentException("���Ȳ���ż��");
		byte[] b2 = new byte[b.length/2];
		for (int n = 0; n < b.length; n+=2) {
			String item = new String(b,n,2);
			b2[n/2] = (byte)Integer.parseInt(item,16);
		}
		return b2;
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

	/**
	 * ��java.util.Date ��ʽת��Ϊ�ַ�����ʽ'yyyy-MM-dd HH:mm:ss'(24Сʱ��)
	 * ��Sat May 11 17:24:21 CST 2002 to '2002-05-11 17:24:21'
	 * @param time
	 * @return
	 */
	public static String dateToString(Date time){
		SimpleDateFormat formatter;
		formatter = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
		String ctime = formatter.format(time);

		return ctime;
	} 

	/**
	 * ��ȡ���ı���Ϣ
	 * @param inputHtml
	 * @return
	 */
	public static String extractText(String inputHtml) throws Exception {
		Parser myParser;
		myParser = Parser.createParser(inputHtml, "GBK");
		TextExtractingVisitor visitor = new TextExtractingVisitor();
		myParser.visitAllNodesWith(visitor);
		String textInPage = visitor.getExtractedText();
		return textInPage;
	}

	

	
	
	
/*
	public static void main(String[] args) throws Exception {
		String html = "<p><span style='color:#333333;font-family:helvetica, tahoma, arial, sans-serif;font-size:14px;font-style:normal;font-variant:normal;font-weight:normal;letter-spacing:normal;line-height:24px;orphans:auto;text-align:left;text-indent:0px;text-transform:none;widows:auto;word-spacing:0px;-webkit-text-size-adjust:auto;-webkit-text-stroke-width:0px;background-color:#f7f7f7;display:inline !important;float:none;'>������˷�����һ������Backdoor.AndroidOS.Obad.a����������������˵��������������Android���������˺��ѷ���Ҳ�����޸ġ����ʼ���������ŵķ�ʽ���вٿأ������߿���Զ�̿����豸����ȡ�û��������ݡ�</span></p><p><br /></p>";
		Parser myParser;
		myParser = Parser.createParser(html, "GBK");
		TextExtractingVisitor visitor = new TextExtractingVisitor();
		myParser.visitAllNodesWith(visitor);
		String textInPage = visitor.getExtractedText();
		System.out.println(textInPage);
	}*/

}
