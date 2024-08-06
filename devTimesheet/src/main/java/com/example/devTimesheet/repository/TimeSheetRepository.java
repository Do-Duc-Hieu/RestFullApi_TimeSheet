package com.example.devTimesheet.repository;

import com.example.devTimesheet.entity.TimeSheet;
import com.example.devTimesheet.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TimeSheetRepository extends JpaRepository<TimeSheet, Integer> {
    @Query("SELECT t FROM TimeSheet t WHERE t.user.username = :username AND t.date = :date")
    List<TimeSheet> findByUsernameAndDate
            (@Param("username") String username,
             @Param("date") LocalDate date);

    @Query("SELECT t FROM TimeSheet t WHERE t.user.username = :username AND t.date BETWEEN :startDate AND :endDate")
    List<TimeSheet> findByUsernameAndDateRange
            (@Param("username") String username,
             @Param("startDate") LocalDate startDate,
             @Param("endDate") LocalDate endDate);

    @Query("SELECT ts FROM TimeSheet ts " +
            "JOIN ts.project p " +
            "JOIN ts.user u " +
            "JOIN u.branch b " +
            "JOIN ts.status s " +
            "WHERE (:projectName IS NULL OR p.nameProject = :projectName) " +
            "AND (:userName IS NULL OR u.username LIKE %:userName%) " +
            "AND (:branchName IS NULL OR b.nameBranch = :branchName) " +
            "AND (:statusName IS NULL OR s.nameStatus = :statusName) " +
            "AND (:type IS NULL OR ts.type = :type)"+
            "AND (:startDate IS NULL OR ts.date >= :startDate) " +
            "AND (:endDate IS NULL OR ts.date <= :endDate)")
    List<TimeSheet> searchTimeSheets(@Param("projectName") String projectName,
                                     @Param("userName") String userName,
                                     @Param("branchName") String branchName,
                                     @Param("statusName") String statusName,
                                     @Param("type") String type,
                                     @Param("startDate") LocalDate startDate,
                                     @Param("endDate") LocalDate endDate);
}
