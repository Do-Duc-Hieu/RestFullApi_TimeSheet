package com.example.devTimesheet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.devTimesheet.entity.Punishment;

@Repository
public interface PunishmentRepository extends JpaRepository<Punishment, Integer> {
    @Query("SELECT p FROM Punishment p " + "WHERE p.user.id = :userId "
            + "AND FUNCTION('YEAR', p.date) = :year "
            + "AND FUNCTION('MONTH', p.date) = :month")
    List<Punishment> findByUserIdAndYearMonth(
            @Param("userId") int userId, @Param("year") int year, @Param("month") int month);
}
