package com.example.devTimesheet.service.impl;

import com.example.devTimesheet.dto.request.ProjectRequest;
import com.example.devTimesheet.dto.request.RoleRequest;
import com.example.devTimesheet.dto.respon.*;
import com.example.devTimesheet.entity.*;
import com.example.devTimesheet.exception.AppException;
import com.example.devTimesheet.exception.ErrorCode;
import com.example.devTimesheet.mapper.ProjectMapper;
import com.example.devTimesheet.mapper.UserPositionMapper;
import com.example.devTimesheet.repository.*;
import com.example.devTimesheet.service.ProjectService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProjectServiceImpl implements ProjectService {
    ProjectRepository projectRepository;
    TaskRepository taskRepository;
    ClientRepository clientRepository;
    TeamRepository teamRepository;
    UserPositionRepository userPositionRepository;
    ProjectMapper projectMapper;
    UserPositionMapper userPositionMapper;

    @Override
    public ProjectRespon createProject(ProjectRequest request) {

        Project project = projectMapper.toProject(request);
        List<Task> tasks = new ArrayList<>();
        request.getNameTasks().forEach(
                nameTask -> tasks.add((Task) taskRepository.findTaskByNameTask(nameTask)
                        .orElseThrow(()-> new AppException(ErrorCode.USER_NOT_EXISTED)))
        );
        project.setClient(clientRepository.findClientByNameClient(request.getNameClient())
                .orElseThrow(()-> new AppException(ErrorCode.USER_EXISTED)));
        project.setTeam(teamRepository.findTeamByNameTeam(request.getNameTeam())
                .orElseThrow(()-> new AppException(ErrorCode.USER_EXISTED)));
        project.setTasks(tasks);
        projectRepository.save(project);
        return projectMapper.toProjectRespon(project);
    }

    @Override
    public List<ProjectRespon> findAllProject() {
        List<ProjectRespon> projectRespons = new ArrayList<>();
        List<Project> projects = projectRepository.findAll();
        projects.forEach(
                project -> projectRespons.add(projectMapper.toProjectRespon(project))

        );
        return projectRespons;
    }

    @Override
    public ProjectRespon getProject (Integer id){
        return projectMapper.toProjectRespon(projectRepository.findById(id)
                .orElseThrow(()-> new AppException(ErrorCode.USER_NOT_EXISTED)));
    }

    @Override
    public List<UserPositionRespon> getUsersByProjectId(Integer id) {
        Optional<Project> projectOptional = projectRepository.findById(id);

        if (projectOptional.isPresent()) {
            Team team = projectOptional.get().getTeam();
            List<UserPosition> userPositions = userPositionRepository.findByTeam(team);

            return userPositions.stream()
                    .map(userPosition -> userPositionMapper.toUserPositionRespon(userPosition))
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    @Override
    public ProjectRespon updateProject(Integer idProject, ProjectRequest request) {

        Project project = projectRepository.findById(idProject)
                .orElseThrow(()-> new RuntimeException("Project not found"));
        List<Task> tasks = new ArrayList<>();
        request.getNameTasks().forEach(
                nameTask -> tasks.add((Task) taskRepository.findTaskByNameTask(nameTask)
                        .orElseThrow(()-> new AppException(ErrorCode.USER_NOT_EXISTED)))
        );
        projectMapper.updateProject(project, request);
        project.setClient(clientRepository.findClientByNameClient(request.getNameClient())
                .orElseThrow(()-> new AppException(ErrorCode.USER_EXISTED)));
        project.setTeam(teamRepository.findTeamByNameTeam(request.getNameTeam())
                .orElseThrow(()-> new AppException(ErrorCode.USER_EXISTED)));
        project.setTasks(tasks);
        return projectMapper.toProjectRespon(projectRepository.save(project));
    }

    @Override
    public void deleteProject(Integer idProject){
        projectRepository.deleteById(idProject);
    }
}
