package com.example.devTimesheet.repository;

import com.example.devTimesheet.entity.User;
import com.example.devTimesheet.projection.UserProjection;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Cacheable("users")
    Optional<User> findUserByUsername(String Username);

    boolean existsByUsername(String Username);

    @Query("SELECT u FROM User u " +
            "WHERE (:username IS NULL OR u.username LIKE %:username%) " +
            "AND (:branch IS NULL OR u.branch.nameBranch = :branch) " +
            "AND (:userType IS NULL OR u.usertype = :userType) " +
            "AND (:role IS NULL OR u.role.nameRole = :role)")
    List<User> searchUser(@Param("username") String username,
                          @Param("branch") String branch,
                          @Param("userType") String userType,
                          @Param("role") String role);

    @Query("SELECT u FROM User u " +
            "WHERE (:username IS NULL OR u.username LIKE %:username%) " +
            "AND (:branch IS NULL OR u.branch.nameBranch = :branch) " +
            "AND (:userType IS NULL OR u.usertype = :userType) " +
            "AND (:role IS NULL OR u.role.nameRole = :role)")
    List<UserProjection> searchUserProjecttion(@Param("username") String username,
                                               @Param("branch") String branch,
                                               @Param("userType") String userType,
                                               @Param("role") String role);

}

