package com.example.devTimesheet.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.devTimesheet.entity.CheckInOut;

@Repository
public interface CheckInOutRepository extends JpaRepository<CheckInOut, Integer> {
    @Query("SELECT c FROM CheckInOut c WHERE c.user.username = :username AND c.date = :date")
    List<CheckInOut> findByUsernameAndDate(@Param("username") String username, @Param("date") LocalDate date);

    @Query("SELECT c FROM CheckInOut c WHERE c.user.id = :userId AND c.date = :date")
    List<CheckInOut> findByUserIdAndDate(@Param("userId") int userId, @Param("date") LocalDate date);
}
