package com.llc.springcloud.onlinecharts.vrtest.controller;

import com.llc.springcloud.onlinecharts.vrtest.pojo.VrBaseDataPojo;
import com.llc.springcloud.onlinecharts.vrtest.pojo.VrDriverRidersPojo;
import com.llc.springcloud.onlinecharts.vrtest.pojo.VrUserDataPojo;
import com.llc.springcloud.onlinecharts.vrtest.util.VrApiConfig;
import com.llc.springcloud.onlinecharts.vrtest.util.VrUserUtil;
import com.llc.springcloud.onlinecharts.vrtest.util.VrUtil;
import com.llc.springcloud.util.TimeUtil;
import com.llc.springcloud.web.response.JsonResponse;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping(value = "test")
@RestController
@Api(tags = {" 接口"})
public class TestApiController {
	
	@RequestMapping(value = "getJson/{uid}", method = {RequestMethod.GET})
	public JsonResponse<VrDriverRidersPojo> getJson(@PathVariable String uid) {
		Map<String, String> map = new HashMap<>();
		map.put("action", "driving_raiders");
		map.put("uid", uid);
		VrDriverRidersPojo result = VrUtil.get(VrApiConfig.USER, map, VrDriverRidersPojo.class);
		result.getD().getData().flattenJSONObject();
		return JsonResponse.ok(result);
	}
	
	@RequestMapping(value = "getJsonByPhone/{phone}", method = {RequestMethod.GET})
	public JsonResponse<VrUserDataPojo> getJsonByPhone(@PathVariable String phone) {
		return JsonResponse.ok(VrUserUtil.getUserByPhone(phone));
	}
	
	@RequestMapping(value = "getJsonByIdCard/{idCard}", method = {RequestMethod.GET})
	public JsonResponse<VrUserDataPojo> getJsonByIdCard(@PathVariable String idCard) {
		return JsonResponse.ok(VrUserUtil.getUserByIdCard(idCard));
	}
	
	@RequestMapping(value = "getList", method = RequestMethod.GET)
	public JsonResponse<VrBaseDataPojo<List<VrUserDataPojo>>> getList(@RequestParam Long startTime, @RequestParam Long endTime, @RequestParam int page) {
		return JsonResponse.ok(VrUserUtil.userList(TimeUtil.getDate(startTime), TimeUtil.getDate(endTime), page));
	}
	
	@RequestMapping(value = "getJsonRaw/{uid}", method = {RequestMethod.GET})
	public JsonResponse<VrDriverRidersPojo> getJsonRaw(@PathVariable String uid) {
		Map<String, String> map = new HashMap<>();
		map.put("action", "driving_raiders");
		map.put("uid", uid);
		VrDriverRidersPojo result = VrUtil.get(VrApiConfig.USER, map, VrDriverRidersPojo.class);
		return JsonResponse.ok(result);
	}
	
}
