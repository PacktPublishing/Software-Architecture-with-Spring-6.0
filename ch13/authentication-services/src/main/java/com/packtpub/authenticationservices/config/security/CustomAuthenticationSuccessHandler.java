package com.packtpub.authenticationservices.config.security;

import com.packtpub.authenticationservices.adapter.datasources.TokenJwt;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class CustomAuthenticationSuccessHandler implements ServerAuthenticationSuccessHandler {

    private final TokenJwt tokenJwt;

    @Override
    public Mono<Void> onAuthenticationSuccess(WebFilterExchange webFilterExchange, Authentication authentication) {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        String username = oAuth2User.getAttribute("email");

        // Create the user authentication entity
        com.packtpub.authenticationservices.internal.entities.Authentication userAuthentication =
                new com.packtpub.authenticationservices.internal.entities.Authentication(
                        username, "", List.of("ROLE_USER"), true, true, true, true
                );

        // Generate the JWT token
        String jwtToken = tokenJwt.generate(userAuthentication);
        String redirectUrl = "http://localhost:3000?token=" + jwtToken;

        // Perform the redirect and return a Mono<Void>
        return webFilterExchange.getExchange().getResponse()
                .writeWith(Mono.defer(() -> {
                    // Set the redirect location and status code
                    webFilterExchange.getExchange().getResponse().setStatusCode(HttpStatus.FOUND);
                    webFilterExchange.getExchange().getResponse().getHeaders().setLocation(URI.create(redirectUrl));
                    return Mono.empty(); // Signal completion of response handling
                }));
    }

    private com.packtpub.authenticationservices.internal.entities.Authentication convertOAuth2UserToAuthentication(OAuth2User oAuth2User) {
        String username = oAuth2User.getAttribute("email");

        // Collect authorities (roles) from OAuth2User
        Collection<? extends GrantedAuthority> authorities = oAuth2User.getAuthorities();
        List<String> roles = authorities.stream()
                .map(GrantedAuthority::getAuthority) // Extract the authority string from each GrantedAuthority
                .collect(Collectors.toList());

        return new com.packtpub.authenticationservices.internal.entities.Authentication(
                username, "", roles, true, true, true, true
        );
    }
}