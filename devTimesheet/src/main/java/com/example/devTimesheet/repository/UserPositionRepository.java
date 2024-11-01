package com.example.devTimesheet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.devTimesheet.entity.Team;
import com.example.devTimesheet.entity.UserPosition;

@Repository
public interface UserPositionRepository extends JpaRepository<UserPosition, Integer> {
    List<UserPosition> findByTeam(Team team);

    void deleteByTeamId(Integer idTeam);
}
