package com.example.devTimesheet.dto.respon;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WorkTimeRespon {
    int id;
    LocalTime morningStartTime;
    LocalTime morningEndTime;
    LocalTime afternoonStartTime;
    LocalTime afternoonEndTime;
}
