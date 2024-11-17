package com.example.techeer_partners_api_session.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String title;

    private boolean isDone = false;

    public Task(String title) {
        this.title = title;
        this.isDone = false;
    }
    public void update(String title, boolean isDone) {
        this.title = title;
        this.isDone = isDone;
    }
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

}
