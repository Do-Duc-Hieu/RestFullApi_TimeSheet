package com.example.devTimesheet.service;

import com.example.devTimesheet.dto.request.ProjectRequest;
import com.example.devTimesheet.dto.request.RoleRequest;
import com.example.devTimesheet.dto.respon.ProjectRespon;
import com.example.devTimesheet.dto.respon.RoleRespon;
import com.example.devTimesheet.dto.respon.UserPositionRespon;
import com.example.devTimesheet.dto.respon.UserRespon;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface ProjectService {

    ProjectRespon createProject(ProjectRequest request);

    ProjectRespon getProject(Integer id);

    List<UserPositionRespon> getUsersByProjectId(Integer id);
    List<ProjectRespon> searchProjectByIdUser(Integer idUser);

    List<ProjectRespon> findAllProject();

    ProjectRespon updateProject(Integer idProject, ProjectRequest request);

    void deleteProject(Integer idProject);
}
