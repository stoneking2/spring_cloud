package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cloud.netflix.zuul.filters.Route;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Component
@Primary
@EnableSwagger2
public class DocumentationConfig implements SwaggerResourcesProvider {
	private final RouteLocator routeLocator;

	public DocumentationConfig(RouteLocator routeLocator) {
		this.routeLocator = routeLocator;
	}

	/**
	 * 遍历eureka路由方式自动添加所有微服务 API 文档，SwaggerResourcesProvider 是资源提供者，
	 * 我们重写他，把各个微服务的API文档资源路径返回，注释部分为手动添加的方式
	 */
	@Override
	public List<SwaggerResource> get() {
		List<SwaggerResource> resources = new ArrayList<>();
		List<Route> routes = routeLocator.getRoutes();
		routes.forEach(route -> {
			resources.add(swaggerResource(route.getId(), route.getFullPath().replace("**", "v2/api-docs"), "1.0"));
		});
		return resources;
	}

//    @Override
//        public List<SwaggerResource> get() {
//            List resources = new ArrayList<>();
//            resources.add(swaggerResource("外接设备系统", "/management-device/v2/api-docs", "1.0"));
//            resources.add(swaggerResource("设备管理系统", "/management-equip/v2/api-docs", "1.0"));
//            return resources;
//        }

	private SwaggerResource swaggerResource(String name, String location, String version) {
		SwaggerResource swaggerResource = new SwaggerResource();
		swaggerResource.setName(name);
		swaggerResource.setLocation(location);
		swaggerResource.setSwaggerVersion(version);
		return swaggerResource;
	}
}