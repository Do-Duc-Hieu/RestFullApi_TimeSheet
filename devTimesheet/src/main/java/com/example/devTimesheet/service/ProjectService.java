package com.example.devTimesheet.service;

import java.util.List;

import com.example.devTimesheet.dto.request.ProjectRequest;
import com.example.devTimesheet.dto.respon.ProjectRespon;
import com.example.devTimesheet.dto.respon.UserPositionRespon;

public interface ProjectService {

    ProjectRespon createProject(ProjectRequest request);

    ProjectRespon getProject(Integer id);

    List<UserPositionRespon> getUsersByProjectId(Integer id);

    List<ProjectRespon> findAllProject();

    ProjectRespon updateProject(Integer idProject, ProjectRequest request);

    void deleteProject(Integer idProject);
}
