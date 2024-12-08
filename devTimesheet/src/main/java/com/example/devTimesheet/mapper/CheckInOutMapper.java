package com.example.devTimesheet.mapper;

import com.example.devTimesheet.dto.respon.CheckInOutRespon;
import com.example.devTimesheet.entity.CheckInOut;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CheckInOutMapper {
    @Mapping(target = "userRespon", source = "user")
    CheckInOutRespon toCheckInOutRespon(CheckInOut checkInOut);
}
