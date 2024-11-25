package com.example.techeer_partners_api_session.entity;

import com.example.techeer_partners_api_session.dto.TaskRequestDto;
import jakarta.persistence.*;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private boolean isDone = false;

    public Task() {} // JPA의 엔티티 클래스 접근을 위한 기본 생성자

    public Task(String title){ // 할 일 제목 생성
        this.title = title;
    }

    // Task 객체를 TaskRequestDto로 변환
    public TaskRequestDto toRequestDto() {
        return new TaskRequestDto(this.title, this.isDone);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setIsDone(boolean done) {
        this.isDone = done;
    }
}
