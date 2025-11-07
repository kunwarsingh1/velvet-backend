package com.velvet.investing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.velvet.investing.entity.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}