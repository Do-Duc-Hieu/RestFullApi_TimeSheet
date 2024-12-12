package com.example.devTimesheet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.devTimesheet.entity.RequestType;

@Repository
public interface RequestTypeRepository extends JpaRepository<RequestType, Integer> {}
