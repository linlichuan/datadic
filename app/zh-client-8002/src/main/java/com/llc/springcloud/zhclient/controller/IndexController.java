package com.llc.springcloud.zhclient.controller;

import com.llc.springcloud.bean.zhapi.entity.Story;
import com.llc.springcloud.bean.zhapi.entity.StoryDetail;
import com.llc.springcloud.web.response.JsonResponse;
import com.llc.springcloud.zhclient.service.IZhService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "zhihu接口")
@RestController
@RequestMapping("news")
public class IndexController {
	
	@Autowired
	private IZhService iZhService;
	
	@ApiOperation(value = "获取最新文章", httpMethod = "GET")
	@RequestMapping(value = "latest", method = RequestMethod.GET)
	JsonResponse<List<Story>> getLatestList() {
		return iZhService.getLatestList();
	}
	
	@ApiOperation(value = "获取详情", httpMethod = "GET")
	@RequestMapping(value = "get/{id}", method = RequestMethod.GET)
	JsonResponse<StoryDetail> getStoryDetail(@PathVariable("id") Integer id) {
		return iZhService.getStoryDetail(id);
	}
	
	@ApiOperation(value = "获取之前的文章", httpMethod = "GET")
	@RequestMapping(value = "before/{date}", method = RequestMethod.GET)
	JsonResponse<List<Story>> getBeforeList(@PathVariable("date") String date) {
		return iZhService.getBeforeList(date);
	}
	
	@ApiOperation(value = "testDate", httpMethod = "GET")
	@RequestMapping(value = "testDate", method = RequestMethod.GET)
	JsonResponse<String> testDate() throws NoSuchMethodException {
		return iZhService.testDate();
	}
}
