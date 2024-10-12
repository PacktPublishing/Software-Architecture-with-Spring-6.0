package com.packtpub.authenticationservices.adapter.datasources;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthenticationJpaDatasource extends MongoRepository<AuthenticationEntity, Long> {
    Optional<AuthenticationEntity> findByUsername(String username);
}
