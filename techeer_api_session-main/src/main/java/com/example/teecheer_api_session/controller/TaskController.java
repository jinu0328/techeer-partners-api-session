package com.example.teecheer_api_session.controller;

import com.example.teecheer_api_session.model.Task;
import com.example.teecheer_api_session.model.TaskDTO;
import com.example.teecheer_api_session.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }
    @Operation(summary = "새로운 할 일을 생성")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "할 일이 성공적으로 생성됨"),
            @ApiResponse(responseCode = "400", description = "유효성 검사 실패")
    })
    @PostMapping // @Valid 추가
    public ResponseEntity<Map<String, String>> createTask(@Valid @RequestBody Task task) {
        // Service 호출을 통해 작업 저장
        taskService.createTask(task);

        // json 응답 본문 생성
        Map<String, String> response = new HashMap<>();
        response.put("data", null);
        response.put("status", "success");
        response.put("message", "할 일이 생성되었습니다.");

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "모든 할 일을 조회")
    @ApiResponse(responseCode = "200", description = "할 일이 성공적으로 조회됨")
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllTasks() {
        // Task -> TaskDTO로 변환
        List<TaskDTO> taskDTOs = taskService.getAllTasks()
                .stream()
                .map(task -> new TaskDTO(
                        task.getId(),
                        task.getTitle(),
                        task.getDeadline(),
                        task.getIsPastDeadline(),
                        task.isDone()
                ))
                .collect(Collectors.toList());

        // 응답 구조 생성
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "모든 일이 조회되었습니다.");
        Map<String, Object> data = new HashMap<>();
        data.put("content", taskDTOs);
        response.put("data", data);

        return ResponseEntity.ok(response);
    }


    @GetMapping("/completed")
    public ResponseEntity<Map<String, Object>> getCompletedTasks() {
    List<TaskDTO> completedTasks = taskService.getCompletedTasks()
            .stream()
            .map(task -> new TaskDTO(
                    task.getId(),
                    task.getTitle(),
                    task.getDeadline(),
                    task.getIsPastDeadline(),
                    task.isDone()
            ))
            .collect(Collectors.toList());

    // 응답 구조 생성
    Map<String, Object> response = new HashMap<>();
    response.put("status", "success");
    response.put("message", "완료된 일이 조회되었습니다.");
    Map<String, Object> data = new HashMap<>();
    data.put("content", completedTasks);
    response.put("data", data);

    return ResponseEntity.ok(response);

    }

    @GetMapping("/incomplete")
    public ResponseEntity<Map<String, Object>> getIncompleteTasks() {
        List<TaskDTO> incompleteTasks = taskService.getIncompleteTasks()
                .stream()
                .map(task -> new TaskDTO(
                        task.getId(),
                        task.getTitle(),
                        task.getDeadline(),
                        task.getIsPastDeadline(),
                        task.isDone()
                ))
                .collect(Collectors.toList());

        // 응답 구조 생성
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "미완료된 일이 조회되었습니다.");
        Map<String, Object> data = new HashMap<>();
        data.put("content", incompleteTasks);
        response.put("data", data);

        return ResponseEntity.ok(response);
    }


    @Operation(summary = "수정")
    @ApiResponse(responseCode = "200", description = "성공적으로 수정됨")
    @PutMapping("/{id}") // @Valid 추가
    public ResponseEntity<Map<String, Object>> updateTask(@PathVariable("id") Long id,@Valid @RequestBody Task updatedTask) {
        taskService.updateTask(id, updatedTask); // 서비스 호출

        // 응답 생성
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "할 일이 수정되었습니다.");
        response.put("data", null);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "삭제")
    @ApiResponse(responseCode = "200", description = "성공적으로 삭제됨")
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteTask(@PathVariable("id") Long id) {
        taskService.deleteTask(id);

        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "할 일이 삭제되었습니다.");
        response.put("data", null);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
