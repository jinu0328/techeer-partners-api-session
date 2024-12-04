    package com.example.techeer_partners_api_session.api.domain.dto.response;

import com.example.techeer_partners_api_session.api.domain.entity.Task;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public class TaskGetCompletedResponse {

        private Long taskId;

        private String title;

        private boolean isDone;

        public static TaskGetCompletedResponse from(Task task){
            return TaskGetCompletedResponse.builder()
                    .taskId(task.getId())
                    .title(task.getTitle())
                    .isDone(task.isDone())
                    .build();
        }

        public static Page<TaskGetCompletedResponse> listOf(Page<Task> completedTasks){
            return completedTasks.map(TaskGetCompletedResponse::from);
        }
    }
