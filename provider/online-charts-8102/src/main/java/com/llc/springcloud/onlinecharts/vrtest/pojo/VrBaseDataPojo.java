package com.llc.springcloud.onlinecharts.vrtest.pojo;

import com.llc.springcloud.onlinecharts.vrtest.util.VrPageInfoPojo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * VR接口响应数据，d的公用字段
 * @param <T> data的类型
 */
@SuppressWarnings("serial")
public class VrBaseDataPojo<T> implements Serializable {
	
	private String msg; // 提示信息, 公共字段
	private VrPageInfoPojo page_info; // 页码信息, 公共字段
	private T data; // 业务数据, 公共字段
	
	private BigDecimal account; // 剩余学时（分钟）   account接口有值
	private BigDecimal have_account; // 已用学时（分钟）    account接口有值
	
	private Integer train_tol; // 累计练车次数    train_vr /  train 接口有值
	private String train_time; // 累计练车时间   train_vr /  train 接口有值
	
	private Integer record_tol; // 累计预约次数    train 接口有值
	
	private Integer code; // 派发VIP卡接口返回操作状态码
	
	public VrBaseDataPojo() {
		super();
	}
	
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public VrPageInfoPojo getPage_info() {
		return page_info;
	}
	public void setPage_info(VrPageInfoPojo page_info) {
		this.page_info = page_info;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public BigDecimal getAccount() {
		return account;
	}
	public void setAccount(BigDecimal account) {
		this.account = account;
	}
	public BigDecimal getHave_account() {
		return have_account;
	}
	public void setHave_account(BigDecimal have_account) {
		this.have_account = have_account;
	}
	public Integer getTrain_tol() {
		return train_tol;
	}
	public void setTrain_tol(Integer train_tol) {
		this.train_tol = train_tol;
	}
	public String getTrain_time() {
		return train_time;
	}
	public void setTrain_time(String train_time) {
		this.train_time = train_time;
	}
	public Integer getRecord_tol() {
		return record_tol;
	}
	public void setRecord_tol(Integer record_tol) {
		this.record_tol = record_tol;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	
	
}
