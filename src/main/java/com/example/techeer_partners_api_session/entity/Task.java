package com.example.techeer_partners_api_session.entity;

import jakarta.persistence.*;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private boolean isDone = false;

    public Task() {}

    public Task(String title) {
        this.title = title;
    }
}