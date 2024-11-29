package com.packtpub.authenticationservices.config.security;

import org.springframework.security.oauth2.client.userinfo.DefaultReactiveOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.ReactiveOAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CustomOAuth2UserService implements ReactiveOAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final DefaultReactiveOAuth2UserService delegate = new DefaultReactiveOAuth2UserService();

    @Override
    public Mono<OAuth2User> loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        return delegate
                .loadUser(userRequest)
                .flatMap(oAuth2User -> {
                    // Extract user attributes
                    String googleId = oAuth2User.getAttribute("sub");
                    String email = oAuth2User.getAttribute("email");
                    String name = oAuth2User.getAttribute("name");

                    // Simulate any additional processing or validation if required
                    // Example: Add custom claims or attributes to the user

                    return Mono.just(oAuth2User);
                });
    }
}