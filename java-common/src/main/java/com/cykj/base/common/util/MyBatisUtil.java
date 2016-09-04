package com.cykj.base.common.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.cykj.base.common.model.Model;

/**
 * MyBatis工具类
 * @author LeiYong
 *
 */
public class MyBatisUtil {
	private static final String splitCharacter = ",";
	/**
	 * 解析String为Model,切割符默认为","
	 * @param texts 如"pk,=,111","age,>,15"
	 * @return 
	 */
	public static List<Model> parseBase(String... texts){
		List<Model> list = new ArrayList<>();
		if (texts!=null&&texts.length>0) {
			for (String text : texts) {
				if (StringUtils.isNotBlank(text)) {
					String[] arr = StringUtil.indexOf(text, splitCharacter, 0);
					String[] arr2 = StringUtil.indexOf(arr[1], splitCharacter, 0);
					list.add(new Model(arr[0],arr2[0], arr2[1]));
				}
			}
		}
		return list;
	}
	/**
	 * 解析String为Model,默认使用,分割
	 * 例如:"name","in","张三,李四,王五"
	 * @param column 列
	 * @param operator 操作符
	 * @param values 值集
	 * @return
	 */
	public static Model parseList(String column,String operator,String values){
		return parseList(column, operator, values, splitCharacter);
	}
	/**
	 * 解析String为Model
	 * 例如:"name","in","张三#李四#王五","#"
	 * @param column 列
	 * @param operator 操作符
	 * @param values 值集
	 * @param split 切割符,支持正则
	 * @return
	 */
	public static Model parseList(String column,String operator,String values,String split){
		if (StringUtils.isNotBlank(values)) {
			List<String> list = Arrays.asList(values.split(split));
			return new Model(column,operator,list);
		}
		return null;
	}
}
