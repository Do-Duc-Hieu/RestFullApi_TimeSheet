package com.example.devTimesheet.dto.respon;

import java.util.List;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TaskRespon {
    int id;
    String nameTask;

    List<ProjectRespon> projectRespons;
}
