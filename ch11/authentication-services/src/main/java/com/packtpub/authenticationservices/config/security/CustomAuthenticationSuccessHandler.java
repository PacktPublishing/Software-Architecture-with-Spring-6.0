package com.packtpub.authenticationservices.config.security;

import com.packtpub.authenticationservices.adapter.datasources.TokenJwt;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final TokenJwt tokenJwt;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        String username = oAuth2User.getAttribute("email");

        com.packtpub.authenticationservices.internal.entities.Authentication userAuthentication =
                new com.packtpub.authenticationservices.internal.entities.Authentication(username,"", List.of("ROLE_USER"), true,true,true,true);

        String jwtToken = tokenJwt.generate(userAuthentication);
        String redirectUrl = "http://localhost:3000?token=" + jwtToken;
        getRedirectStrategy().sendRedirect(request, response, redirectUrl);
    }

    private com.packtpub.authenticationservices.internal.entities.Authentication convertOAuth2UserToAuthentication(OAuth2User oAuth2User) {

        String username = oAuth2User.getAttribute("email");

        // Collect authorities (roles) from OAuth2User
        Collection<? extends GrantedAuthority> authorities = oAuth2User.getAuthorities();
        List<String> roles = authorities.stream()
                .map(GrantedAuthority::getAuthority) // Extract the authority string from each GrantedAuthority
                .collect(Collectors.toList());

        return new com.packtpub.authenticationservices.internal.entities.Authentication(username,"", roles, true,true,true,true);

    }
}
