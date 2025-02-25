package com.packtpub.authenticationservices.internal.repositories;

import java.util.List;

public interface UserRepository {
    List<String> getRolesByUsername(String username);
}
