package com.travelmedia.travel.validation.uniqueUsernameValidator;

import com.travelmedia.travel.repository.UserRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        // Add your validation logic here
        return username != null && !userRepository.existsByUsername(username);
    }
}
