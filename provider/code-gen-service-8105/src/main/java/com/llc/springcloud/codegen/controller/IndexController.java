package com.llc.springcloud.codegen.controller;

import com.alibaba.fastjson.JSONObject;
import com.llc.springcloud.codegen.tpl.TemplateUtil;
import com.llc.springcloud.codegen.tpl.tpldto.GenConfigDto;
import com.llc.springcloud.codegen.xml.MybatisGenConfigParser;
import com.llc.springcloud.web.response.JsonResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;

@RestController
public class IndexController {
	
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public JsonResponse<JSONObject> index() {
		return JsonResponse.ok(MybatisGenConfigParser.getInstance().parse(null));
	}
	
	@RequestMapping(value = "freemarker", method = RequestMethod.GET)
	public void getFreemarker(HttpServletResponse response) {
		
		GenConfigDto genConfigDto = new GenConfigDto();
		
		try {
			response.setContentType("text/plain");
			TemplateUtil.getInstance()
					.out(new OutputStreamWriter(response.getOutputStream()))
					.render(genConfigDto, "gen-config.ftl");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
