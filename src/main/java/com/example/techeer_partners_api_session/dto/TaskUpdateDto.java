package com.example.techeer_partners_api_session.dto;

public class TaskUpdateDto {
    private String title;
    private Boolean isDone;

    public TaskUpdateDto() {}

    public String getTitle() {
        return title;
    }
    public void setTitle(){
        this.title = title;
    }

    public Boolean getIsDone() {
        return isDone;
    }

    public void setIsDone(Boolean isDone) {
            this.isDone = isDone;
    }
}
