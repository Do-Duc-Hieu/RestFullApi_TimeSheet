package com.example.devTimesheet.mapper;


import com.example.devTimesheet.dto.request.RequestWorkTimeRequest;
import com.example.devTimesheet.dto.respon.RequestWorkTimeRespon;
import com.example.devTimesheet.entity.RequestWorkTime;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RequestWorkTimeMapper {
    RequestWorkTime toRequestWorkTime(RequestWorkTimeRequest request);
    RequestWorkTimeRespon toRequestWorkTimeRespon(RequestWorkTime requestWorkTime);
}
