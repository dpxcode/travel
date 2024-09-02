package com.travelmedia.travel.utils;

import java.util.HashMap;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.stream.Collectors;

public class ResponseUtil {
    private ResponseUtil() {
        // private constructor to prevent instantiation
    }
    
    public static ResponseEntity<Object> createErrorResponse(BindingResult result) {
        Map<String, String> errorMap = result.getFieldErrors().stream()
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
        
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("status", "error");
        errorResponse.put("code", HttpStatus.BAD_REQUEST.value());
        errorResponse.put("errors", errorMap);
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    // method takes error as string, key and status then return object similar to createErrorResponse function
    public static ResponseEntity<Object> createErrorStringResponse(String error, String key, HttpStatus status) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("status", "error");
        errorResponse.put("code", status.value());
        errorResponse.put("errors", Map.of(key, error));
        
        return ResponseEntity.status(status).body(errorResponse);
    }

    // response for successful operation

    public static ResponseEntity<Object> createSuccessResponse(String message, HttpStatus status, Object data) {
        Map<String, Object> successResponse = new HashMap<>();
        successResponse.put("status", "success");
        successResponse.put("code", status.value());
        successResponse.put("message", message);
        successResponse.put("data", data);
    
        return ResponseEntity.status(status).body(successResponse);
    }

}
