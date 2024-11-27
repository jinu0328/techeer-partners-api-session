package com.example.techeer_partners_api_session.controller;

import com.example.techeer_partners_api_session.dto.TaskRequestDto;
import com.example.techeer_partners_api_session.dto.TaskUpdateDto;
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

    // 할 일 생성
    @PostMapping
    public ResponseEntity<Map<String, String>> createTask (@RequestBody TaskRequestDto dto) {
        taskService.createTask(dto);

        Map<String, String> response = new HashMap<>();
        response.put("status", "success");
        response.put("message" , "할 일이 생성되었습니다.");

        return ResponseEntity.status(201).body(response);
    }


    @GetMapping("/completed")
    public ResponseEntity<Map<String, Object>> getCompletedTasks() {
        List<Task> completedTasks = taskService.getDoneTasks();

        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "완료 된 일이 조회되었습니다.");
        response.put("data", completedTasks);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/incompleted")
    public ResponseEntity<Map<String, Object>> getInCompletedTasks() {
        List<Task> inCompletedTasks = taskService.getNotDoneTasks();

        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "미완료 된 일이 조회되었습니다.");
        response.put("data", inCompletedTasks);

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateTask(@PathVariable Long id, @RequestBody TaskUpdateDto dto) {
        Task updateTask = taskService.updateTask(id, dto);

        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("message" , "할 일이 수정되었습니다..");

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delteTask(@PathVariable  Long id) {
        taskService.deleteTask(id);

        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("message" , "할 일이 삭제되었습니다.");

        return ResponseEntity.ok(response);
    }

}
