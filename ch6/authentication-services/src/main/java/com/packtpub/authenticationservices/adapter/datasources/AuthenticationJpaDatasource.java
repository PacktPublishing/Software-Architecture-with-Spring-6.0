package com.packtpub.authenticationservices.adapter.datasources;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthenticationJpaDatasource extends JpaRepository<AuthenticationEntity, Long> {
    Optional<AuthenticationEntity> findByUsername(String username);
}
