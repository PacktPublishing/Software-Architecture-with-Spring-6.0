package com.packtpub.authenticationservices.internal.repositories;

import java.util.List;
import java.util.concurrent.TimeoutException;

public interface UserRepository {
    List<String> getRolesByUsername(String username);
}
