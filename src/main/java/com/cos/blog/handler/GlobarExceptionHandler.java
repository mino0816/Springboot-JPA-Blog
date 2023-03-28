package com.cos.blog.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

//모든 Exception발생시 여기로옴
@ControllerAdvice
@RestController
public class GlobarExceptionHandler {
	
	//IllegalExceptio
	@ExceptionHandler(value = Exception.class)
	public String handleArgumentException(Exception e) {
		return"<h1>"+e.getMessage()+"</h1>";
	}
}
