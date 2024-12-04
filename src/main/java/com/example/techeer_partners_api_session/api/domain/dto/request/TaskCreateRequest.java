package com.example.techeer_partners_api_session.api.domain.dto.request;

import com.example.techeer_partners_api_session.api.domain.entity.Task;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskCreateRequest {

    @NotBlank(message = "할 일의 제목이 비어있을 수 없습니다.")
    private String title;

    public Task toEntity(){
        return Task.builder()
                .title(this.title)
                .build();
    }
}
