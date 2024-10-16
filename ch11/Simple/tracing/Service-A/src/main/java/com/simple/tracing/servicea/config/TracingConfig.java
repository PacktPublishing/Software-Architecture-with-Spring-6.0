//package com.simple.tracing.servicea.config;
//
//import io.micrometer.observation.ObservationRegistry;
//import io.micrometer.tracing.Tracer;
//import io.micrometer.tracing.otel.bridge.OtelTracer;
//import io.opentelemetry.api.OpenTelemetry;
//import io.opentelemetry.exporter.zipkin.ZipkinSpanExporter;
//import io.opentelemetry.sdk.OpenTelemetrySdk;
//import io.opentelemetry.sdk.trace.SdkTracerProvider;
//import io.opentelemetry.sdk.trace.export.BatchSpanProcessor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class TracingConfig {
//
//    @Bean
//    public ZipkinSpanExporter zipkinSpanExporter() {
//        return ZipkinSpanExporter.builder()
//                .setEndpoint("http://localhost:9411/api/v2/spans")
//                .build();
//    }
//
//    @Bean
//    public OpenTelemetry openTelemetry(ZipkinSpanExporter zipkinSpanExporter) {
//        SdkTracerProvider sdkTracerProvider = SdkTracerProvider.builder()
//                .addSpanProcessor(BatchSpanProcessor.builder(zipkinSpanExporter).build())
//                .build();
//
//        return OpenTelemetrySdk.builder()
//                .setTracerProvider(sdkTracerProvider)
//                .build();
//    }
////
////    @Bean
////    public Tracer otelTracer(OpenTelemetry openTelemetry, ObservationRegistry observationRegistry) {
////        return new OtelTracer(openTelemetry.getTracer("service-tracing"), observationRegistry);
////    }
//
//    @Bean
//    public ObservationRegistry observationRegistry() {
//        return ObservationRegistry.create();
//    }
//}
