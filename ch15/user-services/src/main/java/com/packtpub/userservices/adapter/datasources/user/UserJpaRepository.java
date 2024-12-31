package com.packtpub.userservices.adapter.datasources.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
    @Query(value = "Select * from users where email = ?" , nativeQuery = true)
    Optional<UserEntity> findByUsername(String username);
}

