package com.example.techeer_partners_api_session.service;

import com.example.techeer_partners_api_session.dto.TaskRequestDto;
import com.example.techeer_partners_api_session.dto.TaskUpdateDto;
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

    public void createTask(TaskRequestDto dto) {
        Task task = new Task(dto.getTitle());
        taskRepository.save(task);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public List<Task> getDoneTasks() {
        return taskRepository.findByIsDoneTrue();
    }

    public List<Task> getNotDoneTasks() {
        return taskRepository.findByIsDoneFalse();
    }

    public Task updateTask(Long id, TaskUpdateDto dto) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 ID를 가진 할 일이 존재하지 않습니다." + id));

        if (dto.getTitle() != null) {
            task.setTitle(dto.getTitle());
        }

        if (dto.getIsDone() != null) {
            task.setIsDone(dto.getIsDone());
        }
        return taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("해당 ID를 가진 할 일이 존재하지 않습니다." + id));

        taskRepository.delete(task);
    }
}
