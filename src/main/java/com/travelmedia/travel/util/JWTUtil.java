package com.travelmedia.travel.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.List; // Add this import statement
import java.util.Arrays; // Add this import statement

public class JWTUtil {

    public static String generateToken(List<String> argument) {
        String secretKey = "travel-jwt-needs-get-from-secure-env"; // Replace with your own secret key
        String subject = String.join(",", argument); // Convert List<String> to a single String
        String token = Jwts.builder()
                .setSubject(subject)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
        return token;
    }
    

    public static List<String> decodeToken(String token) {  
        String secretKey = "travel-jwt-needs-get-from-secure-env"; // Replace with your own secret key     
        return Arrays.asList(Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject().split(",")); // Split the subject string to List<String>  
    }

}
