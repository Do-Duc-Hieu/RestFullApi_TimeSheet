package com.example.devTimesheet.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.devTimesheet.entity.Status;

@Repository
public interface StatusRepository extends JpaRepository<Status, Integer> {
    Optional<Status> findStatusByNameStatus(String nameStatus);
}
