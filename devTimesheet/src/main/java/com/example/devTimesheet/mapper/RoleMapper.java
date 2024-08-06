package com.example.devTimesheet.mapper;

import com.example.devTimesheet.dto.request.RoleRequest;
import com.example.devTimesheet.dto.request.UserRequest;
import com.example.devTimesheet.dto.respon.RoleRespon;
import com.example.devTimesheet.entity.Role;
import com.example.devTimesheet.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
@Mapper(componentModel = "spring")
public interface RoleMapper {
    Role toRole(RoleRequest request);
    void updateRole(@MappingTarget Role role, RoleRequest request);

    @Mapping(target = "permissionRespons", source = "permissions")
    @Mapping(target = "userRespons", source = "users")
    RoleRespon toRoleRespon(Role role);
}
