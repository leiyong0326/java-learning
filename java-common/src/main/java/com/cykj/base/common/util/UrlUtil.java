package com.cykj.base.common.util;

import java.net.URLEncoder;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.cykj.base.common.system.SystemConfig;

/**
 * Url处理工具类
 * @author LeiYong
 *
 */
public class UrlUtil {
	/**
	 * 获取二级域名
	 * www.baidu.com返回www
	 * abc.def.ggg.com.cn返回abc.def
	 * abc.def.ggg.com返回abc.def
	 * @param url
	 * @return
	 */
	public static String getSecondUrl(String url){
		if (StringUtils.isNotBlank(url)) {
			String nurl = url.replaceAll("^(http[s]?://)(.+?)(\\.[\\w-]+?\\.)(net|com|cn|org|com.cn)/(.*)$", "$2");
			return nurl.equals(url)?"cykj":nurl;
		}
		return "";
	}
	/**
	 * 解析Map为参数
	 * @param param
	 * @return
	 */
	public static String parseToUrlParam(Map<String, String> param){
		StringBuffer buffer = new StringBuffer();
		if (param != null && !param.isEmpty()) {
			ArrayUtil.foreach(param, (String key,String value)->{
				try {
					buffer.append(key).append("=").append(URLEncoder.encode(value,SystemConfig.CHARSET)).append("&");
				} catch (Exception e) {
				}
			});
			buffer.deleteCharAt(buffer.length() - 1);
		}
		return buffer.toString();
	}
	public static void main(String[] args) {
		System.out.println(getSecondUrl("https://workorder.console.aliyun.com/console.htm#/ticket/createIndex"));
	}
}
