package com.example.devTimesheet.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.example.devTimesheet.dto.request.TaskRequest;
import com.example.devTimesheet.dto.respon.TaskRespon;
import com.example.devTimesheet.entity.Task;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    Task toTask(TaskRequest request);

    void updateTask(@MappingTarget Task task, TaskRequest request);

    @Mapping(target = "projectRespons", source = "projects")
    TaskRespon toTaskRespon(Task task);
}
