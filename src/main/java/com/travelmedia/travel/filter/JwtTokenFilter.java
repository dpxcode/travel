package com.travelmedia.travel.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.travelmedia.travel.model.User;
import com.travelmedia.travel.service.UserService;
import com.travelmedia.travel.util.JWTUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
                
        String header = request.getHeader("Authorization");
        String token = null;
        String userId = null;

        if (header != null && header.startsWith("Bearer ")) {
            token = header.substring(7);
            List<String> decodedTokens = JWTUtil.decodeToken(token);
            // print decoded tokens
            System.out.println("---------------Decoded Tokens:---------------");
            System.out.println(decodedTokens);
            if (!decodedTokens.isEmpty()) {
                userId = decodedTokens.get(0);
            }
        }

        if (userId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            Optional<User> userOptional = userService.getUserById(userId);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                        user.getUsername(), user.getPassword(), new ArrayList<>());
        
                
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);

            }
        }

        filterChain.doFilter(request, response);

                
    }
}