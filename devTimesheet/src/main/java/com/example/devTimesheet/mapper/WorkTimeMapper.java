package com.example.devTimesheet.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.example.devTimesheet.dto.request.WorkTimeRequest;
import com.example.devTimesheet.dto.respon.WorkTimeRespon;
import com.example.devTimesheet.entity.WorkTime;

@Mapper(componentModel = "spring")
public interface WorkTimeMapper {
    WorkTime toWorkTime(WorkTimeRequest request);

    void updateWorkTime(@MappingTarget WorkTime workTime, WorkTimeRequest request);

    WorkTimeRespon toWorkTimeRespon(WorkTime workTime);
}
