package com.example.devTimesheet.dto.respon;

import com.example.devTimesheet.dto.request.ClientRequest;
import com.example.devTimesheet.dto.request.TaskRequest;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProjectRespon {
    int id;
    String nameProject;
    LocalDate date;
    String note;
    ClientRespon clientRespon;
    TeamRespon teamRespon;
    List<TaskRespon> taskRespons;
}