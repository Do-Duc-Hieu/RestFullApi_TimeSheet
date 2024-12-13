package com.example.devTimesheet.service;

import com.example.devTimesheet.dto.request.TaskRequest;
import com.example.devTimesheet.dto.respon.TaskRespon;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface TaskService {

    TaskRespon CreateTask(TaskRequest taskRequest);

    TaskRespon getTask(Integer id);

    List<TaskRespon> findAllTask();

    TaskRespon updateTask(Integer idTask, TaskRequest request);

    void deleteTask(Integer idTask);
}
