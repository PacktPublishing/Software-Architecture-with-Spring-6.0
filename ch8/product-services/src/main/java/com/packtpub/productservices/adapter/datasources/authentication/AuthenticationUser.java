package com.packtpub.productservices.adapter.datasources.authentication;

import lombok.Getter;

import java.util.List;

@Getter
public class AuthenticationUser {
    String subject;
    List<String> roles;
}