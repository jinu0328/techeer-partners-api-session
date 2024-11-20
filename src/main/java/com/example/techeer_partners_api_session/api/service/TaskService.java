package com.example.techeer_partners_api_session.api.service;

import com.example.techeer_partners_api_session.api.domain.dto.request.TaskUpdateRequest;
import com.example.techeer_partners_api_session.api.domain.dto.response.TaskGetCompletedResponse;
import com.example.techeer_partners_api_session.api.domain.entity.Task;
import com.example.techeer_partners_api_session.api.repository.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    @Transactional
    public Long createTask(Task task) {
        return taskRepository.save(task).getId();
    }

    @Transactional
    public Task modifyTask(Long taskId, TaskUpdateRequest dto) {
        Task foundTask = taskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("수정하려는 테스크를 찾지 못했습니다."));
        foundTask.updateTask(dto.getTitle(), dto.isDone());
        return foundTask;
    }

    @Transactional
    public void deleteTask(Long taskId) {
        try {
            taskRepository.deleteById(taskId);
        } catch (Exception e) {
            throw new EntityNotFoundException("삭제하려는 테스크를 찾지 못했습니다.");
        }
    }

    public Page<Task> getCompletedTasks(Pageable pageable) {
        return taskRepository.findByIsDoneTrue(pageable);
    }

    public Page<Task> getIncompleteTasks(Pageable pageable) {
        return taskRepository.findByIsDoneFalse(pageable);
    }

    public Page<Task> getAllTasks(Pageable pageable) {
        return taskRepository.findAll(pageable);
    }
}
