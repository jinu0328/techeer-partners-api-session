package com.example.techeer_partners_api_session.controller;

import com.example.techeer_partners_api_session.dto.TaskRequestDto;
import com.example.techeer_partners_api_session.dto.TaskResponseDto;
import com.example.techeer_partners_api_session.entity.Task;
import com.example.techeer_partners_api_session.repository.TaskRepository;
import com.example.techeer_partners_api_session.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;
    private final TaskRepository taskRepository;

    public TaskController(TaskService taskService, TaskRepository taskRepository) {
        this.taskService = taskService;
        this.taskRepository = taskRepository;
    }

    private <T> ResponseEntity<Map<String, Object>> createResponse(String status, String message, T data) {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("status", status);
        response.put("message", message);
        if(data != null){
            response.put("data", data);
        }
        return ResponseEntity.ok(response);
    }

    //할 일 등록
    @Operation(summary = "할 일 등록")
    @PostMapping
    public ResponseEntity<Map<String, Object>> createTask(@RequestBody TaskRequestDto dto) {
        if (dto.getTitle() == null || dto.getTitle().isBlank()) {
            return createResponse("fail", "title은 필수 입력입니다.", null);
        }

        taskService.createTask(dto);
        return createResponse("success", "할 일이 생성 되었습니다.", null);
    }

    // 전체 조회
    @Operation(summary = "할 일 전체 조회")
    @GetMapping
    public ResponseEntity<Map<String, Object>> getTasks() {
        List<Task> tasks = taskService.getAllTask();
        List<TaskResponseDto> taskResponseDtos = tasks.stream()
                .map(task -> new TaskResponseDto(task.getId(), task.getTitle(), task.isDone()))
                .collect(Collectors.toList());
        return createResponse("success", "전체 할 일이 조회되었습니다.", taskResponseDtos);
    }

    // 완료된 일만 조회
    @Operation(summary = "완료된 일 조회")
    @GetMapping("/completed")
    public ResponseEntity<Map<String, Object>> getCompletedTasks() {
        List<Task> tasks = taskService.getCompletedTask();
        List<TaskResponseDto> taskResponseDtos = tasks.stream()
                .map(task -> new TaskResponseDto(task.getId(), task.getTitle(), task.isDone()))
                .collect(Collectors.toList());
        return createResponse("success", "완료된 일이 조회되었습니다.", taskResponseDtos);
    }

    // 미완료된 일만 조회
    @Operation(summary = "미완료된 일 조회")
    @GetMapping("/inCompleted")
    public ResponseEntity<Map<String, Object>> getInCompletedTasks() {
        List<Task> tasks = taskService.getInCompletedTask();
        List<TaskResponseDto> taskResponseDtos = tasks.stream()
                .map(task -> new TaskResponseDto(task.getId(), task.getTitle(), task.isDone()))
                .collect(Collectors.toList());
        return createResponse("success", "미완료된 일이 조회되었습니다.", taskResponseDtos);
    }


    //할 일 수정
    @Operation(summary = "할 일 수정")
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateTask(@PathVariable Long id, @RequestBody TaskRequestDto dto){
        Optional<Task> task = taskRepository.findById(id);
        if (task.isPresent()) {
            taskService.updateTask(id, dto); // 작업이 성공적으로 이루어지면 응답
            return createResponse("success", "할 일이 수정 되었습니다.", null);
        } else {
            return createResponse("fail", "해당 할 일이 존재하지 않습니다.", null);
        }
    }

    //할 일 삭제
    @Operation(summary = "할 일 삭제")
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteTask(@PathVariable Long id){
        Optional<Task> task = taskRepository.findById(id);
        if (task.isPresent()) {
            taskService.deleteTask(id);
            return createResponse("success", "할 일이 삭제 되었습니다.", null);
        } else {
            return createResponse("fail", "해당 할 일이 존재하지 않습니다.", null);
        }
    }
}
