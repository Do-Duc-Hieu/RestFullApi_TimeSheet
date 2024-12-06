package com.example.devTimesheet.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.example.devTimesheet.dto.request.LeaveTypeRequest;
import com.example.devTimesheet.dto.respon.LeaveTypeRespon;
import com.example.devTimesheet.entity.LeaveType;

@Mapper(componentModel = "spring")
public interface LeaveTypeMapper {
    LeaveType toLeaveType(LeaveTypeRequest request);

    void updateLeaveType(@MappingTarget LeaveType leaveType, LeaveTypeRequest request);

    LeaveTypeRespon toLeaveTypeRespon(LeaveType leaveType);
}
