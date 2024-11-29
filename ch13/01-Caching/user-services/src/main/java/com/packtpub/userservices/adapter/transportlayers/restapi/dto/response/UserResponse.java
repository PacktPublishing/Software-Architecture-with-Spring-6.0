package com.packtpub.userservices.adapter.transportlayers.restapi.dto.response;

import com.packtpub.userservices.internal.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private Long id;

    private String name;

    private String email;

    private String phoneNumber;

    private String city;

    private String state;

    private String country;

    private Set<Role> roles;
}