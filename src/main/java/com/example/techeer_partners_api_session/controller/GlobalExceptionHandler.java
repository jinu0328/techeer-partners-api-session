package com.example.techeer_partners_api_session.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    // IllegalArgumentException 처리
    @ExceptionHandler
    public ResponseEntity<Map<String, String>> handleIllegalArgumentException(IllegalArgumentException ex){
        Map<String, String> response = new HashMap<>();
        response.put("error", ex.getMessage()); // 에러 메시지
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response); // 404 상태 코드
    }
}
