package com.example.devTimesheet.repository;

import com.example.devTimesheet.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {
    boolean existsByNameProject(String NameProject);
    Optional<Project> findProjectByNameProject(String nameProject);
    @Query("SELECT p FROM Project p " +
            "JOIN p.team t " +
            "JOIN UserPosition up ON up.team = t " +
            "JOIN up.user u " +
            "WHERE u.id = :userId")
    List<Project> findAllProjectsByUserId(@Param("userId") int userId);
}
