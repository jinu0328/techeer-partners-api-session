package com.example.techeer_partners_api_session.service;

import com.example.techeer_partners_api_session.dto.TaskRequestDto;
import com.example.techeer_partners_api_session.entity.Task;
import com.example.techeer_partners_api_session.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    //할 일 생성
    public void createTask(TaskRequestDto dto){
        Task task = new Task(dto.getTitle());
        taskRepository.save(task);
    }

    //할 일 전체 목록 조회
    public List<Task> getAllTask(){
        return taskRepository.findAll();
    }

    //완료된 일 조회
    public List<Task> getCompletedTask(){
        return taskRepository.findByIsDone(true);
    }

    //미완료된 일 조회
    public List<Task> getInCompletedTask(){
        return taskRepository.findByIsDone(false);
    }

    //할 일 수정
    public void updateTask(Long id, TaskRequestDto dto){
        Optional<Task> task = taskRepository.findById(id);

        if(task.isPresent()){ //task에 값이 존재하는지 확인
            Task existingTask = task.get(); //존재하면 해당 task get

            //할 일 수정
            if(dto.getTitle() != null){
                existingTask.setTitle(dto.getTitle());
            }
            existingTask.setDone(dto.isDone());
            taskRepository.save(existingTask);
        }
    }

    //할 일 삭제
    public void deleteTask(Long id){
        taskRepository.deleteById(id);
    }
}

