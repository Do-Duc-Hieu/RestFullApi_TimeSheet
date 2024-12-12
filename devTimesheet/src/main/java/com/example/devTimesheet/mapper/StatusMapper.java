package com.example.devTimesheet.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.example.devTimesheet.dto.request.StatusRequest;
import com.example.devTimesheet.dto.respon.StatusRespon;
import com.example.devTimesheet.entity.Status;

@Mapper(componentModel = "spring")
public interface StatusMapper {
    Status toStatus(StatusRequest request);

    void updateStatus(@MappingTarget Status status, StatusRequest request);

    StatusRespon toStatusRespon(Status status);
}
