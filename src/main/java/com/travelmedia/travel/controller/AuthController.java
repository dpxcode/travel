package com.travelmedia.travel.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.travelmedia.travel.model.User;
import com.travelmedia.travel.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/signup")
public class AuthController {

    @Autowired
    private UserService userService;


    // Create a New User
    @SuppressWarnings("rawtypes")
    @PostMapping
    public ResponseEntity createUser(@Valid @RequestBody User user, BindingResult result) {

        if(result.hasErrors()){
            List<String> errorMessageList = result.getAllErrors().stream()
            .map(DefaultMessageSourceResolvable::getDefaultMessage)
            .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errorMessageList);
        }

        userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("user created successfully!");
    }
    
}
