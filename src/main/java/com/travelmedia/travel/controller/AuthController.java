package com.travelmedia.travel.controller;

import com.travelmedia.travel.dto.UserRegisterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.travelmedia.travel.model.User;
import com.travelmedia.travel.service.UserService;
import com.travelmedia.travel.util.JWTUtil;
import com.travelmedia.travel.util.ResponseUtil;

import jakarta.validation.Valid;
import java.util.List; 

@RestController
@RequestMapping("/api/signup")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    // Create a New User
    @SuppressWarnings("rawtypes")
    @PostMapping
    public ResponseEntity createUser(@Valid @RequestBody User user, BindingResult result) {
        try{
            if (result.hasErrors()) {
                return ResponseUtil.createErrorResponse(result);
            }
    
            if (!user.getPassword().equals(user.getConfirmPassword())) {
                return ResponseUtil.createErrorStringResponse("Password and Confirm Password do not match", "confirmPassword", HttpStatus.BAD_REQUEST);
            }

             // Encode the password before saving the user
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            
            userService.createUser(user);

            // Map user register entity to DTO
            UserRegisterDTO userRegisterDto = new UserRegisterDTO();
            
            userRegisterDto.setEmail(user.getEmail());
            userRegisterDto.setUsername(user.getUsername());
            userRegisterDto.setDisplayName(user.getDisplayName());
            userRegisterDto.setToken(JWTUtil.generateToken(List.of(user.getId().toString(), user.getEmail(), user.getUsername())));
            
            userRegisterDto.setPhotoUrl(user.getPhotoUrl());
            
            // userRegisterDto should be returned as an object
            return ResponseUtil.createSuccessResponse("User created successfully", HttpStatus.CREATED, userRegisterDto);
        
        } catch (Exception e) {
            return ResponseUtil.createErrorStringResponse(e.getMessage(), "exception", HttpStatus.INTERNAL_SERVER_ERROR);
        }
       
    }
    
}
