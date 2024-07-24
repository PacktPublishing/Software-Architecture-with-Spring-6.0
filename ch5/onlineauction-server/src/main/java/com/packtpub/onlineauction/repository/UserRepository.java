package com.packtpub.onlineauction.repository;

import com.packtpub.onlineauction.entity.Authentication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Authentication, Long> {
    Optional<Authentication> findByUsername(String username);
}

