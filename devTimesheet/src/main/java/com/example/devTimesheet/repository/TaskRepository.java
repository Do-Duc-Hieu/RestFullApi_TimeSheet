package com.example.devTimesheet.repository;

import com.example.devTimesheet.entity.Role;
import com.example.devTimesheet.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    boolean existsByNameTask(String nameTask);
    Optional<Task> findTaskByNameTask(String nameTask);
}
