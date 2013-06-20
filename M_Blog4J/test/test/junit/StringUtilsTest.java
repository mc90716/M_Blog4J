package test.junit;

import org.junit.Test;

import com.blog.utils.StringUtils;

public class StringUtilsTest {
	@Test
	public void test() {
		/*String str = StringUtils.encrypt("abcdefgh", "DLOG4JV3");
		System.out.println(str);
		
		System.out.println(StringUtils.decrypt(str,"DLOG4JV3"));*/
		
		String s = "込込込込";
		 String subStr = null;
         byte[] strByte = s.getBytes();
         for(byte bt : strByte)
		System.out.println(bt);
	}

}
