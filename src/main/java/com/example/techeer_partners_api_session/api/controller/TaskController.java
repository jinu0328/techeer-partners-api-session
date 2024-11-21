package com.example.techeer_partners_api_session.api.controller;

import static com.example.techeer_partners_api_session.result.ResultCode.*;

import com.example.techeer_partners_api_session.api.domain.dto.request.TaskCreateRequest;
import com.example.techeer_partners_api_session.api.domain.dto.request.TaskUpdateRequest;
import com.example.techeer_partners_api_session.api.domain.dto.response.TaskCreateResponse;
import com.example.techeer_partners_api_session.api.domain.dto.response.TaskGetCompletedResponse;
import com.example.techeer_partners_api_session.api.domain.dto.response.TaskGetIncompleteResponse;
import com.example.techeer_partners_api_session.api.domain.dto.response.TaskGetResponse;
import com.example.techeer_partners_api_session.api.domain.dto.response.TaskUpdateResponse;
import com.example.techeer_partners_api_session.api.domain.entity.Task;
import com.example.techeer_partners_api_session.api.service.TaskService;
import com.example.techeer_partners_api_session.result.ResultResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tasks")
@Tag(name = "Task APIs", description = "할 일 생성, 수정, 조회 등의 api 입니다.")
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    @Operation(summary = "할 일 생성", description = "할 일을 생성하는 API")
    public ResponseEntity<ResultResponse> createTask(@RequestBody TaskCreateRequest dto) {
        Task task = dto.toEntity();
        TaskCreateResponse response = TaskCreateResponse.from(taskService.createTask(task));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ResultResponse.of(TASK_CREATE_SUCCESS, response));
    }

    @PutMapping("/{taskId}")
    @Operation(summary = "할 일 수정", description = "할 일의 제목 혹은 완료여부를 수정하는 API")
    public ResponseEntity<ResultResponse> updateTask(@PathVariable("taskId") Long taskId, @RequestBody TaskUpdateRequest dto) {
        TaskUpdateResponse response = TaskUpdateResponse.from(taskService.modifyTask(taskId, dto));
        return ResponseEntity.status(HttpStatus.OK)
                .body(ResultResponse.of(TASK_UPDATE_SUCCESS, response));
    }

    @DeleteMapping("/{taskId}")
    @Operation(summary = "할 일 삭제", description = "할 일을 삭제하는 API")
    public ResponseEntity<ResultResponse> deleteTask(@PathVariable("taskId") Long taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ResultResponse.of(TASK_DELETE_SUCCESS));
    }

    @GetMapping
    @Operation(summary = "할 일 조회", description = "전체 할 일(완료, 미완료)을 조회하는 API")
    public ResponseEntity<ResultResponse> getAllTasks(@PageableDefault(page = 0, size = 12, sort = "createdAt", direction = Direction.DESC) Pageable pageable) {
        Page<TaskGetResponse> response = TaskGetResponse.listOf(taskService.getAllTasks(pageable));
        return ResponseEntity.status(HttpStatus.OK)
                .body(ResultResponse.of(TASK_GET_SUCCESS, response));

    }

    @GetMapping("/completed")
    @Operation(summary = "완료된 할 일 조회", description = "할 일 중 완료된 것들을 조회하는 API")
    public ResponseEntity<ResultResponse> getCompletedTasks(@PageableDefault(page = 0, size = 8, sort = "createdAt", direction = Direction.DESC) Pageable pageable) {
        Page<TaskGetCompletedResponse> response = TaskGetCompletedResponse.listOf(taskService.getCompletedTasks(pageable));
        return ResponseEntity.status(HttpStatus.OK)
                .body(ResultResponse.of(TASK_GET_COMPLETE_SUCCESS, response));
    }

    @GetMapping("/incomplete")
    @Operation(summary = "미완료 할 일 조회", description = "아직 완료하지 못한 할 일들을 API")
    public ResponseEntity<ResultResponse> getIncompleteTasks(@PageableDefault(page = 0, size = 8, sort = "createdAt", direction = Direction.DESC) Pageable pageable) {
        Page<TaskGetIncompleteResponse> response = TaskGetIncompleteResponse.listOf(taskService.getIncompleteTasks(pageable));
        return ResponseEntity.status(HttpStatus.OK)
                .body(ResultResponse.of(TASK_GET_INCOMPLETE_SUCCESS, response));
    }
}
