package com.example.devTimesheet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.devTimesheet.entity.Punishment;

@Repository
public interface PunishmentRepository extends JpaRepository<Punishment, Integer> {}
