package com.llc.springcloud.onlinecharts.vrtest.pojo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@SuppressWarnings("serial")
public class DriverSubjectRidersPojo implements Serializable {
	
	private static final Logger log = LoggerFactory.getLogger(DriverSubjectRidersPojo.class);

	private List<DriverRidersDetailPojo> subList1;
	private List<DriverRidersDetailPojo> subList2;
	private List<DriverRidersDetailPojo> subList3;
	private List<DriverRidersDetailPojo> subList4;
	
	private JSONObject sub1;
	private JSONObject sub2;
	private JSONObject sub3;
	private JSONObject sub4;
	
	
	public DriverSubjectRidersPojo() {
		super();
	}
	
	public void setSubList1(List<DriverRidersDetailPojo> subList1) {
		this.subList1 = subList1;
	}
	public List<DriverRidersDetailPojo> getSubList1() {
		return this.subList1;
	}
	public void setSubList2(List<DriverRidersDetailPojo> subList2) {
		this.subList2 = subList2;
	}
	public List<DriverRidersDetailPojo> getSubList2() {
		return this.subList2;
	}
	public void setSubList3(List<DriverRidersDetailPojo> subList3) {
		this.subList3 = subList3;
	}
	public List<DriverRidersDetailPojo> getSubList3() {
		return this.subList3;
	}
	public void setSubList4(List<DriverRidersDetailPojo> subList4) {
		this.subList4 = subList4;
	}
	public List<DriverRidersDetailPojo> getSubList4() {
		return this.subList4;
	}
	public void setSub1(JSONObject sub1) {
		this.sub1 = sub1;
	}
	public JSONObject getSub1() {
		return this.sub1;
	}
	public void setSub2(JSONObject sub2) {
		this.sub2 = sub2;
	}
	public JSONObject getSub2() {
		return this.sub2;
	}
	public void setSub3(JSONObject sub3) {
		this.sub3 = sub3;
	}
	public JSONObject getSub3() {
		return this.sub3;
	}
	public void setSub4(JSONObject sub4) {
		this.sub4 = sub4;
	}
	public JSONObject getSub4() {
		return this.sub4;
	}

	public void flattenJSONObject() {
		this.subList1 = flatten(sub1);
		this.subList2 = flatten(sub2);
		this.subList3 = flatten(sub3);
		this.subList4 = flatten(sub4);
		sub1 = null;
		sub2 = null;
		sub3 = null;
		sub4 = null;
	}
	
	public List<DriverRidersDetailPojo> flatten(JSONObject json) {
		if (json == null || json.isEmpty()) {
			log.info("json is null or empty");
			return Collections.emptyList();
		}
		List<DriverRidersDetailPojo> result = new ArrayList<>();
		Set<String> keySet = json.keySet();
		JSONObject obj;
		JSONArray arrList = null;
		for (String k : keySet) {
			obj = getJsonObject(json, k);
			if (obj != null) {
				arrList = getJsonArray(obj, k);
				result.add(new DriverRidersDetailPojo(k, obj.getInteger("standard"), obj.getInteger("tol"), arrList));
			} else {
				result.add(new DriverRidersDetailPojo(k, 0, 0, arrList));
			}
		}
		return result;
	}
	
	private JSONObject getJsonObject(JSONObject json, String k) {
		JSONObject result = null;
		try {
			result = json.getJSONObject(k);
		} catch (Exception e) {
			log.error("get json object error. key => {}", k);
		}
		return result;
	}
	
	private JSONArray getJsonArray(JSONObject json, String k) {
		JSONArray result = null;
		try {
			result = json.getJSONArray("list");
		} catch (Exception e) {
			log.error("get json array error. key => {}", k);
		}
		return result;
	}
	
