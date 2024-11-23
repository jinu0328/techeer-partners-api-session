package com.example.techeer_partners_api_session.domain.task.service;

import com.example.techeer_partners_api_session.domain.task.dto.TaskRequestDto;
import com.example.techeer_partners_api_session.domain.task.dto.TaskResponseDto;
import com.example.techeer_partners_api_session.domain.task.entity.Task;
import com.example.techeer_partners_api_session.domain.task.repository.TaskRepository;
import java.awt.print.Pageable;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // 할 일 생성
    public void createTask(TaskRequestDto dto) {
        Task task = new Task(dto.getTitle());
        taskRepository.save(task);
    }

    // 할 일 조회
    public List<TaskResponseDto> getTasksByIsDone(Boolean isDone) {
        List<Task> tasks;

        if (isDone == null) {
            tasks = taskRepository.findAll();
        }
        else {
            tasks = taskRepository.findByIsDone(isDone);
        }

        return tasks.stream()
                .map(Task::toDto)
                .toList();
    }


    // 할 일 수정
    public void updateTask(Long id, TaskRequestDto dto) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 할 일이 없습니다: " + id));
        task.update(dto.getTitle(), dto.isDone());
        taskRepository.save(task);
    }

    // 할 일 삭제
    public void deleteTask(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 할 일이 없습니다: " + id));
        taskRepository.delete(task);
    }



}
