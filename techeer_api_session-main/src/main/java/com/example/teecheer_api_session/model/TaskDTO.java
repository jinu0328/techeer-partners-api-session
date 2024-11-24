package com.example.teecheer_api_session.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class TaskDTO {
    private Long id;
    private String title;
    private LocalDate deadline;
    private boolean isPastDeadline;
    private boolean isDone;
}

