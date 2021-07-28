package com.in28Minutes.microservices.limitmicroservice.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties("limits-service")
public class LimitConfiguration {
	
	private int minimum;
	private int maximum;

}
