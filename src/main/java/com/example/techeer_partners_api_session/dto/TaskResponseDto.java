package com.example.techeer_partners_api_session.dto;

import com.example.techeer_partners_api_session.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TaskResponseDto {
    private Long id;
    private String title;
    private boolean isDone;

    // Task 엔티티를 TaskResponseDto로 변환하는 정적 메서드
    public static TaskResponseDto fromEntity(Task task) {
        return new TaskResponseDto(
                task.getId(),
                task.getTitle(),
                task.isDone()
        );
    }
}
