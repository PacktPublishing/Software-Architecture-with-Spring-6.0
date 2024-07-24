package com.packtpub.userservices.config.filters;

import com.packtpub.userservices.adapter.datasources.authentication.AuthenticationRestApi;

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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class ExternalTokenValidationFilter extends OncePerRequestFilter {

    private final HandlerExceptionResolver handlerExceptionResolver;
    private final AuthenticationRestApi authenticationRestApi;

    private static final String URL_GET_ROLES = "^/v1/users/[^/]+/roles$";

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {

        try {
            final String authHeader = request.getHeader("Authorization");
            String token = null;

            Pattern pattern = Pattern.compile(URL_GET_ROLES);
            Matcher matcher = pattern.matcher(request.getRequestURI());
            boolean isGetRoles = matcher.matches();

            if (!isGetRoles) {
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