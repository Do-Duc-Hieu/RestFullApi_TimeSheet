package com.example.devTimesheet.mapper;

import com.example.devTimesheet.dto.request.UserPositionRequest;
import com.example.devTimesheet.dto.respon.UserPositionRespon;
import com.example.devTimesheet.entity.UserPosition;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserPositionMapper {
    UserPosition toUserPosition(UserPositionRequest request);
    void updateUserPosition(@MappingTarget UserPosition userPosition, UserPositionRequest request);

    @Mapping(target = "userRespon", source = "user")
    @Mapping(target = "positionRespon", source = "position")
    UserPositionRespon toUserPositionRespon(UserPosition userPosition);
}
