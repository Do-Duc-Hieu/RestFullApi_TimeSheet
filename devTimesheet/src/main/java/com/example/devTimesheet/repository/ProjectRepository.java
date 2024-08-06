package com.example.devTimesheet.repository;

import com.example.devTimesheet.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {
    Optional<Project> findProjectByNameProject(String nameProject);
}
