package com.cykj.base.common.util;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

import com.cykj.base.common.model.lambda.BeanOperator;
import com.cykj.base.common.model.lambda.ListOperator;
import com.cykj.base.common.model.lambda.MapOperator;

/**
 * 数组工具类
 * 
 * @author LeiYong
 */
public class ArrayUtil {
	/**
	 * 对list执行排序
	 * 
	 * @param list
	 * @param asc
	 *            是否正序排序
	 * @return
	 */
	public static <T extends Comparable<T>> List<T> sortList(List<T> list, boolean asc) {
		if (list == null) {
			return list;
		}
		list.sort((T a, T b) -> {
			if (asc) {
				return a.compareTo(b);
			} else {
				return b.compareTo(a);
			}
		});
		return list;
	}

	/**
	 * 对list中数据执行过滤
	 * 
	 * @param list
	 * @param predicate
	 *            过滤接口
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
		return (List<T>) Arrays.asList(list.stream().filter(predicate).toArray());
	}
	/**
	 * 遍历List
	 * 请勿做删除操作,需执行删除请调用list原生foreach((T t)->{list.remove(t);})
	 * @param list
	 * @param operator
	 * @return
	 */
	public static<T> List<T> foreach(List<T> list,ListOperator<T> operator){
		if (list==null) {
			return list;
		}
		for (int i = 0; i < list.size(); i++) {
			operator.operator(list.get(i), i);
		}
		return list;
	}

	/**
	 * 遍历Set
	 * @param list
	 * @param operator
	 * @return
	 */
	public static<T> Set<T> foreach(Set<T> set,BeanOperator<T> operator){
		if (set==null) {
			return set;
		}
		for (T k : set) {
			operator.operator(k);
		}
		return set;
	}
	/**
	 * 遍历Map
	 * @param list
	 * @param operator
	 * @return
	 */
	public static<K,V> Map<K,V> foreach(Map<K,V> map,MapOperator<K,V> operator){
		if (map==null) {
			return map;
		}
		foreach(map.keySet(), (k) -> {
			operator.operator(k,map.get(k));
		});
		return map;
	}
}
