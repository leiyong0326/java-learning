package com.cykj.common.util;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.cykj.base.common.model.PropertiesModel;
import com.cykj.base.common.system.SystemConfig;
import com.cykj.base.common.util.PropertiesCacheUtil;

public class PropertiesCacheUtilTest {

	List<Integer> list;
	@Before
	public void testBefore(){
		System.out.println(SystemConfig.class.getResource("/").getFile());
		System.out.println(PropertiesCacheUtil.getBasepath());
		System.out.println(PropertiesCacheUtil.getUserconfig());
		//PropertiesCacheUtil.loadProperties(getRequest().getServletContext().getRealPath(PropertiesCacheUtil.getBasepath()+PropertiesCacheUtil.USERCONFIG)).get("generateMac");
	}
	@Test
	public void testLoadProperties() {
		String base = "D:\\workspace\\cykj-common\\resources\\";
		PropertiesModel propertiesModel = PropertiesCacheUtil.loadProperties(base+PropertiesCacheUtil.getUserconfig());
		System.out.println(propertiesModel.get("ACCESS_KEY"));
	}

}
