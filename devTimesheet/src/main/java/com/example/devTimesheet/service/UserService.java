package com.example.devTimesheet.service;

import com.example.devTimesheet.dto.request.UserRequest;
import com.example.devTimesheet.dto.request.UserUpdateRequest;
import com.example.devTimesheet.dto.respon.UserRespon;
import com.example.devTimesheet.projection.UserProjection;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface UserService {

    UserRespon createUser(UserRequest request, MultipartFile avatar) throws IOException;

    UserRespon getUser(Integer id);

    List<UserRespon> searchUser(
            String username, String branch, String userType, String role);

    List<UserProjection> searchUserProjection(
            String username, String branch, String userType, String role);

    List<UserRespon> findAllUser();

    UserRespon updateUser(Integer idUser, UserUpdateRequest updateRequest);

    void resetPassword(Integer idUser, String newPassword);

    void deleteUser(Integer idUser);

    UserRespon getMyInfo();
}
