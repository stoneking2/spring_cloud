package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;


@EnableHystrixDashboard
@EnableHystrix
@EnableZuulProxy
@EnableCircuitBreaker
@SpringBootApplication
public class EurakaZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurakaZuulApplication.class, args);
	}

}
