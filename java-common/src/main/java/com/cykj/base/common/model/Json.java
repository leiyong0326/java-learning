package com.cykj.base.common.model;

import java.io.Serializable;

import com.alibaba.fastjson.JSON;
/**
 * Json通信对象
 * @author LeiYong
 *
 */
@SuppressWarnings("serial")
public class Json implements Serializable {
	private boolean success = false;
	private String msg;
	private Object obj;
	private Integer total;
	public Json(boolean success, String msg, Object obj) {
		this(msg, obj);
		this.success = success;
	}
	public Json(String msg, Object obj) {
		this();
		this.msg = msg;
	}
	
	public Json() {
		super();
	}
	/**
	 * 转为JsonString
	 * @return
	 */
	public String toJsonString(){
		return JSON.toJSONString(this);
	}
	public Json setAll(boolean success, String msg, Object obj){
		this.success = success;
		this.msg = msg;
		this.obj = obj;
		return this;
	}
	public Json setAll(String msg, Object obj){
		this.msg = msg;
		this.obj = obj;
		return this;
	}
	public boolean isSuccess() {
		return success;
	}
	public String getMsg() {
		return msg;
	}
	public Object getObj() {
		return obj;
	}
	public Json setSuccess(boolean success) {
		this.success = success;
		return this;
	}
	public Json setMsg(String msg) {
		this.msg = msg;
		return this;
	}
	public Json setObj(Object obj) {
		this.obj = obj;
		return this;
	}
	public Integer getTotal() {
		return total;
	}
	public Json setTotal(int total) {
		this.total = total;
		return this;
	}
}
