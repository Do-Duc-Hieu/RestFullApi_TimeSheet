package com.example.devTimesheet.service.impl;

import com.example.devTimesheet.dto.request.TeamRequest;
import com.example.devTimesheet.dto.respon.TeamRespon;
import com.example.devTimesheet.entity.Position;
import com.example.devTimesheet.entity.Team;
import com.example.devTimesheet.entity.User;
import com.example.devTimesheet.entity.UserPosition;
import com.example.devTimesheet.exception.AppException;
import com.example.devTimesheet.exception.ErrorCode;
import com.example.devTimesheet.mapper.TeamMapper;
import com.example.devTimesheet.mapper.UserPositionMapper;
import com.example.devTimesheet.repository.PositionRepository;
import com.example.devTimesheet.repository.TeamRepository;
import com.example.devTimesheet.repository.UserPositionRepository;
import com.example.devTimesheet.repository.UserRepository;
import com.example.devTimesheet.service.TeamService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TeamServiceImpl implements TeamService {
    TeamRepository teamRepository;
    UserRepository userRepository;
    PositionRepository positionRepository;
    UserPositionRepository userPositionRepository;
    TeamMapper teamMapper;
    UserPositionMapper userPositionMapper;

    @Override
    public TeamRespon createTeam(TeamRequest request){
        Team team = teamMapper.toTeam(request);
        List<UserPosition> userPositions = new ArrayList<>();
        request.getUserPositions().forEach(
                userPosition -> {
                    User user = userRepository.findUserByUsername(userPosition.getUserName())
                            .orElseThrow(()-> new AppException(ErrorCode.USER_NOT_EXISTED));
                    Position position = positionRepository
                            .findPositionByNamePosition(userPosition.getNamePosition())
                            .orElseThrow(()-> new AppException(ErrorCode.USER_NOT_EXISTED));
                    userPositions.add(UserPosition.builder().user(user)
                            .position(position).team(team).build());
                }
        );
        team.setUserPositions(userPositions);
        teamRepository.save(team);
        return  teamMapper.toTeamRespon(team);
    }

    @Override
    public TeamRespon getTeam (Integer id){
        return teamMapper.toTeamRespon(teamRepository.findById(id)
                .orElseThrow(()-> new AppException(ErrorCode.USER_NOT_EXISTED)));
    }

    @Override
    public List<TeamRespon> findAllTeam(){
        List<TeamRespon> teamRespons = new ArrayList<>();
        List<Team> teams = teamRepository.findAll();
        teams.forEach(
                team -> teamRespons.add(teamMapper.toTeamRespon(team))

        );
        return teamRespons;
    }

    @Override
    public TeamRespon updateTeam(Integer idTeam, TeamRequest request) {
        Team team = teamRepository.findById(idTeam)
                .orElseThrow(()-> new RuntimeException("Team not found"));
        List<UserPosition> userPositions = new ArrayList<>();
        request.getUserPositions().forEach(
                userPosition -> {
                    User user = userRepository.findUserByUsername(userPosition.getUserName())
                            .orElseThrow(()-> new AppException(ErrorCode.USER_NOT_EXISTED));
                    Position position = positionRepository.findPositionByNamePosition(userPosition.getNamePosition())
                            .orElseThrow(()-> new AppException(ErrorCode.USER_NOT_EXISTED));
                    userPositions.add(UserPosition.builder().user(user)
                            .position(position).team(team).build());
                }
        );
        userPositionRepository.deleteByTeamId(team.getId());
        teamMapper.updateTeam(team, request);
        team.setUserPositions(userPositions);
        return teamMapper.toTeamRespon(teamRepository.save(team));
    }

    @Override
    public void deleteTeam(Integer idTeam){
        teamRepository.deleteById(idTeam);
    }
}
