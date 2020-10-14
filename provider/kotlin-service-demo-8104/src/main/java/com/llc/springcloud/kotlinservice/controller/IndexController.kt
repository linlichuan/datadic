package com.llc.springcloud.kotlinservice.controller

import com.llc.springcloud.kotlinservice.dto.IndexParamsDto
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest

@RestController
class IndexController {

	@RequestMapping("index", method = [RequestMethod.GET])
	fun index(request: HttpServletRequest, dto: IndexParamsDto): String {
		return "parent params : ${dto.name}; params: ${dto.userName} ${dto.password}"
	}
}