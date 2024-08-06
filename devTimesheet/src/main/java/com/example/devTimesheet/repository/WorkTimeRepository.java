package com.example.devTimesheet.repository;

import com.example.devTimesheet.entity.WorkTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.List;

@Repository
public interface WorkTimeRepository extends JpaRepository<WorkTime, Integer> {
    @Query("SELECT wt FROM WorkTime wt " +
            "WHERE wt.morningStartTime = :morningStartTime " +
            "AND wt.morningEndTime = :morningEndTime " +
            "AND wt.afternoonStartTime = :afternoonStartTime " +
            "AND wt.afternoonEndTime = :afternoonEndTime")
    List<WorkTime> findByTimes(
            @Param("morningStartTime") LocalTime morningStartTime,
            @Param("morningEndTime") LocalTime morningEndTime,
            @Param("afternoonStartTime") LocalTime afternoonStartTime,
            @Param("afternoonEndTime") LocalTime afternoonEndTime);
}