	public static void main(String[] args) {
		DriverSubjectRidersPojo obj = new DriverSubjectRidersPojo();
		obj.setSub1(JSONObject.parseObject("{\"d_exam\":{\"list\":[{\"str\":\"\",\"standard\":1,\"name\":\"模拟考试\"},{\"name\":\"AI模拟卷\",\"tol\":100,\"num\":0,\"rate\":0,\"standard\":1}],\"standard\":1,\"tol\":2},\"d_ksgl\":{\"list\":[{\"read_num\":0,\"watch_time\":0,\"watch_time_str\":\"00:00:00\",\"name\":\"考试流程全景\",\"standard\":1}],\"standard\":1,\"tol\":1},\"d_kqcc\":{\"list\":[{\"name\":\"考前速记题\",\"tol\":297,\"num\":0,\"rate\":0,\"standard\":1},{\"name\":\"标线专项题\",\"tol\":21,\"num\":0,\"rate\":0,\"standard\":1},{\"name\":\"大数据易错题\",\"tol\":49,\"num\":0,\"rate\":0,\"standard\":1},{\"name\":\"大数据易混淆题\",\"tol\":74,\"num\":0,\"rate\":0,\"standard\":1},{\"name\":\"大数据争议题\",\"tol\":10,\"num\":0,\"rate\":0,\"standard\":1}],\"standard\":1,\"tol\":5},\"d_jcdt\":{\"list\":[{\"name\":\"顺序练习\",\"tol\":1750,\"num\":0,\"rate\":0,\"standard\":1},{\"name\":\"精选500题\",\"tol\":500,\"num\":0,\"rate\":0,\"standard\":1}],\"standard\":1,\"tol\":2}}"));
		obj.setSub2(JSONObject.parseObject("{\"d_video\":[],\"d_ksgl\":{\"list\":[{\"name\":\"考试流程全景\",\"read_num\":0,\"watch_time\":0,\"watch_time_str\":\"00:00:00\",\"standard\":1},{\"name\":\"考试平面\",\"read_num\":0,\"watch_time\":0,\"watch_time_str\":\"00:00:00\",\"standard\":1},{\"name\":\"考试规则\",\"read_num\":0,\"watch_time\":0,\"watch_time_str\":\"00:00:00\",\"standard\":1},{\"name\":\"考试要点\",\"read_num\":0,\"watch_time\":0,\"watch_time_str\":\"00:00:00\",\"standard\":1}],\"standard\":1,\"tol\":4},\"d_qjjd\":{\"list\":\"\",\"standard\":1,\"tol\":0}}"));
		obj.setSub3(JSONObject.parseObject("{\"d_dgjx\":{\"list\":[{\"name\":\"灯光教学视频\",\"read_num\":0,\"watch_time\":0,\"watch_time_str\":\"00:00:00\",\"standard\":1},{\"name\":\"灯光模拟考试\",\"read_num\":0,\"watch_time\":0,\"watch_time_str\":\"00:00:00\",\"standard\":1}],\"standard\":1,\"tol\":2},\"d_ksgl\":{\"list\":[{\"name\":\"考试流程全景\",\"read_num\":0,\"watch_time\":0,\"watch_time_str\":\"00:00:00\",\"standard\":1},{\"name\":\"考试平面\",\"read_num\":0,\"watch_time\":0,\"watch_time_str\":\"00:00:00\",\"standard\":1},{\"name\":\"考试规则\",\"read_num\":0,\"watch_time\":0,\"watch_time_str\":\"00:00:00\",\"standard\":1},{\"name\":\"考试要点\",\"read_num\":0,\"watch_time\":0,\"watch_time_str\":\"00:00:00\",\"standard\":1}],\"standard\":1,\"tol\":4},\"d_qjjd\":{\"list\":\"\",\"standard\":1,\"tol\":0}}"));
		obj.setSub4(JSONObject.parseObject("{\"d_kqcc\":{\"list\":[{\"name\":\"考前速记题\",\"tol\":292,\"num\":0,\"rate\":0,\"standard\":1},{\"name\":\"标线专项题\",\"tol\":22,\"num\":0,\"rate\":0,\"standard\":1},{\"name\":\"大数据易错题\",\"tol\":50,\"num\":0,\"rate\":0,\"standard\":1},{\"name\":\"大数据易混淆题\",\"tol\":57,\"num\":0,\"rate\":0,\"standard\":1},{\"name\":\"大数据争议题\",\"tol\":13,\"num\":0,\"rate\":0,\"standard\":1}],\"standard\":1,\"tol\":5},\"d_jcdt\":{\"list\":[{\"name\":\"顺序练习\",\"tol\":1515,\"num\":1,\"rate\":0,\"standard\":1},{\"name\":\"精选500题\",\"tol\":500,\"num\":0,\"rate\":0,\"standard\":1}],\"standard\":1,\"tol\":2},\"d_exam\":{\"list\":[{\"str\":\"\",\"standard\":1,\"name\":\"模拟考试\"},{\"name\":\"AI模拟卷\",\"tol\":100,\"num\":0,\"rate\":0,\"standard\":1}],\"standard\":1,\"tol\":2},\"d_ksgl\":{\"list\":[{\"read_num\":0,\"watch_time\":0,\"watch_time_str\":\"00:00:00\",\"name\":\"考试流程全景\",\"standard\":1}],\"standard\":1,\"tol\":1}}"));
		obj.flattenJSONObject();
		System.out.println(JSONObject.toJSONString(obj));
	}
}
