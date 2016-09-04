package com.cykj.base.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface RedisCache {
	/**
	 * 缓存key,默认为#record?.pk
	 * 如"#obj?.orgPk+#obj?.loginName"
	 * 集合操作如:"#objs?.![column+operate]"
	 * 语法请参考SpringSpelTest.java
	 * @return
	 */
	String cacheKey() default "#record?.pk";
	/**
	 * -1表示永不过期
	 * @return
	 */
	long expireTime() default -1;
	/**
	 * 是否多笔操作,暂未实现
	 * @return
	 */
	boolean batch() default false;
	/**
	 * 类型
	 * @return
	 */
	String clazz();
}
