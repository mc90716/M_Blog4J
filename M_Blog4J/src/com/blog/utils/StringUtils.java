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
 * 加密密钥为：mc90716
 * @author 马超
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
	/**
	 * 数据解密
	 * @param data
	 * @param key 密钥
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
	 * 数据加密
	 * @param data
	 * @param key 密钥
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
	 * 密码解密
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
	 * 密码加密
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
	 * 二行制转字符串
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
			throw new IllegalArgumentException("长度不是偶数");
		byte[] b2 = new byte[b.length/2];
		for (int n = 0; n < b.length; n+=2) {
			String item = new String(b,n,2);
			b2[n/2] = (byte)Integer.parseInt(item,16);
		}
		return b2;
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

	/**
	 * 将java.util.Date 格式转换为字符串格式'yyyy-MM-dd HH:mm:ss'(24小时制)
	 * 如Sat May 11 17:24:21 CST 2002 to '2002-05-11 17:24:21'
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
	 * 抽取纯文本信息
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
		String html = "<p><span style='color:#333333;font-family:helvetica, tahoma, arial, sans-serif;font-size:14px;font-style:normal;font-variant:normal;font-weight:normal;letter-spacing:normal;line-height:24px;orphans:auto;text-align:left;text-indent:0px;text-transform:none;widows:auto;word-spacing:0px;-webkit-text-size-adjust:auto;-webkit-text-stroke-width:0px;background-color:#f7f7f7;display:inline !important;float:none;'>最近有人发现了一种名叫Backdoor.AndroidOS.Obad.a的特洛伊病毒，据说是世界上最高深的Android病毒，别人很难发现也很难修改。它最开始以垃圾短信的方式进行操控，传播者可以远程控制设备，获取用户敏感数据。</span></p><p><br /></p>";
		Parser myParser;
		myParser = Parser.createParser(html, "GBK");
		TextExtractingVisitor visitor = new TextExtractingVisitor();
		myParser.visitAllNodesWith(visitor);
		String textInPage = visitor.getExtractedText();
		System.out.println(textInPage);
	}*/

}
