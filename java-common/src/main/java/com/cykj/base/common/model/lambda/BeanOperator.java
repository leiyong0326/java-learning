package com.cykj.base.common.model.lambda;

@FunctionalInterface
public interface BeanOperator<T> {
	/**
	 * 适用于普通对象
	 * @param t
	 */
	public void operator(T t);
}
