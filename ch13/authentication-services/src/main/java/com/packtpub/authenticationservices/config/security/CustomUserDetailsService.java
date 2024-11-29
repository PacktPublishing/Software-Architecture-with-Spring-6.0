package com.packtpub.authenticationservices.config.security;

import com.packtpub.authenticationservices.adapter.datasources.AuthenticationJpaDatasource;
import com.packtpub.authenticationservices.adapter.datasources.AuthenticationEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements ReactiveUserDetailsService {

    private final AuthenticationJpaDatasource authenticationRepository;

    public CustomUserDetailsService(AuthenticationJpaDatasource authenticationRepository) {
        this.authenticationRepository = authenticationRepository;
    }

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return authenticationRepository.findByUsername(username)
                .switchIfEmpty(Mono.error(new UsernameNotFoundException("User not found with username: " + username)))
                .map(authentication -> {
                    List<GrantedAuthority> authorityList = new ArrayList<>();
                    return new UserDetailsCustom(
                            authentication.getUsername(),
                            authentication.getPassword(),
                            authorityList,
                            authentication.isEnabled(),
                            authentication.isAccountNonExpired(),
                            authentication.isAccountNonLocked(),
                            authentication.isCredentialsNonExpired()
                    );
                });    }
}