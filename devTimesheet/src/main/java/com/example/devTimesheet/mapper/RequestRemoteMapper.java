package com.example.devTimesheet.mapper;

import org.mapstruct.Mapper;

import com.example.devTimesheet.dto.request.RequestRemoteRequest;
import com.example.devTimesheet.dto.respon.RequestRemoteRespon;
import com.example.devTimesheet.entity.RequestRemote;

@Mapper(componentModel = "spring")
public interface RequestRemoteMapper {
    RequestRemote toRequestRemote(RequestRemoteRequest request);

    RequestRemoteRespon toRequestRemoteRespon(RequestRemote requestRemote);
}
