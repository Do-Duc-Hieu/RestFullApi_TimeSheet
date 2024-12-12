package com.example.devTimesheet.repository;

import com.example.devTimesheet.entity.Project;
import com.example.devTimesheet.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatusRepository extends JpaRepository<Status, Integer> {
    Optional<Status> findStatusByNameStatus(String nameStatus);
}
