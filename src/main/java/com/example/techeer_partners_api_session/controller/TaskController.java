package com.example.techeer_partners_api_session.controller;

import com.example.techeer_partners_api_session.dto.TaskRequestDto;
import com.example.techeer_partners_api_session.entity.Task;
import com.example.techeer_partners_api_session.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tasks")
@CrossOrigin(origins = "http://localhost:3000")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> createTask(@RequestBody TaskRequestDto dto) {
        taskService.createTask(dto);
        Map<String, String> response = new HashMap<>();
        response.put("message", "할 일이 성공적으로 생성되었습니다.");
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping
    public ResponseEntity<List<Task>> getTasks() {
        List<Task> tasks = taskService.getTasks();
        return ResponseEntity.status(200).body(tasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        Task task = taskService.getTaskById(id);
        return ResponseEntity.status(200).body(task);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "할 일이 성공적으로 삭제되었습니다.");
        return ResponseEntity.status(201).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, String>> updateTask(@PathVariable Long id, @RequestBody TaskRequestDto dto) {
        taskService.updateTask(id, dto);
        Map<String, String> response = new HashMap<>();
        response.put("message", "할 일이 수정되었습니다.");
        return ResponseEntity.status(201).body(response);
    }

    @PatchMapping("/{id}/complete")
    public ResponseEntity<Map<String, String>> completeTask(@PathVariable Long id) {
        taskService.completeTask(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "할 일이 완료되었습니다.");
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping("/completed")
    public ResponseEntity<List<Task>> getCompletedTasks() {
        List<Task> tasks = taskService.getTasksByIsDone(true);
        return ResponseEntity.status(200).body(tasks);
    }

    @GetMapping("/not-completed")
    public ResponseEntity<List<Task>> getNotCompletedTasks() {
        List<Task> tasks = taskService.getTasksByIsDone(false);
        return ResponseEntity.status(200).body(tasks);
    }
}
