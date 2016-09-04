package com.cykj.common.util;

import static org.junit.Assert.*;
import static com.cykj.base.common.util.UrlUtil.*;

import org.junit.Test;

public class UrlUtilTest {

	@Test
	public void testGetSecondUrl() {
		assertEquals("workorder.console", getSecondUrl("https://workorder.console.aliyun.com/console.htm#/ticket/createIndex"));
		assertEquals("workorder.console", getSecondUrl("http://workorder.console.aliyun.com/console.htm#/ticket/createIndex"));
		assertEquals("www", getSecondUrl("http://www.blogjava.net/chengang/archive/2010/12/29/341939.html"));
		assertEquals("user.qzone", getSecondUrl("http://user.qzone.qq.com/282211932/infocenter?ptsig=OT7bVWWrByfMvznKhIlDeLCLXNza9pnIENlitmplSLM_"));
		assertEquals("mail", getSecondUrl("https://mail.qq.com/cgi-bin/http://www.abc.com"));
		assertEquals("mail.qq", getSecondUrl("https://mail.qq.con.com/cgi-bin/http://www.abc.com"));
		assertEquals("mail.qq", getSecondUrl("https://mail.qq.cn.com/cgi-bin/http://www.abc.com"));
		assertEquals("mail.cn.qq", getSecondUrl("https://mail.cn.qq.cn.com/cgi-bin/http://www.abc.com"));
		assertEquals("", getSecondUrl("https://mail.qq.ccom/cgi-bin/http://www.abc.com"));
		assertEquals("mail", getSecondUrl("https://mail.qq.com.cn/cgi-bin/http://www.abc.com"));
	}

}
