package com.example.techeer_partners_api_session.domain.task.controller;

import com.example.techeer_partners_api_session.domain.task.dto.TaskRequestDto;
import com.example.techeer_partners_api_session.domain.task.dto.TaskResponseDto;
import com.example.techeer_partners_api_session.domain.task.service.TaskService;
import com.example.techeer_partners_api_session.global.dto.ApiResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // 할 일 생성
    @PostMapping
    public ResponseEntity<ApiResponseDto<Void>> createTask(@RequestBody TaskRequestDto dto) {
        taskService.createTask(dto);
        return ResponseEntity.status(201).body(new ApiResponseDto<>("success", "할 일이 생성되었습니다."));
    }

    // 할 일 조회
    @GetMapping
    public ResponseEntity<ApiResponseDto<List<TaskResponseDto>>> getAllTasks(
            @RequestParam(value = "isDone", required = false) Boolean isDone) {
        List<TaskResponseDto> tasks = taskService.getTasksByIsDone(isDone);
        return ResponseEntity.status(200).body(new ApiResponseDto<>("success", "할 일이 조회되었습니다.", tasks));
    }

    // 할 일 수정
    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponseDto<Void>> updateTask(@PathVariable Long id, @RequestBody TaskRequestDto dto) {
        taskService.updateTask(id, dto);
        return ResponseEntity.status(200).body(new ApiResponseDto<>("success", "할 일이 수정되었습니다."));
    }

    // 할 일 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDto<Void>> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.status(200).body(new ApiResponseDto<>("success", "할 일이 삭제되었습니다."));
    }


}