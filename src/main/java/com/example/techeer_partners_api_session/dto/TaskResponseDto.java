package com.example.techeer_partners_api_session.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TaskResponseDto {
    private Long id;
    private String title;
    private boolean isDone;
}