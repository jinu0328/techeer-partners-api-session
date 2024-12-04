package com.example.techeer_partners_api_session.api.domain.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskUpdateRequest {

    @NotBlank(message = "할 일의 제목이 비어있을 수 없습니다.")
    private String title;

    @JsonProperty("isDone")
    private boolean isDone;
}
