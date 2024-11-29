package com.packtpub.authenticationservices.config.correlation;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

public class CorrelationIdUtil {

    private static final String CORRELATION_ID_HEADER_NAME = "x-correlation-id";

    public static String getCorrelationId() {
        String correlationId = (String) RequestContextHolder.currentRequestAttributes()
                .getAttribute(CORRELATION_ID_HEADER_NAME, RequestAttributes.SCOPE_REQUEST);
        return correlationId;
    }
}