package com.llc.springcloud.onlinecharts.vrtest.util;

import com.google.gson.Gson;
import com.llc.springcloud.util.StringUtil;
import net.sf.json.xml.XMLSerializer;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class HttpUrlConnectionUtil {

	
	public static <T>T post(String url, String params, Map<String, String> headers, Class<T> clazz, boolean fromXml){
		try{
			String data = post(url, params, headers);
			return new Gson().fromJson(fromXml ? new XMLSerializer().read(data).toString() : data, clazz);
		}catch(Exception e){
			return null;
		}
	}
	
	
	public static <T>T post(String url, String params, Class<T> clazz, boolean fromXml){
		return post(url, params, null, clazz, fromXml);
	}
	
	
	public static String post(String url, String params) {
		return post(url, params, null);
	}
	
	
	public static String post(String url, String params, Map<String, String> headers) {
		return request(url, params, headers, "POST");
	}
	
	
	public static String postJson(String url, String params, Map<String, String> headers) {
		return request(url, params, headers, "POST", true);
	}
	
	
	public static String postJson(String url, String params) {
		return postJson(url, params, null);
	}
	
	
	public static <T>T get(String url, String params, Map<String, String> headers, Class<T> clazz, boolean fromXml){
		try{
			String data = get(url, params, headers);
			return new Gson().fromJson(fromXml ? new XMLSerializer().read(data).toString() : data, clazz);
		}catch(Exception e){
			return null;
		}
	}
	
	
	public static <T>T get(String url, String params, Class<T> clazz, boolean fromXml){
		return get(url, params, null, clazz, fromXml);
	}
	
	
	public static <T>T get(String url, String params, Class<T> clazz){
		return get(url, params, clazz, false);
	}
	
	
	public static String get(String url) {
		return get(url, null);
	}
	
	
	public static String get(String url, String params) {
		return get(url, params, new HashMap<String, String>());
	}
	
	
	public static String get(String url, String params, Map<String, String> headers) {
		return request(url, params, headers, "GET");
	}
	
	
	public static String request(String url, String params, Map<String, String> headers, String requestMethod) {
		return request(url, params, headers, requestMethod, false);
	}
	
	
	public static String request(String url, String params, Map<String, String> headers, String requestMethod, boolean isPostJson) {
		DataOutputStream dos = null;
		HttpURLConnection httpConn = null;
		BufferedReader responseReader = null;
		StringBuffer sb = new StringBuffer();
		int timeout = 30000;
		try {
			httpConn = (HttpURLConnection) new URL(url).openConnection();
			httpConn.setDoOutput(true);
			httpConn.setDoInput(true);
			httpConn.setUseCaches(false);
			httpConn.setRequestMethod(requestMethod);
//			httpConn.setRequestProperty("Accept-Language", Locale.getDefault().toString());
			httpConn.setRequestProperty("Charset", "UTF-8");
			httpConn.setRequestProperty("Accept-Charset", "UTF-8");
			httpConn.setRequestProperty("Content-Type", isPostJson ? "application/json;charset=UTF-8" : "application/x-www-form-urlencoded;charset=UTF-8");
			httpConn.setReadTimeout(timeout);
			httpConn.setConnectTimeout(timeout);
			if(headers != null && !headers.isEmpty()){
				for(String header : headers.keySet()){
					if(StringUtil.isNotBlank(headers.get(header))){
						httpConn.setRequestProperty(header, headers.get(header));
					}
				}
			}
			if(params != null){
				dos = new DataOutputStream(httpConn.getOutputStream());
				dos.write(params.getBytes("UTF-8"));
				dos.flush();
			}
			if (HttpURLConnection.HTTP_OK == httpConn.getResponseCode()) {
				String readLine = null;
				responseReader = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), "UTF-8"));
				while ((readLine = responseReader.readLine()) != null) {
					sb.append(readLine).append("\n");
				}
			}
		} catch (Exception e) {
			// nothing to do
		} finally {
			try {
				if (dos != null) {
					dos.close();
				}
				if (responseReader != null) {
					responseReader.close();
				}
				if(httpConn != null){
					httpConn.disconnect();
				}
			} catch (Exception e) {
				// nothing to do
			}
		}
		return sb.toString();
	}
	

}
