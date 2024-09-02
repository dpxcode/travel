package com.travelmedia.travel.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.travelmedia.travel.model.User;

public interface UserRepository extends MongoRepository<User, String> {

    boolean existsByEmail(String email);
    boolean existsByUsername(String username);

}