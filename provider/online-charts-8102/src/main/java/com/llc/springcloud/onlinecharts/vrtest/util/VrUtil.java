package com.llc.springcloud.onlinecharts.vrtest.util;

import com.llc.springcloud.onlinecharts.vrtest.pojo.VrAbstractPojo;
import com.llc.springcloud.onlinecharts.vrtest.pojo.VrBaseDataPojo;
import com.llc.springcloud.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class VrUtil {
	
	
	private static final Logger log = LoggerFactory.getLogger(VrUtil.class);
	
	
	public static <X, T extends VrAbstractPojo<X>> VrBaseDataPojo<X> getData(VrApiConfig api, Map<String, String> params, Class<T> clazz) {
		T result = get(api, params, clazz);
		if(result != null && result.getD() != null) {
			return result.getD();
		}
		return null;
	}
	
	
	public static <X, T extends VrAbstractPojo<X>> T get(VrApiConfig api, Map<String, String> params, Class<T> clazz) {
		String url = VrConfig.getUrl(api);
		String requestParams = getRequestParams(params);
		T result = HttpUrlConnectionUtil.get(url, requestParams, clazz);
		if (result != null && !result.isSuccess()) {
			String msg = "超凡VR接口调用失败： ---->>> URL : " + url + "\t 请求参数：" + requestParams;
			if (result.getD() != null) {
				msg = msg  + "\t 返回msg：" + result.getD().getMsg();
			}
			log.error(msg);
		}
		return result;
	}
	
	
//	public static <X, T extends VrAbstractPojo<X>> VrBaseDataPojo<X> getData(VrApiConfig api, Map<String, String> params, Class<T> clazz) {
//		VrAbstractPojo<X> result = HttpUrlConnectionUtil.get(VrConfig.getUrl(api), getRequestParams(params), clazz);
//		if(result != null && result.getD() != null) {
//			return result.getD();
//		}
//		return null;
//	}
	

	/**
	 * 构造请求参数
	 * @param params
	 * @return
	 */
	private static String getRequestParams(Map<String, String> params) {
		Map<String, String> map = new HashMap<String, String>();
		if (params != null && !params.isEmpty()) {
			map.putAll(params);
		}
		map.put("appid", VrConfig.APP_ID);
		map.put("secret", VrConfig.SECRET);
		map.remove("token");
		List<String> keys = new ArrayList<String>(map.keySet());
		Collections.sort(keys);
		StringBuffer all = new StringBuffer();
		StringBuffer requestParams = new StringBuffer();
		String value = null;
		boolean isAppendAll = false;
		boolean isAppendRequestParams = false;
		for (String key : keys) {
			if(StringUtil.isBlank(key) || StringUtil.isBlank(value = map.get(key))) {
				continue;
			}
			value = value.trim(); // 去除前后空格，不然会和解密方加密出来不一致
			if(isAppendAll) {
				all.append("&").append(key).append("=").append(value);
			} else {
				isAppendAll = true;
				all.append(key).append("=").append(value);
			}
			if (!"secret".equals(key)) {
				if (isAppendRequestParams) {
					requestParams.append("&").append(key).append("=").append(value);
				} else {
					isAppendRequestParams = true;
					requestParams.append(key).append("=").append(value);
				}
			}
		}
		return requestParams.append("&token=").append(MD5.encode(all.toString())).toString();
	}
	
	public static void main(String[] args) {
		Boolean is = null;
		Object obj = is;
		System.out.println(Boolean.FALSE.equals(obj));
	}
	
}
