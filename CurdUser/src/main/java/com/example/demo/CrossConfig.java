package com.example.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class CrossConfig extends WebMvcConfigurationSupport {

	@Override
	protected void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
		.allowedOrigins("http://127.0.0.1:5500")
		.allowedMethods("GET","POST","PUT","DELETE")
		.allowedHeaders("*").allowCredentials(true).maxAge(3600);
	}
	
}
