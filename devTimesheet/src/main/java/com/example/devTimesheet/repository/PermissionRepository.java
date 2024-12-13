package com.example.devTimesheet.repository;

import com.example.devTimesheet.entity.Permission;
import com.example.devTimesheet.entity.Role;
import com.example.devTimesheet.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Integer> {
    Optional<Permission> findPermissionByNamePermission(String namePermission);
}
