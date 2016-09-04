package com.cykj.common.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;

import com.cykj.base.common.model.Model;
import com.cykj.base.common.util.MyBatisUtil;

public class MyBatisUtilTest {

	@Test
	public void testParseBaseStringArray() {
		assertEquals(MyBatisUtil.parseList("abc", "in", "123,345,123").toString(), "abcin[123, 345, 123]");
	}


	@Test
	public void testParseListStringStringString() {
		List<Model> list = MyBatisUtil.parseBase("");
		assertEquals(list, "abcin[123, 345, 123]");
	}

	@Test
	public void testParseListStringStringStringString() {
		fail("Not yet implemented");
	}

}
