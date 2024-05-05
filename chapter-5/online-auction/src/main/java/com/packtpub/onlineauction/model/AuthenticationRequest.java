package com.packtpub.onlineauction.model;

import org.antlr.v4.runtime.misc.NotNull;

public class AuthenticationRequest {

    @NotNull
//    @Size(max = 255)
    private String email;

    @NotNull
//    @Size(max = 255)
    private String password;
}
