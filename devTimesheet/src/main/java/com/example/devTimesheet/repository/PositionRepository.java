package com.example.devTimesheet.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.devTimesheet.entity.Position;

@Repository
public interface PositionRepository extends JpaRepository<Position, Integer> {
    Optional<Position> findPositionByNamePosition(String namePosition);
}
