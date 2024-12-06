package com.example.devTimesheet.dto.respon;

import java.time.LocalDate;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TimeSheetRespon {
    int id;
    LocalDate date;
    String task;
    String note;
    int workingTime;
    String type;
    ProjectRespon projectRespon;
    UserRespon userRespon;
    StatusRespon statusRespon;
}
