package com.example.devTimesheet.service;

import java.util.List;

import com.example.devTimesheet.dto.request.TeamRequest;
import com.example.devTimesheet.dto.respon.TeamRespon;

public interface TeamService {

    TeamRespon createTeam(TeamRequest teamRequest);

    TeamRespon getTeam(Integer id);

    List<TeamRespon> findAllTeam();

    TeamRespon updateTeam(Integer idTeam, TeamRequest request);

    void deleteTeam(Integer idTeam);
}
