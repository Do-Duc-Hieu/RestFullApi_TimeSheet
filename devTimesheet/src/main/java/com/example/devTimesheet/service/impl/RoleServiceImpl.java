package com.example.devTimesheet.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.devTimesheet.dto.request.RoleRequest;
import com.example.devTimesheet.dto.respon.RoleRespon;
import com.example.devTimesheet.entity.Permission;
import com.example.devTimesheet.entity.Role;
import com.example.devTimesheet.exception.AppException;
import com.example.devTimesheet.exception.ErrorCode;
import com.example.devTimesheet.mapper.RoleMapper;
import com.example.devTimesheet.repository.PermissionRepository;
import com.example.devTimesheet.repository.RoleRepository;
import com.example.devTimesheet.service.RoleService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleServiceImpl implements RoleService {
    RoleRepository roleRepository;
    PermissionRepository permissionRepository;
    RoleMapper roleMapper;

    @Override
    public RoleRespon createRole(RoleRequest request) {
        Role role = roleMapper.toRole(request);
        List<Permission> permissions = new ArrayList<>();
        request.getNamePermissions()
                .forEach(namePermission -> permissions.add((Permission) permissionRepository
                        .findPermissionByNamePermission(namePermission)
                        .orElseThrow(() -> new AppException(ErrorCode.USER_EXISTED))));
        role.setPermissions(permissions);
        roleRepository.save(role);
        return roleMapper.toRoleRespon(role);
    }

    @Override
    public RoleRespon getRole(Integer id) {
        return roleMapper.toRoleRespon(
                roleRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_EXISTED)));
    }

    @Override
    public List<RoleRespon> findAllRole() {
        List<RoleRespon> roleRespons = new ArrayList<>();
        List<Role> roles = roleRepository.findAll();
        roles.forEach(role -> roleRespons.add(roleMapper.toRoleRespon(role)));

        return roleRespons;
    }

    @Override
    public RoleRespon updateRole(Integer idRole, RoleRequest request) {
        Role role = roleRepository.findById(idRole).orElseThrow(() -> new RuntimeException("Role not found"));
        List<Permission> permissions = new ArrayList<>();
        request.getNamePermissions()
                .forEach(namePermission -> permissions.add((Permission) permissionRepository
                        .findPermissionByNamePermission(namePermission)
                        .orElseThrow(() -> new AppException(ErrorCode.USER_EXISTED))));
        roleMapper.updateRole(role, request);
        role.setPermissions(permissions);
        return roleMapper.toRoleRespon(roleRepository.save(role));
    }

    @Override
    public void deleteRole(Integer idRole) {
        roleRepository.deleteById(idRole);
    }
}
