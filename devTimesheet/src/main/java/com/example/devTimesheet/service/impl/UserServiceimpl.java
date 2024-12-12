package com.example.devTimesheet.service.impl;

import com.example.devTimesheet.dto.request.UserRequest;
import com.example.devTimesheet.dto.request.UserUpdateRequest;
import com.example.devTimesheet.dto.respon.UserRespon;
import com.example.devTimesheet.entity.Branch;
import com.example.devTimesheet.entity.Role;
import com.example.devTimesheet.entity.User;
import com.example.devTimesheet.entity.WorkTime;
import com.example.devTimesheet.exception.AppException;
import com.example.devTimesheet.exception.ErrorCode;
import com.example.devTimesheet.projection.UserProjection;
import com.example.devTimesheet.repository.BranchRepository;
import com.example.devTimesheet.repository.RoleRepository;
import com.example.devTimesheet.repository.UserRepository;
import com.example.devTimesheet.repository.WorkTimeRepository;
import com.example.devTimesheet.service.FileStorageService;
import com.example.devTimesheet.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.devTimesheet.mapper.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserServiceimpl implements UserService {

    UserRepository userRepository;
    RoleRepository roleRepository;
    BranchRepository branchRepository;
    WorkTimeRepository workTimeRepository;
    UserMapper userMapper;
    UserUpdateMapper userUpdateMapper;
    PasswordEncoder passwordEncoder;
    FileStorageService fileStorageService;

    @Override
    public UserRespon createUser(UserRequest request, MultipartFile avatar) throws IOException {
        log.info(request.toString());
        if(userRepository.existsByUsername(request.getUsername())){
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        User user = userMapper.toUse(request);

        // Lưu file ảnh và trả về đối tượng File
        File avatarFile = fileStorageService.saveFile(avatar);

        user.setRole((Role) roleRepository.findRoleByNameRole(request.getRole().getNameRole())
                .orElseThrow(()-> new AppException(ErrorCode.USER_NOT_EXISTED)));
        user.setBranch((Branch) branchRepository.findBranchByNameBranch(request.getBranch().getNameBranch())
                .orElseThrow(()-> new AppException(ErrorCode.USER_NOT_EXISTED)));
        user.setWorkTime((WorkTime) workTimeRepository.findById(1)
                .orElseThrow(()-> new AppException(ErrorCode.USER_NOT_EXISTED)));
        //ma hoa mat khau
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setAvatar(avatarFile);
        userRepository.save(user);

        return userMapper.toUserRespon(user);
    }

    @Override
    public UserRespon getUser (Integer id){
        return userMapper.toUserRespon(userRepository.findById(id)
                .orElseThrow(()-> new AppException(ErrorCode.USER_EXISTED)));
    }

    @Override
    public List<UserRespon> findAllUser(){
        List<UserRespon> userRespons = new ArrayList<>();
        List<User> users = userRepository.findAll();
        users.forEach(
                user -> userRespons.add(userMapper.toUserRespon(user))

        );
        return userRespons;
    }

    @Override
    public List<UserRespon> searchUser(String username, String branch, String userType, String role){
        List<UserRespon> userRespons = new ArrayList<>();
        List<User> users = userRepository.searchUser(username, branch, userType, role);
        users.forEach(
                user -> userRespons.add(userMapper.toUserRespon(user))

        );
        return userRespons;
    }

    @Override
    public List<UserProjection> searchUserProjection(String username, String branch, String userType, String role){
        List<UserRespon> userRespons = new ArrayList<>();
        List<UserProjection> projections = userRepository.searchUserProjecttion(username, branch, userType, role);
        return projections;
    }

    @Override
    public UserRespon updateUser(Integer idUser, UserUpdateRequest request){
        User user = userRepository.findById(idUser)
                .orElseThrow(()-> new AppException(ErrorCode.USER_EXISTED));
        user.setRole((Role) roleRepository.findRoleByNameRole(request.getRole().getNameRole())
                .orElseThrow(()-> new AppException(ErrorCode.USER_NOT_EXISTED)));
        user.setBranch((Branch) branchRepository.findBranchByNameBranch(request.getBranch().getNameBranch())
                .orElseThrow(()-> new AppException(ErrorCode.USER_NOT_EXISTED)));
        userUpdateMapper.updateUser(user, request);
        return userMapper.toUserRespon(userRepository.save(user));
    }

    @Override
    public void resetPassword(Integer idUser, String newPassword) {
        User user = userRepository.findById(idUser)
                .orElseThrow(()-> new AppException(ErrorCode.USER_EXISTED));
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Integer idUser){
        userRepository.deleteById(idUser);
    }

    @Override
    public User findById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(()-> new AppException(ErrorCode.USER_EXISTED));
    }

    @Override
    public UserRespon getMyInfo() {
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();

        User user = userRepository.findUserByUsername(name)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        return userMapper.toUserRespon(user);
    }
}
