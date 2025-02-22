package com.packtpub.authenticationservices.internal.entities;

import java.util.List;

public class Authentication {
    private Long id;
    private String username;
    private String password;

    private boolean accountNonExpired;

    private boolean isEnabled;

    private boolean accountNonLocked;

    private boolean credentialsNonExpired;

    private String token;

    private List<String> roles;

    public Authentication() {
    }

    public Authentication( String username,  List<String> roles) {
        this.username = username;
        this.roles = roles;
    }

    public Authentication(Long id, String username, String password, boolean accountNonExpired, boolean isEnabled, boolean accountNonLocked, boolean credentialsNonExpired, List<String> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.accountNonExpired = accountNonExpired;
        this.isEnabled = isEnabled;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.roles = roles;
    }

    public Authentication(String username, String password, List<String> roles, boolean enabled, boolean accountNonExpired, boolean accountNonLocked, boolean credentialsNonExpired) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
