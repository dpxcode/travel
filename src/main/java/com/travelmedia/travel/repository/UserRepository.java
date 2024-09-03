package com.travelmedia.travel.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.travelmedia.travel.model.User;

public interface UserRepository extends MongoRepository<User, String> {

    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    Optional<User> findByUsername(String username);
    Optional<User> findByUsernameOrEmail(String username, String email);
}