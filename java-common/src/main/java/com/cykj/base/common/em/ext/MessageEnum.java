package com.cykj.base.common.em.ext;

import com.cykj.base.common.em.EnumSuper;
import com.cykj.base.common.message.ext.MessagePhone;
import com.cykj.base.common.message.ext.MessageWeChat;

/**
 * 支付方式枚举
 * @author LeiYong
 *
 */
public enum MessageEnum implements EnumSuper{
	WeChat("0",MessageWeChat.class.getName()),SMS("1",MessagePhone.class.getName());
	private String value;
	private String discription;
	
	MessageEnum(String value,String discription) {
		this.value = value;
		this.discription = discription;
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
