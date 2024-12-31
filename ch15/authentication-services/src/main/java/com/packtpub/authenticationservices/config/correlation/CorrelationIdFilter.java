package com.packtpub.authenticationservices.config.correlation;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.io.IOException;
import java.util.UUID;

@Component
public class CorrelationIdFilter extends OncePerRequestFilter {

    private static final String CORRELATION_ID_HEADER_NAME = "x-correlation-id";

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        final String correlationId = request.getHeader("x-correlation-id");

        RequestContextHolder.currentRequestAttributes()
                .setAttribute(CORRELATION_ID_HEADER_NAME, correlationId, RequestAttributes.SCOPE_REQUEST);
        response.setHeader(CORRELATION_ID_HEADER_NAME, correlationId);
        filterChain.doFilter(request, response);
    }
}