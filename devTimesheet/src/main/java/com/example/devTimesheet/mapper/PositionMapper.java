package com.example.devTimesheet.mapper;

import com.example.devTimesheet.dto.request.ClientRequest;
import com.example.devTimesheet.dto.request.PositionRequest;
import com.example.devTimesheet.dto.respon.ClientRespon;
import com.example.devTimesheet.dto.respon.PositionRespon;
import com.example.devTimesheet.entity.Client;
import com.example.devTimesheet.entity.Position;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PositionMapper {
    Position toPosition(PositionRequest request);
    void updatePosition(@MappingTarget Position position, PositionRequest request);
    PositionRespon toPositionRespon(Position position);
}
