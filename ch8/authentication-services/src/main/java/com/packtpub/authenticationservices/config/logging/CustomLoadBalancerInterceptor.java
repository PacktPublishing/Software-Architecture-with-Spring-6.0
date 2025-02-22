package com.packtpub.authenticationservices.config.logging;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;


@Slf4j
@Component
public class CustomLoadBalancerInterceptor implements ClientHttpRequestInterceptor {

    private final LoadBalancerClient loadBalancerClient;

    public CustomLoadBalancerInterceptor(LoadBalancerClient loadBalancerClient) {
        this.loadBalancerClient = loadBalancerClient;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        ServiceInstance instance = loadBalancerClient.choose("USER-SERVICES");

        if (instance != null) {
            log.info("Calling service instance: host={}, port={}, path={}", instance.getHost(), instance.getPort(), request.getURI().getPath());
        }
        return execution.execute(request, body);
    }
}