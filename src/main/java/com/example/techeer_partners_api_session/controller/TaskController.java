package com.example.techeer_partners_api_session.controller;

import com.example.techeer_partners_api_session.dto.TaskRequestDto;
import com.example.techeer_partners_api_session.dto.TaskResponseDto;
import com.example.techeer_partners_api_session.repository.TaskRepository;
import com.example.techeer_partners_api_session.service.TaskService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> createTask(@RequestBody TaskRequestDto dto) {
        taskService.createTask(dto);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Task created");
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping
    public ResponseEntity<List<TaskResponseDto>> getAllTasks() {
        List<TaskResponseDto> tasks = taskService.getAllTasks()
                .stream()
                .map(TaskResponseDto::fromEntity)
                .toList();
        return ResponseEntity.status(200).body(tasks);
    }
    @GetMapping("/completed")
    public ResponseEntity<List<TaskResponseDto>> getCompletedTasks() {
        List<TaskResponseDto> tasks = taskService.getCompletedTasks()
                .stream()
                .map(TaskResponseDto::fromEntity)
                .toList();
        return ResponseEntity.status(200).body(tasks);
    }
    @GetMapping("/incompleted")
    public ResponseEntity<List<TaskResponseDto>> getIncompletedTasks() {
        List<TaskResponseDto> tasks = taskService.getIncompletedTasks()
                .stream()
                .map(TaskResponseDto::fromEntity)
                .toList();
        return ResponseEntity.status(200).body(tasks);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, String>> updateTask(
            @PathVariable Long id,
            @RequestBody TaskRequestDto dto) {
        taskService.updateTask(id, dto);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Task updated");
        return ResponseEntity.status(201).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Task deleted");
        return ResponseEntity.ok(response);
    }
}
