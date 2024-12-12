package com.example.devTimesheet.service;

import com.example.devTimesheet.dto.request.RoleRequest;
import com.example.devTimesheet.dto.respon.RoleRespon;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface RoleService {

    RoleRespon createRole(RoleRequest request);

    RoleRespon getRole(Integer id);

    List<RoleRespon> findAllRole();

    RoleRespon updateRole(Integer idRole, RoleRequest request);

    void deleteRole(Integer idRole);
}
