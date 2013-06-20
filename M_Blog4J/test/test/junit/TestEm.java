package test.junit;

import static org.junit.Assert.*;

import org.junit.Test;

import com.blog.service.impl.UserServiceBean;

public class TestEm {

	@Test
	public void test() {
		UserServiceBean usb = new UserServiceBean();
		System.out.println(usb.getCount());
	}

}
