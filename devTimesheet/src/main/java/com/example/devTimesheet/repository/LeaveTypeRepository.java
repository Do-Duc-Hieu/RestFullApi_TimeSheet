package com.example.devTimesheet.repository;

import com.example.devTimesheet.entity.LeaveType;
import com.example.devTimesheet.entity.Position;
import com.example.devTimesheet.entity.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LeaveTypeRepository extends JpaRepository<LeaveType, Integer> {
    boolean existsByNameType(String nameType);
    Optional<LeaveType> findLeaveTypeByNameType(String nameType);
}
