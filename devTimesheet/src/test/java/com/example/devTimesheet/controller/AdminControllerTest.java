package com.example.devTimesheet.controller;

import com.example.devTimesheet.dto.request.BranchRequest;
import com.example.devTimesheet.dto.request.RoleRequest;
import com.example.devTimesheet.dto.request.UserRequest;
import com.example.devTimesheet.dto.respon.BranchRespon;
import com.example.devTimesheet.dto.respon.RoleRespon;
import com.example.devTimesheet.dto.respon.UserRespon;
import com.example.devTimesheet.dto.respon.WorkTimeRespon;
import com.example.devTimesheet.entity.Branch;
import com.example.devTimesheet.entity.Role;
import com.example.devTimesheet.entity.User;
import com.example.devTimesheet.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalTime;
import java.util.Date;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@RequiredArgsConstructor
@TestPropertySource("/test.properties")
public class AdminControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private AdminController adminController;

    @MockBean
    private UserService userService;


    private UserRequest userRequest;
    private UserRespon userRespon;
    private ObjectMapper objectMapper;


    @BeforeEach
    void initData(){
        userRequest = UserRequest.builder()
                .name("hieu1")
                .sex("1")
                .usertype("staff")
                .email("hieu@gmail.com")
                .username("hieu15")
                .password("11111111")
                .address("Dan Phuong")
                .phone("092112343")
                .bank("MB")
                .bankAccount("029301943")
                .taxCode("2143141")
                .salary(1)
                .role(RoleRequest.builder().nameRole("Admin").build())
                .branch(BranchRequest.builder().nameBranch("hn1").build())
                .build();

        userRespon = UserRespon.builder()
                .id(3)
                .name("hieu1")
                .sex("1")
                .usertype("staff")
                .email("hieu@gmail.com")
                .username("hieu15")
                .address("Dan Phuong")
                .phone("092112343")
                .bank("MB")
                .bankAccount("029301943")
                .taxCode("2143141")
                .salary(1)
                .role(RoleRespon.builder().nameRole("Admin").build())
                .branch(BranchRespon.builder().nameBranch("hn1").build())
                .workTime(WorkTimeRespon.builder().id(1)
                        .morningStartTime(LocalTime.of(9, 0, 0))
                        .morningEndTime(LocalTime.  of(12,0,0))
                        .afternoonStartTime(LocalTime.of(13, 0, 0))
                        .afternoonEndTime(LocalTime.of(18, 0, 0))
                        .build())
                .build();
    }

    @Test
    @WithMockUser(username = "hieu2", authorities = {"Admin"})
    void createUser() throws Exception {
        objectMapper = new ObjectMapper();
        String  context = objectMapper.writeValueAsString(userRequest);

        //when
       // when(userService.createUser(ArgumentMatchers.any())).thenReturn(userRespon);

        //then
        mockMvc.perform(post("/admin/addUser")
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(context))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value(0));

    }
}
