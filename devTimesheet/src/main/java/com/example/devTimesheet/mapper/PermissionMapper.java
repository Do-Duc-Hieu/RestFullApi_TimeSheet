package com.example.devTimesheet.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.example.devTimesheet.dto.request.PermissionRequest;
import com.example.devTimesheet.dto.respon.PermissionRespon;
import com.example.devTimesheet.entity.Permission;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);

    void updatePermission(@MappingTarget Permission permission, PermissionRequest request);

    PermissionRespon toPermissionRespon(Permission permission);
}
