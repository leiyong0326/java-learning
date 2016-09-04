package com.cykj.base.common.em.ext;

import com.cykj.base.common.em.EnumSuper;

/**
 * 支付方式枚举
 * @author LeiYong
 *
 */
public enum PayEnum implements EnumSuper{
	WeChat("0","微信支付"),Alipay("1","支付宝支付");
	private String value;
	private String discription;
	
	PayEnum(String value,String discription) {
		this.discription = discription;
		this.value = value;
	}
	
	@Override
	public String getValue() {
		return value;
	}

	@Override
	public String getDiscription() {
		return discription;
	}
}
