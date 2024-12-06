package com.packtpub.authenticationservices.adapter.datasources;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "authentication")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationDocument {

    @Id
    private String id;

    private String username;

    private String password;

    private boolean accountNonExpired;

    private boolean isEnabled;

    private boolean accountNonLocked;

    private boolean credentialsNonExpired;

    private List<String> roles;

}
