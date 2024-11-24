package com.example.techeer_partners_api_session.global.dto;

public class ApiResponseDto<T> {
    private String status;
    private String message;
    private T data;

    // 생성자 (데이터가 있을 때)
    public ApiResponseDto(String status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    // 생성자 (데이터가 없을 때)
    public ApiResponseDto(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}