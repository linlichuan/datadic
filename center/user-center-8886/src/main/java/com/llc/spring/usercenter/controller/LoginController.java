package com.llc.spring.usercenter.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login")
public class LoginController {

	@RequestMapping("index")
	public String login() {
		return "login";
	}

}
