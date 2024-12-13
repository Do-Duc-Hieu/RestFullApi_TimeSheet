package com.example.devTimesheet.mapper;

import com.example.devTimesheet.dto.request.RequestOffRequest;
import com.example.devTimesheet.dto.respon.RequestOffRespon;
import com.example.devTimesheet.entity.RequestOff;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {LeaveTypeMapper.class})
public interface RequestOffMapper {
    @Mapping(target = "leaveType", source = "leaveTypeRequest")
    RequestOff toRequestOff(RequestOffRequest request);
    RequestOffRespon toRequestOffRespon(RequestOff requestOff);
}
