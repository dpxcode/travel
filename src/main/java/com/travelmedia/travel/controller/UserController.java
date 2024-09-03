package com.travelmedia.travel.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication; 
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/users")
public class UserController {

    
    // user me api to get details of logged in user
    @GetMapping("/me")
    public ResponseEntity<String> getMe() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            // Retrieve user details from userDetails
            String username = userDetails.getUsername();
            // You can also retrieve other details if you have a custom UserDetails implementation
            return ResponseEntity.ok("User Details: " + username);
        }
        return ResponseEntity.status(401).body("Unauthorized");
    }

    
}
