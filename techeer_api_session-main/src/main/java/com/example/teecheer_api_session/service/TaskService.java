package com.example.teecheer_api_session.service;

import com.example.teecheer_api_session.model.Task;
import com.example.teecheer_api_session.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public List<Task> getTasksByStatus(boolean isDone) {
        return taskRepository.findByIsDone(isDone);
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public Task updateTask(Long id, Task updatedTask) {
        Task task = taskRepository.findById(id).orElseThrow(()
                -> new RuntimeException("Task not found"));

        // 기존 필드 값을 유지하고, 새로운 값만 업데이트
        if (updatedTask.getTitle() != null) {
            task.setTitle(updatedTask.getTitle());
        }
        task.setIsDone(updatedTask.isDone());

        System.out.println("Updated Task: " + task);


        return taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public List<Task> getIncompleteTasks() {
        return taskRepository.findByIsDone(false);
    }

    public List<Task> getCompletedTasks() {
        return taskRepository.findByIsDone(true);
    }
}
