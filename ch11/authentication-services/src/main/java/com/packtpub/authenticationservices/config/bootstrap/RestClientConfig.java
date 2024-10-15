package com.packtpub.authenticationservices.config.bootstrap;

import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.context.Context;
import io.opentelemetry.context.Scope;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RestClient;
import java.io.IOException;
import java.util.List;

@Configuration
public class RestClientConfig {

    private final Tracer tracer;

    public RestClientConfig(Tracer tracer) {
        this.tracer = tracer;
    }

    @LoadBalanced
    @Bean
    public RestClient.Builder restClient() {
        return RestClient.builder().requestInterceptor(traceInterceptor());
    }

    private ClientHttpRequestInterceptor traceInterceptor() {
        return new ClientHttpRequestInterceptor() {
            @Override
            public ClientHttpResponse intercept(HttpRequest request, byte[] body,
                                                ClientHttpRequestExecution execution) throws IOException {

                // Get the current trace context and inject it into the HTTP headers
                Context context = Context.current();
                Span currentSpan = Span.current();
                try (Scope scope = context.makeCurrent()) {
                    // Inject tracing headers
                    request.getHeaders().add("traceparent", currentSpan.getSpanContext().getTraceId());
                    request.getHeaders().add("tracestate", currentSpan.getSpanContext().getTraceState().toString());

                    // Proceed with the request
                    return execution.execute(request, body);
                }
            }
        };
    }
}
