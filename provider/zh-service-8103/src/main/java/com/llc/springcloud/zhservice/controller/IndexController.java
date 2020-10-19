package com.llc.springcloud.zhservice.controller;

import com.llc.springcloud.util.TimeUtil;
import com.llc.springcloud.util.response.JsonResponse;
import com.llc.springcloud.zhservice.entity.Story;
import com.llc.springcloud.zhservice.entity.StoryDetail;
import com.llc.springcloud.zhservice.service.IndexService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("news")
public class IndexController {
	
	@Resource
	IndexService indexService;
	
	@RequestMapping(value = "latest", method = RequestMethod.GET)
	public JsonResponse<List<Story>> getLatestList() {
		return JsonResponse.ok(indexService.getLatest(TimeUtil.getCurrentDate()));
	}
	
	@RequestMapping("get/{id}")
	public JsonResponse<StoryDetail> getStoryDetail(@PathVariable("id") Integer id) {
		return JsonResponse.ok(indexService.getStoryDetail(id));
	}
	
	@RequestMapping("before/{date}")
	public JsonResponse<List<Story>> getBeforeList(@PathVariable("date") String date) {
		return JsonResponse.ok(indexService.getBefore(date));
	}
	
	@RequestMapping("get")
	public JsonResponse<String> get() {
		return JsonResponse.ok("success");
	}
}
