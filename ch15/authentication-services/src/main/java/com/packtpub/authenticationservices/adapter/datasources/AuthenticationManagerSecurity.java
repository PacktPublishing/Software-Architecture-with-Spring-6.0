package com.packtpub.authenticationservices.adapter.datasources;

import com.packtpub.authenticationservices.config.security.UserDetailsCustom;
import com.packtpub.authenticationservices.internal.entities.Authentication;
import com.packtpub.authenticationservices.internal.repositories.AuthenticationManagerRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.Optional;

public class AuthenticationManagerSecurity implements AuthenticationManagerRepository {

    private final AuthenticationManager authenticationManager;

    public AuthenticationManagerSecurity(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Optional<Authentication> authenticate(String username, String password) {
        org.springframework.security.core.Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        return Optional.of(toDomain(authentication));
    }

    private Authentication toDomain(org.springframework.security.core.Authentication authentication) {
        UserDetailsCustom userDetailsCustom = (UserDetailsCustom) authentication.getPrincipal();
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
