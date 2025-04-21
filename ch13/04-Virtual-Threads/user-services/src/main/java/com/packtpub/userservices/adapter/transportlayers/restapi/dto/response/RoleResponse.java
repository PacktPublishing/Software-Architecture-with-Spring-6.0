package com.packtpub.userservices.adapter.transportlayers.restapi.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class RoleResponse {
    private List<String> roles;
}