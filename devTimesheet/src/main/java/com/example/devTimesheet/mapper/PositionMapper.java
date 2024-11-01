package com.example.devTimesheet.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.example.devTimesheet.dto.request.PositionRequest;
import com.example.devTimesheet.dto.respon.PositionRespon;
import com.example.devTimesheet.entity.Position;

@Mapper(componentModel = "spring")
public interface PositionMapper {
    Position toPosition(PositionRequest request);

    void updatePosition(@MappingTarget Position position, PositionRequest request);

    PositionRespon toPositionRespon(Position position);
}
