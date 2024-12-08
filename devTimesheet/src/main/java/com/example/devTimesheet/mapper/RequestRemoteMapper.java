package com.example.devTimesheet.mapper;

import com.example.devTimesheet.dto.request.RequestRemoteRequest;
import com.example.devTimesheet.dto.respon.RequestRemoteRespon;
import com.example.devTimesheet.entity.RequestRemote;
import com.example.devTimesheet.entity.Status;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RequestRemoteMapper {
    RequestRemote toRequestRemote(RequestRemoteRequest request);
    RequestRemoteRespon toRequestRemoteRespon(RequestRemote requestRemote);
}
