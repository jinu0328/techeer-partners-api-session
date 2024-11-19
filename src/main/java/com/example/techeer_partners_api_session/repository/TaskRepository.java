package com.example.techeer_partners_api_session.repository;

import com.example.techeer_partners_api_session.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
