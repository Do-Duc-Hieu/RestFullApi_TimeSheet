package com.example.devTimesheet.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.example.devTimesheet.dto.request.TeamRequest;
import com.example.devTimesheet.dto.respon.TeamRespon;
import com.example.devTimesheet.entity.Team;

@Mapper(
        componentModel = "spring",
        uses = {UserPositionMapper.class})
public interface TeamMapper {
    Team toTeam(TeamRequest request);

    void updateTeam(@MappingTarget Team team, TeamRequest request);

    @Mapping(target = "userPositionRespons", source = "userPositions")
    TeamRespon toTeamRespon(Team team);
}
