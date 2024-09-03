package com.travelmedia.travel.dto;

public class UserLoginResponseDTO {

    private String token;
    private String displayName;
    private String photoUrl;
    private String username;
    private String email;
    
    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }


    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }   

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getEmail() {
        return email;
    }


   @Override
   public String toString() {
       return "UserLoginResponseDTO{" +
               "email='" + email + '\'' +
               ", username='" + username + '\'' +
               ", photoUrl='" + photoUrl + '\'' +
               ", token='" + token + '\'' +
               ", displayName='" + displayName + '\'' +
               '}';
   }
}
