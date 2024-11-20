package com.example.techeer_partners_api_session.controller;

import com.example.techeer_partners_api_session.dto.TaskRequestDto;
import com.example.techeer_partners_api_session.dto.TaskResponseDto;
import com.example.techeer_partners_api_session.entity.Task;
import com.example.techeer_partners_api_session.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> createTask(@RequestBody TaskRequestDto dto) {
        taskService.createTask(dto);
        Map<String, String> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "할 일이 성공적으로 생성되었습니다.");
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "전체 할 일이 조회되었습니다.");
        response.put("data", tasks);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/completed")
    public ResponseEntity<Map<String, Object>> getCompletedTasks() {
        List<TaskResponseDto> completeTasks = taskService.getCompletedTasks();
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "완료된 일이 조회되었습니다.");
        response.put("data", completeTasks);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/incomplete")
    public ResponseEntity<Map<String, Object>> getIncompleteTasks() {
        List<TaskResponseDto> incompleteTasks = taskService.getIncompleteTasks();
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "미완료 된 일이 조회되었습니다.");
        response.put("data", incompleteTasks);
        return ResponseEntity.ok(response);
    }



    @PatchMapping("/{id}")
    public ResponseEntity<Map<String, String>> updateTask(
            @PathVariable Long id,
            @RequestBody TaskRequestDto dto
    ) {
        taskService.updateTask(id, dto);
        Map<String, String> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "할 일이 수정되었습니다.");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        Map<String, String> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "할 일이 삭제되었습니다.");
        return ResponseEntity.ok(response);
    }



}
