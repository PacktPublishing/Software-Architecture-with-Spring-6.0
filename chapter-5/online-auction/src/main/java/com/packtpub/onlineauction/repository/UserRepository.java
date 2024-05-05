package com.packtpub.onlineauction.repository;

import com.packtpub.onlineauction.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
