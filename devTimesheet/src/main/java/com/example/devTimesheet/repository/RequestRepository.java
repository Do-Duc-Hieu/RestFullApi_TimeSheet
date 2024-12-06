package com.example.devTimesheet.repository;

import com.example.devTimesheet.entity.Request;
import com.example.devTimesheet.entity.RequestType;
import com.example.devTimesheet.entity.TimeSheet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Integer> {
    @Query("SELECT r FROM Request r " +
            "WHERE r.user.username = :username " +
            "AND r.date BETWEEN :startDate AND :endDate " +
            "AND NOT EXISTS (SELECT 1 FROM RequestWorkTime rw WHERE rw.id = r.requestType.id)")
    List<Request> findByUsernameAndDateRangeExcludingWorkTime(
            @Param("username") String username,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);

    @Query("SELECT r FROM Request r " +
            "WHERE r.user.username = :username " +
            "AND r.date BETWEEN :startDate AND :endDate " +
            "AND TYPE(r.requestType) = RequestWorkTime")
    List<Request> findAllWorkTimeRequestsByUserAndDateRange(
            @Param("username") String username,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);

    @Query("SELECT r FROM Request r " +
            "JOIN r.status s " +
            "WHERE r.user.username = :username " +
            "AND (s.nameStatus = :statusName) "+
            "AND TYPE(r.requestType) = RequestWorkTime")
    List<Request> findAllWorkTimeRequestByUserAndStatusName(@Param("username") String username,
                                                         @Param("statusName") String statusName);

    @Query("SELECT r FROM Request r " +
            "JOIN r.status s " +
            "JOIN r.user u " +
            "JOIN UserPosition up ON up.user = u " +
            "JOIN Team t ON up.team = t " +
            "JOIN Project p ON p.team = t " +
            "WHERE u.username = :username " +
            "AND (:statusName IS NULL OR s.nameStatus = :statusName) " +
            "AND p.nameProject IN :projectNames "+
            "AND TYPE(r.requestType) = RequestWorkTime")
    List<Request> findAllWorkTimeRequestsByUserAndPending(
            @Param("username") String username,
            @Param("statusName") String statusName,
            @Param("projectNames") List<String> projectNames);

    @Query("SELECT r FROM Request r " +
            "JOIN r.status s " +
            "JOIN r.user u " +
            "JOIN UserPosition up ON up.user = u " +
            "JOIN Team t ON up.team = t " +
            "JOIN Project p ON p.team = t " +
            "LEFT JOIN r.requestType rt " +
            "WHERE (:projectName IS NULL OR p.nameProject = :projectName) " +
            "AND (:statusName IS NULL OR s.nameStatus = :statusName) " +
            "AND (:userEmail IS NULL OR u.email LIKE %:userEmail%) " +
            "AND (:requestTypeClass IS NULL OR (TYPE(r.requestType) = :requestTypeClass) )"+
            "AND (:startDate IS NULL OR r.date >= :startDate) " +
            "AND (:endDate IS NULL OR r.date <= :endDate)" +
            "AND NOT EXISTS (SELECT 1 FROM RequestWorkTime rw WHERE rw.id = r.requestType.id)")
    List<Request> searchRequestsByProject(@Param("projectName") String projectName,
                                         @Param("statusName") String statusName,
                                         @Param("userEmail") String userEmail,
                                         @Param("requestTypeClass") Class<? extends RequestType> requestTypeClass,
                                         @Param("startDate") LocalDate startDate,
                                         @Param("endDate") LocalDate endDate);

    @Query("SELECT r FROM Request r " +
            "JOIN r.status s " +
            "JOIN r.user u " +
            "JOIN u.branch b " +
            "LEFT JOIN r.requestType rt " +
            "WHERE (:branchName IS NULL OR b.nameBranch = :branchName) " +
            "AND (:statusName IS NULL OR s.nameStatus = :statusName) " +
            "AND (:userEmail IS NULL OR u.email LIKE %:userEmail%) " +
            "AND (:requestTypeClass IS NULL OR TYPE(r.requestType) = :requestTypeClass )"+
            "AND (:startDate IS NULL OR r.date >= :startDate) " +
            "AND (:endDate IS NULL OR r.date <= :endDate)" +
            "AND NOT EXISTS (SELECT 1 FROM RequestWorkTime rw WHERE rw.id = r.requestType.id)")
    List<Request> searchRequestsByBranch(@Param("branchName") String branchName,
                                         @Param("statusName") String statusName,
                                         @Param("userEmail") String userEmail,
                                         @Param("requestTypeClass") Class<? extends RequestType> requestTypeClass,
                                         @Param("startDate") LocalDate startDate,
                                         @Param("endDate") LocalDate endDate);
}
