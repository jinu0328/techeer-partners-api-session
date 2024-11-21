package com.example.techeer_partners_api_session.dto;


public class TaskRequestDto {

    private final String title;

    private final boolean isDone;

    public TaskRequestDto(String title, boolean isDone){
        this.title = title;
        this.isDone = isDone;
    }

    public String getTitle() {
        return title;
    }

    public boolean getIsDone() {
        return isDone;
    }
}