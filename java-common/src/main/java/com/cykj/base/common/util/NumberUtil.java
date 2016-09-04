package com.cykj.base.common.util;
/**
 * 数字工具类
 * @author LeiYong
 *
 */
public class NumberUtil {
	/**
	 * 获取一个随机整数
	 * @param length
	 * @return
	 */
	public static long random(long length){
		return (long) (Math.random()*length);
	}
	public static int random(int length){
		return (int) (Math.random()*length);
	}
}
