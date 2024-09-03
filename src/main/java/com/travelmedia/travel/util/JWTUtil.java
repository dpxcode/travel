package com.travelmedia.travel.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.List;
import java.util.Arrays;

public class JWTUtil {

    private static final String SECRET_KEY = "travel-jwt-needs-get-from-secure-env"; // Replace with your own secret key

    public static String generateToken(List<String> argument) {
        String subject = String.join(",", argument); // Convert List<String> to a single String
        return Jwts.builder()
                .setSubject(subject)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public static List<String> decodeToken(String token) {
        return Arrays.asList(Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody()
                .getSubject()
                .split(",")); // Split the subject string to List<String>
    }

}