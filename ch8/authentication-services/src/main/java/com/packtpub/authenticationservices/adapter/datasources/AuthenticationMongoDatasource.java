package com.packtpub.authenticationservices.adapter.datasources;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthenticationMongoDatasource extends MongoRepository<AuthenticationDocument, String> {
    Optional<AuthenticationDocument> findByUsername(String username);
}
