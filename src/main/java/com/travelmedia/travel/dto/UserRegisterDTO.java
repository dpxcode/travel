package com.travelmedia.travel.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRegisterDTO {
    
    private String displayName;
    private String username;
    private String email;
    private String photoUrl;
    private String token;

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName){
        this.displayName = displayName;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }


    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl){
        this.photoUrl = photoUrl;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token){
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
       this.email = email;
    }

    @Override
    public String toString() {
        return "UserRegisterDTO{" +
                "email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", photoUrl='" + photoUrl + '\'' +
                ", token='" + token + '\'' +
                ", displayName='" + displayName + '\'' +
                '}';
    }

}
