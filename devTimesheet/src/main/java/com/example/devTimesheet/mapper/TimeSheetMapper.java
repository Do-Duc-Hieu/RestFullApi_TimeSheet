package com.example.devTimesheet.mapper;

import com.example.devTimesheet.dto.request.TimeSheetRequest;
import com.example.devTimesheet.dto.respon.TimeSheetRespon;
import com.example.devTimesheet.entity.TimeSheet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TimeSheetMapper {
    TimeSheet toTimeSheet(TimeSheetRequest request);
    void updateTimeSheet(@MappingTarget TimeSheet timeSheet, TimeSheetRequest request);

    @Mapping(target = "userRespon", source = "user")
    @Mapping(target = "statusRespon", source = "status")
    @Mapping(target = "projectRespon", source = "project")
    TimeSheetRespon toTimeSheetRespon(TimeSheet timeSheet);
}
