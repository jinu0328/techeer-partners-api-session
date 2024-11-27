package com.example.techeer_partners_api_session.service;

import com.example.techeer_partners_api_session.dto.TaskRequestDto;
import com.example.techeer_partners_api_session.dto.TaskResponseDto;
import com.example.techeer_partners_api_session.entity.Task;
import com.example.techeer_partners_api_session.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void createTask(TaskRequestDto dto) {
        Task task = new Task(dto.getTitle());
        taskRepository.save(task);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public List<TaskResponseDto> getCompletedTasks() {
        return taskRepository.findAll().stream()
                .filter(task -> task.isDone()) // 완료된 항목 필터링
                .map(task -> new TaskResponseDto(task.getId(), task.getTitle(), task.isDone())) // Task -> TaskResponseDto 변환
                .collect(Collectors.toList());
    }

    public List<TaskResponseDto> getIncompleteTasks() {
        return taskRepository.findAll().stream()
                .filter(task -> !task.isDone()) // 완료되지 않은 항목 필터링
                .map(task -> new TaskResponseDto(task.getId(), task.getTitle(), task.isDone())) // Task -> TaskResponseDto 변환
                .collect(Collectors.toList());
    }



    public void updateTask(Long id, TaskRequestDto dto) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Task not found"));
        task.setTitle(dto.getTitle());
        task.setDone(dto.isDone());
        taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new IllegalArgumentException("Task not found");
        }
        taskRepository.deleteById(id);
    }


}
