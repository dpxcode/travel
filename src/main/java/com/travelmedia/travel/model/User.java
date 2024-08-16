package com.travelmedia.travel.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document("users")
public class User {
    @Id
    private String id;
    private String displayName;
    private String username;
    private String email;
    private String photoUrl;

    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;

    // Getters and Setters

    public String getDisplayName() {
        return this.displayName;
    }

    public String setDisplayName(String displayName2) {
       return this.displayName;
    }

    
}
