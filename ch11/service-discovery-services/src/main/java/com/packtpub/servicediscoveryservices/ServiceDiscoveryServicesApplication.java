package com.packtpub.servicediscoveryservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Bean;

@EnableEurekaServer
@SpringBootApplication
public class ServiceDiscoveryServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceDiscoveryServicesApplication.class, args);
	}

//	@Bean
//	public OtlpGrpcSpanExporter otlpHttpSpanExporter(@Value("${tracing.url}") String url) {
//		return OtlpGrpcSpanExporter.builder().setEndpoint(url).build();
//	}
}
