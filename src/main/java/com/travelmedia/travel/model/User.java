package com.travelmedia.travel.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import com.travelmedia.travel.validation.uniqueEmailValidator.UniqueEmail;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

@Document("users")
public class User {
    @Id
    private String id;
    
    @NotNull(message="display name must not be null") @NotBlank(message="display name must not be blank")
    private String displayName;
    
    @NotNull(message="usernam must not be null") @NotBlank(message="username must not be blank")
    private String username;
   
    @Email(message="invalid email") @NotBlank(message="email must not be blank") @UniqueEmail
    private String email;
    
    @NotNull(message="photo url must not be null") @NotBlank(message="photo url must not be blank")
    private String photoUrl;
    
    @NotNull(message="password must not be null") @NotBlank(message="password must not be blank") 
    private String password;

    @CreatedDate
    private Date createdAt;


    @LastModifiedDate
    private Date updatedAt;

    // Getters and Setters

    public Object getId() {
        return this.id;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public String getUsername() {
        return this.username;
    }

    public String getEmail() {
        return this.email;
    }
    
    public String getPhotoUrl() {
        return this.photoUrl;
    }

    public void setPassword(String passwordString){
        this.password = passwordString;
    }

    public void setDisplayName(String displayNameString) {
       this.displayName = displayNameString;
    }

    public void setUsername(String usernameString) {
        this.username = usernameString;
    }

     public void setEmail(String emailString) {
        this.email = emailString;
     }

     public void setPhotoUrl(String photoUrlString) {
        this.photoUrl = photoUrlString;
     }

     public User(){

     }
    
}
