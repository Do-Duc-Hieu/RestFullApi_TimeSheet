package com.example.devTimesheet.mapper;


import com.example.devTimesheet.dto.request.LeaveTypeRequest;
import com.example.devTimesheet.dto.respon.LeaveTypeRespon;
import com.example.devTimesheet.entity.LeaveType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface LeaveTypeMapper {
    LeaveType toLeaveType(LeaveTypeRequest request);
    void updateLeaveType(@MappingTarget LeaveType leaveType, LeaveTypeRequest request);
    LeaveTypeRespon toLeaveTypeRespon(LeaveType leaveType);
}
