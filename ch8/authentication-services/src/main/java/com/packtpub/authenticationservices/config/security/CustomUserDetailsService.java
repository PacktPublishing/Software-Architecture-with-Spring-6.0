package com.packtpub.authenticationservices.config.security;

import com.packtpub.authenticationservices.adapter.datasources.AuthenticationMongoDatasource;
import com.packtpub.authenticationservices.adapter.datasources.AuthenticationDocument;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final AuthenticationMongoDatasource authenticationRepository;

    public CustomUserDetailsService(AuthenticationMongoDatasource authenticationRepository) {
        this.authenticationRepository = authenticationRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<GrantedAuthority> authorityList = new ArrayList<>();

        AuthenticationDocument authentication = authenticationRepository.findByUsername(username)
                .orElseThrow(() -> new
                        UsernameNotFoundException("User not found with username: " + username));

        UserDetailsCustom userDetailsCustom = new UserDetailsCustom(
                authentication.getUsername(),
                authentication.getPassword(),
                authorityList,
                authentication.isEnabled(),
                authentication.isAccountNonExpired(),
                authentication.isAccountNonLocked(),
                authentication.isCredentialsNonExpired()
        );
        return userDetailsCustom;
    }
}