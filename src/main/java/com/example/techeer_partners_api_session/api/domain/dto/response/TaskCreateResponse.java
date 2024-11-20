package com.example.techeer_partners_api_session.api.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskCreateResponse {

    private Long taskId;

    public static TaskCreateResponse from(Long taskId) {
        return TaskCreateResponse.builder()
                .taskId(taskId).build();
    }
}
