package com.example.devTimesheet.service;

import java.util.List;

import com.example.devTimesheet.dto.request.TaskRequest;
import com.example.devTimesheet.dto.respon.TaskRespon;

public interface TaskService {

    TaskRespon CreateTask(TaskRequest taskRequest);

    TaskRespon getTask(Integer id);

    List<TaskRespon> findAllTask();

    TaskRespon updateTask(Integer idTask, TaskRequest request);

    void deleteTask(Integer idTask);
}
