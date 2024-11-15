package com.cursus.cursus.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cursus.cursus.Entity.User;

public interface UserRepository extends JpaRepository<User, String> {
    User findByUsername(String username);
}