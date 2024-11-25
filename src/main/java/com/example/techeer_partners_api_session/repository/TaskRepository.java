package com.example.techeer_partners_api_session.repository;

import com.example.techeer_partners_api_session.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByIsDone(boolean isDone); //완료 여부 필터링
}
