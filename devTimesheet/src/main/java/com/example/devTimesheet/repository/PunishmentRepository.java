package com.example.devTimesheet.repository;

import com.example.devTimesheet.entity.Punishment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PunishmentRepository extends JpaRepository<Punishment, Integer> {
}
