package com.packtpub.configuration_services;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigServer
public class ConfigurationServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigurationServicesApplication.class, args);
	}

//
//	@Bean
//	public OtlpGrpcSpanExporter otlpHttpSpanExporter(@Value("${tracing.url}") String url) {
//		return OtlpGrpcSpanExporter.builder().setEndpoint(url).build();
//	}
}
