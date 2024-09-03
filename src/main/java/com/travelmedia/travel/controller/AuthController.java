package com.travelmedia.travel.controller;

import com.travelmedia.travel.dto.UserLoginResponseDTO;
import com.travelmedia.travel.dto.UserRegisterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.travelmedia.travel.model.User;
import com.travelmedia.travel.service.UserService;
import com.travelmedia.travel.util.JWTUtil;
import com.travelmedia.travel.util.ResponseUtil;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional; 

@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    // Create a New User
    @SuppressWarnings("rawtypes")
    @PostMapping("/api/signup")
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
    

    @SuppressWarnings("rawtypes")
    @PostMapping("/api/login")
    public ResponseEntity loginUser(@Valid @RequestBody Map<String, String> loginRequest, BindingResult result) {

        try {
            if (result.hasErrors()) {
                return ResponseUtil.createErrorResponse(result);
            }

            String usernameOrEmail = loginRequest.get("usernameOrEmail");
            String password = loginRequest.get("password");


            Optional<User> optionalUser = userService.findByUsernameOrEmail(usernameOrEmail);
            if (optionalUser.isEmpty()) {
                return ResponseUtil.createErrorStringResponse("Invalid username or email", "usernameOrEmail", HttpStatus.BAD_REQUEST);
            }

            User user = optionalUser.get();

            if (!bCryptPasswordEncoder.matches(password, user.getPassword())) {
                return ResponseUtil.createErrorStringResponse("Invalid password", "password", HttpStatus.BAD_REQUEST);
            }

            // Create response DTO
            UserLoginResponseDTO responseDTO = new UserLoginResponseDTO();
            responseDTO.setEmail(user.getEmail());
            responseDTO.setUsername(user.getUsername());
            responseDTO.setDisplayName(user.getDisplayName());
            responseDTO.setToken(JWTUtil.generateToken(List.of(user.getId().toString(), user.getEmail(), user.getUsername())));
            
            responseDTO.setPhotoUrl(user.getPhotoUrl());

            return ResponseUtil.createSuccessResponse("login success", HttpStatus.OK, responseDTO);

        } catch (Exception e) {
            return ResponseUtil.createErrorStringResponse("An error occurred during login", "error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
