package com.packtpub.authorizationserver.config.security;

import com.packtpub.authorizationserver.adapter.datasources.AuthenticationEntity;
import com.packtpub.authorizationserver.adapter.datasources.AuthenticationMongoDatasource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class UserManagementConfig {

    private final AuthenticationMongoDatasource authenticationRepository;

    public UserManagementConfig(AuthenticationMongoDatasource authenticationRepository) {
        this.authenticationRepository = authenticationRepository;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return this::loadUserByUsername;
    }

    private UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthenticationEntity authentication = authenticationRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        authentication.setRoles(List.of("USER"));

        List<GrantedAuthority> authorityList = authentication.getRoles().stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        return new UserDetailsCustom(
                authentication.getUsername(),
                authentication.getPassword(),
                authorityList,
                authentication.isEnabled(),
                authentication.isAccountNonExpired(),
                authentication.isAccountNonLocked(),
                authentication.isCredentialsNonExpired()
        );
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
