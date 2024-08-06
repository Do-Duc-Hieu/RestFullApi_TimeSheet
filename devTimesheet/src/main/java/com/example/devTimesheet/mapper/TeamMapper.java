package com.example.devTimesheet.mapper;

import com.example.devTimesheet.dto.request.ClientRequest;
import com.example.devTimesheet.dto.request.TeamRequest;
import com.example.devTimesheet.dto.respon.ClientRespon;
import com.example.devTimesheet.dto.respon.TeamRespon;
import com.example.devTimesheet.entity.Client;
import com.example.devTimesheet.entity.Team;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {UserPositionMapper.class})
public interface TeamMapper {
    Team toTeam(TeamRequest request);
    void updateTeam(@MappingTarget Team team, TeamRequest request);

    @Mapping(target = "userPositionRespons", source = "userPositions")
    TeamRespon toTeamRespon(Team team);
}
