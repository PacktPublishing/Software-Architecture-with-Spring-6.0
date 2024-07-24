package com.packtpub.onlineauction.service.security;

import com.packtpub.onlineauction.entity.Authentication;
import com.packtpub.onlineauction.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Authentication authentication = userRepository.findByUsername(username)
                .orElseThrow(() -> new
                        UsernameNotFoundException("User not found with username: " + username));

        UserDetailsCustom userDetailsCustom = new UserDetailsCustom(
                authentication.getUsername(),
                authentication.getPassword(),
                authentication.getUser().getRoles().stream()
                        .map(r -> new SimpleGrantedAuthority(r.getName()))
                        .collect(Collectors.toList()),
                authentication.isEnabled(),
                authentication.isAccountNonExpired(),
                authentication.isAccountNonLocked(),
                authentication.isCredentialsNonExpired()
        );

        return userDetailsCustom;

    }
}