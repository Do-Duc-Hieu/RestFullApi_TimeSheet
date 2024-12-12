package com.example.devTimesheet.mapper;

import org.mapstruct.Mapper;

import com.example.devTimesheet.dto.request.RequestLastRequest;
import com.example.devTimesheet.dto.respon.RequestLastRespon;
import com.example.devTimesheet.entity.RequestLast;

@Mapper(componentModel = "spring")
public interface RequestLastMapper {
    RequestLast toRequestLast(RequestLastRequest request);

    RequestLastRespon toRequestLastRespon(RequestLast requestLast);
}
