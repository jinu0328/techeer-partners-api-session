package com.example.teecheer_api_session.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)

    private String title;

    @JsonProperty("isDone") // JSON에서 필드 이름을 "isDone"으로 강제
    private boolean isDone = false; // "isDone" 기본값 설정

    private LocalDate deadline; // 마감일 필드


    @Transient
    private boolean isPastDeadline; // 계산된 필드 (데이터베이스에 저장되지 않음)

    public boolean getIsPastDeadline() {
        return deadline != null && deadline.isBefore(LocalDate.now());
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone; // 전달된 값을 제대로 반영
    }
}