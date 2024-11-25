package com.example.techeer_partners_api_session.controller;

import com.example.techeer_partners_api_session.Service.TaskService;
import com.example.techeer_partners_api_session.dto.TaskRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tasks") // 상위 경로
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> createTask (@RequestBody TaskRequestDto dto) {
        taskService.createTask(dto);
        Map<String, String> response = new HashMap<>();
        response.put("message", "할 일이 성공적으로 생성되었습니다.");
        return ResponseEntity.status(201).body(response);
    }

    // 1. 전체 할 일 조회
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllTasks() {
        List<TaskRequestDto> tasks = taskService.getAllTasks();

        // 응답 데이터 구성
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("status", "success");
        response.put("message", "모든 일이 조회되었습니다.");
        response.put("data", tasks);

        // 201 상태 코드와 함께 DTO 반환
        return ResponseEntity.status(201).body(response);
    }

    // 2. 완료된 할 일 조회
    @GetMapping("/completed") // 하위 경로
    public ResponseEntity<Map<String, Object>> getIsDoneTasks() {
        List<TaskRequestDto> isDoneTasks = taskService.getTasksByIsDoneStatus(true);

        // 응답 데이터 구성
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("status", "success");
        response.put("message", "완료 된 일이 조회되었습니다.");
        response.put("data", isDoneTasks);

        // 201 상태 코드와 함께 DTO 반환
        return ResponseEntity.ok(response);
    }

    // 3. 미완료된 할 일 조회
    @GetMapping("/uncompleted") // 하위 경로
    public ResponseEntity<Map<String, Object>> getNotIsDoneTasks() {
        List<TaskRequestDto> notIsDoneTasks = taskService.getTasksByIsDoneStatus(false);

        // 응답 데이터 구성
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("status", "success");
        response.put("message", "미완료 된 일이 조회되었습니다.");
        response.put("data", notIsDoneTasks);

        // 201 상태 코드와 함께 DTO 반환
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateTask(@PathVariable Long id, @RequestBody TaskRequestDto dto) {
        TaskRequestDto updatedTask = taskService.updateTask(id, dto);

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("status", "success");
        response.put("message", "할 일이 성공적으로 수정되었습니다.");
        response.put("data", updatedTask);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "할 일이 성공적으로 삭제되었습니다.");
        return ResponseEntity.ok(response);
    }
}
