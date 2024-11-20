package com.example.techeer_partners_api_session.Service;

import com.example.techeer_partners_api_session.dto.TaskRequestDto;
import com.example.techeer_partners_api_session.entity.Task;
import com.example.techeer_partners_api_session.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    public void createTask(TaskRequestDto dto){
        Task task = new Task(dto.getTitle()); // 할 일 객체 생성
        taskRepository.save(task); // 객체 저장
    }

    public List<TaskRequestDto> getAllTasks() { // 전체 할 일 조회
        List<Task> tasks = taskRepository.findAll();
        // Task 객체를 TaskRequestDto로 변환하여 반환
        return tasks.stream()
                .map(Task::toRequestDto)
                .toList();
    }

    public List<TaskRequestDto> getTasksByIsDoneStatus(boolean isDone) { // 할 일 상태 조회
        List<Task> tasks = taskRepository.findByIsDone(isDone);
        // Task 객체를 TaskRequestDto로 변환하여 반환
        return tasks.stream()
                .map(Task::toRequestDto)
                .toList();
    }

    public TaskRequestDto updateTask(Long id, TaskRequestDto dto) {
        // 1. Task 찾기
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 할 일이 존재하지 않습니다."));

        // 2. Task 수정
        if (dto.getTitle() != null) {
            task.setTitle(dto.getTitle());
        }
        task.setIsDone(dto.isDone()); // true/false 업데이트

        // 3. 저장 후 반환
        taskRepository.save(task);
        return task.toRequestDto();
    }
}
