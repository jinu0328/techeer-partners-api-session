    package com.example.techeer_partners_api_session.api.domain.dto.response;

import com.example.techeer_partners_api_session.api.domain.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public class TaskGetIncompleteResponse {

        private Long taskId;

        private String title;

        private boolean isDone;

        public static TaskGetIncompleteResponse from(Task task){
            return TaskGetIncompleteResponse.builder()
                    .taskId(task.getId())
                    .title(task.getTitle())
                    .isDone(task.isDone())
                    .build();
        }

        public static Page<TaskGetIncompleteResponse> listOf(Page<Task> IncompleteTasks){
            return IncompleteTasks.map(TaskGetIncompleteResponse::from);
        }
    }
