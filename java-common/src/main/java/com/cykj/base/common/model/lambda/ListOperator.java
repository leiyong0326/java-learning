package com.cykj.base.common.model.lambda;

@FunctionalInterface
public interface ListOperator<T> {
	/**
	 * 适用于集合等有下标的操作
	 * @param t
	 * @param index
	 */
	public void operator(T t,int index);
}
