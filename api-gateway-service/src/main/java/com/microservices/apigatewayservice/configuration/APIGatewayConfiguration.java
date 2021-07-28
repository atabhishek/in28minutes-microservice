package com.microservices.apigatewayservice.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class APIGatewayConfiguration {

	@Bean
	public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {

		return builder.routes()
				.route(p -> p.path("/get")
						.filters(f -> f.addRequestHeader("MyHeader", "MyURI").addRequestParameter("Param", "Myvalue"))
						.uri("http://httpbin.org:80"))
				.route(p -> p.path("/currency-exchange/**").uri("lb://CURRENCY-EXCHANGE-SERVICE/**"))
				.route(p -> p.path("/currency-conversion/**").uri("lb://CURRENCY-CONVERSION-SERVICE/**"))
				.route(p -> p.path("/currency-conversion-feign/**")
						.filters(f -> f.rewritePath("/currency-conversion-feign/(?<segment>.*)","/currency-conversion/feign/${segment}"))
						.uri("lb://CURRENCY-CONVERSION-SERVICE/**"))
				.build();
	}

}
