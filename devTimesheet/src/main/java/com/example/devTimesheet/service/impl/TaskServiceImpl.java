package com.example.devTimesheet.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.devTimesheet.dto.request.TaskRequest;
import com.example.devTimesheet.dto.respon.TaskRespon;
import com.example.devTimesheet.entity.Task;
import com.example.devTimesheet.exception.AppException;
import com.example.devTimesheet.exception.ErrorCode;
import com.example.devTimesheet.mapper.TaskMapper;
import com.example.devTimesheet.repository.TaskRepository;
import com.example.devTimesheet.service.TaskService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TaskServiceImpl implements TaskService {
    TaskRepository taskRepository;
    TaskMapper taskMapper;

    @Override
    public TaskRespon CreateTask(TaskRequest request) {
        Task task = taskMapper.toTask(request);
        taskRepository.save(task);
        return taskMapper.toTaskRespon(task);
    }

    @Override
    public TaskRespon getTask(Integer id) {
        return taskMapper.toTaskRespon(
                taskRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_EXISTED)));
    }

    @Override
    public List<TaskRespon> findAllTask() {
        List<TaskRespon> taskRespons = new ArrayList<>();
        List<Task> tasks = taskRepository.findAll();
        tasks.forEach(task -> taskRespons.add(taskMapper.toTaskRespon(task)));

        return taskRespons;
    }

    @Override
    public TaskRespon updateTask(Integer idTask, TaskRequest request) {
        Task task = taskRepository.findById(idTask).orElseThrow(() -> new RuntimeException("Task not found"));
        taskMapper.updateTask(task, request);
        return taskMapper.toTaskRespon(taskRepository.save(task));
    }

    @Override
    public void deleteTask(Integer idTask) {
        taskRepository.deleteById(idTask);
    }
}
