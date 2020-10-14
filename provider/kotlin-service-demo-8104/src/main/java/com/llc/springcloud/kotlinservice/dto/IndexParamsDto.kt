package com.llc.springcloud.kotlinservice.dto

import org.slf4j.Logger
import org.slf4j.LoggerFactory

class IndexParamsDto : BaseParamsDto() {

	private val log: Logger = LoggerFactory.getLogger(IndexParamsDto::class.java)
	lateinit var userName: String
	lateinit var password: String
}