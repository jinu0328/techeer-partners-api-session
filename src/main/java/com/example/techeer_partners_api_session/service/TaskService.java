package com.example.techeer_partners_api_session.service;

import com.example.techeer_partners_api_session.dto.TaskRequestDto;
import com.example.techeer_partners_api_session.entity.Task;
import com.example.techeer_partners_api_session.repository.TaskRepository;
import org.hibernate.boot.model.naming.IllegalIdentifierException;
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

    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 id가 존재하지 않습니다." + id));
    }

    public void deleteTask(Long id) {
        Task task = getTaskById(id);
        taskRepository.delete(task);
    }

    public void updateTask(Long id, TaskRequestDto dto) {
        Task task = getTaskById(id);
        task.setTitle(dto.getTitle());
        task.setDone(dto.isDone());
        taskRepository.save(task);
    }

    public void completeTask(Long id) {
        Task task = getTaskById(id);
        task.setDone(true);
        taskRepository.save(task);
    }
}
