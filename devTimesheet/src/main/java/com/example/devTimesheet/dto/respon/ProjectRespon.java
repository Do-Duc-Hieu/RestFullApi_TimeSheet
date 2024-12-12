package com.example.devTimesheet.dto.respon;

import java.time.LocalDate;
import java.util.List;

import lombok.*;
import lombok.experimental.FieldDefaults;

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
