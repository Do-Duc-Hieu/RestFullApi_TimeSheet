package com.example.devTimesheet.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.example.devTimesheet.dto.request.ProjectRequest;
import com.example.devTimesheet.dto.respon.ProjectRespon;
import com.example.devTimesheet.entity.Project;

@Mapper(componentModel = "spring")
public interface ProjectMapper {
    Project toProject(ProjectRequest request);

    void updateProject(@MappingTarget Project project, ProjectRequest request);

    @Mapping(target = "taskRespons", source = "tasks")
    @Mapping(target = "clientRespon", source = "client")
    @Mapping(target = "teamRespon", source = "team")
    ProjectRespon toProjectRespon(Project project);
}
