    package com.example.techeer_partners_api_session.api.domain.dto.response;

import com.example.techeer_partners_api_session.api.domain.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskUpdateResponse {

    private Long taskId;

    private String title;

    private boolean isDone;

    public static TaskUpdateResponse from(Task task) {
        return TaskUpdateResponse.builder()
                .taskId(task.getId())
                .title(task.getTitle())
                .isDone(task.isDone())
                .build();
    }
}
