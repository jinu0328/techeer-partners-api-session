package com.example.techeer_partners_api_session.api.repository;

import com.example.techeer_partners_api_session.api.domain.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    Page<Task> findByIsDoneTrue(Pageable pageable);

    Page<Task> findByIsDoneFalse(Pageable pageable);
}
