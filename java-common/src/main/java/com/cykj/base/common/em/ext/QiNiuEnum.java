package com.cykj.base.common.em.ext;

import com.cykj.base.common.em.EnumSuper;

/**
 * 支付方式枚举
 * @author LeiYong
 *
 */
public enum QiNiuEnum implements EnumSuper{
	Img("testimage","图片上传路径"),File("test","其它文件路径");
	private String value;
	private String discription;
	
	QiNiuEnum(String value,String discription) {
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
