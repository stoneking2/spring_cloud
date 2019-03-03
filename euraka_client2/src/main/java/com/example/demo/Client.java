package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RefreshScope
@Api("testcontroll")
@RestController
public class Client {

	@Value("${from:local}")
	private String from;
	@Value("${server.port:8080}")
	private String port;

	@ApiOperation(value = "问好世界", httpMethod = "GET", notes = "暂无")
	@RequestMapping("/hello")
	public String hello() {

		return "hello word>>>>>"+port;
	}
	
	@ApiOperation(value = "config_test", httpMethod = "GET", notes = "暂无")
	@RequestMapping("/from")
	public String from() {

		return from+">>>>>"+port;
	}
}
