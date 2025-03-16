package com.packtpub.authorizationserver.adapter.datasources;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthenticationMongoDatasource extends MongoRepository<AuthenticationEntity, Long> {
    Optional<AuthenticationEntity> findByUsername(String username);
}
