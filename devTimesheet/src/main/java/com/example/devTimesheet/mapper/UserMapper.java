package com.example.devTimesheet.mapper;

import com.example.devTimesheet.dto.request.UserRequest;
import com.example.devTimesheet.dto.respon.UserRespon;
import com.example.devTimesheet.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUse(UserRequest request);
    void updateUser(@MappingTarget User user, UserRequest request);
    UserRespon toUserRespon(User user);
}
