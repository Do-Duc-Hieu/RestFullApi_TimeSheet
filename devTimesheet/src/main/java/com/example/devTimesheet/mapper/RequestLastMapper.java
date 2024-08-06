package com.example.devTimesheet.mapper;

import com.example.devTimesheet.dto.request.RequestLastRequest;
import com.example.devTimesheet.dto.respon.RequestLastRespon;
import com.example.devTimesheet.entity.RequestLast;
import com.example.devTimesheet.entity.RequestRemote;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RequestLastMapper {
    RequestLast toRequestLast(RequestLastRequest request);
    RequestLastRespon toRequestLastRespon(RequestLast requestLast);
}
