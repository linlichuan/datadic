package com.llc.springcloud.codegen.controller;

import com.llc.springcloud.codegen.xml.MybatisGenConfigParser;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class IndexController {
	
	@Resource
	private MybatisGenConfigParser mybatisGenConfigParser;
	
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public void index() {
		mybatisGenConfigParser.parse("pc_GeneratorConfig.xml");
	}
	
}
