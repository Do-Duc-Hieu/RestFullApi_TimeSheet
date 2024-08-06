package com.example.devTimesheet.repository;

import com.example.devTimesheet.entity.Permission;
import com.example.devTimesheet.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PositionRepository extends JpaRepository<Position, Integer> {
    Optional<Position> findPositionByNamePosition(String namePosition);
}
