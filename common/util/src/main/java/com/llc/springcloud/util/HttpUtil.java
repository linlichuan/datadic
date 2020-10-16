package com.llc.springcloud.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

public class HttpUtil {

	public static String post(String url, Map<String, Object> params) {
		HttpClient httpClient = HttpClientBuilder.create().build();
		String result = null;
		HttpPost post = new HttpPost(url);
		JSONObject json = new JSONObject(params);
		HttpEntity entity = new StringEntity(json.toString(), ContentType.APPLICATION_JSON);
		post.setEntity(entity);
		try {
			HttpResponse response = httpClient.execute(post);
			result = readStringResult(response.getEntity().getContent());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String get(String uri) {
		HttpClient httpClient = HttpClientBuilder.create().build();
		String result = null;
		HttpGet get = new HttpGet(uri);
		try {
			HttpResponse response = httpClient.execute(get);
			HttpEntity entity = response.getEntity();
			result = EntityUtils.toString(entity, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	private static String readStringResult(InputStream in) {
		StringBuilder result = new StringBuilder();
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				result.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result.toString();
	}
}
