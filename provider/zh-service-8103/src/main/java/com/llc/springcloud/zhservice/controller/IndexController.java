package com.llc.springcloud.zhservice.controller;

import com.llc.springcloud.util.TimeUtil;
import com.llc.springcloud.web.response.JsonResponse;
import com.llc.springcloud.zhservice.entity.Story;
import com.llc.springcloud.zhservice.entity.StoryDetail;
import com.llc.springcloud.zhservice.service.IndexService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Api("文章接口")
@RestController
@RequestMapping("news")
public class IndexController {
	
	@Resource
	IndexService indexService;
	
	@ApiOperation(value = "获取最新文章", httpMethod = "GET")
	@RequestMapping(value = "latest", method = RequestMethod.GET)
	public JsonResponse<List<Story>> getLatestList() {
		return JsonResponse.ok(indexService.getLatest());
	}
	
	@ApiOperation(value = "获取详情", httpMethod = "GET")
	@RequestMapping("get/{id}")
	public JsonResponse<StoryDetail> getStoryDetail(@PathVariable("id") Integer id) {
		return JsonResponse.ok(indexService.getStoryDetail(id));
	}
	
	@ApiOperation(value = "获取之前的文章", httpMethod = "GET")
	@RequestMapping("before/{date}")
	public JsonResponse<List<Story>> getBeforeList(@PathVariable("date") String date) {
		return JsonResponse.ok(indexService.getBefore(date));
	}
	
	@ApiOperation(value = "testDate", httpMethod = "GET")
	@RequestMapping("testDate")
	public JsonResponse<String> testDate() throws NoSuchMethodException {
		
		System.out.println(IndexController.class.getMethod("getLatestList").getDeclaredAnnotation(ApiOperation.class).value());
		
		return JsonResponse.ok(String.format("这是ok的 %s", TimeUtil.getCurrentDate().toString()));
	}
}
