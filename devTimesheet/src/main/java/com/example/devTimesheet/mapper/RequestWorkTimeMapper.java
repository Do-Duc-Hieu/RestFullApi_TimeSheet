package com.example.devTimesheet.mapper;

import org.mapstruct.Mapper;

import com.example.devTimesheet.dto.request.RequestWorkTimeRequest;
import com.example.devTimesheet.dto.respon.RequestWorkTimeRespon;
import com.example.devTimesheet.entity.RequestWorkTime;

@Mapper(componentModel = "spring")
public interface RequestWorkTimeMapper {
    RequestWorkTime toRequestWorkTime(RequestWorkTimeRequest request);

    RequestWorkTimeRespon toRequestWorkTimeRespon(RequestWorkTime requestWorkTime);
}
