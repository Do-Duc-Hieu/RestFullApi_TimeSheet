package com.example.devTimesheet.service;

import com.example.devTimesheet.dto.request.TeamRequest;
import com.example.devTimesheet.dto.respon.TeamRespon;
import com.example.devTimesheet.entity.Team;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface TeamService {

    TeamRespon createTeam(TeamRequest teamRequest);

    TeamRespon getTeam(Integer id);

    List<TeamRespon> findAllTeam();

    TeamRespon updateTeam(Integer idTeam, TeamRequest request);

    void deleteTeam(Integer idTeam);
}
