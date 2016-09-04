package com.cykj.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cykj.base.common.util.ArrayUtil;

public class ArrayUtilTest {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LoggerFactory.getLogger(ArrayUtilTest.class);

	List<Integer> list;
	@Before
	public void testBefore(){
		if (logger.isDebugEnabled()) {
			logger.debug("testBefore() - start"); //$NON-NLS-1$
		}

		list = new ArrayList<>();
		for (int i = 0; i < 20; i++) {
			list.add((int)(Math.random()*100));
		}

		if (logger.isDebugEnabled()) {
			logger.debug("testBefore() - end"); //$NON-NLS-1$
		}
	}
	@Test
	public void testSortList() {
		if (logger.isDebugEnabled()) {
			logger.debug("testSortList() - start"); //$NON-NLS-1$
		}

		System.out.println("this is sort method");
		System.out.println(ArrayUtil.sortList(list, true));

		if (logger.isDebugEnabled()) {
			logger.debug("testSortList() - end"); //$NON-NLS-1$
		}
	}

	@Test
	public void testFileter() {
		if (logger.isDebugEnabled()) {
			logger.debug("testFileter() - start"); //$NON-NLS-1$
		}

		System.out.println("this is filter method");
		System.out.println(ArrayUtil.filter(list,(Integer t) -> {return t > 50;}));

		if (logger.isDebugEnabled()) {
			logger.debug("testFileter() - end"); //$NON-NLS-1$
		}
	}
	@Test
	public void testForeachList () {
		if (logger.isDebugEnabled()) {
			logger.debug("testFileter() - start"); //$NON-NLS-1$
		}

		System.out.println("this is foreach list method");
		ArrayUtil.foreach(list,(Integer t,int i) -> {System.out.println(i+"--"+t);});

		if (logger.isDebugEnabled()) {
			logger.debug("testFileter() - end"); //$NON-NLS-1$
		}
	}
	@Test
	public void testForeachMap () {
		if (logger.isDebugEnabled()) {
			logger.debug("testFileter() - start"); //$NON-NLS-1$
		}
		Map<String, Object> map = new HashMap<>();
		map.put("ss", "tt");
		map.put("ii", 193);
		map.put("ll", list);
		System.out.println("this is foreach list method");
		ArrayUtil.foreach(map,(String k,Object v) -> {System.out.println(k+"::"+v);});

		if (logger.isDebugEnabled()) {
			logger.debug("testFileter() - end"); //$NON-NLS-1$
		}
	}
	@Test
	public void testForeachSet () {
		if (logger.isDebugEnabled()) {
			logger.debug("testFileter() - start"); //$NON-NLS-1$
		}
		Set<Object> ss = new HashSet<>();
		ss.add("1");
		ss.add("3");
		ss.add(list);
		System.out.println("this is foreach list method");
		ArrayUtil.foreach(ss,(Object s) -> {System.out.println(s);});

		if (logger.isDebugEnabled()) {
			logger.debug("testFileter() - end"); //$NON-NLS-1$
		}
	}

}
