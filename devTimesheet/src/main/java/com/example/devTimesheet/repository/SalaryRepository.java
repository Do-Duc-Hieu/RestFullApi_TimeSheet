package com.example.devTimesheet.repository;
import com.example.devTimesheet.entity.Salary;
import com.example.devTimesheet.entity.TimeSheet;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

@Repository
public interface SalaryRepository extends JpaRepository<Salary, Integer>{
    @Modifying
    @Transactional
    @Query("DELETE FROM Salary s WHERE s.time = :time")
    void deleteByTime(@Param("time") YearMonth time);

    @Query("SELECT s FROM Salary s WHERE s.user.username = :username")
    List<Salary> findByUsername
            (@Param("username") String username);
}
