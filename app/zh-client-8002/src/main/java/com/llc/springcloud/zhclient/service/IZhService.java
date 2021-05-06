package com.llc.springcloud.zhclient.service;

import com.llc.springcloud.bean.zhapi.entity.Story;
import com.llc.springcloud.bean.zhapi.entity.StoryDetail;
import com.llc.springcloud.web.response.JsonResponse;
import com.llc.springcloud.zhclient.config.FeignClientConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;


@FeignClient(name = "zh-service-8103")
public interface IZhService {
	@RequestMapping(value = "zh-api/news/latest", method = RequestMethod.GET)
	JsonResponse<List<Story>> getLatestList();
	
	@RequestMapping(value = "zh-api/news/get/{id}", method = RequestMethod.GET)
	JsonResponse<StoryDetail> getStoryDetail(@PathVariable("id") Integer id);
	
	@RequestMapping(value = "zh-api/news/before/{date}", method = RequestMethod.GET)
	JsonResponse<List<Story>> getBeforeList(@PathVariable("date") String date);
	
	@RequestMapping(value = "zh-api/news/testDate", method = RequestMethod.GET)
	JsonResponse<String> testDate() throws NoSuchMethodException;
}
