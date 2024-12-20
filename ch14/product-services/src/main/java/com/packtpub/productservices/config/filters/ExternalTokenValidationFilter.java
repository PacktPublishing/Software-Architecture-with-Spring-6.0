package com.packtpub.productservices.config.filters;

import com.packtpub.productservices.adapter.datasources.authentication.AuthenticationRestApi;
import com.packtpub.productservices.adapter.datasources.authentication.AuthenticationUser;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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
               AuthenticationUser authenticationUser = authenticationRestApi.validateToken(authHeader);

                if (authenticationUser != null) {

                    List<SimpleGrantedAuthority> authorities = authenticationUser.getRoles().stream()
                            .map(SimpleGrantedAuthority::new)
                            .collect(Collectors.toList());

                    SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(authenticationUser.getRoles(), null, authorities));
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