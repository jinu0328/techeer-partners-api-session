package com.example.techeer_partners_api_session.domain.task.entity;

import com.example.techeer_partners_api_session.domain.task.dto.TaskResponseDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private boolean isDone = false;

    public Task() {
    }

    public Task(String title) {
        this.title = title;
    }

    public void update(String title, Boolean isDone) {
        if(title != null) {
            this.title = title;
        }
        if(isDone != null) {
            this.isDone = isDone;
        }
    }

    public TaskResponseDto toDto() {
        return new TaskResponseDto(id, title, isDone);
    }

}
