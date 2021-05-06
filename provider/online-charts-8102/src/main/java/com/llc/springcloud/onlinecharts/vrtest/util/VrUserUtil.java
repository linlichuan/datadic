package com.llc.springcloud.onlinecharts.vrtest.util;



import com.llc.springcloud.onlinecharts.vrtest.pojo.VrAbstractPojo;
import com.llc.springcloud.onlinecharts.vrtest.pojo.VrBaseDataPojo;
import com.llc.springcloud.onlinecharts.vrtest.pojo.VrUserDataPojo;
import com.llc.springcloud.onlinecharts.vrtest.pojo.VrUserListPojo;
import com.llc.springcloud.onlinecharts.vrtest.pojo.VrUserOnePojo;
import com.llc.springcloud.util.TimeUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VrUserUtil {

	/**
	 * 通过VR系统的学员手机号获取单个学员
	 * @param phone vr系统的学员手机号
	 * @return
	 */
	public static VrUserDataPojo getUserByPhone(String phone) {
		return getUser(3, phone);
	}
	
	
	/**
	 * 通过VR系统的学员身份证获取单个学员
	 * @param idCard vr系统的学员身份证
	 * @return
	 */
	public static VrUserDataPojo getUserByIdCard(String idCard) {
		return getUser(4, idCard);
	}
	
	
	/**
	 * 获取学员列表
	 * @param startTime
	 * @param endTime
	 * @param page
	 * @return
	 */
	public static VrBaseDataPojo<List<VrUserDataPojo>> userList(Date startTime, Date endTime, int page) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("action", "list");
		params.put("page", String.valueOf(page));
		params.put("start_time", TimeUtil.format(startTime, "yyyyMMdd"));
		params.put("end_time", TimeUtil.format(endTime, "yyyyMMdd"));
		return get(params, VrUserListPojo.class);
	}
	
	
	/**
	 * 获取单个学员
	 * @param type 1：学员ID，2：学员姓名，3：学员手机，4：学员身份证号码
	 * @param keywords
	 * @return
	 */
	private static VrUserDataPojo getUser(int type, String keywords) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("action", "one");
		params.put("type", String.valueOf(type));
		params.put("keywords", keywords);
		VrBaseDataPojo<VrUserDataPojo> result = get(params, VrUserOnePojo.class);
		if(result != null ) {
			return result.getData();
		}
		return null;
	}
	
	
	private static <X, T extends VrAbstractPojo<X>> VrBaseDataPojo<X> get(Map<String, String> params, Class<T> clazz) {
		return VrUtil.getData(VrApiConfig.USER, params, clazz);
	}
	

}
