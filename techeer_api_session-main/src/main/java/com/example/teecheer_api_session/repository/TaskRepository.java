package com.example.teecheer_api_session.repository;

import com.example.teecheer_api_session.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByIsDone(boolean isDone); // 완료/미완료 작업 조회
}