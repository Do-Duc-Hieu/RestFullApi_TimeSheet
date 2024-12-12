package com.example.devTimesheet.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.devTimesheet.dto.request.UserRequest;
import com.example.devTimesheet.dto.request.UserUpdateRequest;
import com.example.devTimesheet.dto.respon.UserRespon;
import com.example.devTimesheet.entity.User;
import com.example.devTimesheet.projection.UserProjection;

public interface UserService {

    UserRespon createUser(UserRequest request, MultipartFile avatar) throws IOException;

    UserRespon getUser(Integer id);

    List<UserRespon> searchUser(String username, String branch, String userType, String role);

    List<UserProjection> searchUserProjection(String username, String branch, String userType, String role);

    List<UserRespon> findAllUser();

    UserRespon updateUser(Integer idUser, UserUpdateRequest updateRequest);

    void resetPassword(Integer idUser, String newPassword);

    void deleteUser(Integer idUser);

    User findById(Integer id);

    UserRespon getMyInfo();
}
