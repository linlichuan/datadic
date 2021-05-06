package com.llc.springcloud.onlinecharts.vrtest.pojo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class VrUserDataPojo implements Serializable {
	
	private String uid; // 学员id
	private String name; // 学员姓名
	private String phone; // 学员手机号码
	private String id_number; // 学员身份证
	private String province; // 省份
	private String city; // 城市
	private String reg_time; // 注册时间 yyyy-MM-dd HH:mm:ss
	private String is_ban; // 是否正常
	private String is_vr; // 是否VR用户
	private String vip; // VIP用户
	private String school; // 所属驾校
	private String coach; // 所属教练
	private String ex_name; // 考场
	private String model_name; // 车型
	private String car_type; // 学车类型
	
	
	public VrUserDataPojo() {
		super();
	}
	
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getId_number() {
		return id_number;
	}
	public void setId_number(String id_number) {
		this.id_number = id_number;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getReg_time() {
		return reg_time;
	}
	public void setReg_time(String reg_time) {
		this.reg_time = reg_time;
	}
	public String getIs_ban() {
		return is_ban;
	}
	public void setIs_ban(String is_ban) {
		this.is_ban = is_ban;
	}
	public String getIs_vr() {
		return is_vr;
	}
	public void setIs_vr(String is_vr) {
		this.is_vr = is_vr;
	}
	public String getVip() {
		return vip;
	}
	public void setVip(String vip) {
		this.vip = vip;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getCoach() {
		return coach;
	}
	public void setCoach(String coach) {
		this.coach = coach;
	}
	public String getEx_name() {
		return ex_name;
	}
	public void setEx_name(String ex_name) {
		this.ex_name = ex_name;
	}
	public String getModel_name() {
		return model_name;
	}
	public void setModel_name(String model_name) {
		this.model_name = model_name;
	}
	public String getCar_type() {
		return car_type;
	}
	public void setCar_type(String car_type) {
		this.car_type = car_type;
	}
	
	
}
