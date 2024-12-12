package com.example.devTimesheet.mapper;


import com.example.devTimesheet.dto.request.StatusRequest;
import com.example.devTimesheet.dto.respon.StatusRespon;
import com.example.devTimesheet.entity.Status;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface StatusMapper {
    Status toStatus(StatusRequest request);
    void updateStatus(@MappingTarget Status status, StatusRequest request);
    StatusRespon toStatusRespon(Status status);
}
