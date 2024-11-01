package com.example.devTimesheet.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.devTimesheet.entity.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Integer> {
    Optional<Team> findTeamByNameTeam(String nameTeam);
}
