package com.example.techeer_partners_api_session.entity;

import jakarta.persistence.*;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 생성
    private Long id;

    @Column(nullable = false) // null 허용X
    private String title;

    private boolean isDone = false;

    public Task() {} // JPA가 entity에 접근하기 위해 기본 생성자 필수

    public Task(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public boolean isDone() {
        return isDone;
    }
}
