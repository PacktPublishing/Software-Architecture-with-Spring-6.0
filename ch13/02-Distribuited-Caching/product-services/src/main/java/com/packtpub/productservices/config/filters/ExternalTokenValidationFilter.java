package com.packtpub.productservices.config.filters;

import com.packtpub.productservices.adapter.datasources.authentication.AuthenticationRestApi;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;
import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class ExternalTokenValidationFilter extends OncePerRequestFilter {

    private final HandlerExceptionResolver handlerExceptionResolver;
    private final AuthenticationRestApi authenticationRestApi;

    private static final String[] WHITE_LIST = {
            "^/v1/users/[^/]+/roles$",
            "^/actuator/[^/]+",
            "^/swagger-ui/[^/]+",
            "/v3/api-docs/[^/]+",
            "/v3/api-docs"
    };


    private boolean isInWhiteList(String uri) {
        for (String whiteList : WHITE_LIST) {
            if (Pattern.compile(whiteList).matcher(uri).matches()) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {

        try {
            final String authHeader = request.getHeader("Authorization");
            String token = null;

            if (!isInWhiteList(request.getRequestURI())) {
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    token = authHeader.substring(7);
                } else {
                    throw new ExpiredJwtException(null, null, authHeader);
                }
            }

            if (token != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                boolean isValid = authenticationRestApi.validateToken(authHeader);

                if (isValid) {
                    SecurityContextHolder.getContext().setAuthentication(
                            new UsernamePasswordAuthenticationToken(null, null, null));
                } else {
                    throw new ExpiredJwtException(null, null, authHeader);
                }
            }

            filterChain.doFilter(request, response);

        } catch (Exception exception) {
            handlerExceptionResolver.resolveException(request, response, null, exception);
        }
    }
}