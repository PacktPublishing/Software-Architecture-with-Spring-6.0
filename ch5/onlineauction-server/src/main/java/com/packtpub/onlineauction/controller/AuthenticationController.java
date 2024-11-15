package com.packtpub.onlineauction.controller;

import com.packtpub.onlineauction.dto.request.AuthenticationRequest;
import com.packtpub.onlineauction.dto.response.AuthenticationResponse;
import com.packtpub.onlineauction.service.security.JwtService;
import com.packtpub.onlineauction.service.security.UserDetailsCustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/auth")
@RestController
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsCustomService userDetailsService;

    @Autowired
    private JwtService jwtService;

    @PostMapping
    public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
        );

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtService.generateToken(userDetails);

        return new AuthenticationResponse(token);
    }
}