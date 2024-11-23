package com.example.techeer_partners_api_session.domain.task.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TaskResponseDto {
    private Long id;
    private String title;
    private boolean isDone;

    public TaskResponseDto(Long id, String title, boolean isDone) {
        this.id = id;
        this.title = title;
        this.isDone = isDone;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    @JsonProperty("isDone")
    public boolean isDone() {
        return isDone;
    }
}
