package com.example.devTimesheet.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.example.devTimesheet.dto.request.UserUpdateRequest;
import com.example.devTimesheet.entity.User;

@Mapper(componentModel = "spring")
public interface UserUpdateMapper {
    User toUse(UserUpdateRequest request);

    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
