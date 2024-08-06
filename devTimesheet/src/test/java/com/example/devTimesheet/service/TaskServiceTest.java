package com.example.devTimesheet.service;

import com.example.devTimesheet.dto.request.TaskRequest;
import com.example.devTimesheet.dto.respon.TaskRespon;
import com.example.devTimesheet.entity.Task;
import com.example.devTimesheet.mapper.TaskMapper;
import com.example.devTimesheet.repository.TaskRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
public class TaskServiceTest {
    @Autowired
    private TaskService taskService;

    @MockBean
    private TaskRepository taskRepository;

    @MockBean
    private TaskMapper taskMapper;

    private TaskRequest taskRequest;
    private TaskRespon taskRespon;
    private Task task, task1;

    @BeforeEach
    void initData(){
        taskRequest = TaskRequest.builder()
                .nameTask("dev10")
                .build();
        taskRespon = TaskRespon.builder()
                .id(10)
                .nameTask("dev10")
                .build();
        task1 = Task.builder()
                .nameTask("dev10")
                .build();
        task = Task.builder()
                .id(10)
                .nameTask("dev10")
                .build();
    }

    @Test
    void createTask_success(){
        //given
        when(taskMapper.toTask(any())).thenReturn(task1);
        when(taskRepository.save(any())).thenReturn(task);
        when(taskMapper.toTaskRespon(any())).thenReturn(taskRespon);

        //when
        var respon = taskService.CreateTask(taskRequest);

        //then
        Assertions.assertThat(respon.getId()).isEqualTo(10);
        Assertions.assertThat(respon.getNameTask()).isEqualTo("dev10");
    }
}
