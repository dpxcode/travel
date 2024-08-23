package com.travelmedia.travel.validation.uniqueEmailValidator;

import com.travelmedia.travel.repository.UserRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {
    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context){
        return email != null && !userRepository.existsByEmail(email);
    }
    
}
