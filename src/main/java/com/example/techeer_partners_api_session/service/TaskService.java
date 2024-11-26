package com.example.techeer_partners_api_session.service;

import com.example.techeer_partners_api_session.dto.TaskRequestDto;
import com.example.techeer_partners_api_session.entity.Task;
import com.example.techeer_partners_api_session.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
    // post service
    public void createTask(TaskRequestDto dto) {
        Task task = new Task(dto.getTitle());  // Task 생성 시 title을 넘겨줌
        task.setDone(dto.isDone());  // isDone 값을 설정
        taskRepository.save(task);  // 엔티티 저장
    }

    // get service
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }
    public List<Task> getCompletedTasks() {
        return taskRepository.findByIsDone(true);
    }
    public List<Task> getIncompletedTasks() {
        return taskRepository.findByIsDone(false);
    }

    // put service
    public void updateTask(Long id, TaskRequestDto dto) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Task not found"));
        task.update(dto.getTitle(), dto.isDone());
        taskRepository.save(task);
    }

    // delete service
    public void deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new IllegalArgumentException("Task not found");
        }
        taskRepository.deleteById(id);
    }


}

