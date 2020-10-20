package com.llc.springcloud.web.config;

import com.llc.springcloud.web.exception.NoWriterException;
import com.llc.springcloud.web.exception.TemplateProcessException;
import com.llc.springcloud.web.response.JsonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
public class MyExceptionHandler {

	@ResponseStatus(HttpStatus.ACCEPTED)
	@ExceptionHandler(NoWriterException.class)
	public JsonResponse<Object> noWriterException(NoWriterException e) {
		return JsonResponse.failure(e.getMessage());
	}
	
	@ResponseStatus(HttpStatus.ACCEPTED)
	@ExceptionHandler(TemplateProcessException.class)
	public JsonResponse<Object> processException(TemplateProcessException e) {
		return JsonResponse.failure(e.getMessage());
	}
	
}
