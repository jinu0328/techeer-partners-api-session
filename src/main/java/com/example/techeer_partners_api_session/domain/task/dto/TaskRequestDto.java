package com.example.techeer_partners_api_session.domain.task.dto;

public class TaskRequestDto {
    private final String title;
    private final boolean isDone;

    public TaskRequestDto(String title, boolean isDone) {
        this.title = title;
        this.isDone = isDone;
    }

    public String getTitle() {
        return title;
    }

    public boolean isDone() {
        return isDone;
    }
}
