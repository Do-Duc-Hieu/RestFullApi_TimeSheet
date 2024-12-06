package com.example.devTimesheet.service;

import java.util.List;

import com.example.devTimesheet.dto.request.RoleRequest;
import com.example.devTimesheet.dto.respon.RoleRespon;

public interface RoleService {

    RoleRespon createRole(RoleRequest request);

    RoleRespon getRole(Integer id);

    List<RoleRespon> findAllRole();

    RoleRespon updateRole(Integer idRole, RoleRequest request);

    void deleteRole(Integer idRole);
}
