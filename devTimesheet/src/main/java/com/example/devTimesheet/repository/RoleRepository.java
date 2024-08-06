package com.example.devTimesheet.repository;

import com.example.devTimesheet.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findRoleByNameRole(String nameRole);
}
