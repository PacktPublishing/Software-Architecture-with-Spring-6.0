package com.packtpub.authenticationservices.adapter.datasources;

import com.packtpub.authenticationservices.config.security.UserDetailsCustom;
import com.packtpub.authenticationservices.internal.entities.Authentication;
import com.packtpub.authenticationservices.internal.repositories.AuthenticationManagerRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import reactor.core.publisher.Mono;

import java.util.Optional;

public class AuthenticationManagerSecurity implements AuthenticationManagerRepository {

    private final ReactiveUserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationManagerSecurity(ReactiveUserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Mono<Authentication> authenticate(String username, String password) {
        return userDetailsService.findByUsername(username)
                .flatMap(userDetails -> {
                    if (passwordEncoder.matches(password, userDetails.getPassword())) {
                        return Mono.just(toDomain(userDetails));
                    } else {
                        return Mono.error(new AuthenticationException("Invalid username or password") {});
                    }
                });
    }

    private Authentication toDomain(org.springframework.security.core.userdetails.UserDetails userDetails) {
        UserDetailsCustom userDetailsCustom = (UserDetailsCustom) userDetails;
        return new Authentication(
                null,
                userDetailsCustom.getUsername(),
                userDetailsCustom.getPassword(),
                userDetailsCustom.isAccountNonExpired(),
                userDetailsCustom.isEnabled(),
                userDetailsCustom.isAccountNonLocked(),
                userDetailsCustom.isCredentialsNonExpired(),
                null
        );
    }

}