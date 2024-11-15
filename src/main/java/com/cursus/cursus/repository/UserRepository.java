package com.cursus.cursus.repository;


import com.cursus.cursus.models.User;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * @author oussama othmani
 */
public interface UserRepository extends CrudRepository<User, Integer> {
    @Query("SELECT e FROM User WHERE e.email=:email and e.password=:password")
    public User getUserByEmailAndPassword(@Param("email")String login, @Param("password")String password);
    Optional<User> findByEmail(String email);
    Boolean existsByEmail(String email);
}