package com.cykj.common.util;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.cykj.base.common.system.SystemConfig;
import com.cykj.base.common.util.HttpUtil;

public class HttpUtilTest {

	@Test
	public void testSendToConnection() {
		String serviceUrl = "http://ip.taobao.com/service/getIpInfo.php";
		String returnStr = HttpUtil.sendToConnection(serviceUrl, "POST", SystemConfig.CHARSET, "ip=113.102.163.120");
		System.out.println(JSON.toJSONString(returnStr));
	}

}
