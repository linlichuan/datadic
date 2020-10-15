package com.llc.springcloud.codegen.controller;

import com.alibaba.fastjson.JSONObject;
import com.llc.springcloud.codegen.xml.MybatisGenConfigParser;
import com.llc.springcloud.util.response.JsonResponse;
import freemarker.template.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class IndexController {
	
	@Resource
	Configuration freemarkerCfg;
	
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public JsonResponse<JSONObject> index() {
		return JsonResponse.ok(MybatisGenConfigParser.getInstance().parse(null));
	}
	
	@RequestMapping(value = "freemarker", method = RequestMethod.GET)
	public JsonResponse<String> getFreemarker() {
		return JsonResponse.ok(freemarkerCfg.toString());
	}
}
