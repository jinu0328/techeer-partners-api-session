package com.example.techeer_partners_api_session.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TaskRequestDto {
    private final String title;
    private final boolean isDone;
}
